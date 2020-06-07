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

public class UpdateDeleteUser extends AppCompatActivity {

    String name, email, password, username,contact,x;

    StringRequest req;
TextView t1;

    String url = "http://192.168.225.81:8888/ankur/search.php";

    String url2 = "http://192.168.225.81:8888/ankur/update.php";
    String url3 = "http://192.168.225.81:8888/ankur/delete.php";

    EditText e2, e3, e4,e5;

    Button b1, bu, bd, bh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_user);



        e2 = findViewById(R.id.editText2);
        e3 = findViewById(R.id.editText3);
        e4 = findViewById(R.id.editText4);
        e5 = findViewById(R.id.editText12);

        t1= findViewById(R.id.textView16);
        b1 = findViewById(R.id.button2);
        bu = findViewById(R.id.button5);
        bd = findViewById(R.id.button6);
        bh = findViewById(R.id.button3);


        Bundle b = getIntent().getExtras();
        x = b.getString("username");

t1.setText("Showing records of "+x);

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
                        password = pro.getString("password");
                        email = pro.getString("email");
                        contact = pro.getString("contact");

                        Log.e("shafga",username);
                        Log.e("shafga",password);



                        if (x.equals(username)){

                            e2.setText(username);
                            e3.setText(password);
                            e4.setText(email);
                            e5.setText(contact);


                            Toast.makeText(getApplicationContext(), "Showing Record Of: "+username, Toast.LENGTH_SHORT).show();


                        }



                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(UpdateDeleteUser.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }



    private void delete()
    {


              username=x;
            RequestQueue que = Volley.newRequestQueue(UpdateDeleteUser.this);
            StringRequest str = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



                    Toast.makeText(getApplicationContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(getApplicationContext(),Main3Activity.class);
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










    private void update()
    {

         username=x;
        password=e3.getText().toString();
        email=e4.getText().toString();
        contact=e5.getText().toString();

        RequestQueue que = Volley.newRequestQueue(UpdateDeleteUser.this);
        StringRequest str = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();

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

                parm.put("password",password);

                parm.put("email",email);
                parm.put("contact",contact);

                return parm;
            }
        };

        que.add(str);



    }
}
