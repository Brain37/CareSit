package com.example.brianscott.caresit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class MainActivity extends AppCompatActivity
{
    EditText username;
    EditText password;
    Button loginButton;
    Button registerButton;
    Button forgotPasswordButton;
    Account currentUser;
    Intent registerIntent;
    Intent userHomeIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        registerIntent = new Intent(getApplicationContext(), Register.class);

        userHomeIntent = new Intent(getApplicationContext(), UserHome.class);

        username = (EditText)this.findViewById(R.id.username);
        password = (EditText)this.findViewById(R.id.password);
        loginButton = (Button)this.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                {
                    currentUser = new Account(username.getText().toString(), password.getText().toString());
                    Core.myFirebaseRef.authWithPassword(currentUser.getUsername(), currentUser.getPassword(), new Firebase.AuthResultHandler()
                    {
                        @Override
                        public void onAuthenticated(AuthData authData)
                        {
                            System.out.println("Account ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            startActivity(userHomeIntent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError)
                        {
                            // there was an error
                        }
                    });
                    System.out.println("authenticated");
                }
            }
        });


        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(registerIntent);
            }
        });


        forgotPasswordButton = (Button)findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("forgot password");
            }
        });

    }


}
