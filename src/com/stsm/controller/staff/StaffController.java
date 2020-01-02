package com.stsm.controller.staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stsm.bean.Atten;
import com.stsm.bean.Staff;
import com.stsm.bean.User;
import com.stsm.dao.StaffDao;
import com.stsm.service.AttenService;

/**
 * Servlet implementation class StaffAttendance
 */
@WebServlet(urlPatterns = {
		"/staff/attendance_today",
		"/staff/attendance_record",
		"/staff/salary_check"
		})
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffController() {
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
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");if(user==null){response.sendRedirect("/STSM/login");return;}
				Atten atten = new AttenService().getTodayStaff();
				Staff staff = new StaffDao().queryStaffByNumber(user.getAccount());
				request.setAttribute("atten", atten);
				request.setAttribute("staff", staff);
				request.getRequestDispatcher("/WEB-INF/view/staff/attendanceToday.jsp").forward(request, response);
				break;
			}
			//考勤记录页面
			case "/staff/attendance_record":{
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");if(user==null){response.sendRedirect("/STSM/login");return;}
				Staff staff = new StaffDao().queryStaffByNumber(user.getAccount());
				request.setAttribute("staff", staff);
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
