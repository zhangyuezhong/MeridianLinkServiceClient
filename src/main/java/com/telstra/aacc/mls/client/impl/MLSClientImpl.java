package com.telstra.aacc.mls.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.telstra.aacc.mls.client.ByteConvertible;
import com.telstra.aacc.mls.client.InboundHandler;
import com.telstra.aacc.mls.client.MLSClient;
import com.telstra.aacc.mls.client.StreamParser;

public class MLSClientImpl implements MLSClient, Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MLSClientImpl.class);
	/**
	 * Uses to connect to the server
	 */
	private Socket socket;

	/**
	 * For reading input from server.
	 */
	private InputStream inStream;

	/**
	 * For writing output to server.
	 */
	private OutputStream outStream;

	/**
	 * parser for the inbound stream
	 */
	private StreamParser parser;
	/**
	 * handler for the inbound message
	 */

	private InboundHandler handler;

	/**
	 * Status of client.
	 */
	private boolean connected;

	/**
	 * Port number of server
	 */
	private int port = 3000;
	/**
	 * Host Name or IP address in String form
	 */
	private String hostName = "localhost";

	public MLSClientImpl() {
		this.connected = false;
	}

	public MLSClientImpl(String hostName, int port) {
		this.connected = false;
		this.hostName = hostName;
		this.port = port;

		LOGGER.debug("client created");
	}

	@Override
	public boolean connect() {
		if (!connected) {
			try {
				LOGGER.debug("connecting to the MLS service");
				socket = new Socket(this.hostName, this.port);
				// get I/O from socket
				this.inStream = socket.getInputStream();
				this.outStream = socket.getOutputStream();
				this.connected = true;
				LOGGER.debug("connected");
				// initiate reading from server...
				Thread t = new Thread(this);
				t.start(); // will call run method of this class
			} catch (IOException ex) {
				LOGGER.error(ex.getMessage(), ex);
				this.connected = false;
			}
		}
		return this.connected;
	}

	@Override
	public boolean connect(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
		return this.connect();
	}

	@Override
	public void send(ByteConvertible obj) throws IOException {
		byte[] data = obj.toByteArray();
		this.send(data, 0, data.length);
	}

	public void send(byte[] data) throws IOException {
		this.send(data, 0, data.length);
	}

	public void send(byte[] data, int offset, int length) throws IOException {
		if (connected) {
			outStream.write(data, offset, data.length);
			outStream.flush();
		} else
			throw new IOException("Not connected to server");
	}

	@Override
	public void disconnect() {
		if (socket != null && connected) {
			try {
				socket.close();
			} catch (IOException ioe) {
				// unable to close, nothing to do...
			} finally {
				this.connected = false;
			}
		}

	}

	@Override
	public boolean isConnected() {
		return this.connected;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;

	}

	@Override
	public String getHostName() {
		return hostName;
	}

	@Override
	public void setHostName(String hostName) {
		this.hostName = hostName;

	}

	@Override
	public void setInboundHandler(InboundHandler handler) {
		this.handler = handler;

	}

	@Override
	public void setStreamParser(StreamParser parser) {
		this.parser = parser;
	}

	@Override
	public void run() {
		try {
			while (connected) {
				LOGGER.debug("reading data");
				Object data = parser.parse(inStream);
				handler.messageReceived(data);
			}
		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(), ioe);
		} finally {
			LOGGER.debug("connected=false");
			connected = false;
		}
	}

}