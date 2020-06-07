package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MechanicHomePanel extends AppCompatActivity {
TextView t2,t3;
Button b1,bu,bd,bh;
    String url = "http://192.168.225.81:8888/ankur/mechanic3.php";
    String url2 = "http://192.168.225.81:8888/ankur/mechupdate.php";
    String url3 = "http://192.168.225.81:8888/ankur/mechdelete.php";

    String x, username,email,locality,password,shopname,addides,contact,lati,longi,feedback;
EditText e10,e9,e8,e7,e6,e5,e4,e3,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_home_panel);


        e2 = findViewById(R.id.editText2);
        e3 = findViewById(R.id.editText3);
        e4 = findViewById(R.id.editText4);
        e5 = findViewById(R.id.editText12);
        e6 = findViewById(R.id.editText9);
        e7 = findViewById(R.id.editText10);
        e8 = findViewById(R.id.editText13);
        e9 = findViewById(R.id.editText14);
        e10 = findViewById(R.id.editText15);


        t2 = findViewById(R.id.textView10);
        t3 = findViewById(R.id.textView16);


        b1 = findViewById(R.id.button2);
        bu = findViewById(R.id.button5);
        bd = findViewById(R.id.button6);
        bh = findViewById(R.id.button3);

            Bundle b=getIntent().getExtras();
             x=b.getString("name");


            t3.setText("Welcome " +x);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                direct();
            }

        });



bu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        update();
    }
});


        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete();

            Intent i=new Intent(getApplicationContext(),Main4Activity.class);
            startActivity(i);


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

                            e2.setText(username);

                            e3.setText(email);
                            e4.setText(password);
                            e5.setText(locality);
                            e6.setText(shopname);
                            e7.setText(addides);
                            e8.setText(contact);
                            e9.setText(lati);
                            e10.setText(longi);
                            t2.setText(feedback);

                            Toast.makeText(getApplicationContext(), "Showing Record Of: "+username, Toast.LENGTH_SHORT).show();


                        }



                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(MechanicHomePanel.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MechanicHomePanel.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }










    private void update()
    {

        username=e2.getText().toString();

        email=e3.getText().toString();
        password=e4.getText().toString();
        locality=e5.getText().toString();
        shopname=e6.getText().toString();
        addides=e7.getText().toString();
        contact=e8.getText().toString();
        lati=e9.getText().toString();
        longi=e10.getText().toString();


        RequestQueue que = Volley.newRequestQueue(MechanicHomePanel.this);
        StringRequest str = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();

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
                parm.put("email",email);
                parm.put("password",password);
                parm.put("locality",locality);
                parm.put("shopname",shopname);
                parm.put("addides",addides);
                parm.put("contact",contact);
                parm.put("lati",lati);
                parm.put("longi",longi);



                return parm;
            }
        };

        que.add(str);



    }








    private void delete()
    {


        RequestQueue que = Volley.newRequestQueue(MechanicHomePanel.this);
        StringRequest str = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "Account deleted sucessfully", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(getApplicationContext(),MechanicHomePanel.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

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
