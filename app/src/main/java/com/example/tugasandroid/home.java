package com.example.tugasandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
private RecyclerView  rc;
private RequestQueue rq;
private List<album> albumList;
private albumAdapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rc = findViewById(R.id.rcv);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adp);
        rq=VolleySingleTon.getMinstance(this).getRq();
        albumList= new ArrayList<>();

        fetchAlbum();

    }

    private void fetchAlbum() {
        String url = "https://jsonplaceholder.typicode.com/photos";
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String url = jsonObject.getString("url");

                        album al= new album(title,url);
                        albumList.add(al);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    albumAdapter adapter = new albumAdapter(home.this,albumList);
                    rc.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(home.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jsonArrayRequest);
    }
}