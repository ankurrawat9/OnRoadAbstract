package com.example.onroadabstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministratorLogin extends AppCompatActivity {
EditText e1,e2;
Button b1;
String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);


    b1=findViewById(R.id.button);
    e1=findViewById(R.id.editText);
    e2=findViewById(R.id.editText3);


 b1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         username=e1.getText().toString();
         password=e2.getText().toString();

         if (username.equalsIgnoreCase("ankur")&&password.equalsIgnoreCase("ankur123")){


             Toast.makeText(AdministratorLogin.this, "Welcome "+username, Toast.LENGTH_SHORT).show();
             Intent i=new Intent(getApplicationContext(),AdministratorHome.class);


             startActivity(i);
         }else {

             Toast.makeText(AdministratorLogin.this, "invalid username or password", Toast.LENGTH_SHORT).show();

         }



     }
 });
    }
}
