package zhaojing.com.myowntest.common;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018-04-08.
 */
public class IMMediaRecoder {

    private static volatile IMMediaRecoder recorder;
    private static MediaRecorder mRecoder;

    private IMMediaRecoder(){}
    public static IMMediaRecoder getInstance(){
        if(recorder==null){
            synchronized (IMMediaRecoder.class){
                if(recorder==null){
                    recorder=new IMMediaRecoder();
                }
            }
        }return recorder;
    }
    private boolean state;
    public void init(){
        if(mRecoder!=null){
            mRecoder.release();
            mRecoder=null;
        }
        mRecoder=new MediaRecorder();
        mRecoder.reset();
        mRecoder.setAudioSource(MediaRecorder.AudioSource.MIC);//麦克风为音频源
        mRecoder.setMaxDuration(10 * 1000);
        mRecoder.setAudioChannels(1);
        mRecoder.setAudioSamplingRate(8000);//amr_nb匹配8khz
         mRecoder.setAudioEncodingBitRate(64);//按照默认的位数
        mRecoder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mRecoder.setOutputFile(new File(Common.getFilePath()).getAbsolutePath());//路径一定要有分隔符
//        文件创建有问题
        try {
            mRecoder.prepare();


        }catch (IOException o){
            o.printStackTrace();
        }
        mRecoder.start();
        state=true;
    }//init

    public void getRecordeState(){

        mRecoder.getMaxAmplitude();
    }

    public void discardRecording(){
        if(mRecoder!=null){
            mRecoder.stop();
            mRecoder.release();
            mRecoder=null;
            state=false;
        }
    }

    public void stopRecording(){
        if(mRecoder!=null){
            mRecoder.stop();
            mRecoder.release();
            mRecoder=null;
            state=false;
        }
    }


}
