package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main4Activity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
   String username,password;
   String url="http://192.168.225.81:8888/ankur/mechlogin.php";
   String x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


    b1=findViewById(R.id.button);
    b2=findViewById(R.id.button2);

    e1=findViewById(R.id.editText);
    e2=findViewById(R.id.editText3);

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent i=new Intent(getApplicationContext(),Main5Activity.class);
            startActivity(i);
        }
    });





b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        x=e1.getText().toString();
        y=e2.getText().toString();

        loadData();

    }
});






    }

    public void loadData(){


        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

int flag=1;
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pro = array.getJSONObject(i);

                        username = pro.getString("username");
                        password = pro.getString("password");

                        Log.e("shafga",username);
                        Log.e("shafga",password);


                        if (x.equals(username) && y.equals(password)){

                            flag=2;
                            break;




                        }






                    }
                    if(flag==2)
                    {

                        Intent in=new Intent(getApplicationContext(),MechanicHomePanel.class);
                        in.putExtra("name",username);
                        startActivity(in);


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(Main4Activity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

}
