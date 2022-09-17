package com.example.flamespizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText signPhone,signPassword;
    Button btnSignIn;
    TextView toRegister;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signPhone = (EditText) findViewById(R.id.signPhone);
        signPassword = (EditText) findViewById(R.id.signPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        toRegister = (TextView) findViewById(R.id.toRegister);
        
        //Database Connection
        
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user1 = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.child(signPhone.getText().toString().trim()).exists()) {

                            User user = snapshot.child(signPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(signPassword.getText().toString())) {
                                Toast.makeText(MainActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "failed doesn't exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(MainActivity.this,SignUp.class);
                startActivity(Register);
            }
        });


    }
}
