package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Cause implements IE {

	private short cause;

	public Cause() {

	}

	public Cause(short cause) {
		this.cause = cause;
	}

	public short getCause() {
		return cause;
	}

	public void setCause(short cause) {
		this.cause = cause;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.CAUSE;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x02;
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		data[2] = (byte) ((cause >>> 8) & 0xFF);
		data[3] = (byte) ((cause >>> 0) & 0xFF);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier
		// data[1] == length;
		this.cause = (short) ((data[2] << 8) + (data[3] << 0));
	}

}
