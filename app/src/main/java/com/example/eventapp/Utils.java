package com.example.eventapp;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import static com.example.eventapp.MainActivity.getCurrentUser;
import static com.example.eventapp.MainActivity.mDatabaseReference;
import static com.example.eventapp.MainActivity.mFirebaseDatabase;

public class Utils {
    public static void deleteLike(Event event){
        DatabaseReference reference = mFirebaseDatabase.getInstance().getReference("favourites").child(event.getId()+getCurrentUser().getUid());
        reference.getRef().removeValue();
        reference = mFirebaseDatabase.getInstance().getReference("events").child(event.getId()).child("like");
        reference.getRef().setValue(event.getLike()-1);
    }
    public static void sendLike(Event event){
        if (getCurrentUser()!=null) {
            Favourites fav = new Favourites(getCurrentUser().getUid(), event.getId());
            mDatabaseReference.child("favourites").child(event.getId()+getCurrentUser().getUid()).setValue(fav);

            DatabaseReference ref = mFirebaseDatabase.getInstance().getReference("events").child(event.getId());
            ref.child("like").setValue(event.getLike()+1);
//            tv.setText(count + "");
        }else {
            //toast
        }
    }
}