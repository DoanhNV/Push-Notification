package main;

import org.json.JSONObject;
import org.riversun.fcm.FcmClient;
import org.riversun.fcm.model.EntityMessage;
import org.riversun.fcm.model.FcmResponse;

public class Application_Library {

	private static final String AUTH_KEY = "xxxxxxxxxxxxxxxxxxx";
	private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	private static final String DEVICE_TOKEN = "dJc6HgnXjVE:APA91bFg0YpdkTsC2dcFyS8w4z4CeQI9xqHziHQR_zKatsTjtcXD1-Expv4RaHB9Qr3dMsBhbzgzvpiPO5zBoWMoQutdejYwi-JIzibJKzCuTrblBx_wSAVaKmdzcob4Xyy3m78hQOtA";

	public static void main(String[] args) {
		FcmClient client = new FcmClient();
		client.setAPIKey(AUTH_KEY);

		EntityMessage message = new EntityMessage();
		message.addRegistrationToken(DEVICE_TOKEN);

		JSONObject data = new JSONObject();
		data.put("name", "doanh");
		message.putData("data", data);

		FcmResponse response = client.pushToEntities(message);
		System.out.println(response);
	}
}
