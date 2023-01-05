package com.example.mlem.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BlogRepository {
    private static final String collectionPath = "blogs";
    private static final String TAG = "Blog Repository";

    private final FirebaseFirestore firestore;

    public BlogRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void insert(Blog blog) {
        firestore.collection(collectionPath).add(blog).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    public void update(Blog blog) {
        firestore.collection(collectionPath).document(blog.getId()).set(blog).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public void delete(Blog blog) {
        firestore.collection(collectionPath).document(blog.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public Blog getOne(String id) {
        final Blog[] blog = {null};

        firestore.collection(collectionPath).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Blog blogTemp = document.toObject(Blog.class);
                        assert blogTemp != null;
                        blogTemp.setId(document.getId());
                        blog[0] = blogTemp;
                    } else {
                        Log.d(TAG, "Document does not exist");
                    }
                } else {
                    Log.d(TAG, "Error getting document: ", task.getException());
                }
            }
        });

        return blog[0];
    }

    public LiveData<List<Blog>> getAll() {
        final MutableLiveData<List<Blog>> liveData = new MutableLiveData<>();

        firestore.collection(collectionPath).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Blog> blogs = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Blog blog = documentSnapshot.toObject(Blog.class);
                    blog.setId(documentSnapshot.getId());
                    blogs.add(blog);
                }
                liveData.setValue(blogs);
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
