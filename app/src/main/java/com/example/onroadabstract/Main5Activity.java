package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class Main5Activity extends AppCompatActivity {

    Button b1, b2, b3;
    private static final String url = "http://192.168.225.81:8888/ankur/mechreg.php";
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
    String username, password, cpassword, locality, shopname, addides, email, contact, lati, longi,feedback=" ";

    private FusedLocationProviderClient client;


    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        client = LocationServices.getFusedLocationProviderClient(this);

        b1 = findViewById(R.id.button4);
        b2 = findViewById(R.id.button6);
        b3 = findViewById(R.id.button8);


        e1 = findViewById(R.id.editText);
        e2 = findViewById(R.id.editText2);
        e3 = findViewById(R.id.editText3);
        e4 = findViewById(R.id.editText4);
        e5 = findViewById(R.id.editText5);
        e6 = findViewById(R.id.editText6);
        e7 = findViewById(R.id.editText7);
        e8 = findViewById(R.id.editText8);

        e9 = findViewById(R.id.editText9);
        e10 = findViewById(R.id.editText10);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.getLastLocation().addOnSuccessListener(Main5Activity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {

                            e9.setText(String.valueOf(location.getLatitude()));
                            e10.setText(String.valueOf(location.getLongitude()));


                        }


                    }
                });

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reguser();

            }
        });


    }

    public void reguser() {
        dialog=new ProgressDialog(this);
        dialog.setTitle("Register");
        dialog.setMessage("please wait....");
        dialog.show();

        username = e1.getText().toString();
        email = e2.getText().toString();
        password = e3.getText().toString();
        cpassword = e4.getText().toString();
        contact = e5.getText().toString();
        addides = e6.getText().toString();
        locality = e7.getText().toString();
        shopname = e8.getText().toString();
         lati = e9.getText().toString();
        longi = e10.getText().toString();


        RequestQueue queue = Volley.newRequestQueue(Main5Activity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "recorded added", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        Toast.makeText(Main5Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                params.put("locality",locality);
                params.put("shopname",shopname);
                params.put("addides",addides);
                params.put("contact",contact);
                params.put("lati",lati);
                params.put("longi",longi);
                params.put("feedback",feedback);

                return params;
            }
        };
        queue.add(stringRequest);


    }

}





