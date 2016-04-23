package com.example.brianscott.caresit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class Register extends AppCompatActivity
{
    EditText username;
    EditText password;
    Button registerButton;
    Account currentUser;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://blazing-heat-8324.firebaseio.com/");
        setContentView(R.layout.activity_register);

        registerButton = (Button)this.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentUser = new Account(username.getText().toString(), password.getText().toString());
                myFirebaseRef.createUser(currentUser.getUsername(), currentUser.getPassword(), new Firebase.ValueResultHandler<Map<String, Object>>()
                {
                    @Override
                    public void onSuccess(Map<String, Object> result)
                    {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        {
                            myFirebaseRef.authWithPassword(currentUser.getUsername(), currentUser.getPassword(), new Firebase.AuthResultHandler()
                            {
                                @Override
                                public void onAuthenticated(AuthData authData)
                                {
                                    System.out.println("Account ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
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

                    @Override
                    public void onError(FirebaseError firebaseError)
                    {
                        // there was an error
                    }
                });
            }
        });
        username = (EditText)this.findViewById(R.id.usernameEditText);
        password = (EditText)this.findViewById(R.id.passwordEditText);

    }


}
