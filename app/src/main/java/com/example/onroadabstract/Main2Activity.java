package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    Button b1,b2;
    private static final String url="http://192.168.225.81:8888/ankur/onroad.php";
    EditText e1,e2,e3,e4;
    String username,email,password,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        b1=findViewById(R.id.button);


        b2=findViewById(R.id.button2);

        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reguser();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
            }
        });


    }
    public void reguser()
    {
        username=e1.getText().toString();
        email=e2.getText().toString();
        password=e3.getText().toString();
        contact=e4.getText().toString();


        RequestQueue queue= Volley.newRequestQueue(Main2Activity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "recorded added", Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                })
        {
            protected Map<String,String> getParams()throws AuthFailureError {

                Map<String,String>params=new HashMap<>();

                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                params.put("contact",contact);
                return params;
            }
        };
        queue.add(stringRequest);

    }
}
