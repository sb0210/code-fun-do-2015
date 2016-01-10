package net.mustafaozcan.materialnavigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class newsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String[] data=intent.getStringArrayExtra("data");
        int numberOfDataPoints = 4;

        TextView tv = (TextView) findViewById(R.id.textViewTitle);
        tv.setText(title);

        ViewGroup tv2 = (ViewGroup) findViewById(R.id.textViewData);
        int noOfpublicOpinions = 2;

        LayoutInflater vi_ = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v_ = vi_.inflate(R.layout.graph_layout, null);
        WebView webview = (WebView) v_.findViewById(R.id.webView);
        webview.setVerticalScrollBarEnabled(true);
        webview.setHorizontalScrollBarEnabled(true);

        String poss = data[13];
        Float pos = Float.parseFloat(poss);
        Float neg = 1 - pos;
        String myurl = "http://www.cse.iitb.ac.in/~siddharth.bulia/codefundo2016/pie.php?pos=" + Float.toString(pos) + "&neg=" +  Float.toString(neg);
        webview.loadUrl(myurl);
        ViewGroup insertPoint_ = tv2;
        insertPoint_.addView(v_, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        for(int i=noOfpublicOpinions-1;i>=0;--i)
        {
            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.reddit_opinions, null);
            // get the comments and the associated data here
            // fill in any details dynamically here

            TextView textView2 = (TextView) v.findViewById(R.id.redditComment);

            textView2.setText(data[14+i]); // article summary

            // insert into main view
            ViewGroup insertPoint = tv2;
            insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }

//        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = vi.inflate(R.layout.reddit_title, null);
//        ViewGroup insertPoint = tv2;
//        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        for(int i=numberOfDataPoints-1;i>=0;--i)
        {

            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.template_layout, null);
            // get the comments and the associated data here
            // fill in any details dynamically here
            TextView textView1 = (TextView) v.findViewById(R.id.textViewArticleSource);
            TextView textView2 = (TextView) v.findViewById(R.id.textViewArticleSummary);



            textView1.setText(data[i*3+2]); // source of article
            textView2.setText(data[i*3+1]); // article summary

            // insert into main view
            ViewGroup insertPoint = tv2;
            insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }

//        TextView textView3 = (TextView) v.findViewById(R.id.redditSection);
//        textView3.setText("Reddit CockSuckers"); // source of article

    }
}
