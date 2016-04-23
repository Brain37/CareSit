package com.example.brianscott.caresit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserHome extends AppCompatActivity
{
    EditText startTime;
    EditText endTime;
    EditText startDate;
    EditText endDate;
    EditText description;
    ListView theListView;
    Button purchaseButton;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://blazing-heat-8324.firebaseio.com/");

        startTime = (EditText) this.findViewById(R.id.startTimeEditText);
        endTime = (EditText) this.findViewById(R.id.endTimeEditText);
        startDate = (EditText) this.findViewById(R.id.startDateEditText);
        endDate = (EditText) this.findViewById(R.id.endDateEditText);
        description = (EditText) this.findViewById(R.id.descriptionEditText);
        theListView = (ListView) this.findViewById(R.id.userListView);
        purchaseButton = (Button)this.findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pushRequestToFirebase();
                resetEditTexts();
            }
        });
    }



    private void updateListView()
    {
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                // do some stuff once
            }
            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
    }

    private void pushRequestToFirebase()
    {
        Firebase serviceRequestsRef = myFirebaseRef.child("service requests");

        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("start time", this.startTime.getText().toString());
        post1.put("start date", this.startDate.getText().toString());
        post1.put("end time", this.endTime.getText().toString());
        post1.put("end date", this.endDate.getText().toString());
        post1.put("description", this.description.getText().toString());

        serviceRequestsRef.push().setValue(post1);
    }

    private void resetEditTexts()
    {
        this.startDate.setText("");
        this.startTime.setText("");
        this.endDate.setText("");
        this.endTime.setText("");
        this.description.setText("");
    }
}
