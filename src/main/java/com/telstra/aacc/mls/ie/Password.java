package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Password implements IE {

	private static final String DEFAULT_PASSOWRD = "pass";
	/**
	 * max length 16, minimum 4
	 */
	private String password;

	public Password() {

	}

	public Password(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = IEUtils.ensureStringLength(password, DEFAULT_PASSOWRD, 16);
	}

	@Override
	public byte getIdentifier() {
		return Identifier.PASSWORD;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + this.getPassword().length());
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.getPassword().getBytes(), 0, data, 2, data.length - 2);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);
		// data[0] = identifier;
		// data[1] = length;
		this.password = IEUtils.subArrayToString(2, data);
	}

}
