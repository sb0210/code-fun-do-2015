package net.mustafaozcan.materialnavigation;
/**
 * Copyright (C) 2015 Mustafa Ozcan
 * Created on 06 May 2015 (www.mustafaozcan.net)
 * *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * *
 * http://www.apache.org/licenses/LICENSE-2.0
 * *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ContentFragment extends Fragment {
    // Store instance variables

    private static String[] convert(List<String[]> from)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String[] strings : from) {
            Collections.addAll(list, strings);
        }
        return list.toArray(new String[list.size()]);
    }

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

    // newInstance constructor for creating fragment with arguments
    public static ContentFragment newInstance(int pageIndex) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt("pageIndex", pageIndex);
        contentFragment.setArguments(args);
        return contentFragment;
    }

    int numberOfArticles = 10;
    String[] Tarray = new String[numberOfArticles];

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            final int pageIndex = bundle.getInt("pageIndex", 0);

            InputStream inputStream = getResources().openRawResource(R.raw.gadget);
            CSVFile csvFile = new CSVFile(inputStream);
            final List technology = csvFile.read();

            inputStream = getResources().openRawResource(R.raw.india);
            csvFile = new CSVFile(inputStream);
            final List india = csvFile.read();

            inputStream = getResources().openRawResource(R.raw.news);
            csvFile = new CSVFile(inputStream);
            final List news = csvFile.read();

            inputStream = getResources().openRawResource(R.raw.politics);
            csvFile = new CSVFile(inputStream);
            final List politics = csvFile.read();

            final String [] gtitle = new String[technology.size()];
            final String [] ptitle = new String[politics.size()];
            final String [] ntitle = new String[news.size()];
            final String [] ititle = new String[india.size()];

            for(int i=0;i<technology.size();i++)
            {
                String [] str = new String[15];
                str = (String[]) technology.get(i);
                gtitle[i] = str[0];
            }

            for(int i=0;i<politics.size();i++)
            {
                String [] str = new String[15];
                str = (String[]) politics.get(i);
                ptitle[i] = str[0];
            }

            for(int i=0;i<news.size();i++)
            {
                String [] str = new String[15];
                str = (String[]) news.get(i);
                ntitle[i] = str[0];
            }

            for(int i=0;i<india.size();i++)
            {
                String [] str = new String[15];
                str = (String[]) india.get(i);
                ititle[i] = str[0];
            }



            // Get String here from request
//            final String[] Tarray ={ // needs to be final to be used within the event handler
//                    "Isis Guy kills mom on ISIS insistence",
//                    "Isis Guy kills mom on ISIS insistence",
//                    "Isis Guy kills mom on ISIS insistence",
//                    "Camera",
//                    "Global",
//                    "FireFox",
//                    "UC Browser",
//                    "Android Folder",
//                    "VLC Player",
//                    "Futher below added to test scrolling",
//                    "Cold War",
//            };

            ArrayAdapter adapter = null;

            if(pageIndex == 0)
            {
                adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview, ptitle);
            }
            else if(pageIndex == 1)
            {
                adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview, gtitle);
            }
            else if(pageIndex == 2)
            {
                adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview,ntitle );
            }
            else
            {
                adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview, ititle);
            }


            ListView listview = (ListView) view.findViewById(R.id.listView);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // your code is here on item click
                    String selval = ((TextView) view).getText().toString();
                    Intent intnt = new Intent(getContext(), newsActivity.class);
                    if(pageIndex == 0)
                    {
                        intnt.putExtra("title", ptitle[(int)id]);
                        intnt.putExtra("data", (String[])politics.get((int)id));
                    }

                    else if (pageIndex == 1)
                    {
                        intnt.putExtra("title", gtitle[(int)id]);
                        intnt.putExtra("data", (String[]) technology.get((int)id));
                    }

                    else if(pageIndex == 2)
                    {
                        intnt.putExtra("title", ititle[(int)id]);
                        intnt.putExtra("data", (String[])news.get((int) id));
                    }

                    else
                    {
                        intnt.putExtra("title", ititle[(int) id]);
                        intnt.putExtra("data", (String[])india.get((int)id));
                    }
//                    intnt.putExtra("name", position);
                    startActivity(intnt);
                }
            });

//            if(pageIndex == 0)
//            {
//                WebView browser = (WebView) view.findViewById(R.id.webview);
//                browser.loadUrl("http://meetshah1995.github.io/academics");
//            }
//            else
//            {
//                WebView browser = (WebView) view.findViewById(R.id.webview);
//                browser.loadUrl("http://meetshah1995.github.io/blog");
//            }

        }
        return view;

    }
}
