package com.stsm.servlet.staff;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class StaffIsNumberExist
 */
@WebServlet("/StaffIsNumberExist")
public class StaffIsNumberExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffIsNumberExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("staff_number")==null) {
			response.sendRedirect("/STSM/login");
			return;
		}
		
		String staff_number = request.getParameter("staff_number");
				
        StaffDao dao = new StaffDao();
        Staff staff = new Staff();
        staff = dao.queryStaffByNumber(staff_number);
        
        boolean isExist = false;
        if(staff!=null) {
        	isExist = true;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isExist", isExist);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("language").equals("zh_CN")) {
			map.put("errorMes","该工号已存在！");
			map.put("successMes","该工号可用！");
		}else if(session.getAttribute("language").equals("en_US")){
			map.put("errorMes","The email address has been registered!");
			map.put("successMes","The email address can be registered!");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
