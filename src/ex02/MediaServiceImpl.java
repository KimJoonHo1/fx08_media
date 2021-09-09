package ex02;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaServiceImpl implements MediaService{
	MediaPlayer mediaPlayer;
	MediaView mediaView;
	Button btnPlay, btnPause, btnStop;
	boolean endOfMedia;
	@Override
	public void myStart() {
		// TODO Auto-generated method stub
		mediaPlayer.play();
	}
	
	@Override
	public void myStop() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
	}
	
	@Override
	public void myPause() {
		// TODO Auto-generated method stub
		mediaPlayer.pause();
	}
	
	public void setControl(Parent root) {
		mediaView = (MediaView)root.lookup("#fxMediaView");
		btnPlay = (Button)root.lookup("#btnPlay");
		btnPause = (Button)root.lookup("#btnPause");
		btnStop = (Button)root.lookup("#btnStop");
		
	}
	
	@Override
	public void setMedia(Parent root, String mediaName) {
		setControl(root);
		Media media = new Media(getClass().getResource(mediaName).toString());
		mediaPlayer = new MediaPlayer(media);
		
		mediaView.setMediaPlayer(mediaPlayer);
		
		mediaPlayer.setOnReady(new Runnable() {

			public void run() {
				btnPlay.setDisable(false);
				btnStop.setDisable(true);
				btnPause.setDisable(true);
				
			}
		});
		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true);
			btnStop.setDisable(false);
			btnPause.setDisable(false);
		});
		
		mediaPlayer.setOnStopped(()->{
			btnPlay.setDisable(false);
			btnStop.setDisable(true);
			btnStop.setDisable(true);
		});
		
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnStop.setDisable(false);
			btnPause.setDisable(true);
		});
		
		mediaPlayer.setOnEndOfMedia(()->{
			btnPlay.setDisable(false);
			btnStop.setDisable(true);
			btnPause.setDisable(true);
			myStop();
		});
		
	}
}
