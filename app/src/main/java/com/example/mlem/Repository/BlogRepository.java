package com.example.mlem.Repository;

import android.util.Log;

import com.example.mlem.Model.Blog;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BlogRepository {
    private static final String TAG = BlogRepository.class.getName();
    private static final String collectionPath = "blogs";

    private final FirebaseFirestore firestore;
    private final CollectionReference collectionReference;

    public BlogRepository() {
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(collectionPath);
    }

    public void insert(Blog blog) {
        collectionReference.add(blog)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding", e));
    }

    public void update(Blog blog) {
        collectionReference.document(blog.getId()).set(blog)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Updated successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error updating", e));
    }

    public void delete(Blog blog) {
        collectionReference.document(blog.getId()).delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Deleted successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error deleting", e));
    }

//    public Blog getOne(String id) {
//        final Blog[] blog = {new Blog()};
//        collectionReference.document(id).get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    Blog item = documentSnapshot.toObject(Blog.class);
//                    if (item == null) return;
//                    item.setId(documentSnapshot.getId());
//                    blog[0] = item;
//                })
//                .addOnFailureListener(e -> {
//                    Log.e(TAG, "Error getting one " + id, e);
//                });
//        return blog[0];
//    }

    public Task<DocumentSnapshot> getOne(String id) {
        return collectionReference.document(id).get();
    }

    public Task<QuerySnapshot> getAll() {
        return collectionReference.get();
    }

    public Task<QuerySnapshot> searchByTitle(String queryString) {
        return collectionReference.whereEqualTo("title", queryString).get();
    }

    public Task<QuerySnapshot> searchByTag(String queryString) {
        String[] queryList = queryString.split("\\s+");
        return collectionReference.whereArrayContainsAny("tagNames", Arrays.asList(queryList)).get();
    }
}
