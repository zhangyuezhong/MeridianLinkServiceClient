package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Meridian1CustomerNumber implements IE {

	private byte customerNumber;

	public Meridian1CustomerNumber() {

	}

	public Meridian1CustomerNumber(byte customerNumber) {
		this.customerNumber = customerNumber;
	}

	public byte getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(byte customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.MERIDIAN_1_CUSTOMER_NUMBER;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), this.getCustomerNumber() };
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier;
		// data[1] = length
		this.customerNumber = data[2];
	}
}
