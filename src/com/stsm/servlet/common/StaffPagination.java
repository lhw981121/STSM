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
import com.stsm.bean.Pagination;
import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class StaffPagination
 */
@WebServlet("/StaffPagination")
public class StaffPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffPagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页码
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		//员工性别
		int staff_sex = Integer.valueOf(request.getParameter("staff_sex"));
		//员工职位
		int staff_position = Integer.valueOf(request.getParameter("staff_position"));
		//是否已打卡（上班）
		int last_in = Integer.valueOf(request.getParameter("last_in"));
		//是否已打卡（下班）
		int last_out = Integer.valueOf(request.getParameter("last_out"));
		//查询字段
		String queryStr = request.getParameter("queryStr");
		//排序字段
		String sortField = request.getParameter("sortField");
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		//获取分页数据总量
		int recordCount = new StaffDao().getPageDataStaffCount(staff_sex, staff_position, last_in, last_out, queryStr);
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取分页数据
		List<Staff> staffs = new StaffDao().getPageDataStaff(pagination.getPageNo(), pageSize, staff_sex, staff_position, last_in, last_out, queryStr, sortField);
		map.put("staffs", staffs);
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
