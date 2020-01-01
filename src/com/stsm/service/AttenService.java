package com.stsm.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.stsm.bean.Atten;
import com.stsm.dao.AttenDao;
import com.stsm.util.COMUtil;

public class AttenService {
	private Atten atten = new Atten();
	private AttenDao attenDao = new AttenDao();
	private Logger logger = Logger.getLogger(getClass());
	
	/**
	* 获取当天考勤记录
	* @return 当天考勤对象
	*/
	public Atten getTodayStaff(){
		List<Atten> attens = attenDao.queryAttenBySingleData("atten_date",String.valueOf(COMUtil.dataToStr(new Date())));
		if(attens.size()==0) {//当日考勤记录不存在，插入记录
			//是否有前一天的数据
			if(attenDao.queryAttenBySingleData("atten_date",String.valueOf(COMUtil.dataToStr(new Date(new Date().getTime() - 1000*60*60*24)))).size()==0) {//否
				atten.setPeriod(Atten.default_period);
			}else {//有前一天的数据使用前一天设置的工作时间
				atten.setPeriod(attenDao.queryAttenBySingleData("atten_date",String.valueOf(COMUtil.dataToStr(new Date(new Date().getTime() - 1000*60*60*24)))).get(0).getPeriod());
			}
			atten.setDate(new Date());
			int id = attenDao.createAtten(atten);
			if(id==0) {
				logger.info("添加当日考勤记录成功："+COMUtil.dataToStr(new Date()));
			}else {
				logger.error("添加当日考勤记录出错失败！");
			}
			return attenDao.queryAttenByID(id);
		}else {
			return attens.get(0);
		}
	}
	
	
	/*************************************
	 * 通过员工ID获取员工考勤记录
	 * @param staff_id 员工ID
	 * @param pastDay 要查询的过去天数（包括今天）
	 * @return 考勤表
	 */
	public List<Atten> getStaffAtten(int staff_id,int pastDay){
		String id = String.valueOf(staff_id);
		String atten_start_time = null;
		String atten_end_time = null;
		List<String> dateList = null;
		List<Atten> attenList = attenDao.getAttenByPastDay(pastDay);
		for(Atten atten : attenList) {
			atten_start_time = COMUtil.ifNull(atten.getStartTime());
			atten_end_time = COMUtil.ifNull(atten.getEndTime());
			if(atten_start_time.length()==0) {
				atten.setStartTime("");
			}else {
				dateList = Arrays.asList(atten.getStartStaff().split("_"));
				int index = 0;
				for(String str : dateList) {
					if(str.equals(id))	break;
					index++;
				}
				if(index == dateList.size()) {
					atten.setStartTime("");
				}else {
					atten.setStartTime(Arrays.asList(atten.getStartTime().split("_")).get(index));
				}
			}
			if(atten_end_time.length()==0) {
				atten.setEndTime("");
			}else {
				dateList = Arrays.asList(atten.getEndStaff().split("_"));
				int index = 0;
				for(String str : dateList) {
					if(str.equals(id))	break;
					index++;
				}
				if(index == dateList.size()) {
					atten.setEndTime("");
				}else {
					atten.setEndTime(Arrays.asList(atten.getEndTime().split("_")).get(index));
				}
			}
		}
		return attenList;
	}
	
	public static void main(String[] s) {
		List<Atten> list = new AttenService().getStaffAtten(1, 3);
		for(Atten atten : list) {
			System.out.println(atten.getDate()+" "+atten.getStartTime()+" "+atten.getEndTime());
		}
	}
}
