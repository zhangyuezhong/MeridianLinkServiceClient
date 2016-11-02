package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Sysload implements IE {

	private boolean statusOn;

	public Sysload() {

	}

	public Sysload(boolean statusOn) {
		this.statusOn = statusOn;
	}

	public boolean isStatusOn() {
		return statusOn;
	}

	public void setStatusOn(boolean statusOn) {
		this.statusOn = statusOn;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.SYSLOAD;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), ((this.statusOn) ? (byte) 1 : (byte) 0) };
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier
		// data[1]= length
		this.statusOn = (data[2] == 1);
	}
}
