package com.stsm.controller.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StaffAttendance
 */
@WebServlet(urlPatterns = {"/staff/attendance_today","/staff/attendance_record","/staff/salary_check"})
public class Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Attendance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
			//今日考勤页面
			case "/staff/attendance_today":{
				
				request.getRequestDispatcher("/WEB-INF/view/staff/attendanceToday.jsp").forward(request, response);
				break;
			}
			//考勤记录页面
			case "/staff/attendance_record":{
				
				request.getRequestDispatcher("/WEB-INF/view/staff/attendanceRecord.jsp").forward(request, response);
				break;
			}
			//薪水核实页面
			case "/staff/salary_check":{
				
				request.getRequestDispatcher("/WEB-INF/view/staff/salaryCheck.jsp").forward(request, response);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
