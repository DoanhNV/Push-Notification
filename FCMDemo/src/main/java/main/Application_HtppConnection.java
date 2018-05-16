package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Application_HtppConnection {
	private static final String AUTH_KEY = "xxxxxxxxxxxxxxxxxxx";
	private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	private static final String DEVICE_TOKEN = "dJc6HgnXjVE:APA91bFg0YpdkTsC2dcFyS8w4z4CeQI9xqHziHQR_zKatsTjtcXD1-Expv4RaHB9Qr3dMsBhbzgzvpiPO5zBoWMoQutdejYwi-JIzibJKzCuTrblBx_wSAVaKmdzcob4Xyy3m78hQOtA";

	public static void main(String[] args) throws IOException {
		URL url = new URL(FCM_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		JSONObject FCMJson = new JSONObject();
		//FCMJson.put("to", DEVICE_TOKEN);
		JSONObject topic = new JSONObject();
		FCMJson.put("BuBu", "Hello");
		JSONObject jsonData = new JSONObject();
		jsonData.put("message", "thế giới này là của chúng mình!");
		jsonData.put("message_type", 1);
		FCMJson.put("data", jsonData);
		JSONObject notification = new JSONObject();
		notification.put("title", "XWord");
		notification.put("body", "Hello Doanh");
		FCMJson.put("notification", notification);
		
		//
		List<String> list = new ArrayList<String>();
		list.add(DEVICE_TOKEN);
		FCMJson.put("registration_ids", list);
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Language", "en-US");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Authorization", "key="+AUTH_KEY);
		connection.setRequestProperty("Content-Length",String.valueOf(FCMJson.toJSONString().length()));
		
		OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
		outputStream.write(FCMJson.toJSONString());
		outputStream.flush();
		outputStream.close();
		
		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
		String result = "";
		String line = "";
		while((line = reader.readLine()) != null) {
			result += line;
		}
		System.out.println(result);
	}
}
