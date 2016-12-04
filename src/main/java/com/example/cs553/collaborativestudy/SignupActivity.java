package com.example.cs553.collaborativestudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Author : Ankur Kaushik
 */
public class SignupActivity extends AppCompatActivity {

    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    EditText _phoneNumber;
    Button _signupButton;
    TextView _loginLink;
    private FirebaseAuth mFirebaseAuth;

    //  DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent i = getIntent();
        setContentView(R.layout.activity_signup);
        //    db=new DbHelper(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        //_nameText = (EditText) findViewById(R.id.input_name2);
        _emailText = (EditText) findViewById(R.id.input_email2);
        _passwordText = (EditText) findViewById(R.id.input_password2);
        //_phoneNumber = (EditText) findViewById(R.id.contact_number);
        _signupButton = (Button) findViewById(R.id.btn_signup2);
        _loginLink = (TextView) findViewById(R.id.link_login2);

        _signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = _passwordText.getText().toString();
                String email = _emailText.getText().toString();

                password = password.trim();
                email = email.trim();

                if (password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });
        // Toast.makeText(getApplicationContext(), "STRING bkjhbkMESSAGE", Toast.LENGTH_LONG).show();
                /*ContactData cd=new ContactData(_nameText.getText().toString(), _emailText.getText().toString(),
                        _passwordText.getText().toString(), _phoneNumber.getText().toString());
                boolean isInserted=db.addContact(cd);
                if(isInserted==true){
                    Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
                }*/

    }
}

