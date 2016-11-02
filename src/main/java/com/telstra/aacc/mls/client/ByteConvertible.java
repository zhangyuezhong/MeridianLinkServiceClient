package com.telstra.aacc.mls.client;

public interface ByteConvertible {

	public byte[] toByteArray();

	public void fromByteArray(byte[] data) throws Exception;
}
