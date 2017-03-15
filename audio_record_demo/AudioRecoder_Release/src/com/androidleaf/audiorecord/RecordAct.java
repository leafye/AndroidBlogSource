package com.androidleaf.audiorecord;

import com.androidleaf.audiorecord.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

public class RecordAct extends Activity implements OnClickListener{

	/**
	 * Status：录音初始状态
	 */
	private static final int STATUS_PREPARE = 0;
    
	/**
	 * Status：正在录音中
	 */
    private static final int STATUS_RECORDING = 1;
    
    /**
     * Status：暂停录音
     */
    private static final int STATUS_PAUSE = 2;
    
    /**
     * Status：播放初始状态
     */
    private static final int STATUS_PLAY_PREPARE = 3;
    
    /**
     * Status：播放中
     */
    private static final int STATUS_PLAY_PLAYING = 4;
    /**
     * Status：播放暂停
     */
    private static final int STATUS_PLAY_PAUSE = 5;
    
    private int status = STATUS_PREPARE;
    
    /**
     * 录音时间
     */
    private TextView tvRecordTime; 
    
    /**
     * 录音按钮
     */
    private ImageView btnRecord;// 录音按钮
    
    private PopupWindow popAddWindow;
    
    /**
     * 试听界面
     */
    private LinearLayout layoutListen;
    
    /**
     * 录音长度
     */
    private TextView tvLength;
    
    private TextView recordContinue;
    
    /**
     * 重置按钮
     */
    private View resetRecord;
    
    /**
     * 结束录音
     */
    private View recordOver;
    
    private ImageView audioRecordNextImage;
    
    private TextView audioRecordNextText;
    
    /**
     * 音频播放进度
     */
    private TextView tvPosition;
    
    long startTime = 0;
    
    /**
     * 最大录音长度
     */
    private static final int MAX_LENGTH = 300 * 1000;
	
    private Handler handler = new Handler();
    
    private Runnable runnable;
    
    /**
     * 音频录音的总长度
     */
    private static int voiceLength;
    
    /**
     * 音频录音帮助类
     */
    private AudioRecordUtils mRecordUtils;
    
    /**
     * 播放进度条
     */
    private SeekBar seekBar;
    /**
     * 音频播放类
     */
    private Player player;
    /**
     * 录音文件名
     */
    private String audioRecordFileName;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_add_record);
		initView();
	}
	
	public void initView(){
		//音频录音的文件名称
		audioRecordFileName = TimeUtils.getTimestamp();
		//初始化音频录音对象
		mRecordUtils = new AudioRecordUtils(this,audioRecordFileName);
		View view = LayoutInflater.from(this).inflate(R.layout.pop_add_record, null);
		tvRecordTime = (TextView)findViewById(R.id.tv_time);
		btnRecord = (ImageView)findViewById(R.id.iv_btn_record);
		btnRecord.setOnClickListener(this);
		recordContinue = (TextView)findViewById(R.id.record_continue_txt);
		resetRecord = findViewById(R.id.btn_record_reset);
		recordOver = findViewById(R.id.btn_record_complete);
		resetRecord.setOnClickListener(this);
		recordOver.setOnClickListener(this);
		audioRecordNextImage = (ImageView)findViewById(R.id.recrod_complete_img);
		audioRecordNextText = (TextView)findViewById(R.id.record_complete_txt);
		
		layoutListen = (LinearLayout)findViewById(R.id.layout_listen);
		tvLength = (TextView)findViewById(R.id.tv_length);
		tvPosition = (TextView)findViewById(R.id.tv_position);
		seekBar = (SeekBar)findViewById(R.id.seekbar_play);
		seekBar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
		seekBar.setEnabled(false);
		player = new Player(seekBar, tvPosition);
		player.setMyPlayerCallback(new MyPlayerCallback() {

			@Override
			public void onPrepared() {
				seekBar.setEnabled(true);
			}
			@Override
			public void onCompletion() {
				status = STATUS_PLAY_PREPARE;
				seekBar.setEnabled(false);
				seekBar.setProgress(0);
				tvPosition.setText("00:00");
				recordContinue.setBackgroundResource(R.drawable.record_audio_play);
			}
		});
		
		popAddWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		popAddWindow.setFocusable(true);
		popAddWindow.setAnimationStyle(R.style.pop_anim);
		popAddWindow.setBackgroundDrawable(new BitmapDrawable());
	}
	
	public void handleRecord(){
		switch(status){
		case STATUS_PREPARE:
			mRecordUtils.startRecord();
			btnRecord.setBackgroundResource(R.drawable.record_round_red_bg);
			status = STATUS_RECORDING;
			voiceLength = 0;
			timing();
			break;
		case STATUS_RECORDING:
			pauseAudioRecord();
			resetRecord.setVisibility(View.VISIBLE);
			recordOver.setVisibility(View.VISIBLE);
			btnRecord.setBackgroundResource(R.drawable.record_round_blue_bg);
			recordContinue.setVisibility(View.VISIBLE);
			status = STATUS_PAUSE;
			break;
		case STATUS_PAUSE:
			mRecordUtils.startRecord();
			resetRecord.setVisibility(View.INVISIBLE);
			recordOver.setVisibility(View.INVISIBLE);
			btnRecord.setBackgroundResource(R.drawable.record_round_red_bg);
			recordContinue.setVisibility(View.INVISIBLE);
			status = STATUS_RECORDING;
			timing();
			break;
		case STATUS_PLAY_PREPARE:
			player.playUrl(FileUtils.getM4aFilePath(audioRecordFileName));
			recordContinue.setBackgroundResource(R.drawable.record_audio_play_pause);
			status = STATUS_PLAY_PLAYING;
			break;
		case STATUS_PLAY_PLAYING:
			player.pause();
			recordContinue.setBackgroundResource(R.drawable.record_audio_play);
			status = STATUS_PLAY_PAUSE;
			break;
		case STATUS_PLAY_PAUSE:
			player.play();
			recordContinue.setBackgroundResource(R.drawable.record_audio_play_pause);
			status = STATUS_PLAY_PLAYING;
			break;
		}
	}
	/**
	 * 暂停录音
	 */
	public void pauseAudioRecord(){
		mRecordUtils.pauseRecord();
		if (handler != null && runnable != null) {
			handler.removeCallbacks(runnable);
			runnable = null;
		}
	}
	
	/**
	 * 停止录音
	 */
	public void stopAudioRecord(){
		pauseAudioRecord();
		mRecordUtils.stopRecord();
		status = STATUS_PLAY_PREPARE;
		showListen();
	}
	
	/**
	 * 重新录音参数初始化
	 */
	@SuppressLint("NewApi")
	public void resetAudioRecord(){
		//停止播放音频
		player.stop();
		pauseAudioRecord();
		mRecordUtils.reRecord();
		status = STATUS_PREPARE;
		voiceLength = 0;
		tvRecordTime.setTextColor(Color.WHITE);
		tvRecordTime.setText(TimeUtils.convertMilliSecondToMinute2(voiceLength));
		recordContinue.setText(R.string.record_continue);
		recordContinue.setBackground(null);
		recordContinue.setVisibility(View.GONE);
		layoutListen.setVisibility(View.GONE);
		tvRecordTime.setVisibility(View.VISIBLE);
		audioRecordNextImage.setImageResource(R.drawable.btn_record_icon_complete);
		audioRecordNextText.setText(R.string.record_over);
		btnRecord.setBackgroundResource(R.drawable.record_round_blue_bg);
		resetRecord.setVisibility(View.INVISIBLE);
		recordOver.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * 计时功能
	 */
	private void timing() {
		runnable = new Runnable() {
			@Override
			public void run() {
				voiceLength += 100;
				if (voiceLength >= (MAX_LENGTH - 10 * 1000)) {
					tvRecordTime.setTextColor(getResources().getColor(
							R.color.red_n));
				} else {
					tvRecordTime.setTextColor(Color.WHITE);
				}
				if (voiceLength > MAX_LENGTH) {
					stopAudioRecord();
					
				} else {
					tvRecordTime.setText(TimeUtils.convertMilliSecondToMinute2(voiceLength));
					handler.postDelayed(this, 100);
				}
			}
		};
		handler.postDelayed(runnable, 100);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_btn_record:
			handleRecord();
			break;
		case R.id.btn_record_reset:
			resetAudioRecord();
			break;
		case R.id.btn_record_complete:
			stopAudioRecord();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 显示播放界面
	 */
	private void showListen() {
		layoutListen.setVisibility(View.VISIBLE);
		tvLength.setText(TimeUtils.convertMilliSecondToMinute2(voiceLength));
		tvRecordTime.setVisibility(View.GONE);
		resetRecord.setVisibility(View.VISIBLE);
		recordOver.setVisibility(View.INVISIBLE);
		recordContinue.setVisibility(View.VISIBLE);
		seekBar.setProgress(0);
		tvPosition.setText("00:00");
		btnRecord.setBackgroundResource(R.drawable.record_round_blue_bg);
		recordContinue.setText(null);
		recordContinue.setBackgroundResource(R.drawable.record_audio_play);
		
	}
	
	/**
	 * 
	 * SeekBar进度条改变事件监听类
	 */
	class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
		int progress;

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (null != player && player.mediaPlayer != null) {
				this.progress = progress * player.mediaPlayer.getDuration()
						/ seekBar.getMax();
				tvPosition.setText(TimeUtils
						.convertMilliSecondToMinute2(player.currentPosition));
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if (player.mediaPlayer != null) {
				player.mediaPlayer.seekTo(progress);
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		player.stop();
	}
	
}
