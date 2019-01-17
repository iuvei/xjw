package com.gameportal.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

public class MobileLocationUtil {
	private static Logger logger = Logger.getLogger(MobileLocationUtil.class);
	/**
	 * 归属地查询
	 * 
	 * @param mobile
	 * @return mobileAddress
	 */
//	private static String getLocationByMobile(final String mobile)
//			throws ParserConfigurationException, SAXException, IOException {
//		String MOBILEURL = " http://www.youdao.com/smartresult-xml/search.s?type=mobile&q=";
//		String result = callUrlByGet(MOBILEURL + mobile, "GBK");
//		StringReader stringReader = new StringReader(result);
//		InputSource inputSource = new InputSource(stringReader);
//		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//		Document document = documentBuilder.parse(inputSource);
//
//		if (!(document.getElementsByTagName("location").item(0) == null)) {
//			return document.getElementsByTagName("location").item(0).getFirstChild().getNodeValue();
//		} else {
//			return "无此号记录！";
//		}
//	}
	
	/**
	 * 获取手机号码地区
	 * @param mobile
	 * @return
	 */
	public static String getMobileLocation(String mobile) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
		try {
			Pattern pattern = Pattern.compile("1\\d{10}");
			Matcher matcher = pattern.matcher(mobile);
			if (matcher.matches()) {
				PhoneNumber referencePhonenumber = phoneUtil.parse(mobile, "CN");
				String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);
				return city;
			}
			return "未知区域";
		} catch (NumberParseException e) {
			logger.error(e.getMessage());
			return "未知区域";
		}
	}

	/**
	 * 获取URL返回的字符串
	 * 
	 * @param callurl
	 * @param charset
	 * @return
	 */
	private static String callUrlByGet(String callurl, String charset) {
		String result = "";
		try {
			URL url = new URL(callurl);
			URLConnection connection = url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
				result += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	/**
	 * 手机号码归属地
	 * 
	 * @param tel
	 *            手机号码
	 * @return 135XXXXXXXX,联通/移动/电信,湖北武汉
	 * @throws Exception
	 * @author
	 */
//	public static String getMobileLocation(String tel) throws Exception {
//		try{
//			Pattern pattern = Pattern.compile("1\\d{10}");
//			Matcher matcher = pattern.matcher(tel);
//			if (matcher.matches()) {
//				String url = "http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi?chgmobile=" + tel;
//				String result = callUrlByGet(url, "GBK");
//				StringReader stringReader = new StringReader(result);
//				InputSource inputSource = new InputSource(stringReader);
//				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//				Document document = documentBuilder.parse(inputSource);
//				String retmsg = document.getElementsByTagName("retmsg").item(0).getFirstChild().getNodeValue();
//				if (retmsg.equals("OK")) {
//					String supplier = document.getElementsByTagName("supplier").item(0).getFirstChild().getNodeValue()
//							.trim();
//					String province = document.getElementsByTagName("province").item(0).getFirstChild().getNodeValue()
//							.trim();
//					String city = document.getElementsByTagName("city").item(0).getFirstChild().getNodeValue().trim();
//					if (province.equals("-") || city.equals("-")) {
//						// return (tel + "," + supplier + ","+ getLocationByMobile(tel));
//						return (getLocationByMobile(tel) + "," + supplier);
//					} else {
//	
//						// return (tel + "," + supplier + ","+ province + city);
//						return (province + city + "," + supplier);
//					}
//				} else {
//					return "无此号记录！";
//				}
//	
//			} else {
//				return "未知区域";
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return "未知区域";
//		}
//	}

	public static void main(String[] args) {
		try {
			System.out.println(MobileLocationUtil.getMobileLocation("13455553695"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}