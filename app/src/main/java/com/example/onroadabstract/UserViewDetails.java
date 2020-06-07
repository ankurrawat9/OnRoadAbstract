package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserViewDetails extends AppCompatActivity {
TextView t1,t2,t3;
Button b1,b2,b3,b4,b5;
String x;
String username,email,locality,shopname,addides,contact,lati,longi;

    String url = "http://192.168.225.81:8888/ankur/mechanic2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_details);


        Bundle b=getIntent().getExtras();
        x=b.getString("data");
        Toast.makeText(this,"you selected "+ x, Toast.LENGTH_SHORT).show();

    t1=findViewById(R.id.textView1);
    t2=findViewById(R.id.textView2);
    t3=findViewById(R.id.textView3);
    b1=findViewById(R.id.button1);
    b2=findViewById(R.id.button2);
    b3=findViewById(R.id.button3);
    b4=findViewById(R.id.button4);
    b5=findViewById(R.id.button5);

direct();
/*

b5.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent  i=new Intent(getApplicationContext(),Main3Activity.class);
        startActivity(i);
        Toast.makeText(UserViewDetails.this,"logging you out", Toast.LENGTH_SHORT).show();


    }
});
*/
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Toast.makeText(getApplicationContext(), "calling to "+x, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+contact));
        startActivity(i);


    }
});



b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{email});

        i.putExtra(Intent.EXTRA_SUBJECT,"Need help "+x);
        i.putExtra(Intent.EXTRA_TEXT,"contact as soon as possible");
        i.setType("message/rfc822");
        startActivity(i);



    }
});



b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {




        Uri i=Uri.parse("geo:"+lati+","+longi);
        Intent mi=new Intent(Intent.ACTION_VIEW,i);
        mi.setPackage("com.google.android.apps.maps");
        startActivity(mi);

    }
});


b4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent i=new Intent(getApplicationContext(),Feedback.class);
        i.putExtra("yoo",username);
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
                        locality = pro.getString("locality");
                        shopname = pro.getString("shopname");
                        addides = pro.getString("addides");
                        contact = pro.getString("contact");
                        lati = pro.getString("lati");
                        longi = pro.getString("longi");



                        if (x.equals(shopname)){

                            t1.setText("Your Selected Mechanic is "+username);
                            t2.setText("With Shop Name as "+shopname+" Loacted at "+locality);
                            t3.setText(addides);

                            Toast.makeText(getApplicationContext(), "Showing Record Of: "+x, Toast.LENGTH_SHORT).show();


                        }



                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(UserViewDetails.this, "Error", Toast.LENGTH_SHORT).show();
                    Toast.makeText(UserViewDetails.this, e.toString(), Toast.LENGTH_SHORT).show();

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
