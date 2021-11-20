package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    //Data
    private ArrayList<Item> items;

    public ItemAdapter() {
        items= new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //Dotar de UI a un arrayList
    @Override
    public View getView(int pos, View row, ViewGroup list) {
        LayoutInflater inflater= LayoutInflater.from(list.getContext());
        View rowView= inflater.inflate(R.layout.rowreminder, null);

        Item item= items.get(pos);

        Button deleteBtn= rowView.findViewById(R.id.deleteBtn);
        TextView reminderName= rowView.findViewById(R.id.reminderName);
        TextView dateTxt= rowView.findViewById(R.id.dateTxt);
        TextView descriptionTxt= rowView.findViewById(R.id.descriptionTxt);
        TextView statusTxt= rowView.findViewById(R.id.statusTxt);

        reminderName.setText(item.getItemName());
        dateTxt.setText(item.getDate());
        descriptionTxt.setText(item.getItemDescription());
        statusTxt.setText(item.getStatus());

        deleteBtn.setOnClickListener(view -> {
            String id= item.getItemId();
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("items").child(id);
            ref.setValue(null);
        });

        statusTxt.setOnClickListener(view -> {
            if(item.getStatus().equals("Pending")){

                DatabaseReference modRef= FirebaseDatabase.getInstance().getReference().child("items").child(item.getItemId());
                Item itemMod= new Item( item.getItemId(),
                        item.getOwner(),
                        item.getItemName(),
                        item.getItemDescription(),
                        item.getDate(),
                        "Completed");
                modRef.setValue(itemMod);
            }


        });


        return rowView;

    }


}
