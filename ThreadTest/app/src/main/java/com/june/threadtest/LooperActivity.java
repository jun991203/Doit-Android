package com.june.threadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LooperActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    Handler handler = new Handler();

    ProcessThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);

        editText = findViewById(R.id.looper_edittext);
        textView = findViewById(R.id.looper_textview);

        Button button = findViewById(R.id.looper_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                Message message = Message.obtain();
                message.obj = input;

                // 새로 만든 스레드 안에 있는 핸들러로 메시지 전송
                thread.processHandler.sendMessage(message);
            }
        });

        thread = new ProcessThread();
    }

    class ProcessThread extends Thread {
        ProcessHandler processHandler = new ProcessHandler();

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
                // 새로 만든 스레드 안에서 전달받은 메시지 처리
                super.handleMessage(msg);
                final String output = msg.obj + " from thread.";
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(output);
                    }
                });
            }
        }
    }


}