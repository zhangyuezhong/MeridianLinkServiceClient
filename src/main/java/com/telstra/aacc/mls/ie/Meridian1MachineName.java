package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Meridian1MachineName implements IE {

	private static final String DEFAULT_MACHINE_NAME = "SL16";
	private String machineName = DEFAULT_MACHINE_NAME;

	public Meridian1MachineName() {

	}

	public Meridian1MachineName(String machineName) {
		this.setMachineName(machineName);
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = IEUtils.ensureStringLength(machineName, DEFAULT_MACHINE_NAME, 20);
	}

	@Override
	public byte getIdentifier() {
		return Identifier.MERIDIAN_1_MACHINE_NAME;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + this.getMachineName().length());
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.getMachineName().getBytes(), 0, data, 2, data.length - 2);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);
		// data[0] = identifier;
		// data[1] = length;
		this.machineName = IEUtils.subArrayToString(2, data);
	}
}
