package com.example.helplogic.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Classe de configuração do Firebase para o App.
 */
public class FirebaseConfig {

    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static StorageReference storage;

    /**
     * Retorna a instância do FirebaseAuth.
     *
     * @return
     */
    public static FirebaseAuth getFirebaseAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }

        return auth;
    }

    /**
     * Retorna a instância do FirebaseDatabase.
     *
     * @return
     */
    public static DatabaseReference getFirebaseDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }

        return database;
    }

    //Retorna a instancia do FirebaseStorage
    public static StorageReference getFirebaseStorage() {
        if (storage == null) {
            storage = FirebaseStorage.getInstance().getReference();
        }

        return storage;
    }
}
