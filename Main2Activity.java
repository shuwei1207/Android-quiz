package com.example.a0413418quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private Intent intent;
    private Bundle bundle;
    TextView tva ,tva1 ,tva2 ,tva3 ,tva4 ,tva5 ;
    int [] ans = {0,0,0,0,0};
    int alk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tva = (TextView)findViewById(R.id.textView2);
        tva1 = (TextView)findViewById(R.id.anstext1);
        tva2 = (TextView)findViewById(R.id.anstext2);
        tva3 = (TextView)findViewById(R.id.anstext3);
        tva4 = (TextView)findViewById(R.id.anstext4);
        tva5 = (TextView)findViewById(R.id.anstext5);
        intent = this.getIntent();
        bundle = intent.getExtras();
        ans = bundle.getIntArray("output");
        alk = bundle.getInt("outputa");
        tva.setText("目前測試結果:  " );
        tva1.setText("張飛: " +ans[0]);
        tva2.setText("呂布: " +ans[1]);
        tva3.setText("關羽: " +ans[2]);
        tva4.setText("諸葛亮: " +ans[3]);
        tva5.setText("曹操: " +ans[4]);
        Button bt = (Button)findViewById(R.id.button3);
        Button bt4 = (Button)findViewById(R.id.button4);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bundle.putIntArray("outputb",ans );
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                Main2Activity.this.finish();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ans[alk] --;
                bundle.putIntArray("outputb",ans );
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                Main2Activity.this.finish();
            }
        });
    }
}