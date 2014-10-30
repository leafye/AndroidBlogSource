package com.androidleaf.audiorecord;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 播放网络音频。
 * 
 */
public class Player implements OnBufferingUpdateListener, OnCompletionListener,
		MediaPlayer.OnPreparedListener {

	public MediaPlayer mediaPlayer;
	private SeekBar skbProgress;
	private TextView tvPosition;
	private MyPlayerCallback callback;
	private static final int PERIOD = 500;
	public int currentPosition = 0;
	
	/**
	 * 线程池。
	 */
	private ExecutorService service = Executors.newFixedThreadPool(10);

	public Player(SeekBar skbProgress, TextView tvPosition) {
		this.skbProgress = skbProgress;
		this.tvPosition = tvPosition;
		createMediaPlayer();
	}

	/**
	 * 创建MediaPlayer
	 */
	private void createMediaPlayer() {
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e) {
			
		}
	}

	/**
	 * 通过定时器和Handler来更新进度条.
	 */
	TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer != null && mediaPlayer.isPlaying()
					&& skbProgress.isPressed() == false) {
			}
		}
	};

	Runnable runnable;
	final Handler handler = new Handler();

	/**
	 * 计时 TODO
	 */
	private void timing() {
		// TODO
		runnable = new Runnable() {
			@Override
			public void run() {
				if (mediaPlayer == null)
					return;
				if (mediaPlayer != null && mediaPlayer.isPlaying()
						&& skbProgress.isPressed() == false) {
					setSeekBarProgress();
				}
				handler.postDelayed(this, PERIOD);
			}
		};
		handler.postDelayed(runnable, 0);
	}

	public void setSeekBarProgress() {
		int position = mediaPlayer.getCurrentPosition();
		int duration = mediaPlayer.getDuration();
		currentPosition = position;
		tvPosition.setText(TimeUtils.convertMilliSecondToMinute2(position));
		if (duration >= 0) {
			long pos = skbProgress.getMax() * position / duration;
			skbProgress.setProgress((int) pos);
		}
	}

	public void setMyPlayerCallback(MyPlayerCallback callback) {
		this.callback = callback;
	}

	public void play() {
		timing();
		mediaPlayer.start();
	}

	/**
	 * 新开一个线程，播放音频。
	 * 
	 * @param videoUrl
	 */
	public void playUrl(final String videoUrl) {
		if (mediaPlayer == null) {
			createMediaPlayer();
		}
		timing();
		service.submit(new Runnable() {
			@Override
			public void run() {
				try {
					mediaPlayer.reset();
					mediaPlayer.setDataSource(videoUrl);
					mediaPlayer.prepare();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void pause() {
		handler.removeCallbacks(runnable);
		mediaPlayer.pause();
	}

	public void stop() {
		handler.removeCallbacks(runnable);
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	/**  
	 * 通过onPrepared播放  
	 */
	public void onPrepared(MediaPlayer arg0) {
		arg0.start();
		if (callback != null) {
			callback.onPrepared();
		}
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		if (callback != null) {
			callback.onCompletion();
		}
		mediaPlayer.stop();
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {
	}

}