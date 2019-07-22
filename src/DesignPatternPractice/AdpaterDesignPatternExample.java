package DesignPatternPractice;

interface AdvancedMediaPlayer {
	public void playVLC(String fileName);
	public void playMP4(String fileName);
}

class VLCPlayer implements AdvancedMediaPlayer {
	@Override
	public void playVLC(String fileName) {
		System.out.println("VLC player playing the file :: "+fileName);
	}

	@Override
	public void playMP4(String fileName) {}
}

class MP4Player implements AdvancedMediaPlayer {
	@Override
	public void playVLC(String fileName) {}

	@Override
	public void playMP4(String fileName) {
		System.out.println("MP4 player playing the file :: "+fileName);
	}
}

interface MediaPlayer {
	public void play(String audioType, String fileName);
}

class MediaAdapter implements MediaPlayer {
	AdvancedMediaPlayer advancedMediaPlayer;
	
	public MediaAdapter(String audioType) {
		if(audioType.equalsIgnoreCase("vlc")) {
			advancedMediaPlayer = new VLCPlayer();
		} else if(audioType.equalsIgnoreCase("mp4")) {
			advancedMediaPlayer = new MP4Player();
		}
	}
	
	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreCase("vlc")) {
			advancedMediaPlayer.playVLC(fileName);
		} else if(audioType.equalsIgnoreCase("mp4")) {
			advancedMediaPlayer.playMP4(fileName);
		}	
	}
}

class AudioPlayer implements MediaPlayer {
	MediaAdapter mediaAdapter;
	
	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equalsIgnoreCase("mp3")) {
			System.out.println("MP3 Player playing file :: "+fileName);
		} else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println("Invalid Media. "+audioType+" format not supported.");
		}
	}
}

public class AdpaterDesignPatternExample {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}
