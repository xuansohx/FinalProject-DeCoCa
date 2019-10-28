package Final.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Component;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

@Component
public class FcmUtil {
	//본인의 json 파일 경로 입력
	public void send_FCM(String tokenId, String title,String content){
		try {
			FileInputStream refreshToken = new FileInputStream("C:\\kh\\new\\new\\FinalProject\\DeCoCa\\DecocarWeb\\web\\fcmtest2-9108c-firebase-adminsdk-5iy7t-07401a6ffd.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(refreshToken))
					  .setDatabaseUrl("https://fcmtest2-9108c.firebaseio.com")
					  .build();
			//firebase 처음 호출시에만 initializing 처리
			if(FirebaseApp.getApps().isEmpty()){
				FirebaseApp.initializeApp(options);
			}
			String registrationToken = tokenId;
			Message msg = Message.builder()
						.setAndroidConfig(AndroidConfig.builder()
								.setTtl(3600*1000)
								.setPriority(AndroidConfig.Priority.NORMAL)
								//.setNotification(AndroidNotification.builder()
								//.setTitle(title)
								//.setBody(content)
								//.setIcon("stock_ticker_update")
								//.setColor("#f45342")
								//.build())
								.build())
						.putData("title",title)
						.putData("body",content)
						.setToken(registrationToken)
						.build();
			//메세지를 firebasemessaging에 보내기
			
			String response = FirebaseMessaging.getInstance().send(msg);
			//결과 출력
			System.out.println("successfully sent message:"+response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
