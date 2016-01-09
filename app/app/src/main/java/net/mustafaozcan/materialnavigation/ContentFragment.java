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

public class ContentFragment extends Fragment {
    // Store instance variables

    // newInstance constructor for creating fragment with arguments
    public static ContentFragment newInstance(int pageIndex) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt("pageIndex", pageIndex);
        contentFragment.setArguments(args);
        return contentFragment;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int pageIndex = bundle.getInt("pageIndex", 0);

            TextView tvSection = (TextView) view.findViewById(R.id.tvSection);
            tvSection.setText(getString(R.string.page) + " " + String.valueOf(pageIndex + 1));


            // Get String here from request
            String[] itemname ={
                    "Safari",
                    "Camera",
                    "Global",
                    "FireFox",
                    "UC Browser",
                    "Android Folder",
                    "VLC Player",
                    "Cold War"
            };

            ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),R.layout.activity_listview,itemname);

            ListView listview = (ListView) view.findViewById(R.id.listView);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // your code is here on item click
                    String selval = ((TextView) view).getText().toString();
                    Intent intnt = new Intent(getContext(), newsActivity.class);
                    intnt.putExtra("title", id);
                    intnt.putExtra("name", position);
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
