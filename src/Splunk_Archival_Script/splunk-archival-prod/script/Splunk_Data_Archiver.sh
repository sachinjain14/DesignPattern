#!/bin/bash

#PREVIOUS_DIRECTORY_PATH="$(dirname "$(pwd)")"
kinit -kt deuser.keytab deuser@ADOBENET.GLOBAL.ADOBE.COM

PREVIOUS_DIRECTORY_PATH=/user/deuser/deploy/splunk-archival/splunk-archival-prod
CONFIG_BASE_PATH=$PREVIOUS_DIRECTORY_PATH/config
CONFIG_FILE_NAME=$CONFIG_BASE_PATH/Splunk_Data_Archiver.config

source $CONFIG_FILE_NAME

get_current_timestamp() {
	echo $(date +"%d/%m/%Y %H:%M:%S:%N")
}

print_logs() {
	line_to_be_print=$1
	echo "[$(get_current_timestamp)] : $line_to_be_print"
}

check_last_modified_timestamp_file_exists() {
	FILE=$1

	if [ -f "$FILE" ]; then
		print_logs "$LAST_MODIFIED_TIMESTAMP_FILE exists"
		check_file_status=true
	else
		print_logs "Failure: $LAST_MODIFIED_TIMESTAMP_FILE does not exists"
		check_file_status=false
	fi
}

send_mail() {
	MAIL_CONTENT=$1
	
	echo -e "$MAIL_CONTENT"|mail -s "$MAIL_SUBJECT" "$MAIL_TO_PEOPLE"

	if [ $? -eq 0 ]; then
		print_logs "sending mail to concerned people and abort the process" 
	else
		print_logs "Failure: sending mail to concerned people"
  		exit 1
	fi
}

update_last_modified_timestamp_file() {
	current_working_directory=$(pwd)
	current_timestamp=$(date +%s)
	echo $current_timestamp > $LAST_MODIFIED_TIMESTAMP_FILE

	if [ $? -eq 0 ]; then
		print_logs "$LAST_MODIFIED_TIMESTAMP_FILE updated successfully"
	else
		print_logs "Failure: $LAST_MODIFIED_TIMESTAMP_FILE not updated successfully"
		send_mail "Failure: $LAST_MODIFIED_TIMESTAMP_FILE not updated successfully"
  		exit 1
	fi
	
}

retry_file_copy_on_hdfs() {
	filename=$1
	file_copied_successfully=false
	no_of_retries="1 2 3"

	for i in $no_of_retries; do 
		$(hadoop fs -copyFromLocal -f $filename $DESTINATION_HDFS_PATH)
			
		if [ $? -eq 0 ]; then
			print_logs "[attempt-$i] $filename archived on $DESTINATION_HDFS_PATH successfully"
			file_copied_successfully=true
			break
		else
			print_logs "[attempt-$i] Failure: $filename failed to archive on $DESTINATION_HDFS_PATH"
  		fi
	done

	if [[ "$file_copied_successfully" == false ]]; then
		print_logs "$filename failed to archived on $DESTINATION_HDFS_PATH after all attempts. so exiting from the script"
		send_mail "$filename failed to archived on $DESTINATION_HDFS_PATH after all attempts."
		exit 1	
	fi
}

copy_files_to_hdfs() {
	last_timestamp=$(head -1 $LAST_MODIFIED_TIMESTAMP_FILE)
	print_logs "last timestamp from $LAST_MODIFIED_TIMESTAMP_FILE is $last_timestamp"

	list_files=$(ls $SOURCE_DIRECTORY_PATH)

	if [ $? -eq 0 ]; then
		print_logs "listing files at path $SOURCE_DIRECTORY_PATH is sucssfull"
	else
		print_logs "listing files at path $SOURCE_DIRECTORY_PATH got failed. so aborting the process"
		send_mail "listing files at path $SOURCE_DIRECTORY_PATH got failed."
  		exit 1
	fi

	for filename in $list_files
	do
		last_modified=$(stat -c "%Y" $SOURCE_DIRECTORY_PATH/$filename)

		if [ $(($last_modified-$last_timestamp)) -gt 0 ]; then
			print_logs "$SOURCE_DIRECTORY_PATH/$filename added/updated from last run, so archiving on hdfs path $DESTINATION_HDFS_PATH" 
			$(hadoop fs -copyFromLocal -f $SOURCE_DIRECTORY_PATH/$filename $DESTINATION_HDFS_PATH)

			if [ $? -eq 0 ]; then
				print_logs "$SOURCE_DIRECTORY_PATH/$filename archived on $DESTINATION_HDFS_PATH successfully"
			else
				print_logs "Failure: $SOURCE_DIRECTORY_PATH/$filename failed to archive on $DESTINATION_HDFS_PATH successfully"
				retry_file_copy_on_hdfs $SOURCE_DIRECTORY_PATH/$filename
			fi
		else
			print_logs "$SOURCE_DIRECTORY_PATH/$filename is old file, so not archiving on $DESTINATION_HDFS_PATH" 
		fi
		
	done

	##updating the last modified timestamp file
	update_last_modified_timestamp_file
}

check_all_files_timestamps() {
	current_working_directory=$(pwd)

	print_logs "checking $LAST_MODIFIED_TIMESTAMP_FILE exists or not in $current_working_directory"
	check_last_modified_timestamp_file_exists $LAST_MODIFIED_TIMESTAMP_FILE

	if [[ "$check_file_status" == false ]]; then
		print_logs "$LAST_MODIFIED_TIMESTAMP_FILE file does not exist. so sending mail process started"
		send_mail "$LAST_MODIFIED_TIMESTAMP_FILE file does not exists."
	else
		print_logs "$LAST_MODIFIED_TIMESTAMP_FILE file exist. so files archiving on HDFS initiated"
    		copy_files_to_hdfs
	fi
}

##main function
##checking all files timestamp and copy the required files on hdfs path
check_all_files_timestamps
