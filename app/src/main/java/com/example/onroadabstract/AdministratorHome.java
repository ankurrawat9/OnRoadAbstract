package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class AdministratorHome extends AppCompatActivity {
    String username;
    String[] a;

    Button b1;
    ArrayAdapter<String> aa;
    TextView t1;


    SearchUserAdapter2 adapter;
    RecyclerView recycler2;
    List<SearchUserData2> dataList;
    RecyclerView.LayoutManager layoutManager;




    String url = "http://192.168.43.146:8888/ankur/adminslist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_home);





        recycler2 = findViewById(R.id.recycler2);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler2.setLayoutManager(layoutManager);




        b1=findViewById(R.id.button14);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData();


            }
        });
    }


    public void loadData() {


        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    dataList = new ArrayList<>();

                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pro = array.getJSONObject(i);

                        SearchUserData2 data=new SearchUserData2();

                        username = pro.getString("username");
                        data.setUsername(username);

                        dataList.add(data);


                    }

                    adapter= new SearchUserAdapter2(dataList,getApplicationContext());
                    recycler2.setAdapter(adapter);



                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(AdministratorHome.this, "Error", Toast.LENGTH_SHORT).show();
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
