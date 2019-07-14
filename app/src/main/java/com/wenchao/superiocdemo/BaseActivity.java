package com.wenchao.superiocdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wenchao.library.InjectManager;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 13:58
 * description：
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //帮助子类进行：布局、控件、点击事件的注入
        InjectManager.inject(this);
    }
}
