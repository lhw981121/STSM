package com.stsm.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stsm.bean.Atten;
import com.stsm.bean.Pagination;
import com.stsm.service.AttenService;

/**
 * Servlet implementation class StaffAttendancePagination
 */
@WebServlet("/StaffAttendancePagination")
public class StaffAttendancePagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffAttendancePagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页码
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		//员工ID
		int staff_id = Integer.valueOf(request.getParameter("staff_id"));
		//要查询的天数
		int pastDay = Integer.valueOf(request.getParameter("pastDay"));
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Atten> attenList = new AttenService().getStaffAtten(staff_id, pastDay);
		//获取分页数据总量
		int recordCount = attenList.size();
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取分页数据
		List<Atten> attens = null;
    	int start = pageNo*pageSize-pageSize;
    	int end;
    	if(start == (recordCount/pageSize)*pageSize) {
    		end = start+recordCount%pageSize;
    		attens = attenList.subList(start, end);
    	}else {
    		end = start+pageSize;
    		attens = attenList.subList(start, end);
    	}
		map.put("attens", attens);
		map.put("pagination", pagination);
		
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
