package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ProcessID implements IE {

	private int id;

	public ProcessID() {

	}

	public ProcessID(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.PROCESS_ID;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x04;
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		data[2] = (byte) ((this.getId() >>> 24) & 0xFF);
		data[3] = (byte) ((this.getId() >>> 16) & 0xFF);
		data[4] = (byte) ((this.getId() >>> 8) & 0xFF);
		data[5] = (byte) ((this.getId() >>> 0) & 0xFF);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier;
		// data[1] = length;
		this.id = ((data[2] << 24) + (data[3] << 16) + (data[4] << 8) + (data[5] << 0));
	}
}
