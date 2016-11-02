package com.telstra.aacc.mls.client.impl;

import java.io.IOException;
import java.io.InputStream;

import com.telstra.aacc.mls.client.StreamParser;

public class RecordParser implements StreamParser {
	@Override
	public Object parse(InputStream stream) throws IOException {
		byte[] header = new byte[10];
		int count = 0;

		count = stream.read(header, 0, 10);

		int length = (header[2] << 8) + header[3];

		byte[] data = new byte[length - 10];
		count = stream.read(data, 0, length);

		System.out.println("count=" + count);
		// parse to object;

		return null;
	}
}
