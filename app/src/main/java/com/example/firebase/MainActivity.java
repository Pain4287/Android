package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText edttxt1,edttxt2;
    Button btn1;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edttxt1 = findViewById(R.id.Edt1);
        edttxt2 = findViewById(R.id.Edt2);
        btn1 = findViewById(R.id.mybtn);
        firebaseAuth = FirebaseAuth.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = edttxt1.getText().toString().trim();
                String pwd = edttxt2.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(uid,pwd).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText
                                        (getApplicationContext(),
                                                "Welcome "+edttxt1.getText().toString().trim(),
                                                Toast.LENGTH_LONG).show();
                                Intent intent =
                                        new Intent(MainActivity.this,
                                                Dashboard.class);
                                intent.putExtra("name", uid);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),
                                        "Login Failed",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });


    }
}