package zhaojing.com.myowntest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

import zhaojing.com.myowntest.R;
/**
 * AUTHOR：Created by Administrator on 2018-07-24 18:34
 * EMAIL:  2910763715@qq.com
 *
 * 《OpenSL ES Basics》

 《Native Audio: OpenSL ES for Android》

 《Android Audio Architecture》



 《OpenSL ES for Android》

 《Tutorials for OpenSL ES for Android》
 *
 *
 */

public class NativeAudioActivity extends Activity {

    private AssetManager am;
    private String[] files;
    private InputStream is;

    //audio
    private boolean hasLowLatencyFeature;
    private boolean hasProFeature;
    private AudioManager audioM;

    //////////////////////////////////////

    private boolean isPlayingAsset = false;

    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        // get asset
        getAssetContent();
//        // all asset files
//        getListAssetFiles();
//
//        // open file
//        getOpenAssetFile();
//
//        //check audio perfermance
//        check_get_audio_latency();
//        // to the most high xingneng , create the most good sample
//        set_most_sample();
//        // set the best queue for audio data
//        add_audio_to_queue();
        //initialize native audio system
        createEngine();
//        createBufferQueueAudioPlayer();

        init();
    }

    /**
     *  add the best audio data to queue
     *  HAL layer could contain the best frame ,to reduce doudong,so should zhengshubei *frame
     * */
    private void add_audio_to_queue() {
        if(audioM!=null){
            String framePerBuffer = audioM.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER);
            int framePerBufferInt=Integer.parseInt(framePerBuffer);
            if(framePerBufferInt==0)
                framePerBufferInt=256;
        }
    }

    /**
     *  create audioplayer the most sample
     *  samplesPerSec 指的是每个通道的采样率
     */
    private void set_most_sample() {
        audioM =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        String sampleRateStr = audioM.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE);
        int sampleRate = Integer.parseInt(sampleRateStr);
        if(sampleRate == 0) sampleRate=44100;//use a default value if property not found

    }

    private void check_get_audio_latency() {

    //    android.hardware.audio.low_latency 指示 45 毫秒或更短的持续输出延迟时间。
    //    android.hardware.audio.pro 指示 20 毫秒或更短的持续往返延迟时间。
        hasLowLatencyFeature=getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_AUDIO_LOW_LATENCY);

        hasProFeature = getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_AUDIO_PRO);
    }

    private void init() {
        mButton=(Button) findViewById(R.id.animation_translate);
        mButton.setText("kaishiceshi");
        mButton.setOnClickListener(new View.OnClickListener(){

            boolean created = false;


            @Override
            public void onClick(View v) {

                if(!created) {
                    created=createAssetAudioPlayer(am, "background.mp3");

                }else if(created){
                    isPlayingAsset = !isPlayingAsset;
                    setPlayingAssetAudioPlayer(isPlayingAsset);
                }
            }
        });
    }

    private void getOpenAssetFile() {
        try {
            is = am.open("background.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListAssetFiles() {
        try {
            files = am.list("");

            if(files!=null||files.length>0){

                for(int i=0;i<files.length;i++){
                    System.out.println("file name = "+files[i]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAssetContent() {
        am = this.getAssets();
    }


    @Override
    protected void onPause() {

        isPlayingAsset = false;
        setPlayingAssetAudioPlayer(isPlayingAsset);

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        shutdown();
        super.onDestroy();
    }
    ////////////////////////////////////////////////////////////////
    /**no need queue, only MODE_STATIC mode **/
    // create engine
    public static native void createEngine();
    //public static native void createBufferQueueAudioPlayer();
    // play asset object,filename
    public static native boolean createAssetAudioPlayer(AssetManager assetManager, String filename);
    // set player state  true == PLAYING, false == PAUSED
    public static native void setPlayingAssetAudioPlayer(boolean isPlaying);
    ///////////////////////////////////////////////////////////////////////
    //release resource
    public static native void shutdown();

    static{
        System.loadLibrary("NativeAudioLib");
    }



}
