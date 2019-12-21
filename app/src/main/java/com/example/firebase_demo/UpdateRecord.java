package com.example.firebase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateRecord extends AppCompatActivity {
    EditText nameT, mobileT;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameT = (EditText)findViewById(R.id.nameT);
        mobileT = (EditText)findViewById(R.id.mobileT);
    }

    public void update(View v){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateRecord.this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                if(dataSnapshot.getChildrenCount()>0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (nameT.getText().toString().equals(ds.child("name").getValue())) {
                            key = ds.getKey();
                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Students").child(key);
                            ref.child("phone").setValue(mobileT.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alertDialog.setMessage("Record updated successfully!!!");
                                    alertDialog.show();
                                }
                            });
                        }
                    }

                }
                else
                    alertDialog.setMessage("No records available!!!");
                    alertDialog.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
