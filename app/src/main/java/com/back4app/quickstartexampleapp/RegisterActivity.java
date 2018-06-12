package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity  {






   /* public void signUp()
    {





        if (etusername.getText().toString().matches("") || etpassword.getText().toString().matches("") || etemail.getText().toString().matches(""))
        {
            Toast.makeText(this,"Enter valid email and passsword and username",Toast.LENGTH_LONG).show();
        }
        else
        {

                ParseUser.logInInBackground(etusername.getText().toString(), etpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null)
                        {
                            //Intent intent = new Intent(RegisterActivity.this, RiderActivity.class);
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


        }
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etemail = (EditText) findViewById(R.id.etemail);
        final EditText etpassword = (EditText) findViewById(R.id.etpassword);
        final EditText etusername = (EditText) findViewById(R.id.etusername);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_register = (Button) findViewById(R.id.btn_register);

        final Switch userTypeSwitch = (Switch) findViewById(R.id.usertypeswitch);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });




        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = etemail.getText().toString();
                String password = etpassword.getText().toString();
                String username = etusername.getText().toString();

                String usertype = "rider";

                if(userTypeSwitch.isChecked())
                {
                    usertype = "driver";
                }

                if (username.matches("") || password.matches("") || useremail.matches(""))
                {
                    Toast.makeText(RegisterActivity.this,"Enter valid email and passsword and username",Toast.LENGTH_LONG).show();
                }
                else {
                    ParseUser user = new ParseUser();

                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(useremail);
                    user.put("usertype",usertype);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null)
                            {
                                if(ParseUser.getCurrentUser().get("usertype").equals("rider"))
                                {
                                    startActivity(new Intent(RegisterActivity.this,RiderActivity.class));
                                }
                                else
                                {
                                    startActivity(new Intent(RegisterActivity.this,ViewRequestsActivity.class));
                                }
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });





    }
}
