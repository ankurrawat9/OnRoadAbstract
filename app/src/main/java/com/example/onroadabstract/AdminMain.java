package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminMain extends AppCompatActivity {
    TextView t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15;
    String x;
    String username,email,locality,password,shopname,addides,contact,lati,longi,feedback;

    String url = "http://192.168.225.81:8888/ankur/mechanic3.php";
    String url3 = "http://192.168.225.81:8888/ankur/mechdelete.php";

    Button b1,b2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);


        Bundle b=getIntent().getExtras();
        x=b.getString("data");

        /*t2.setText("Showing records of "+x);*/



        t3=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView2);

        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button7);

        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);
        t6=findViewById(R.id.textView6);
        t7=findViewById(R.id.textView7);
        t8=findViewById(R.id.textView8);
        t9=findViewById(R.id.textView9);
        t10=findViewById(R.id.textView10);



    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            direct();

        }
    });
  b2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          delete();

      }
  });





    }





    public void direct(){


        StringRequest stringRequest;

        stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pro = array.getJSONObject(i);

                        username = pro.getString("username");
                        email = pro.getString("email");
                        password = pro.getString("password");
                        locality = pro.getString("locality");
                        shopname = pro.getString("shopname");
                        addides = pro.getString("addides");
                        contact = pro.getString("contact");
                        lati = pro.getString("lati");
                        longi = pro.getString("longi");
                        feedback=pro.getString("feedback");


                        if (x.equals(username)){

                            t3.setText(username);

                            t4.setText(email);

                            t5.setText(locality);
                            t6.setText(shopname);
                            t7.setText(addides);
                            t8.setText(contact);
                            t9.setText(lati);
                            t10.setText(longi);


                            Toast.makeText(getApplicationContext(), "Showing Record Of: "+username, Toast.LENGTH_SHORT).show();


                        }



                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(AdminMain.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminMain.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }






    private void delete()
    {


        RequestQueue que = Volley.newRequestQueue(AdminMain.this);
        StringRequest str = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "Account deleted sucessfully", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getApplicationContext(),AdministratorHome.class);
                startActivity(i);
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

                return parm;
            }
        };

        que.add(str);



    }





}
