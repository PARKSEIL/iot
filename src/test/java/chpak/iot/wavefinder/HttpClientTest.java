package chpak.iot.wavefinder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import chpak.iot.wavefinder.core.constants.MqttClientConstants;

public class HttpClientTest {

	public static void main(String[] args) {
//		String url = "http://localhost:8088/tag/twitt";
		String url = "http://chpak.herokuapp.com/tag";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", "TEST");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		try {
			urlParameters.add(new BasicNameValuePair(MqttClientConstants.TOPIC_KEY, "tag/twitt/brexit"));
			urlParameters.add(new BasicNameValuePair(MqttClientConstants.MESSAGE_KEY, "British gone out EU "+dateFormat.format(calendar.getTime())));
			urlParameters.add(new BasicNameValuePair(MqttClientConstants.CLIENT_ID_KEY, "heracul"));
			urlParameters.add(new BasicNameValuePair(MqttClientConstants.QOS_KEY, "0"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);
			System.out.println("Response Code : " 
		                + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
