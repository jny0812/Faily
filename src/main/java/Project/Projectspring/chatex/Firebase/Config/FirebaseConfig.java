package Project.Projectspring.chatex.Firebase.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Configuration
public class FirebaseConfig {

    private FirebaseApp firebaseApp;

    @PostConstruct
    public void init() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/faily-e8a47-firebase-adminsdk-1mx32-920437b2ea.json");

        FirebaseOptions
                options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        firebaseApp = FirebaseApp.initializeApp(options);
        log.info("Firebase application has been initialized");

    }

    @Bean
    public FirebaseAuth initFirebaseAuth() {
        return FirebaseAuth.getInstance(firebaseApp);
    }

    @Bean
    public FirebaseMessaging initFirebaseMessaging() {
        return FirebaseMessaging.getInstance(firebaseApp);
    }


}
