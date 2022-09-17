package com.example.flamespizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText Name,Phone,Email,Address,Password;
    Button btnSignUp;
    TextView toSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name= (EditText) findViewById(R.id.upName);
        Phone= (EditText) findViewById(R.id.signPhone);
        Email= (EditText) findViewById(R.id.upEmail);
        Address= (EditText) findViewById(R.id.upAddress);
        Password= (EditText) findViewById(R.id.signPassword);
        toSignIn= (TextView) findViewById(R.id.toSignIn);

        btnSignUp=(Button) findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user1 = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    user1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(Phone.getText().toString()).exists())
                            {
                                Toast.makeText(SignUp.this, "Number exists", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                User user = new User( Phone.getText().toString(),  Password.getText().toString(),  Name.getText().toString(),
                                        Address.getText().toString(),  Email.getText().toString());
                                user1.child(Phone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        });
        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn = new Intent(SignUp.this,MainActivity.class);
                startActivity(SignIn);
            }
        });
    }
}