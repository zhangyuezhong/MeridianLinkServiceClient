package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class HostName implements IE {

	private static final String DEFAULT_HOSTNAME = "Lanlink";
	private String hostName = DEFAULT_HOSTNAME;

	public HostName() {

	}

	public HostName(String hostName) {
		this.setHostName(hostName);
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = IEUtils.ensureStringLength(hostName, DEFAULT_HOSTNAME, 20);

	}

	@Override
	public byte getIdentifier() {
		return Identifier.HOST_NAME;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + this.getHostName().length());
	}

	@Override
	public byte[] encode() {
		byte[] data = new byte[this.getLength()];
		data[0] = this.getIdentifier();
		data[1] = this.getLength();
		System.arraycopy(this.getHostName().getBytes(), 0, data, 2, data.length - 2);
		return data;
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);
		// data[0] = identifier;
		// data[1] = length;
		this.hostName = IEUtils.subArrayToString(2, data);
		
	}
}
