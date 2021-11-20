package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        editTextUsername=findViewById(R.id.editTextUsername);
        btnLogIn=findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                String username= editTextUsername.getText().toString();
                if(!username.isEmpty()) {
                    Intent i = new Intent(this, todolist.class);
                    i.putExtra("username", username);
                    startActivity(i);
                    editTextUsername.setText("");
                }

                break;
        }
    }
}