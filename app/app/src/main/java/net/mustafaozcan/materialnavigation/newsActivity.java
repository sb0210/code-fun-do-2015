package net.mustafaozcan.materialnavigation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class newsActivity extends AppCompatActivity {

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int numberOfDataPoints = 10;

        TextView tv = (TextView) findViewById(R.id.textViewTitle);
        tv.setText(title);

        ViewGroup tv2 = (ViewGroup) findViewById(R.id.textViewData);

        for(int i=0;i<numberOfDataPoints;++i)
        {

            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.template_layout, null);

            // get the comments and the associated data here
            // fill in any details dynamically here
            TextView textView1 = (TextView) v.findViewById(R.id.textViewArticleSource);
            TextView textView2 = (TextView) v.findViewById(R.id.textViewArticleSummary);

            textView1.setText("your text" + i); // source of article
            textView2.setText("your text" + i*2); // article summary

            // insert into main view
            ViewGroup insertPoint = tv2;
            insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
    }
}
