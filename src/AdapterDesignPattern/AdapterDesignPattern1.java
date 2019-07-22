//Source :: https://www.tutorialspoint.com/design_pattern/adapter_pattern.htm

package AdapterDesignPattern;

interface AdvanceMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}

class VlcPlayer implements AdvanceMediaPlayer {
    public void playVlc(String fileName){
        System.out.println("Playing VLC fileName : "+fileName);
    }
    
    public void playMp4(String fileName) {}
}

class Mp4Player implements AdvanceMediaPlayer {
    public void playVlc(String fileName){}
    
    public void playMp4(String fileName) {
        System.out.println("Playing Mp4 fileName : "+fileName);
    }
}

interface MediaPlayer {
    public void play(String audioType, String fileName);
}

class AudioPlayer implements MediaPlayer {
   MediaAdapter mediaAdapter; 

   @Override
   public void play(String audioType, String fileName) {		

      //inbuilt support to play mp3 music files
      if(audioType.equalsIgnoreCase("mp3")){
         System.out.println("Playing mp3 file. Name: " + fileName);			
      } 
      
      //mediaAdapter is providing support to play other file formats
      else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")){
         mediaAdapter = new MediaAdapter(audioType);
         mediaAdapter.play(audioType, fileName);
      }
      
      else{
         System.out.println("Invalid media. " + audioType + " format not supported");
      }
   } 
}

class MediaAdapter implements MediaPlayer {

   AdvanceMediaPlayer advancedMusicPlayer;

   public MediaAdapter(String audioType){
   
      if(audioType.equalsIgnoreCase("vlc") ){
         advancedMusicPlayer = new VlcPlayer();			
         
      }else if (audioType.equalsIgnoreCase("mp4")){
         advancedMusicPlayer = new Mp4Player();
      }	
   }

   @Override
   public void play(String audioType, String fileName) {
   
      if(audioType.equalsIgnoreCase("vlc")){
         advancedMusicPlayer.playVlc(fileName);
      }
      else if(audioType.equalsIgnoreCase("mp4")){
         advancedMusicPlayer.playMp4(fileName);
      }
   }
}

class AdapterDesignPattern1 {
	public static void main (String[] args) throws Exception {
      AudioPlayer audioPlayer = new AudioPlayer();

      audioPlayer.play("mp3", "beyond the horizon.mp3");
      audioPlayer.play("mp4", "alone.mp4");
      audioPlayer.play("vlc", "far far away.vlc");
      audioPlayer.play("avi", "mind me.avi");
	}
}