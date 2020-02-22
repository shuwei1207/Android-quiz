package com.example.a0413418quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int [] an = {0,0,0,0,0};
    RadioButton rb1,rb2,rb3;
    Button b1,b2,b3;
    RadioGroup rg;
    TextView tv;
    int [] select = {-1,-1,-1,-1};
    int b3mode = 0;
    int page = 0;
    int ansnum;
    String [] question = {"1,以下三位女藝人，你最欣賞哪一位的外型？","2,假設你結婚後第一次回娘家，你覺得你老丈人或妳爸會開什麼酒來跟男方喝？","3,以下三種球類運動中，你最常參加的是：","4,你最好的朋友血型為："};
    String [][] choice = {{"林志玲","蔡依林","楊丞琳"},{"高梁酒","葡萄酒","啤酒"},{"籃球","羽球","桌球"},{"O型","B型或AB型","A型"}};
    String [] answer = {"三國武將中，你最像張飛","三國武將中，你最像呂布","三國武將中，你最像關羽","三國武將中，你最像諸葛亮","三國武將中，你最像曹操"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        tv = (TextView)findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        rb1 = (RadioButton)findViewById(R.id.radioButton1);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        b1.setOnClickListener(buttonclick);
        b2.setOnClickListener(buttonclick);
        b3.setOnClickListener(buttonclick);
        rb1.setOnClickListener(radiobuttonclick);
        rb2.setOnClickListener(radiobuttonclick);
        rb3.setOnClickListener(radiobuttonclick);
        b3.setEnabled(false);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3 && resultCode==RESULT_OK)
        {
            an = data.getExtras().getIntArray("outputb");
            b1.setVisibility(Button.VISIBLE);
            b2.setVisibility(Button.VISIBLE);
            rb1.setVisibility(Button.VISIBLE);
            rb2.setVisibility(Button.VISIBLE);
            rb3.setVisibility(Button.VISIBLE);
            b3.setText("Exame");
            b3.setEnabled(false);
            b3mode = 0;
            select[0] = -1;
            select[1] = -1;
            select[2] = -1;
            select[3] = -1;
            page=0;
            rb1.setText(choice[0][0]);
            rb2.setText(choice[0][1]);
            rb3.setText(choice[0][2]);
            tv.setText(question[0]);
            rg.clearCheck();
        }
    }
    private Button.OnClickListener buttonclick = new Button.OnClickListener() {public void onClick(View v) {
        if(v.getId() == R.id.button1) {
            rg.clearCheck();
            if(page >= 1){
                page -- ;
            }
            else {
                page = 3;
            }
            rb1.setText(choice[page][0]);
            rb2.setText(choice[page][1]);
            rb3.setText(choice[page][2]);
            tv.setText(question[page]);
            rg.clearCheck();
            if(select[page] == 0){
                rb1.setChecked(true);
            }
            else if(select[page] == 1){
                rb2.setChecked(true);
            }
            else if(select[page] == 2){
                rb3.setChecked(true);
            }
        }
        else if(v.getId() == R.id.button2){
            rg.clearCheck();
            if(page<3){
                page ++ ;
            }
            else {
                page= 0;
            }
            rb1.setText(choice[page][0]);
            rb2.setText(choice[page][1]);
            rb3.setText(choice[page][2]);
            tv.setText(question[page]);
            rg.clearCheck();
            if(select[page] == 0){
                rb1.setChecked(true);
            }
            else if(select[page] == 1){
                rb2.setChecked(true);
            }
            else if(select[page] == 2){
                rb3.setChecked(true);
            }
        }
        else if(v.getId() == R.id.button3) {
            if(b3mode == 0){
                int sum = select[0] + select[1] + select[2] + select[3];
                if (sum == 0) {
                    tv.setText(answer[0]);
                    an[0] ++;
                    ansnum = 0;
                } else if (sum == 1 || sum == 2) {
                    tv.setText(answer[1]);
                    an[1] ++;
                    ansnum = 1;
                } else if (sum == 3 || sum == 4) {
                    tv.setText(answer[2]);
                    an[2] ++;
                    ansnum = 2;
                } else if (sum == 5 || sum == 6) {
                    tv.setText(answer[3]);
                    an[3] ++;
                    ansnum = 3;
                } else if (sum == 7 || sum == 8) {
                    tv.setText(answer[4]);
                    an[4] ++;
                    ansnum = 4;
                }
                b1.setVisibility(Button.INVISIBLE);
                b2.setVisibility(Button.INVISIBLE);
                b3.setText("Retry");
                rb1.setVisibility(Button.INVISIBLE);
                rb2.setVisibility(Button.INVISIBLE);
                rb3.setVisibility(Button.INVISIBLE);
                select[0] = -1;
                select[1] = -1;
                select[2] = -1;
                select[3] = -1;
                b3mode = 1;
            }
            else if(b3mode == 1){
                if(v.getId() == R.id.button3){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, Main2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("output",an );
                    bundle.putInt("outputa",ansnum);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,3);
                }
            }
        }
    }};
    private RadioButton.OnClickListener radiobuttonclick = new RadioButton.OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.radioButton1) {
                select[page] = 0;
            }
            else if (v.getId() == R.id.radioButton2) {
                select[page] = 1;
            }
            else if (v.getId() == R.id.radioButton3) {
                select[page] = 2;
            }
            if(select[0]!=-1 && select[1]!=-1 && select[2]!=-1 && select[3]!=-1){b3.setEnabled(true);}
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }}