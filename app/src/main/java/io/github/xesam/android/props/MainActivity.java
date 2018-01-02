package io.github.xesam.android.props;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
                AProps props = new AProps();
                Log.e("r", Environment.getExternalStorageDirectory().toString());
//                props.load("");
                vContent.setText(props.getString("key1"));
            }
        });
    }
}
