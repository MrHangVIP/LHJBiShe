package com.lhj.beans;

import java.io.Serializable;

public class StatusBean implements Serializable {
	
	private int id;
	//0���ã�1ʹ���У�2ά���У�3�Ѿ�����
	private int statusId;
	
	private int vehcileId;
	
	private String updateTime;
	
	private String statusType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getVehcileId() {
		return vehcileId;
	}

	public void setVehcileId(int vehcileId) {
		this.vehcileId = vehcileId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	
}
