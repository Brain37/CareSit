package com.example.brianscott.caresit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
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
    Firebase serviceRequestsRef;
    String toAdd;

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
        ListAdapter theAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, toAdd);
        theListView.setAdapter(theAdapter);

        purchaseButton = (Button)this.findViewById(R.id.purchaseButton);

        serviceRequestsRef = myFirebaseRef.child("service requests");

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Request newRequest = new Request();
                newRequest.setEndDate(endDate.toString());
                newRequest.setDescription(description.toString());
                newRequest.setEndTime(endTime.toString());
                newRequest.setStartDate(startDate.toString());
                newRequest.setStartTime(startTime.toString());

                serviceRequestsRef.push().setValue(newRequest);

                startDate.setText("");
                startTime.setText("");
                endDate.setText("");
                endTime.setText("");
                description.setText("");


                myFirebaseRef.child("service requests").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot)
                    {
                        String toAdd = "Start Time: " + startTime + " Start Date: " + startDate + " - " + "\n" + "End Time: " + endTime + " End Date: " + endDate + "\n" + "Description: " +description;
                    }

                    @Override public void onCancelled(FirebaseError error)
                    {

                    }

                });
            }
        });

    }

}
