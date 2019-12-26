package com.stsm.service;

import java.util.Date;

import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

public class StaffService {
	/**
	 * 上班打开
	 * @param staff_id
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockIn(int staff_id)
	{
		StaffDao dao = new StaffDao();
		Staff staff = dao.queryStaffByID(staff_id);
		staff.setLastIn(new Date());
		return dao.updataStaff(staff);
	}
	/**
	 * 下班打卡
	 * @param staff_id 员工id
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockOut(int staff_id)
	{
		StaffDao dao = new StaffDao();
		Staff staff = dao.queryStaffByID(staff_id);
		staff.setLastOut(new Date());
		return dao.updataStaff(staff);
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
	
	
//	public static void main(String [] args)
//	{
//		StaffService to = new StaffService();
//		to.staffClockIn(2);
//	}

}
