package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class UserSearch extends AppCompatActivity {

    private static final String url = "http://192.168.225.81:8888/ankur/usersearch.php";
    String x;
    String locality, shopname, email, addides, contact, lati, longi;

    SearchUserAdapter adapter;
    RecyclerView recycler;
    List<SearchUserData> dataList;
    RecyclerView.LayoutManager layoutManager;
TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        recycler = findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);

        t1=findViewById(R.id.textView12);

        Bundle b = getIntent().getExtras();
        x = b.getString("locality");
        /*String y= b.getString("city");*/

        t1.setText("Located at "+x);
        loadData();


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

                        SearchUserData data=new SearchUserData();

                        locality = pro.getString("locality");
                        shopname = pro.getString("shopname");
                        email = pro.getString("email");
                        addides = pro.getString("addides");
                        contact = pro.getString("contact");
                        lati = pro.getString("lati");
                        longi = pro.getString("longi");


                            data.setShopname(shopname);

                            dataList.add(data);



                    }

                    adapter=new SearchUserAdapter(dataList,getApplicationContext());
                    recycler.setAdapter(adapter);



                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(UserSearch.this, "Error", Toast.LENGTH_SHORT).show();
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
