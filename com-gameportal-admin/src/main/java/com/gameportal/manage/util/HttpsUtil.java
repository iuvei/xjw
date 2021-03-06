package com.gameportal.manage.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.log4j.Logger;

/**
 * Https请求工具类。
 * 
 * @author sum
 *
 */
public class HttpsUtil {
	private static Logger logger = Logger.getLogger(HttpsUtil.class);

	public HttpsUtil(){
		//sun.net.www.protocol.http.HttpURLConnection
	}
	public static String processRequst(String requestUrl, String key) {
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection yc = (HttpsURLConnection)url.openConnection();
			KeyStore ks = KeyStore.getInstance("PKCS12");
			//String keyPath = "E:/ptplay.p12";
			String keyPath = HttpsUtil.class.getResource("/").getPath()+"key/ptplay.p12";
			//keyPath = keyPath.replaceFirst("/", "");
			File file = new File(keyPath);
			FileInputStream fis = new FileInputStream(file);
			ks.load(fis, "iQ3xuZrS".toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, "iQ3xuZrS".toCharArray());
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(kmf.getKeyManagers(), null, null);
			yc.setRequestProperty("X_ENTITY_KEY", key);
			yc.setSSLSocketFactory(sc.getSocketFactory());
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			StringBuffer result = new StringBuffer();
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
			in.close();
			fis.close();
			return result.toString();
		} catch (Exception e) {
			logger.error("HTTPS REQUEST ERROR,请求地址:" + requestUrl, e);
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		String loginname = "dxytest888";
//		String password = "123456";
//		String kisoskname = "DXYTLK";
//		String adminname = "DXYTLA";
//
//		String requestUrl = "https://kioskpublicapi.mightypanda88.com/player/create/playername/" + loginname + "/kioskname/" + kisoskname
//				+ "/adminname/" + adminname + "/password/" + password;
//		System.out.println(requestUrl);
//		System.out.println(processRequst(requestUrl,
//				"a86426036b55bf31c9f17e082c81f8c10807bcacf81a97049849177bc402e0b926edef271a9510526777560f8ba53056843f75a516ca7c74b2290b8c107b4b2a"));
	}
}
