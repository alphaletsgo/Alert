package cn.isif.alert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.isif.library.IfAlert;

public class MainActivity extends AppCompatActivity {
private IfAlert alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alert = new IfAlert.Builder(this)
                .setContentView(R.layout.alert_warn)
                .setHasAnimation(true)
                .create();
    }

    @Override
    protected void onResume() {
        alert.show();
        super.onResume();
    }
}
