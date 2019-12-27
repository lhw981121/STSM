package com.stsm.service;

import java.util.Date;

import com.stsm.bean.Atten;
import com.stsm.bean.Staff;
import com.stsm.dao.AttenDao;
import com.stsm.dao.StaffDao;
import org.apache.log4j.Logger;

public class StaffService {
	private Atten atten = new Atten();
	private AttenDao attenDao = new AttenDao();
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 上班打卡，修改考勤表的atten_start_staff为员工id，atten_start_time为打卡时间
	 * @param staff_id 员工id
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockIn(int staff_id)
	{
		StaffDao dao = new StaffDao();
		Staff staff = dao.queryStaffByID(staff_id);
		staff.setLastIn(new Date());
		Atten atten = attenDao.queryAttenByDate(new Date());
		//Atten atten = attenDao.queryAttenByID(4);
		
		String atten_start_staff = atten.getStartStaff()==null?"":atten.getStartStaff();
		String atten_start_time = atten.getStartTime()==null?"":atten.getStartTime();
		
		atten_start_staff += atten.getStartStaff()==null?String.valueOf(staff_id):"_"+staff_id;
		atten_start_time += atten.getStartTime()==null?new Date().toString():"_"+new Date().toString();
		
		System.out.println("atten_start_staff"+atten_start_staff);
		System.out.println("atten_start_time"+atten_start_time);
		atten.setStartStaff(atten_start_staff);
		atten.setStartTime(atten_start_time);
		if(attenDao.updataStaff(atten))
		{
			return dao.updataStaff(staff);
		}else
			return false;
		
	}
	/**
	 * 上班取消打卡
	 * @param staff_id 员工id
	 * @return
	 */
	public boolean staffCancelClockIn(int staff_id)
	{
		return true;
	}
	/**
	 * 下班打卡 修改考勤表的atten_end_staff为员工id，atten_end_time为打卡时间
	 * @param staff_id 员工id 
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockOut(int staff_id)
	{
		StaffDao dao = new StaffDao();
		Staff staff = dao.queryStaffByID(staff_id);
		staff.setLastOut(new Date());
		Atten atten = attenDao.queryAttenByDate(new Date());
		String atten_end_staff = atten.getEndStaff()==null?"":atten.getEndStaff();
		String atten_end_time = atten.getEndTime()==null?"":atten.getEndTime();
		atten_end_staff += atten.getEndStaff()==null?String.valueOf(staff_id):"_"+staff_id;
		atten_end_time += atten.getEndTime()==null?new Date().toString():"_"+new Date().toString();
		
		System.out.println(atten_end_staff);
		System.out.println(atten_end_time);
		
		atten.setEndStaff(atten_end_staff);
		atten.setEndTime(atten_end_time);
		
		if(attenDao.updataStaff(atten)) //修改考勤表信息
		{
			return dao.updataStaff(staff);
		}else
			return false;
	}
	/**
	 * 下班取消打卡 
	 * @param staff_id 员工id
	 * @return
	 */
	public boolean staffCancelClockOut(int staff_id)
	{
		return true;
	}
	/**
	 * 精确到秒的时间
	 * @param date 当前时间
	 * @return
	 */
	public static int getSecondTimestampTwo(Date date){  
	    if (null == date) {  
	        return 0;  
	    }  
	    String timestamp = String.valueOf(date.getTime()/1000);  
	    return Integer.valueOf(timestamp);  
	}  
	
	
	public static void main(String [] args)
	{
		StaffService to = new StaffService();
		to.staffClockOut(6);
	}

}
