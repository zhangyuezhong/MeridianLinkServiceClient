package com.telstra.aacc.mls.client.registrationmessages;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import com.telstra.aacc.mls.client.ByteConvertible;
import com.telstra.aacc.mls.ie.Cause;
import com.telstra.aacc.mls.ie.ProcessID;
import com.telstra.aacc.mls.ie.Redundancy;
import com.telstra.aacc.mls.ie.Result;
import com.telstra.aacc.mls.ie.ServiceIDList;
import com.telstra.aacc.mls.ie.constants.Identifier;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationRegistrationResponse implements ByteConvertible {

	private Result result;
	private ProcessID processID;
	private Cause cause;
	private ServiceIDList serviceIDList;
	private Redundancy redundancy;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public ProcessID getProcessID() {
		return processID;
	}

	public void setProcessID(ProcessID processID) {
		this.processID = processID;
	}

	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	public ServiceIDList getServiceIDList() {
		return serviceIDList;
	}

	public void setServiceIDList(ServiceIDList serviceIDList) {
		this.serviceIDList = serviceIDList;
	}

	public Redundancy getRedundancy() {
		return redundancy;
	}

	public void setRedundancy(Redundancy redundancy) {
		this.redundancy = redundancy;
	}

	@Override
	public byte[] toByteArray() {
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		IEUtils.writeTo(result, body);
		IEUtils.writeTo(processID, body);
		IEUtils.writeTo(cause, body);
		IEUtils.writeTo(serviceIDList, body);
		IEUtils.writeTo(serviceIDList, body);
		IEUtils.writeTo(redundancy, body);
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
		if (identifer == Identifier.RESULT) {
			this.result = new Result();
			this.result.decode(data);
		} else if (identifer == Identifier.PROCESS_ID) {
			this.processID = new ProcessID();
			this.processID.decode(data);
		} else if (identifer == Identifier.CAUSE) {
			this.cause = new Cause();
			this.cause.decode(data);
		} else if (identifer == Identifier.SERVICE_ID_LIST) {
			this.serviceIDList = new ServiceIDList();
			this.serviceIDList.decode(data);
		} else if (identifer == Identifier.REDUNDANCY) {
			this.redundancy = new Redundancy();
			this.redundancy.decode(data);
		}
	}

}
