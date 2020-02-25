package zsc.androidstudy.counttime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView time;//获取到的时间
    private EditText inputtime;//用户输入的时间
    private Button start;//开始按钮
    private Button stop;//暂停按钮
    private Button getTime;//点击获取时间
    private int i= 0;//获取到的时间
    private Timer timer;//timer对像
    private TimerTask task;//配合timer对象使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();//初始化控件
    }

    private void initView(){
        time = findViewById(R.id.time);
        inputtime = findViewById(R.id.inputtime);
        getTime = findViewById(R.id.gettime);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        getTime.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gettime:
                time.setText(inputtime.getText().toString());
                i= Integer.parseInt(inputtime.getText().toString());
                break;

            case R.id.start:
                start.setClickable(false);
                startTime();
                break;

            case R.id.stop:
                stopTime();
                start.setClickable(true);
                break;
        }

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            time.setText(msg.arg1+"");
            startTime();
        }
    };

    public void startTime(){
        timer = new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = handler.obtainMessage();
                message.arg1= i;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task,1000);
    }

    public void stopTime(){
        timer.cancel();
    }

}
