package io.github.xesam.android.props;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import io.github.xesam.android.props.cfg.Api;

public class MainActivity extends AppCompatActivity {

    TextView vContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vContent = findViewById(R.id.content);
        findViewById(R.id.inspect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory(), "props_1.txt");
                AProps props = new AProps(file);
                props.setClass(Api.class, "HOST", "192.168.1.2");
                Api api = new Api();
                props.setObject(api, "port", 90);
                vContent.setText(Api.HOST + ":" + api.port + "/" + props.getValue("key1", "default"));
            }
        });
    }
}
