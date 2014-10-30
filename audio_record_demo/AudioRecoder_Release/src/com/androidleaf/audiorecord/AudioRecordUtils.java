package com.androidleaf.audiorecord;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.todoroo.aacenc.AACEncoder;
import com.todoroo.aacenc.AACToM4A;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.Log;

public class AudioRecordUtils {

	private final int audioSource = MediaRecorder.AudioSource.MIC;
	// 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050,16000,11025
    private final int sampleRateInHz = 16000;
    // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
    private final int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
    // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
    private final int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    
    private int inBufSize = 0;
	
	private AudioRecord audioRecord;
	
	private AACEncoder encoder = null;
	
	private ProgressDialog mProgressDialog = null;
	
	private boolean isRecord = false;
	
	private Context mContext;
	/**
	 * 录制的音频文件名称
	 */
	private String mAudioRecordFileName;
	
	private static final int RECORDED_INIT_DELETE = 0;
	
	private static final int RECORDED_COMPLETED_DELETE = 1;
	
	
	public AudioRecordUtils(Context context,String audioRecordFileName){
		mContext = context;
		mAudioRecordFileName = audioRecordFileName;
		initAudioRecord();
	}
    
	/**
	 * 初始化对象
	 */
    private void initAudioRecord(){
		
		inBufSize = AudioRecord.getMinBufferSize(
				sampleRateInHz, 
				channelConfig, 
				audioFormat);
		
		audioRecord  = new AudioRecord(
				audioSource, 
				sampleRateInHz, 
				channelConfig, 
				audioFormat, 
				inBufSize);
		
		encoder = new AACEncoder();
		deleteAllFiles(RECORDED_INIT_DELETE);
		
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setTitle("提示");
		mProgressDialog.setMessage("正在保存录音，请耐心等候......");
		
	}
    
    /**
	 * 开始录音
	 */
	public void startRecord(){
		new AudioRecordTask().execute();
	}
	
	/**
	 * 暂停录音
	 */
	public void pauseRecord(){
		isRecord = false;
	}
	
	/**
	 * 停止录音
	 */
	public void stopRecord(){
		new AudioEncoderTask().execute();
	}
	
	/**
	 * 重新录制
	 */
	public void reRecord(){
		//重新录制时，删除录音文件夹中的全部文件
		deleteAllFiles(RECORDED_INIT_DELETE);
	} 
	
	private void encodeAudio(){
		try {
			//读取录制的pcm音频文件
	    	DataInputStream mDataInputStream = new DataInputStream(new FileInputStream(
	    			FileUtils.getPcmFilePath(mAudioRecordFileName)));
				byte[] b = new byte[(int) new File(FileUtils.
						getPcmFilePath(mAudioRecordFileName)).length()];
				mDataInputStream.read(b);
				//初始化编码配置
				encoder.init(32000, 2, sampleRateInHz, 16, FileUtils.
						getAAcFilePath(mAudioRecordFileName));
				//对二进制代码进行编码
		        encoder.encode(b);
		        //编码完成
		        encoder.uninit();
		        //关闭流
		        mDataInputStream.close();
		        try {
		        	//将aac文件转码成m4a文件
		            new AACToM4A().convert(mContext, FileUtils.getAAcFilePath(mAudioRecordFileName), 
		            		FileUtils.getM4aFilePath(mAudioRecordFileName));
		        } catch (IOException e) {
		            Log.e("ERROR", "error converting", e);
		        }
		        deleteAllFiles(RECORDED_COMPLETED_DELETE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	class AudioRecordTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(audioRecord == null){
				initAudioRecord();
			}
			RandomAccessFile mRandomAccessFile = null;
			try {
				mRandomAccessFile = new RandomAccessFile(new File(
						FileUtils.getPcmFilePath(mAudioRecordFileName)), "rw");
				byte[] b = new byte[inBufSize/4];
				//开始录制音频
				audioRecord.startRecording();
				//判断是否正在录制
				isRecord = true;
				while(isRecord){
					audioRecord.read(b, 0, b.length);
					//向文件中追加内容
					mRandomAccessFile.seek(mRandomAccessFile.length());
					mRandomAccessFile.write(b, 0, b.length);
				}
				//停止录制
				audioRecord.stop();
				mRandomAccessFile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	class AudioEncoderTask extends AsyncTask<Void, Void, Long>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(mProgressDialog != null && !mProgressDialog.isShowing()){
				mProgressDialog.show();
			}
		}

		@Override
		protected Long doInBackground(Void... params) {
			// TODO Auto-generated method stub
			encodeAudio();
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(mProgressDialog.isShowing()){
				mProgressDialog.cancel();
				mProgressDialog.dismiss();
			}
		}
	}
	
	/**
	 * 清空音频录制文件夹中的所有文件
	 * @param isRecorded
	 */
	public void deleteAllFiles(int isRecorded){
		 File[] files = new File(FileUtils.getAudioRecordFilePath()).listFiles();
		switch (isRecorded) {
		case RECORDED_INIT_DELETE:
			for(File file: files){
	        	file.delete();
	        }
			break;
		case RECORDED_COMPLETED_DELETE:
	        for(File file: files){
	        	if(!file.getName().equals(mAudioRecordFileName + Constants.M4A_SUFFIX)){
	        		file.delete();
	        	}
	        }
			break;
		default:
			break;
		}
	}
}
