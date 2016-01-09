package net.mustafaozcan.materialnavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class newsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");

        TextView tv = (TextView) findViewById(R.id.textViewTitle);
        // set the title here using some static global variable
        tv.setText();
        TextView tv2 = (TextView) findViewById(R.id.textViewData);
        tv2.setText(title);
        tv.setText(id);
    }
}
