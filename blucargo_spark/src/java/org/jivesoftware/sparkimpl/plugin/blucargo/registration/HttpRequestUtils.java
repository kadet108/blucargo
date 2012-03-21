package org.jivesoftware.sparkimpl.plugin.blucargo.registration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;


public class HttpRequestUtils {

	
	public static String sendPostRequest(String urlStr, Map<String,String> data)
	{
		if( urlStr == null || data == null ) return null;
		
		String response = null;
		try {
			StringBuffer buf = new StringBuffer(64*data.size());
			for( String key : data.keySet() )
			{
				buf.append(URLEncoder.encode(key, "UTF-8"));
				buf.append("=");
				buf.append(URLEncoder.encode(data.get(key), "UTF-8"));
				buf.append("&");
			}

		    URL url = new URL(urlStr);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(buf.toString());
		    wr.flush();

		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    buf = new StringBuffer(32);
		    while ((line = rd.readLine()) != null) {
		        buf.append(line);
		    }
		    wr.close();
		    rd.close();
		    
		    response = buf.toString();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
}