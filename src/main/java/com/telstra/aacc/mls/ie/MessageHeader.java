package com.telstra.aacc.mls.ie;

import java.nio.ByteBuffer;

import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class MessageHeader implements IE {

	private short messageLength;
	private byte associationID;
	private short messageReferenceID;
	private short messageType;
	private byte reserved = 0x00;

	public short getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(short messageLength) {
		this.messageLength = messageLength;
	}

	public byte getAssociationID() {
		return associationID;
	}

	public void setAssociationID(byte associationID) {
		this.associationID = associationID;
	}

	public short getMessageReferenceID() {
		return messageReferenceID;
	}

	public void setMessageReferenceID(short messageReferenceID) {
		this.messageReferenceID = messageReferenceID;
	}

	public short getMessageType() {
		return messageType;
	}

	public void setMessageType(short messageType) {
		this.messageType = messageType;
	}

	public byte getReserved() {
		return reserved;
	}

	public void setReserved(byte reserved) {
		this.reserved = reserved;
	}

	@Override
	public byte getLength() {
		return 0x0A; // 10
	}

	@Override
	public byte getIdentifier() {
		return Identifier.MESSAGE_HEADER;
	}

	@Override
	public byte[] encode() {
		ByteBuffer buffer = ByteBuffer.allocate(this.getLength());
		buffer.put(this.getIdentifier());
		buffer.put(this.getLength());
		buffer.putShort(this.getMessageLength());
		buffer.put(this.getAssociationID());
		buffer.putShort(this.getMessageReferenceID());
		buffer.putShort(this.getMessageType());
		buffer.put(this.reserved);
		return buffer.array();
	}

	@Override
	public void decode(byte[] data) throws Exception {
		IEUtils.assertIE(this.getIdentifier(), this.getLength(), data);
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.get(); // identifier
		buffer.get(); // length
		this.messageLength = buffer.getShort();
		this.associationID = buffer.get();
		this.messageReferenceID = buffer.getShort();
		this.messageType = buffer.getShort();
		buffer.get(); // reserved;
	}
}
