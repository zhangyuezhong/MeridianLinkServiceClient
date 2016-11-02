package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationProtocol implements IE {

	private byte protocol = 0x01;

	public ApplicationProtocol() {

	}

	public ApplicationProtocol(byte protocol) {
		this.protocol = protocol;
	}

	public byte getProtocol() {
		return protocol;
	}

	public void setProtocol(byte protocol) {
		this.protocol = protocol;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.APPLICTION_PROTOCOL;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), this.getProtocol() };
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] == identifier
		// data[1] == length
		this.protocol = data[2];
	}
}
