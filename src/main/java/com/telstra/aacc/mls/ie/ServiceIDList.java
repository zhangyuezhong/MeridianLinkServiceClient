package com.telstra.aacc.mls.ie;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ServiceIDList implements IE {

	private List<Byte> services = new ArrayList<Byte>();

	public ServiceIDList() {

	}

	public ServiceIDList(byte[] service_ids) {
		this.add(service_ids);
	}

	public void add(byte service_id) {
		this.services.add(service_id);
	}

	public void add(byte[] service_ids) {
		if (service_ids != null) {
			for (byte service_id : service_ids) {
				this.services.add(service_id);
			}
		}
	}

	public void remove(byte service_id) {
		this.services.remove((byte) service_id);
	}

	public void clear() {
		this.services.clear();
	}

	@Override
	public byte getIdentifier() {
		return Identifier.SERVICE_ID_LIST;
	}

	@Override
	public byte getLength() {
		return (byte) (0x02 + 0x01 + (services.size() * 2));
	}

	@Override
	public byte[] encode() {
		ByteBuffer buffer = ByteBuffer.allocate(this.getLength());
		buffer.put(this.getIdentifier());
		buffer.put(this.getLength());
		buffer.put((byte) this.services.size());
		for (byte service_id : services) {
			buffer.put((byte) 0x01);
			buffer.put(service_id);
		}
		return buffer.array();
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), (byte) -1, data);

		// data[0] = identifier
		// data[1]= length
		byte numberOfServices = data[2];
		int expectedTotalLength = 3 + (numberOfServices * 2);
		int index = 3;
		if (data.length == expectedTotalLength) {
			for (byte i = 0; i < numberOfServices; i++) {
				// byte lengthOfService = data[index++];
				index++;
				byte service_id = data[index++];
				this.add(service_id);
			}
		}

	}
}
