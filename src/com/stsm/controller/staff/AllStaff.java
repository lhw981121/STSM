package com.stsm.controller.staff;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stsm.bean.Pagination;
import com.stsm.bean.Staff;
import com.stsm.dao.StaffDao;

/**
 * Servlet implementation class AllStaff
 */
@WebServlet(urlPatterns = {"/admin/manage_info/staff/info"})
public class AllStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllStaff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StaffDao dao = new StaffDao();
		//获取当前页码
		int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		//查询字段
		String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
		//排序字段
		String sortField = "staff_id";
		//获取单页记录数
		int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
		//获取分页数据总量
		int recordCount = dao.getPageDataStaffCount(queryStr);
		//实例化分页对象
		Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
		//获取当页数据量
		List<Staff> staffinfo  = dao.getPageDataStaff(pagination.getPageNo(), pageSize, queryStr, sortField);

		request.setAttribute("pagination", pagination);
		request.setAttribute("staffinfo", staffinfo);
		request.setAttribute("notUseScriptPagination", true);
		request.setAttribute("urlQueryStr", "?");
//		request.setAttribute("queryStr", queryStr);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/info.jsp").forward(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
