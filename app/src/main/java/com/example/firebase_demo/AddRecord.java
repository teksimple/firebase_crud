package com.example.firebase_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRecord extends AppCompatActivity {

    EditText nameT, regT, mobileT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameT = findViewById(R.id.nameT);
        regT = findViewById(R.id.regT);
        mobileT = findViewById(R.id.mobileT);
    }

    public  void addRecord(View v)
    {
        if (TextUtils.isEmpty(nameT.getText().toString()))
        {
            nameT.setError("This field is required!!!");
            return;
        }
        if (TextUtils.isEmpty(regT.getText().toString()))
        {
            regT.setError("This field is required!!!");
            return;
        }
        if (TextUtils.isEmpty(mobileT.getText().toString()))
        {
            mobileT.setError("This field is required!!!");
            return;
        }

        //save data to Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Students").push();
        reference.child("name").setValue(nameT.getText().toString());
        reference.child("reg").setValue(regT.getText().toString());
        reference.child("phone").setValue(mobileT.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(AddRecord.this, "Success: Data saved", Toast.LENGTH_SHORT).show();
                }
                if (!task.isSuccessful())
                {
                    Toast.makeText(AddRecord.this, "Error saving data", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
