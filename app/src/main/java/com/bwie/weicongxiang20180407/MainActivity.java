package com.bwie.weicongxiang20180407;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.weicongxiang20180407.activity.GouWuCheActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity
                .this, GouWuCheActivity.class);
        startActivity(intent);
        finish();
    }


}
