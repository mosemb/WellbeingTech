package com.hfad.wellbeingtech;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOHeartRateDB {

    private final DatabaseReference databaseReference;


    public DAOHeartRateDB() {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://wellbeingtechreal-default-rtdb.europe-west1.firebasedatabase.app");
        this.databaseReference = database.getReference(HeartRateVariabilityDB.class.getSimpleName());

    }

    public Task<Void> insert(HeartRateVariabilityDB record){

        return databaseReference.push().setValue(record);
    }


}
