package com.example.brianscott.caresit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


public class MainActivity extends AppCompatActivity
{
    TextView username;
    TextView password;
    Button loginButton;
    Button registerButton;
    Button forgotPasswordButton;
    Firebase myFirebaseRef;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://blazing-heat-8324.firebaseio.com/");
        setContentView(R.layout.activity_main);

        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                {
                    currentUser = new User(username.getText().toString(), password.getText().toString());
                    myFirebaseRef.authWithPassword(currentUser.getUsername(), currentUser.getPassword(), new Firebase.AuthResultHandler()
                    {
                        @Override
                        public void onAuthenticated(AuthData authData)
                        {
                            System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
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
                currentUser = new User(username.getText().toString(), password.getText().toString());
                myFirebaseRef.createUser(currentUser.getUsername(), currentUser.getPassword(), new Firebase.ValueResultHandler<Map<String, Object>>()
                {
                    @Override
                    public void onSuccess(Map<String, Object> result)
                    {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                    }

                    @Override
                    public void onError(FirebaseError firebaseError)
                    {
                        // there was an error
                    }
                });
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
