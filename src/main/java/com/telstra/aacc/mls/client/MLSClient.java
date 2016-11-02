package com.telstra.aacc.mls.client;

import java.io.IOException;

import com.telstra.aacc.mls.client.impl.MLSClientImpl;

public interface MLSClient {

	public boolean connect();

	public boolean connect(String hostName, int port);

	public void send(byte[] data) throws IOException;

	public void send(byte[] data, int offset, int length) throws IOException;

	public void send(ByteConvertible obj) throws IOException;

	public void disconnect();

	public boolean isConnected();

	public int getPort();

	public void setPort(int port);

	public String getHostName();

	public void setHostName(String hostName);

	public void setInboundHandler(InboundHandler handler);

	public void setStreamParser(StreamParser parser);

	public static MLSClient create() {
		return new MLSClientImpl();
	}

	public static MLSClient create(String hostName, int port) {
		return new MLSClientImpl(hostName, port);
	}

}
