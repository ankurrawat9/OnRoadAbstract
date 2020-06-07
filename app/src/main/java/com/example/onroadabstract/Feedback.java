package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.spark.submitbutton.SubmitButton;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
SubmitButton b1;
EditText e1;

String feedback,username;
    private static final String url = "http://192.168.225.81:8888/ankur/feedback.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
Bundle b=getIntent().getExtras();
username=b.getString("yoo");

       b1=findViewById(R.id.button11);
       e1=findViewById(R.id.editText11);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });
    }


    private void update()
    {


        feedback=e1.getText().toString();

        RequestQueue que = Volley.newRequestQueue(Feedback.this);
        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "feedback Posted Sucessfully", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        )
        {
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> parm= new HashMap<>();

                parm.put("username",username);

                parm.put("feedback",feedback);

                return parm;
            }
        };

        que.add(str);



    }



}
