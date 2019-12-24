package com.stsm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class RefreshJobState
 *
 */
@WebListener
public class STSMStartOrStopListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public STSMStartOrStopListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//每日凌晨新增考勤记录
        //new AddAttendance();
    }
	
}
