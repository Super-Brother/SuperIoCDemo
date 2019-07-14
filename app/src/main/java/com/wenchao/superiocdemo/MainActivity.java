package com.wenchao.superiocdemo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wenchao.library.annotations.ContentView;
import com.wenchao.library.annotations.InjectView;
import com.wenchao.library.annotations.OnClick;

/**
 * @author OMEN
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv)
    private TextView tv;
    @InjectView(R.id.btn)
    private Button btn;

    private String name;

    @Override
    protected void onResume() {
        super.onResume();
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });
    }

    @OnClick({R.id.tv, R.id.btn})
    public void onViewClick(View view) {
        switch (view.getId()) {

            case R.id.tv:
                Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn:
                Toast.makeText(this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

//    @OnClick(R.id.tv)
//    public void onViewClick(View view) {
//        Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
//    }
}
