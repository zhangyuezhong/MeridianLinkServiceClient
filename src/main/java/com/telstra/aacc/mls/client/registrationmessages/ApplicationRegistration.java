package com.telstra.aacc.mls.client.registrationmessages;

import java.io.ByteArrayOutputStream;

import com.telstra.aacc.mls.client.ByteConvertible;
import com.telstra.aacc.mls.ie.ApplicationID;
import com.telstra.aacc.mls.ie.ApplicationProtocol;
import com.telstra.aacc.mls.ie.HostName;
import com.telstra.aacc.mls.ie.Meridian1CustomerNumber;
import com.telstra.aacc.mls.ie.Meridian1MachineName;
import com.telstra.aacc.mls.ie.MeridianMailName;
import com.telstra.aacc.mls.ie.Password;
import com.telstra.aacc.mls.ie.Polling;
import com.telstra.aacc.mls.ie.ProcessID;
import com.telstra.aacc.mls.ie.Redundancy;
import com.telstra.aacc.mls.ie.ServiceID;
import com.telstra.aacc.mls.ie.ServiceIDList;
import com.telstra.aacc.mls.ie.Sysload;
import com.telstra.aacc.mls.ie.utils.IEUtils;

public class ApplicationRegistration implements ByteConvertible {

	private ApplicationProtocol applicationProtocol;
	private ApplicationID applicationID;
	private HostName hostName;
	private ServiceIDList serviceIDList;
	private ServiceID serviceID;
	private Meridian1MachineName meridian1MachineName;
	private Meridian1CustomerNumber meridian1CustomerNumber;
	private MeridianMailName meridianMailName;
	private Redundancy redundancy;
	private ProcessID processID;
	private Password password;
	private Polling polling;
	private Sysload sysload;

	public ApplicationProtocol getApplicationProtocol() {
		return applicationProtocol;
	}

	public void setApplicationProtocol(ApplicationProtocol applicationProtocol) {
		this.applicationProtocol = applicationProtocol;
	}

	public ApplicationID getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(ApplicationID applicationID) {
		this.applicationID = applicationID;
	}

	public HostName getHostName() {
		return hostName;
	}

	public void setHostName(HostName hostName) {
		this.hostName = hostName;
	}

	public ServiceIDList getServiceIDList() {
		return serviceIDList;
	}

	public void setServiceIDList(ServiceIDList serviceIDList) {
		this.serviceIDList = serviceIDList;
	}

	public ServiceID getServiceID() {
		return serviceID;
	}

	public void setServiceID(ServiceID serviceID) {
		this.serviceID = serviceID;
	}

	public Meridian1MachineName getMeridian1MachineName() {
		return meridian1MachineName;
	}

	public void setMeridian1MachineName(Meridian1MachineName meridian1MachineName) {
		this.meridian1MachineName = meridian1MachineName;
	}

	public Meridian1CustomerNumber getMeridian1CustomerNumber() {
		return meridian1CustomerNumber;
	}

	public void setMeridian1CustomerNumber(Meridian1CustomerNumber meridian1CustomerNumber) {
		this.meridian1CustomerNumber = meridian1CustomerNumber;
	}

	public MeridianMailName getMeridianMailName() {
		return meridianMailName;
	}

	public void setMeridianMailName(MeridianMailName meridianMailName) {
		this.meridianMailName = meridianMailName;
	}

	public Redundancy getRedundancy() {
		return redundancy;
	}

	public void setRedundancy(Redundancy redundancy) {
		this.redundancy = redundancy;
	}

	public ProcessID getProcessID() {
		return processID;
	}

	public void setProcessID(ProcessID processID) {
		this.processID = processID;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public Polling getPolling() {
		return polling;
	}

	public void setPolling(Polling polling) {
		this.polling = polling;
	}

	public Sysload getSysload() {
		return sysload;
	}

	public void setSysload(Sysload sysload) {
		this.sysload = sysload;
	}

	@Override
	public byte[] toByteArray() {
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		IEUtils.writeTo(applicationProtocol, body);
		IEUtils.writeTo(applicationID, body);
		IEUtils.writeTo(hostName, body);
		IEUtils.writeTo(serviceIDList, body);
		IEUtils.writeTo(serviceID, body);
		IEUtils.writeTo(meridian1MachineName, body);
		IEUtils.writeTo(meridian1CustomerNumber, body);
		IEUtils.writeTo(meridianMailName, body);
		IEUtils.writeTo(redundancy, body);
		IEUtils.writeTo(processID, body);
		IEUtils.writeTo(password, body);
		IEUtils.writeTo(polling, body);
		IEUtils.writeTo(sysload, body);
		return body.toByteArray();
	}

	@Override
	public void fromByteArray(byte[] data) {

	}

	public static Builder builder() {
		return new ApplicationRegistration.Builder();
	}

	public static class Builder {
		private ApplicationRegistration instance = new ApplicationRegistration();

		public Builder() {
			instance = new ApplicationRegistration();
			instance.setApplicationProtocol(new ApplicationProtocol());
			instance.setApplicationID(new ApplicationID());
			instance.setHostName(new HostName());
		}

		public Builder applicationProtocol(byte protocol) {
			instance.getApplicationProtocol().setProtocol(protocol);
			return this;
		}

		public Builder applicationID(String id) {
			instance.getApplicationID().setId(id);
			return this;
		}

		public Builder hostName(String hostName) {
			instance.getHostName().setHostName(hostName);
			return this;
		}

		public Builder serviceIDList(byte[] service_ids) {
			instance.setServiceID(null);
			instance.setServiceIDList(new ServiceIDList(service_ids));
			return this;
		}

		public Builder serviceID(byte service_id) {
			instance.setServiceIDList(null);
			instance.setServiceID(new ServiceID(service_id));
			return this;
		}

		public Builder machineName(String machineName) {
			instance.setMeridian1MachineName(new Meridian1MachineName(machineName));
			return this;
		}

		public Builder customerNumber(byte customerNumber) {
			instance.setMeridian1CustomerNumber(new Meridian1CustomerNumber(customerNumber));
			return this;
		}

		public Builder mailName(String mailName) {
			instance.setMeridianMailName(new MeridianMailName(mailName));
			return this;
		}

		public Builder redundancy(byte mode) {
			instance.setRedundancy(new Redundancy(mode));
			return this;
		}

		public Builder processID(int id) {
			instance.setProcessID(new ProcessID(id));
			return this;
		}

		public Builder password(String password) {
			instance.setPassword(new Password(password));
			return this;
		}

		public Builder pollingTimeoutInterval(byte interval) {
			instance.setPolling(new Polling(interval));
			return this;
		}

		public Builder sysload(boolean statusOn) {
			instance.setSysload(new Sysload(statusOn));
			return this;
		}

		public ApplicationRegistration build() {
			return instance;
		}
	}
}
