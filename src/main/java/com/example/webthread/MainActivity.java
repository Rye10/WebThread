package com.example.webthread;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    public static Handler handler;                                           //必须要静态static才能在其他类中调用MainActivity的Handler，static防止内存外泄
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        handler=new Handler(){                                                //handler放入onCreate中刷新UI
            @Override
            public void handleMessage(Message msg) {
                List data= (List) msg.obj;
                ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,data);
                listView.setAdapter(adapter);
            }
        };
        WebThread webThread=new WebThread();
        webThread.start();
    }
}
