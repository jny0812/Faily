package Project.Projectspring.chatex.Firebase.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FirebaseInit {

    private static final String path = "fb-faily-firebase-adminsdk-sxu9i-97ad8f9dc0.json";

    @PostConstruct
    public void init(){
        try{
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())).build();

            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
