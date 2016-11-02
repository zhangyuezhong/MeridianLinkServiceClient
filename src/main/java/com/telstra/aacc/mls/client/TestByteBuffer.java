package com.telstra.aacc.mls.client;

import java.nio.ByteBuffer;

public class TestByteBuffer {

	public static void main(String[] args) {
		byte[] value = new byte[] { 3, 3, 3, 3 };
		ByteBuffer buf = ByteBuffer.wrap(value);
		byte[] d = new byte[-3];
		buf.get(d);
	}
}
