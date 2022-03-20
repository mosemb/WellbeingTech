package com.hfad.wellbeingtech;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOMentalDB {

    private final DatabaseReference databaseReference;

    public DAOMentalDB(){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://wellbeingtechreal-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference(MentalDB.class.getSimpleName());

    }

    public Task<Void> insert(MentalDB record){

        return databaseReference.push().setValue(record);
    }


}
