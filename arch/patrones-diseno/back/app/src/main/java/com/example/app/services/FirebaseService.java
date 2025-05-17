package com.example.app.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Service
public class FirebaseService {

    private boolean initialized = false;

    @PostConstruct
    public void init() {
        if (!initialized) {
            try (InputStream serviceAccount =
                         getClass().getClassLoader().getResourceAsStream("credenciales.json")) {
                if (serviceAccount == null) {
                    throw new FileNotFoundException("No se encontró credenciales.json en resources");
                }
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://act11-fdcb7-default-rtdb.firebaseio.com/")
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                    FirebaseApp.initializeApp(options);
                }
                initialized = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, Object>> getFormFields(String role) {
        List<Map<String, Object>> fields = new ArrayList<>();
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("forms/" + role.toLowerCase());

            CountDownLatch latch = new CountDownLatch(1);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        Map<String, Object> field = (Map<String, Object>) child.getValue();
                        fields.add(field);
                    }
                    latch.countDown();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    latch.countDown();
                }
            });
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fields;
    }
}

