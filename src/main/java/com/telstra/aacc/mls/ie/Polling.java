package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Polling implements IE {

	/**
	 * This is a 1-octet integer number in 10-second units is the Polling
	 * Timeout Interval. For example, a value of 0x0A is equal to 100 seconds.
	 * Values: 1 (10 seconds)–60 (600 seconds = 10 minutes), represented in hex.
	 */
	private byte timeoutInterval;

	public Polling() {

	}

	public Polling(byte timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	public byte getTimeoutInterval() {
		return timeoutInterval;
	}

	public void setTimeoutInterval(byte timeoutInterval) {
		this.timeoutInterval = timeoutInterval;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.POLLING;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), this.getTimeoutInterval() };
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier
		// data[1] = length
		this.timeoutInterval = data[2];
	}
}
