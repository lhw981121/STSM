package com.stsm.controller.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stsm.bean.Atten;
import com.stsm.dao.AttenDao;
import com.stsm.util.COMUtil;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AttenDao dao = new AttenDao();
		Atten atten = dao.queryAttenByDate(new Date());
		if(COMUtil.isNull(request.getParameter("period"))) {
			request.setAttribute("atten", atten);
			request.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
		}else {
			
			String period = COMUtil.isNull(request.getParameter("period"))?Atten.default_period:request.getParameter("period");
			atten.setPeriod(period);
			if(dao.updataStaff(atten)) {
				System.out.println("工作时间修改成功");
				request.setAttribute("atten", atten);
				request.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
			}else {
				System.out.println("工作时间修改失败");
				request.setAttribute("atten", atten);
				request.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
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
