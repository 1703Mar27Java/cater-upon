package com.revature.util.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Util {

	private static final String GEO_CODE_SERVER = "http://maps.googleapis.com/maps/api/geocode/json?";

	public double DistanceBetweenTwoZipcodes(int source, int destination) {

		String response = getLocation(Integer.toString(source));
		String response2 = getLocation(Integer.toString(destination));

		String[] result = parseLocation(response);
		String[] result2 = parseLocation(response2);

		return haversine(Double.valueOf(result[0]), Double.valueOf(result[1]), Double.valueOf(result2[0]),
				Double.valueOf(result2[1]));
	}

	public double DistanceBetweenTwoCity(String source, String destination) {

		String response = getLocation(source);
		String response2 = getLocation(destination);

		String[] result = parseLocation(response);
		String[] result2 = parseLocation(response2);

		return haversine(Double.valueOf(result[0]), Double.valueOf(result[1]), Double.valueOf(result2[0]),
				Double.valueOf(result2[1]));
	}

	public static double haversine(double lat1, double lng1, double lat2, double lng2) {
		int r = 6371; // average radius of the earth in km
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = r * c;
		return d;
	}

	private static String getLocation(String code) {
		String address = buildUrl(code);

		String content = null;

		try {
			URL url = new URL(address);

			InputStream stream = url.openStream();

			try {
				int available = stream.available();

				byte[] bytes = new byte[available];

				stream.read(bytes);

				content = new String(bytes);
			} finally {
				stream.close();
			}

			return content.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String buildUrl(String code) {
		StringBuilder builder = new StringBuilder();

		builder.append(GEO_CODE_SERVER);

		builder.append("address=");
		builder.append(code.replaceAll(" ", "+"));
		builder.append("&sensor=false");

		return builder.toString();
	}

	private static String[] parseLocation(String response) {
		String[] lines = response.split("\n");

		String lat = null;
		String lng = null;

		for (int i = 0; i < lines.length; i++) {
			if ("\"location\" : {".equals(lines[i].trim())) {
				lat = getOrdinate(lines[i + 1]);
				lng = getOrdinate(lines[i + 2]);
				break;
			}
		}

		return new String[] { lat, lng };
	}

	private static String getOrdinate(String s) {
		String[] split = s.trim().split(" ");

		if (split.length < 1)
			return null;

		String ord = split[split.length - 1];

		if (ord.endsWith(","))
			ord = ord.substring(0, ord.length() - 1);
		
		Double.parseDouble(ord);
		return ord;
	}
}
