package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class MeridianMailName implements IE {

	private static final String DEFAULT_MAIL_NAME = "MeridianMail";
	private String mailName = DEFAULT_MAIL_NAME;

	public MeridianMailName() {

	}

	public MeridianMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = IEUtils.ensureStringLength(mailName, DEFAULT_MAIL_NAME, 20);
	}

	@Override
	public byte getIdentifier() {
		return Identifier.MERIDIAN_MAIL_NAME;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + this.getMailName().length());
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.getMailName().getBytes(), 0, data, 2, data.length - 2);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);
		// data[0] = identifier;
		// data[1] = length;
		this.mailName = IEUtils.subArrayToString(2, data);
	}

}
