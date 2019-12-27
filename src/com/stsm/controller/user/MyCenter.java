package com.stsm.controller.user;

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
 * Servlet implementation class MyCenter
 */
@WebServlet(urlPatterns = {"/user/mycenter","/user"})
public class MyCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null)	{response.sendRedirect("/STSM/login");return;}
		Atten atten = new AttenService().getTodayStaff();
		Staff staff = new StaffDao().queryStaffByNumber(user.getAccount());
		request.setAttribute("atten", atten);
		request.setAttribute("staff", staff);
		
		/*System.out.println(atten.getStartStaff().length());*/
		request.getRequestDispatcher("/WEB-INF/view/user/myCenter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
