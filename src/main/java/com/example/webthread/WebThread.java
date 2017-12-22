package com.example.webthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

/**
 * Created by rye on 2017/12/4.
 */

public class WebThread extends Thread {
    private ListView listView;
    private static final String PATH = "http://apis.juhe.cn/cook/query?key=fbbcc01acef6e728583a2c522a410346&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&pn=3";
    @Override
    public void run() {

        try {
            URL url=new URL(PATH);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode()==200){
                InputStream in=connection.getInputStream();
                InputStreamReader isr=new InputStreamReader(in,"utf-8");
                String result="";
                String line="";
                BufferedReader br=new BufferedReader(isr);
                if ((line=br.readLine())!=null){
                    result+=line;
                }
                List list=new ArrayList();
                JSONObject jsonObject;
                jsonObject = new JSONObject(result);
                JSONObject res = jsonObject.getJSONObject("result");
                JSONArray data = res.getJSONArray("data");
                for (int i=0;i<data.length();i++){
                    jsonObject=data.getJSONObject(i);
                    String title=jsonObject.getString("title");
                    list.add(title);
                }

                Message msg=new Message();
                msg.obj=list;
                MainActivity.handler.sendMessage(msg);                              //调用MainActivity的handler传送消息回主函数更新UI
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
