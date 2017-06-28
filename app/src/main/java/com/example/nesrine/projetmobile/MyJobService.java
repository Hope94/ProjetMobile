package com.example.nesrine.projetmobile;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.jobdispatcher.JobService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nesrine on 21/05/2017.
 */

public class MyJobService extends JobService {
    private String url="http://192.168.43.71:8080/postStatus";
    private String id_log="001";
    private String status="valide";
    com.firebase.jobdispatcher.JobParameters job;
    private  void sendNotification(String message){
        Uri defaultSounUri=
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder=
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText(message)
                        .setAutoCancel(false)
                        .setSound(defaultSounUri);
        NotificationManager notificationManager=
                (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }
    public void updateStatus()
    {

        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(s.equals("true"))
                {
                    sendNotification(s);
                Toast.makeText(MyJobService.this,"Good",Toast.LENGTH_SHORT).show();
                jobFinished( job,false);
                }
                else{
                    sendNotification(s);
                    jobFinished(job,true);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                sendNotification("Erreur");
                Toast.makeText(MyJobService.this,"Not sent to the server",Toast.LENGTH_SHORT).show();
                jobFinished(job,true);

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("id_rdv",id_log);
                map.put("status",status);
                return map;
            }
        };


        queue.add(request);

    }

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {

        this.job=job;
        updateStatus();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return true;
    }

}
