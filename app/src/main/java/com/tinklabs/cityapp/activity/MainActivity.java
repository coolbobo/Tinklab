package com.tinklabs.cityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tinklabs.cityapp.R;
import com.tinklabs.cityapp.service.ContentServ;

public class MainActivity extends AppCompatActivity {

    public ContentServ conterServ = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        conterServ = ContentServ.getInstance();

        //提前加载数据，显示时能快速显示出来。

        setContentView(R.layout.activity_main);
    }
}
