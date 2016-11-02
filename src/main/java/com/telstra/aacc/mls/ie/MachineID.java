package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class MachineID implements IE {

	private static final String DEFAULT_MACHINE_ID = "";

	private String id;

	public MachineID() {

	}

	public MachineID(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = IEUtils.ensureStringLength(id, DEFAULT_MACHINE_ID, 20);
	}

	@Override
	public byte getIdentifier() {
		return Identifier.MACHINE_ID;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + this.getId().length());
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.getId().getBytes(), 0, data, 2, data.length - 2);
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
