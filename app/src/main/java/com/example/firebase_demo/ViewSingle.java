package com.example.firebase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewSingle extends AppCompatActivity {
    public TextView nameT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single);

        nameT = (EditText)findViewById(R.id.nameT);
    }

    public void view(View v){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewSingle.this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s="";
                if(dataSnapshot.getChildrenCount()>0)
                {
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        if (nameT.getText().toString().equals(ds.child("name").getValue(String.class)))
                       {
                           alertDialog.setMessage(""+ds.child("name").getValue()+"\n"+ds.child("phone").getValue()+ds.child("reg").getValue());
                           alertDialog.show();
                       }

                    }

                }
                else {
                    alertDialog.setMessage("No records available!!!");
                    alertDialog.show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
