package com.telstra.aacc.mls.ie;

public interface IE {

	public byte getIdentifier();

	public byte getLength();

	public byte[] encode();

	public void decode(byte[] buffer) throws Exception;

}
