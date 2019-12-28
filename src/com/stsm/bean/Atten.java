package com.stsm.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.stsm.util.COMUtil;

public class Atten {
	//考勤主键
	private int ID;
	//考勤日期
	private Date date;
	//工作时间段
	private String period;
	//当前是否为考勤时间段 0(否) 1(上班考勤时间段) 2(下班考勤时间段)
	private int state;
	//当前是否为工作时间
	private boolean isWork;
	//今日考勤是否已开始
	private boolean isStart;
	//上班考勤是否已结束
	private boolean isClockInEnd;
	//今日考勤是否已结束
	private boolean isEnd;
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
	
	//默认工作时间段
	public static String default_period = "9:00-18:00";
	
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
	
	/**
	* 获取当前时间是否为考勤时间段 
	* @return int 0(否) 1(上班考勤时间段) 2(下班考勤时间段)
	*/
	public int getState() throws ParseException {
		String inTime = period.split("-")[0];
		String outTime = period.split("-")[1];
		String format = "HH:mm";
        Date nowTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date()));
        //判断是否为上班打卡时间段
        Date endTime = new SimpleDateFormat(format).parse(inTime);
        Date startTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date(endTime.getTime() - 1000*60*31)));
        if(COMUtil.belongPeriod(nowTime,startTime,endTime)) {
        	state = 1;
        }
        //判断是否为下班打卡时间段
        startTime = new SimpleDateFormat(format).parse(outTime);
        startTime = new Date(startTime.getTime() - 1000*60*1);
        endTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date(startTime.getTime() + 1000*60*31)));
        if(COMUtil.belongPeriod(nowTime,startTime,endTime)) {
        	state = 2;
        }
		return state;
	}
	
	/**
	* 获取当前时间是否为工作时间段
	* @return boolean 
	*/
	public boolean getIsWork() throws ParseException {
		String inTime = period.split("-")[0];
		String outTime = period.split("-")[1];
		String format = "HH:mm";
        Date nowTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date()));
        //判断是否为工作时间段
        Date startTime = new SimpleDateFormat(format).parse(inTime);
        startTime = new Date(startTime.getTime() - 1000*60*1);
        Date endTime = new SimpleDateFormat(format).parse(outTime);
        isWork = COMUtil.belongPeriod(nowTime,startTime,endTime);
		return isWork;
	}
	
	/**
	* 获取今日考勤是否已开始
	* @return boolean 是否已开始
	 * @throws ParseException 
	*/
	public boolean getIsStart() throws ParseException {
		String inTime = period.split("-")[0];
		String format = "HH:mm";
		Date nowTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date()));
        Date endTime = new SimpleDateFormat(format).parse(inTime);
        Date startTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date(endTime.getTime() - 1000*60*31)));
		Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar end = Calendar.getInstance();
        end.setTime(startTime);
        isStart = date.after(end);
        return isStart;
	}
	
	/**
	* 获取上班考勤是否已结束
	* @return boolean 是否已结束
	 * @throws ParseException 
	*/
	public boolean getIsClockInEnd() throws ParseException {
		String inTime = period.split("-")[0];
		String format = "HH:mm";
		Date nowTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date()));
		Date endTime = new SimpleDateFormat(format).parse(inTime);
		endTime = new Date(endTime.getTime() - 1000*60*1);
		Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        isClockInEnd = date.after(end);
        return isClockInEnd;
	}
	
	/**
	* 获取今日考勤是否已结束
	* @return boolean 是否已结束
	 * @throws ParseException 
	*/
	public boolean getIsEnd() throws ParseException {
		String outTime = period.split("-")[1];
		String format = "HH:mm";
		Date nowTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date()));
		Date startTime = new SimpleDateFormat(format).parse(outTime);
		startTime = new Date(startTime.getTime() - 1000*60*1);
		Date endTime = new SimpleDateFormat(format).parse(COMUtil.dataToTime(new Date(startTime.getTime() + 1000*60*31)));
		Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        isEnd = date.after(end);
        return isEnd;
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
