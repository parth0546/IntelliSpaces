package AsyncTask;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ServiceHandler {

	String response = null;
	public final static int GET = 1;
	public final static int POST = 2;
 
	public ServiceHandler() {

	}

	/**
	 * Making service call
	 * @url - url to make request
	 * @method - http request method
	 * */
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}

	/**
	 * Making service call
	 * 
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	public String makeServiceCall(String url, int method, String params) {
			try {
				// http client
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				final HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
				httpClient=new DefaultHttpClient(httpParams);
				// Checking http request method type
				if (method == POST) {
					HttpPost httpPost = new HttpPost(url);
					// adding post params
					if (params != null) {
						StringEntity se = new StringEntity(params, "UTF8"); 
						se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
								"application/json"));
						httpPost.setEntity(se);
					}

					httpResponse = httpClient.execute(httpPost);

				} else if (method == GET) {
					// appending params to url
					// if (jsonParams != null) {
					// String paramString = URL.format(jsonParams, "utf-8");
					// url += "?" + paramString;
					// }
					HttpGet httpGet = new HttpGet(url);
					httpResponse = httpClient.execute(httpGet);
				}
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			}
			return response;

	}

}
