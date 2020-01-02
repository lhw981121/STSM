package com.stsm.bean;
import java.util.Date;

public class AttenInfo {
	//员工工号
	private String AttenInfo_number;
	//员工姓名
	private String AttenInfo_name;
	//员工上班打卡时间
	private String AttenInfo_in;
	//员工下班打开时间
	private String AttenInfo_out;
	
	public AttenInfo() {
		
	}
	
	public AttenInfo(String AttenInfo_number,String AttenInfo_name,String AttenInfo_in,String AttenInfo_out) {
		this.AttenInfo_number = AttenInfo_number;
		this.AttenInfo_name = AttenInfo_name;
		this.AttenInfo_in = AttenInfo_in;
		this.AttenInfo_out = AttenInfo_out;
	}
	
	public String getAttenInfo_number() {
		return AttenInfo_number;
	}
	
	public void setAttenInfo_number(String attenInfo_number) {
		AttenInfo_number = attenInfo_number;
	}
	
	public String getAttenInfo_name() {
		return AttenInfo_name;
	}
	
	public void setAttenInfo_name(String attenInfo_name) {
		AttenInfo_name = attenInfo_name;
	}
	
	public String getAttenInfo_in() {
		if(AttenInfo_in.length()==0 || AttenInfo_in==null)
		return "未打卡";
		AttenInfo_in = AttenInfo.formatDate1(AttenInfo_in);
		return AttenInfo_in;
	}
	
	public void setAttenInfo_in(String attenInfo_in) {
		AttenInfo_in = attenInfo_in;
	}
	
	public String getAttenInfo_out() {
		if(AttenInfo_out.length()==0 || AttenInfo_out==null)
			return "未打卡";
		AttenInfo_out = AttenInfo.formatDate1(AttenInfo_out);
		return AttenInfo_out;
	}
	
	public void setAttenInfo_out(String attenInfo_out) {
		AttenInfo_out = attenInfo_out;
	}
	
	/**
	     * 标准化时间显示
	     * yyyy-MM-dd HH:mm:ss
	     * @param dateStr
	     * @return
	     */
	 public static String formatDate1(String dateStr) {
		 String[] aStrings = dateStr.split(" ");
		 if(aStrings[1].equals("Jan")) {
			 aStrings[1] = "01";
			}
		if(aStrings[1].equals("Feb")) {
			 aStrings[1] = "02";
			}
		if(aStrings[1].equals("Mar")) {
			 aStrings[1] = "03";
			}
		if(aStrings[1].equals("Apr")) {
			 aStrings[1] = "04";
			}
		if(aStrings[1].equals("May")) {
			 aStrings[1] = "05";
			}
		if(aStrings[1].equals("Jun")) {
			 aStrings[1] = "06";
			}
		if(aStrings[1].equals("Jul")) {
			 aStrings[1] = "07";
			}
		if(aStrings[1].equals("Aug")) {
			 aStrings[1] = "08";
			}
		if(aStrings[1].equals("Sep")) {
			 aStrings[1] = "09";
			}
		if(aStrings[1].equals("Oct")) {
			 aStrings[1] = "10";
			}
		if(aStrings[1].equals("Nov")) {
			 aStrings[1] = "11";
			}
		if(aStrings[1].equals("Dec")) {
			 aStrings[1] = "12";
			}
		 return aStrings[5] + "-" + aStrings[1] + "-" + aStrings[2] + " " + aStrings[3];
	}
	 
	public static void main(String []s)
	{
		AttenInfo info = new AttenInfo("123","湛波","",new Date().toString());
		System.out.println(info.getAttenInfo_in());
	}
}
