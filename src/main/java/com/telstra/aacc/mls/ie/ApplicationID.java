package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationID implements IE {

	private static final String DEFAULT_ID = "app_tool";
	private String id = DEFAULT_ID;

	public ApplicationID() {

	}

	public ApplicationID(String id) {
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = IEUtils.ensureStringLength(id, DEFAULT_ID, 20);
	}

	public byte getLength() {
		return (byte) (0x02 + this.id.length());
	}

	@Override
	public byte getIdentifier() {
		return Identifier.APPLICATION_ID;
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.id.getBytes(), 0, data, 2, data.length - 2);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);
		// data[0] = identifier;
		// data[1] = length;
		this.id = IEUtils.subArrayToString(2, data);
	}

}
