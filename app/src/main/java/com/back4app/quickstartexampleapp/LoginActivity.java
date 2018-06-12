package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etpassword = (EditText) findViewById(R.id.etlpassword);
        final EditText etusername = (EditText) findViewById(R.id.etlusername);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);


        if(ParseUser.getCurrentUser() != null)
        {

            if(ParseUser.getCurrentUser().get("usertype").equals("rider"))
            {
                startActivity(new Intent(LoginActivity.this,RiderActivity.class));
                finish();
            }
            else
            {
                startActivity(new Intent(LoginActivity.this,ViewRequestsActivity.class));
                finish();
            }

        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String password = etpassword.getText().toString();
                // String username = etusername.getText().toString();


                if (etusername.getText().toString().matches("") || etpassword.getText().toString().matches("") )
                {
                    Toast.makeText(LoginActivity.this,"Enter valid email and passsword and username",Toast.LENGTH_LONG).show();
                }
                else
                {
                  ParseUser.logInInBackground(etusername.getText().toString(), etpassword.getText().toString(), new LogInCallback() {
                      @Override
                      public void done(ParseUser user, ParseException e) {
                          if(user != null)
                          {
                              if(ParseUser.getCurrentUser().get("usertype").equals("rider"))
                              {
                                  startActivity(new Intent(LoginActivity.this,RiderActivity.class));
                              }
                              else
                              {
                                  startActivity(new Intent(LoginActivity.this,ViewRequestsActivity.class));
                              }

                          }
                          else
                          {
                              Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                          }
                      }
                  });

                }
            }
        });





    }
}

