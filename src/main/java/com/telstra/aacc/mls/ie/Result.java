package com.telstra.aacc.mls.ie;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class Result implements IE {

	private byte result;

	public Result() {

	}

	public Result(byte result) {
		this.result = result;
	}

	public byte getResult() {
		return result;
	}

	public void setResult(byte result) {
		this.result = result;
	}

	@Override
	public byte getIdentifier() {
		return Identifier.RESULT;
	}

	@Override
	public byte getLength() {
		return 0x02 + 0x01;
	}

	@Override
	public byte[] encode() {
		return new byte[] { this.getIdentifier(), this.getLength(), this.getResult() };
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		// data[0] = identifier
		// data[1] = //length
		this.result = data[2];

	}

}
