package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;

public class UserHomePanel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView t1;
    Button b1,b2;
    Spinner s1, s2, s3;
    ArrayAdapter aa,aa1, aa2,aa3,aa4,aa5,aa6;


    /*String url="http://192.168.225.81:8888/ankur/usersearch.php";*/
    String state[] = {"madhya pradesh", "maharastra"};

    String city1[] = {"bhopal", "indore"};
    String city2[] = {"mumbai", "pune"};


    String bhopallocal[] = {"kolar road", "mp nagar"};
    String indorelocal[] = {"vmart", "sarafa market"};
    String mumbailocal[] = {"marnine drive ", "lonavla"};

    String punelocal[] = {"it park", "singhgargh"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_panel);

        t1 = findViewById(R.id.textView4);
        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button12);
        s1 = findViewById(R.id.spinner);
        s2 = findViewById(R.id.spinner2);
        s3 = findViewById(R.id.spinner3);

        Bundle b = getIntent().getExtras();
        final String x = b.getString("name");

        t1.setText("Welcome " + x);


        s1.setOnItemSelectedListener(this);

        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, state);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(aa);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent in=new Intent(getApplicationContext(),UpdateDeleteUser.class);
               in.putExtra("username",x);
                startActivity(in);


            }
        });






    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, final long id) {


        final String select = (String) aa.getItem(position);

        if (select.equals("madhya pradesh")) {


            aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city1);
            aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(aa1);

            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {

                    String q = (String) aa1.getItem(position);

                    if (q.equals("bhopal")) {



                        aa2 = new ArrayAdapter ( UserHomePanel.this,android.R.layout.simple_spinner_dropdown_item,bhopallocal);
                        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s3.setAdapter(aa2);

                        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {






                                b1.setOnClickListener(new View.OnClickListener() {
                                    String x= (String) aa2.getItem(position);

                                    @Override
                                    public void onClick(View v) {

                                        Intent i = new Intent(getApplicationContext(), UserSearch.class);
                                        i.putExtra("locality", x);
                                        startActivity(i);
                                    }

                                });






                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });




                    }else if (q.equals("indore")){




                        aa2 = new ArrayAdapter ( UserHomePanel.this,android.R.layout.simple_spinner_dropdown_item,indorelocal);
                        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s3.setAdapter(aa2);








                    }








                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });





        }

        else if (select.equals("maharastra")) {
            aa4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city2);
            aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s2.setAdapter(aa4);


            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String q = (String) aa4.getItem(position);

                    if (q.equals("mumbai")) {



                        aa5 = new ArrayAdapter ( UserHomePanel.this,android.R.layout.simple_spinner_dropdown_item,mumbailocal);
                        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s3.setAdapter(aa5);


                        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {


                                b1.setOnClickListener(new View.OnClickListener() {
                                    String x= (String) aa5.getItem(position);

                                    @Override
                                    public void onClick(View v) {

                                        Intent i = new Intent(getApplicationContext(), UserSearch.class);
                                        i.putExtra("locality", x);
                                        startActivity(i);
                                    }

                                });







                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });






                    }else if (q.equals("pune")){




                        aa5 = new ArrayAdapter ( UserHomePanel.this,android.R.layout.simple_spinner_dropdown_item,punelocal);
                        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s3.setAdapter(aa5);



                    }







                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
















        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}