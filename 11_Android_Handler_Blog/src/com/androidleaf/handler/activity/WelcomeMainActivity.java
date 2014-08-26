package com.androidleaf.handler.activity;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          // TODO Auto-generated method stub
          super.onCreate(savedInstanceState);
          TextView mTextView = new TextView(this);
          mTextView.setText("Ê×Ò³");
          mTextView.setTextSize(20);
          mTextView.setTextColor(Color.RED);
         setContentView(mTextView);
    }
}
