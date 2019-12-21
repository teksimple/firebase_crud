package com.example.firebase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteRecord extends AppCompatActivity {
    public TextView nameT;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        nameT = (EditText)findViewById(R.id.nameT);
    }

    public void delete(View v){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DeleteRecord.this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                if(dataSnapshot.getChildrenCount()>0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (nameT.getText().toString().equals( ds.child("name").getValue())) {
                            key = ds.getKey();
                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Students").child(key);
                            ref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alertDialog.setMessage("Record deleted successfully!!!");
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
