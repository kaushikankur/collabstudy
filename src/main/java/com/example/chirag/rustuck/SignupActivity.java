package com.example.chirag.rustuck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent i = getIntent();
        setContentView(R.layout.activity_signup);
        db=new DbHelper(this);

        _nameText =(EditText)findViewById(R.id.input_name2);
        _emailText=(EditText)findViewById(R.id.input_email2);
        _passwordText=(EditText)findViewById(R.id.input_password2);
        _phoneNumber=(EditText)findViewById(R.id.contact_number);
        _signupButton=(Button)findViewById(R.id.btn_signup2);
        _loginLink=(TextView)findViewById(R.id.link_login2);

        _signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "STRING bkjhbkMESSAGE", Toast.LENGTH_LONG).show();
                ContactData cd=new ContactData(_nameText.getText().toString(), _emailText.getText().toString(),
                        _passwordText.getText().toString(), _phoneNumber.getText().toString());
                boolean isInserted=db.addContact(cd);
                if(isInserted==true){
                    Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
