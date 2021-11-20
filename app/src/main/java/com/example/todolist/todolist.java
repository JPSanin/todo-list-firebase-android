package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class todolist extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextDescription;
    private Button addBtn;
    private ListView remindersList;
    private FirebaseDatabase db;
    private String username;

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        getSupportActionBar().hide();

        db=FirebaseDatabase.getInstance();
        editTextName=findViewById(R.id.editTextName);
        editTextDescription=findViewById(R.id.editTextDescription);
        addBtn=findViewById(R.id.addBtn);
        remindersList=findViewById(R.id.remindersList);
        username= getIntent().getExtras().getString("username");
        adapter= new ItemAdapter();
        remindersList.setAdapter(adapter);

        addBtn.setOnClickListener(this);

        loadDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                String id= UUID.randomUUID().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                String date= sdf.format(calendar.getTime());
                DatabaseReference ref=db.getReference().child("items").child(id);

                Item item = new Item(
                        id,
                        username,
                        editTextName.getText().toString(),
                        editTextDescription.getText().toString(),
                        date,
                        "Pending"
                );

                ref.setValue(item);
                editTextName.setText("");
                editTextDescription.setText("");

                runOnUiThread(()->{
                    Toast.makeText(this, "Item added to "+ username+"'s "+" to-do list", Toast.LENGTH_SHORT).show();
                });
                break;
        }

    }

    private void loadDatabase(){
        DatabaseReference ref=db.getReference().child("items");

        ref.addValueEventListener(//on
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        adapter.clear();
                        for(DataSnapshot child : data.getChildren()){
                            Item item= child.getValue(Item.class);
                            if(item.getOwner().equals(username)){
                                adapter.addItem(item);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );


    }


}