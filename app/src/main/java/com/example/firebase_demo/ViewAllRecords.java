package com.example.firebase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAllRecords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
    }

    public void viewAll(View v)
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewAllRecords.this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                if(dataSnapshot.getChildrenCount()>0)
                {
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        s+= "Name: " + ds.child("name").getValue()+"\n";
                        s+= "Reg: " + ds.child("reg").getValue()+"\n";
                        s+= "Mobile: " + ds.child("phone").getValue()+"\n-----------------\n";

                    }
                    alertDialog.setTitle("Records: Students");
                    alertDialog.setMessage(s);
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                }
                else {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Nothing to show!!!");
                    alertDialog.setPositiveButton("OK",null);
                    alertDialog.show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
