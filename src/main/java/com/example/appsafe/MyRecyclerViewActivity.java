package com.example.appsafe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Appsafe.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //called for individual items
                String fileName = dataSnapshot.getKey();
                String url = dataSnapshot.getValue(String.class);
                ((MyAdapter)recyclerView.getAdapter()).update(fileName, url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //CODING STARTS
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this));
        //populate the recycler view using custom ADAPTER
        MyAdapter myAdapter = new MyAdapter(recyclerView, getApplicationContext(), new ArrayList<String>(), new ArrayList<String>());
        //binding array list with the RECYCLER_VIEW
        recyclerView.setAdapter(myAdapter);

    }
}