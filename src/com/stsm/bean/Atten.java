package com.stsm.bean;

import java.util.Date;

public class Atten {
	//考勤主键
	private int ID; 
	//考勤日期
	private Date date; 
	//考勤时间段
	private String period; 
	//考勤记录(开始,员工)
	private String startStaff;
	//考勤记录(开始,时间)
	private String startTime; 
	//考勤记录(结束,员工)
	private String endStaff; 
	//考勤记录(结束,时间)
	private String endTime;
	//添加时间
	private Date created_at;
	//修改时间
	private Date updated_at;
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getStartStaff() {
		return startStaff;
	}
	
	public void setStartStaff(String startStaff) {
		this.startStaff = startStaff;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndStaff() {
		return endStaff;
	}
	
	public void setEndStaff(String endStaff) {
		this.endStaff = endStaff;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Date getCreated() {
		return created_at;
	}
	
	public void setCreated(Date created_at) {
		this.created_at = created_at;
	}
	
	public Date getUpdated() {
		return updated_at;
	}
	
	public void setUpdated(Date updated_at) {
		this.updated_at = updated_at;
	}
}
