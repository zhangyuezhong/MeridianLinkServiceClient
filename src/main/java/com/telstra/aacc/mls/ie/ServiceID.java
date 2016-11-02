package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ServiceID implements IE {

	private byte serviceId;

	public ServiceID() {

	}

	public ServiceID(byte serviceId) {
		this.serviceId = serviceId;
	}

	public byte getServiceId() {
		return serviceId;
	}

	public void setServiceId(byte serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.SERVICE_ID;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), this.getServiceId() };
	}

	@Override
	public void decode(byte[] data) throws Exception{
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier
		// data[1] = //length
		this.serviceId = data[2];

	}

}
