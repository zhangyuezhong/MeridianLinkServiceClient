package com.telstra.aacc.mls.ie.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.telstra.aacc.mls.ie.IE;

public class IEUtils {

	public static String ensureStringLength(String value, String defaultValue, int maxLength) {
		String newValue = (value == null) ? defaultValue : value;
		if (newValue.length() > maxLength) {
			newValue = newValue.substring(0, maxLength);
		}
		return newValue;
	}

	public static void writeTo(IE ie, OutputStream outStream) {
		if (ie != null && outStream != null) {
			try {
				outStream.write(ie.encode());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeTo(byte[] data, OutputStream outStream) {
		if (data != null && outStream != null) {
			try {
				outStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeTo(ByteArrayOutputStream out, OutputStream outStream) {
		if (out != null && outStream != null) {
			try {
				outStream.write(out.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String toHex(byte[] a) {
		if (a != null && a.length > 0) {
			StringBuilder sb = new StringBuilder(a.length * 2);
			for (byte b : a) {
				sb.append(String.format("%02x", b & 0xff)).append(' ');
			}
			return sb.toString();
		}
		return "";
	}

	public static String toHex(byte b) {
		return String.format("%02x", b & 0xff);
	}

	public static void assertIE(byte identifier, byte expectedLength, byte[] data) throws Exception {
		if (data == null) {
			String message = String.format("Data can not be null. :expectedIdentifier=%s", IEUtils.toHex(identifier));
			throw new Exception(message);
		}
		if (data.length < 2) {
			String message = String.format("Data is insufficient. :expectedIdentifier=%s :data=%s",
					IEUtils.toHex(identifier), IEUtils.toHex(data));
			throw new Exception(message);
		}
		if (data[0] != identifier) {
			String message = String.format("Expected IE Identifier not found. :expectedIdentifier=%s :data=%s",
					IEUtils.toHex(identifier), IEUtils.toHex(data));
			throw new Exception(message);
		}
		if (data[1] != data.length) {
			String message = String.format("Data Length not equals to IE Length. :expectedIdentifier=%s :data=%s",
					IEUtils.toHex(identifier), IEUtils.toHex(data));
			throw new Exception(message);
		}
		if (expectedLength != -1 && expectedLength != data.length) {
			String message = String.format("Data is insufficient. :expectedLength=%d :expectedIdentifier=%s :data=%s",
					expectedLength, IEUtils.toHex(identifier), IEUtils.toHex(data));
			throw new Exception(message);
		}
	}

	public static String subArrayToString(int offset, byte[] data) {
		if (data != null && data.length > offset) {
			return new String(data, offset, data.length - offset);
		} else {
			return "";
		}
	}
}
