package com.example.mlem.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Tag;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TagRepository {
    private static final String collectionPath = "tags";
    private static final String TAG = "Tag Repository";

    private final FirebaseFirestore firestore;

    public TagRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void insert(Tag tag) {
        firestore.collection(collectionPath).add(tag).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding", e);
            }
        });
    }

    public void update(Tag tag) {
        firestore.collection(collectionPath).document(tag.getId()).set(tag).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Updated successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating", e);
            }
        });
    }

    public void delete(Tag tag) {
        firestore.collection(collectionPath).document(tag.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Deleted successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error deleting", e);
            }
        });
    }

    public LiveData<List<Tag>> getAll() {
        final MutableLiveData<List<Tag>> liveData = new MutableLiveData<>();

        firestore.collection(collectionPath).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Tag> tags = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Tag tag = documentSnapshot.toObject(Tag.class);
                    tag.setId(documentSnapshot.getId());
                    tags.add(tag);
                }
                liveData.setValue(tags);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error getting all", e);
            }
        });

        return liveData;
    }
}
