package com.telstra.aacc.mls.client.registrationmessages;

import java.io.ByteArrayOutputStream;

import com.telstra.aacc.mls.client.ByteConvertible;
import com.telstra.aacc.mls.ie.Password;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationRelease implements ByteConvertible {

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
	public void fromByteArray(byte[] data) {
	}

	public static Builder builder() {
		return new ApplicationRelease.Builder();
	}

	public static class Builder {
		private ApplicationRelease instance;

		public Builder() {
			instance = new ApplicationRelease();
		}

		public Builder password(String password) {
			instance.setPassword(new Password(password));
			return this;
		}
		public ApplicationRelease build() {
			return instance;
		}
	}
}
