package com.stsm.service;

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
	
	
}
