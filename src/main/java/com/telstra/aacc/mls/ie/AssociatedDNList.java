package com.telstra.aacc.mls.ie;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.telstra.aacc.mls.ie.constants.Identifier;

public class AssociatedDNList implements IE {

	private List<DN> dns = new ArrayList<DN>(50);

	public AssociatedDNList() {
	}

	public void add(DN dn) {
		this.dns.add(dn);
	}

	@Override
	public byte getIdentifier() {
		return Identifier.ASSOCIATED_DN_LIST;
	}

	@Override
	public byte getLength() {

		return 0x02;
	}

	@Override
	public byte[] encode() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(this.getIdentifier());
		dos.writeByte(this.getLength());
		dos.writeByte((byte) this.dns.size());
	}

	@Override
	public void decode(byte[] buffer) throws Exception {
		// TODO Auto-generated method stub

	}

	public class DN {
		public byte type;
		private String coding;

		public DN() {

		}

		public DN(byte type, String coding) {
			this.type = type;
			this.coding = coding;
		}

		public byte getType() {
			return type;
		}

		public void setType(byte type) {
			this.type = type;
		}

		public String getCoding() {
			return coding;
		}

		public void setCoding(String coding) {
			this.coding = coding;
		}

	}
}
