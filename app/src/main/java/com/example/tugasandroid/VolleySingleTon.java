package com.example.tugasandroid;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleTon {
    private RequestQueue rq;
    private static VolleySingleTon minstance;

    private VolleySingleTon(Context context){
        rq = Volley.newRequestQueue(context.getApplicationContext());

    }
    public  static synchronized VolleySingleTon getMinstance(Context context){
        if (minstance==null){
            minstance=new VolleySingleTon(context);
        }
        return minstance;
    }

    public RequestQueue getRq() {
        return rq;
    }
}
