package zhaojing.com.myowntest.common;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-03-27.
 */
public class OwnCountDownTimer extends CountDownTimer {

    private static final long maxTime=30*1000L;
    private static final long interval=1000L;
    private TextView view;

    public OwnCountDownTimer(long millisInFuture, long countDownInterval,View view) {
        super(millisInFuture, countDownInterval);
        this.view=(TextView)view;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        System.out.println("还剩下："+millisUntilFinished/1000+" 时间");
        view.setText(Long.toString(millisUntilFinished/1000));
    }

    @Override
    public void onFinish() {
        this.cancel();//取消计时器
        view.setText("已经超时");
    }
}
