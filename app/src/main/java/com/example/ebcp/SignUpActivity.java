package com.example.ebcp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUpActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FULLNAME = "mypreffullname";
    private static final String KEY_EMAIL = "myprefemail";

    Button Reg;
    Button login;
    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);


        textInputEditTextFullname = findViewById(R.id.signUp_name);
        textInputEditTextUsername = findViewById(R.id.signUp_username);
        textInputEditTextEmail = findViewById(R.id.signUp_email);
        textInputEditTextPassword = findViewById(R.id.signUp_password);

        login = findViewById(R.id.btn_toLogin);
        Reg = findViewById(R.id.btn_register);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, username, email, password;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());


                //triggering shared preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_FULLNAME,textInputEditTextFullname.getText().toString());
                editor.putString(KEY_EMAIL,textInputEditTextEmail.getText().toString());
                editor.apply();



                if (!fullname.equals("") && !username.equals("") && !email.equals("") && !password.equals("")) {


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "email";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = email;
                            data[3] = password;
                            PutData putData = new PutData("https://effa838f09c8.ngrok.io/phpmyadmin/se/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals(fullname+"Sign Up Success")){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {

                    Toast.makeText(getApplicationContext(), "All Fields Required",Toast.LENGTH_SHORT).show();

                }


            }
         });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
                Context context = getApplicationContext();
                CharSequence text="Processing!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


    }
}