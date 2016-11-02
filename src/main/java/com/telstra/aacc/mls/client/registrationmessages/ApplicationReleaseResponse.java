package com.telstra.aacc.mls.client.registrationmessages;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import com.telstra.aacc.mls.client.ByteConvertible;
import com.telstra.aacc.mls.ie.Password;
import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationReleaseResponse implements ByteConvertible {

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	private Password password;

	@Override
	public byte[] toByteArray() {
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		IEUtils.writeTo(password, body);
		return body.toByteArray();
	}

	@Override
	public void fromByteArray(byte[] data) throws Exception {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		while (buffer.remaining() >= 2) {
			byte identifer = buffer.get();
			byte length = buffer.get();
			byte count = (byte) (length - 2);
			if (count > 0 && count <= buffer.remaining()) {
				byte[] ie = new byte[count + 2];
				buffer.position(buffer.position() - 2);
				buffer.get(ie);
				this.decodeIE(ie);
			} else {
				this.decodeIE(new byte[] { identifer, length });
			}
		}
	}

	private void decodeIE(byte[] data) throws Exception {
		byte identifer = data[0];
		if (identifer == Identifier.PASSWORD) {
			this.password = new Password();
			this.password.decode(data);
		}
	}
}
