package com.stsm.service;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import com.stsm.bean.Atten;
import com.stsm.bean.Staff;
import com.stsm.dao.AttenDao;
import com.stsm.dao.StaffDao;
import com.stsm.util.COMUtil;

import org.apache.log4j.Logger;

public class StaffService {
	private Staff staff = new Staff();
	private StaffDao StaffDao = new StaffDao();
	private Logger logger = Logger.getLogger(getClass());
	/**
	 * 上班打卡，修改考勤表的atten_start_staff为员工id，atten_start_time为打卡时间
	 * @param staff_id 员工id
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockIn(int staff_id)
	{
		Atten atten = new AttenService().getTodayStaff();
		try {
			if(atten.getIsClockInEnd()) {return false;}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AttenDao attenDao = new AttenDao();
		
		staff = StaffDao.queryStaffByID(staff_id);
		staff.setLastIn(new Date());
		//当日考勤信息
		atten = new AttenService().getTodayStaff();
		//atten_start_staff为员工id，atten_start_time为打卡时间
		String atten_start_staff = COMUtil.isNull(atten.getStartStaff())?"":atten.getStartStaff();
		String atten_start_time = COMUtil.isNull(atten.getStartTime())?"":atten.getStartTime();
		
		atten_start_staff += COMUtil.isNull(atten.getStartStaff())?String.valueOf(staff_id):"_"+staff_id;
		atten_start_time += COMUtil.isNull(atten.getStartTime())?new Date().toString():"_"+new Date().toString();	
		
		atten.setStartStaff(atten_start_staff);
		atten.setStartTime(atten_start_time);
		if(attenDao.updataStaff(atten)&&StaffDao.updataStaff(staff))
		{
			logger.info("用户："+staff_id+"上班时间打卡,成功");
			return true;
		}else {
			logger.error("用户："+staff_id+"上班时间打卡,失败");
			return false;
		}
		
	}
	/**
	 * 下班打卡 修改考勤表的atten_end_staff为员工id，atten_end_time为打卡时间
	 * @param staff_id 员工id 
	 * @return true：成功 false ：失败
	 */
	public boolean staffClockOut(int staff_id)
	{
		Atten atten = new AttenService().getTodayStaff();
		try {
			if(atten.getIsEnd()) {return false;}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AttenDao attenDao = new AttenDao();
		
		staff = StaffDao.queryStaffByID(staff_id);
		staff.setLastOut(new Date());
		//当日考勤信息
		atten = new AttenService().getTodayStaff();
		//atten_end_staff为员工id，atten_end_time为打卡时间
		String atten_end_staff = COMUtil.isNull(atten.getEndStaff())?"":atten.getEndStaff();
		String atten_end_time = COMUtil.isNull(atten.getEndTime())?"":atten.getEndTime();
		
		atten_end_staff += COMUtil.isNull(atten.getEndStaff())?String.valueOf(staff_id):"_"+staff_id;
		atten_end_time += COMUtil.isNull(atten.getEndTime())?new Date().toString():"_"+new Date().toString();
		
		atten.setEndStaff(atten_end_staff);
		atten.setEndTime(atten_end_time);
		
		if(attenDao.updataStaff(atten)&&StaffDao.updataStaff(staff)) //修改考勤表信息
		{
			logger.info("用户："+staff_id+"下班时间打卡,成功");
			return true;
		}else{
			logger.error("用户："+staff_id+"下班时间打卡,失败");
			return false;
		}
	}
	/**
	 * 取消用户上班打卡
	 * @param staff_id 员工id
	 * @return
	 */
	public boolean staffCancelClockIn(int staff_id)
	{
		AttenDao attenDao = new AttenDao();
		
		staff = StaffDao.queryStaffByID(staff_id);
		staff.setLastIn(new Date((new Date().getTime()-(24*60*60*1000))));
		//当日考勤信息
		Atten atten = new AttenService().getTodayStaff();	
		//atten_start_staff为员工id，atten_start_time为打卡时间
		String atten_start_staff = COMUtil.isNull(atten.getStartStaff())?"":atten.getStartStaff();
		String atten_start_time = COMUtil.isNull(atten.getStartTime())?"":atten.getStartTime();
		
		atten_start_staff=StaffService.listToString(StaffService.StringTolist(atten_start_staff.split("_"), String.valueOf(staff_id)));
		atten_start_time =StaffService.listToString(StaffService.StringTolist(atten_start_staff.split("_"),atten_start_time.split("_"), String.valueOf(staff_id)));
		
		atten.setStartStaff(atten_start_staff);
		atten.setStartTime(atten_start_time);
		
		if(attenDao.updataStaff(atten)&&StaffDao.updataStaff(staff))
		{
			logger.info("取消用户："+staff_id+"上班时间打卡,成功");
			return true;
		}else {
			logger.error("取消用户："+staff_id+"上班时间打卡,失败");
			return false;
		}

	}
	/**
	 * 取消用户下班打卡
	 * @param staff_id 员工id
	 * @return
	 */
	public boolean staffCancelClockOut(int staff_id)
	{
		AttenDao attenDao = new AttenDao();
		
		staff = StaffDao.queryStaffByID(staff_id);
		staff.setLastOut(new Date((new Date().getTime()-(24*60*60*1000))));
		//当日考勤信息
		Atten atten = new AttenService().getTodayStaff();
		//atten_end_staff为员工id，atten_end_time为打卡时间
		String atten_end_staff = COMUtil.isNull(atten.getEndStaff())?"":atten.getEndStaff();
		String atten_end_time = COMUtil.isNull(atten.getEndTime())?"":atten.getEndTime();
		
		atten_end_staff =StaffService.listToString(StaffService.StringTolist(atten_end_staff.split("_"), String.valueOf(staff_id)));
		atten_end_time =StaffService.listToString(StaffService.StringTolist(atten_end_staff.split("_"),atten_end_time.split("_"), String.valueOf(staff_id)));
		
		atten.setEndStaff(atten_end_staff);
		atten.setEndTime(atten_end_time);
		
		if(attenDao.updataStaff(atten)&&StaffDao.updataStaff(staff)) //修改考勤表信息
		{
			logger.info("取消用户："+staff_id+"下班时间打卡,成功");
			return true;
		}else {
			logger.error("取消用户："+staff_id+"下班时间打卡,失败");
			return false;
		}
	}
	/**
	 * list转换为String
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list){
	      if(list==null){
	      return null;
	   }
	   StringBuilder result = new StringBuilder();
	   boolean first = true;
	   //第一个前面不拼接","
	   for(String string :list) {
	      if(first) {
	         first=false;
	      }else{
	         result.append("_");
	      }
	      result.append(string);
	   }
	   return result.toString();
	}
	/**
	 * String数组转list
	 * @param strs String数组
	 * @param staff_id 员工id
	 * @return strs转为list
	 */
	public static List<String> StringTolist(String [] strs ,String staff_id){
		if(strs.length==0)
			return null;
		List<String> list = new ArrayList<String>();
		for(int i =0 ; i<strs.length;i++)
		{
			if(strs[i].equals(staff_id))
				continue;
			else
				list.add(strs[i]);
		}
		return list;
	}
	/**
	 * str1和str2一一对应,指定删除str2对应的元素
	 * @param str1 
	 * @param str2
	 * @param staff_id
	 * @return str2 转为list
	 */
	public static List<String> StringTolist(String [] str1,String [] str2, String staff_id){
		int falg=0;
		if(str1.length==0||str2.length==0)
			return null;
		List<String> timelist = new ArrayList<String>();
		for(int i =0 ; i<str1.length;i++)
		{
			if(str1[i].equals(staff_id))  //与第一个字符串相同位置元素
			{
				falg=i;
				break;
			}
		}
		for(int k=0;k<str2.length;k++) //与第一个字符串相同位置元素
		{
			if(falg==k)
				continue;
			else
				timelist.add(str2[k]);
		}
		return timelist;
	}

	public static void main(String [] args)
	{	
//		StaffService to = new StaffService();
		long startTime=System.currentTimeMillis();   //获取开始时间
//		System.out.println(to.staffCancelClockIn(3));
		long endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");   
//		String a="1_2_3_4_5_6_7_8_9_10_11_12_13";
//		String b="a_b_c";
//		System.out.println(StaffService.listToString(StaffService.StringTolist(a.split("_"), "2")));
//		System.out.println(StaffService.listToString(StaffService.StringTolist(a.split("_"),b.split("_"), "2")));
	}

}
