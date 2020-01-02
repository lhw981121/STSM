package com.stsm.controller.admin;

import java.io.IOException;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stsm.bean.Atten;
import com.stsm.bean.AttenInfo;
import com.stsm.bean.Pagination;
import com.stsm.bean.Staff;
import com.stsm.dao.AttenDao;
import com.stsm.dao.StaffDao;
import com.stsm.service.AttenService;
import com.stsm.util.COMUtil;

/**
 * Servlet implementation class manageInfo
 */
@WebServlet(urlPatterns = {
		"/admin/manage_info/staff/info",
		"/admin/manage_info/staff/create",
		"/admin/manage_info/staff/update",
		"/admin/manage_info/staff/detail",
		"/admin/manage_info/attendance/info",
		"/admin/staff_attendance/stat",
		"/admin/staff_attendance/yes",
		"/admin/staff_attendance/no",
		})
public class ManageInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		//员工信息页面
		case "/admin/manage_info/staff/info":{
			StaffDao dao = new StaffDao();
			//获取当前页码
			int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
			//查询字段
			String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
			//排序字段
			String sortField = "";
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
			
			String urlQueryStr = "?";
			if(request.getQueryString()!=null){
				urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
			}
			if(urlQueryStr.indexOf("page")!=-1){
				int pageLen = 6+(pagination.getPageNo()+"").length();
				urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
			}
			request.setAttribute("urlQueryStr", urlQueryStr);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/info.jsp").forward(request,response);
			break;
		}
		//员工新增页面
		case "/admin/manage_info/staff/create":{
			
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/create.jsp").forward(request,response);
			break;
		}
		//员工修改页面
		case "/admin/manage_info/staff/update":{
			StaffDao dao = new StaffDao();
			//获取员工id
			int staff_id= Integer.valueOf(request.getParameter("staff_id"));
			Staff staff =  dao.queryStaffByID(staff_id);
			request.setAttribute("staff", staff);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/update.jsp").forward(request,response);
			break;
		}
		//员工详情页面
		case "/admin/manage_info/staff/detail":{
			StaffDao dao = new StaffDao();
			//获取员工id
			int staff_id = Integer.valueOf(request.getParameter("staff_id"));
			Staff staff =  dao.queryStaffByID(staff_id);
			request.setAttribute("staff", staff);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/staff/detail.jsp").forward(request,response);
			break;
		}
		//考勤信息页面
		case "/admin/manage_info/attendance/info":{
			AttenDao dao = new AttenDao();
			//获取当前页码
			int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
			//查询字段
			String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
			//排序字段
			String sortField = "";
			//获取单页记录数
			int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
			//获取分页数据总量
			int recordCount = dao.getPageDataAttenCount(queryStr);
			//实例化分页对象
			Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
			//获取当页数据量
			List<Atten> atteninfo  = dao.getPageDataAtten(pagination.getPageNo(), pageSize, queryStr, sortField);
			

			request.setAttribute("pagination", pagination);
			request.setAttribute("atteninfo", atteninfo);
			request.setAttribute("notUseScriptPagination", true);
			
			String urlQueryStr = "?";
			if(request.getQueryString()!=null){
				urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
			}
			if(urlQueryStr.indexOf("page")!=-1){
				int pageLen = 6+(pagination.getPageNo()+"").length();
				urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
			}
			request.setAttribute("urlQueryStr", urlQueryStr);		
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/attendance/info.jsp").forward(request, response);
			break;
		}
		//考勤统计
		case "/admin/staff_attendance/stat":{
			AttenDao dao = new AttenDao();
			Atten atten = null;
			if(request.getParameter("date")==null||request.getParameter("date").length()==0) {
				atten = new AttenService().getTodayStaff();
			}else {
				atten = dao.queryAttenByDate(new Date(COMUtil.dateStrToDate(request.getParameter("date"), "yyyy-MM-dd").getTime()));
			}
			
			String [] StartStaff =COMUtil.isNull(atten.getStartStaff())?new String[0]:atten.getStartStaff().split("_");
			String [] StartTime = COMUtil.isNull(atten.getStartTime())?new String[0]:atten.getStartTime().split("_");
			String [] EndStaff = COMUtil.isNull(atten.getEndStaff())?new String[0]:atten.getEndStaff().split("_");
			String [] EndTime = COMUtil.isNull(atten.getEndTime())?new String[0]:atten.getEndTime().split("_");
			//获取当前页码
			int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
			//查询字段
			String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
			//排序字段
			String sortField = "";
			//获取单页记录数
			int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
			//获取分页数据总量
			int recordCount = dao.getPageDataAttenInfoCount(queryStr);
			//实例化分页对象
			Pagination pagination = new Pagination(recordCount,pageNo,pageSize);
			//获取当页数据量
			List<AttenInfo> attenInfo  = dao.getPageDateAttenInfo(pagination.getPageNo(), pageSize, queryStr, sortField,StartStaff,StartTime,EndStaff,EndTime);

			request.setAttribute("pagination", pagination);
			request.setAttribute("attenInfo", attenInfo);
			request.setAttribute("notUseScriptPagination", true);
			
			String urlQueryStr = "?";
			if(request.getQueryString()!=null){
				urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
			}
			if(urlQueryStr.indexOf("page")!=-1){
				int pageLen = 6+(pagination.getPageNo()+"").length();
				urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
			}
			request.setAttribute("urlQueryStr", urlQueryStr);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/attendance/stat.jsp").forward(request, response);
			break;
		}
		//已经考勤员工
		case "/admin/staff_attendance/yes":{
			AttenDao dao = new AttenDao();
			Atten atten = null;
			if(request.getParameter("date")==null||request.getParameter("date").length()==0) {
				atten = new AttenService().getTodayStaff();
			}else {
				atten = dao.queryAttenByDate(new Date(COMUtil.dateStrToDate(request.getParameter("date"), "yyyy-MM-dd").getTime()));
			}
			String [] StartStaff =COMUtil.isNull(atten.getStartStaff())?new String[0]:atten.getStartStaff().split("_");
			String [] StartTime = COMUtil.isNull(atten.getStartTime())?new String[0]:atten.getStartTime().split("_");
			String [] EndStaff = COMUtil.isNull(atten.getEndStaff())?new String[0]:atten.getEndStaff().split("_");
			String [] EndTime = COMUtil.isNull(atten.getEndTime())?new String[0]:atten.getEndTime().split("_");
			
			String sortField = "";
			String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
			
			int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
			int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());
			
			List<AttenInfo> Atten = dao.getPageDateAttenInfo(queryStr, sortField, StartStaff, StartTime, EndStaff, EndTime, 1);
			List<AttenInfo>  attenInfo = this.getPageDateAttenInfo(pageNo, pageSize, Atten.size(),Atten);
			
		    Pagination pagination = new Pagination(Atten.size(),pageNo,pageSize);

			request.setAttribute("pagination", pagination);
			request.setAttribute("attenInfo", attenInfo);
			request.setAttribute("notUseScriptPagination", true);
			
			String urlQueryStr = "?";
			if(request.getQueryString()!=null){
				urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
			}
			if(urlQueryStr.indexOf("page")!=-1){
				int pageLen = 6+(pagination.getPageNo()+"").length();
				urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
			}
			request.setAttribute("urlQueryStr", urlQueryStr);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/attendance/yes.jsp").forward(request, response);
			break;
		}
		//未考勤员工
		case "/admin/staff_attendance/no":{
			
			AttenDao dao = new AttenDao();
			Atten atten = null;
			if(request.getParameter("date")==null||request.getParameter("date").length()==0) {
				atten = new AttenService().getTodayStaff();
			}else {
				atten = dao.queryAttenByDate(new Date(COMUtil.dateStrToDate(request.getParameter("date"), "yyyy-MM-dd").getTime()));
			}
			
			String [] StartStaff =COMUtil.isNull(atten.getStartStaff())?new String[0]:atten.getStartStaff().split("_");
			String [] StartTime = COMUtil.isNull(atten.getStartTime())?new String[0]:atten.getStartTime().split("_");
			String [] EndStaff = COMUtil.isNull(atten.getEndStaff())?new String[0]:atten.getEndStaff().split("_");
			String [] EndTime = COMUtil.isNull(atten.getEndTime())?new String[0]:atten.getEndTime().split("_");

			String sortField = "";
			String queryStr = request.getParameter("queryStr")==null?"":request.getParameter("queryStr");
			
			int pageNo = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
			int pageSize = request.getSession().getAttribute("pageSize")==null?10:Integer.valueOf(request.getSession().getAttribute("pageSize").toString());

			List<AttenInfo> Atten = dao.getPageDateAttenInfo(queryStr, sortField, StartStaff, StartTime, EndStaff, EndTime, 2);
			List<AttenInfo>  attenInfo = this.getPageDateAttenInfo(pageNo, pageSize, Atten.size(),Atten);
			
		    Pagination pagination = new Pagination(Atten.size(),pageNo,pageSize);

			request.setAttribute("pagination", pagination);
			request.setAttribute("attenInfo", attenInfo);
			request.setAttribute("notUseScriptPagination", true);
			
			String urlQueryStr = "?";
			if(request.getQueryString()!=null){
				urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
			}
			if(urlQueryStr.indexOf("page")!=-1){
				int pageLen = 6+(pagination.getPageNo()+"").length();
				urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
			}
			request.setAttribute("urlQueryStr", urlQueryStr);
			request.getRequestDispatcher("/WEB-INF/view/admin/manageInfo/attendance/no.jsp").forward(request, response);
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
	
	/**
	 * 对list分页
	 * @param PageNo
	 * @param PageSize
	 * @param recordCount
	 * @param list
	 * @return
	 */
	public List<AttenInfo> getPageDateAttenInfo(int PageNo,int PageSize,int recordCount,List<AttenInfo> list){
    	int start = PageNo*PageSize-PageSize;
    	int end;
    	if(start == (recordCount/PageSize)*PageSize) {
    		end = start+recordCount%PageSize;
    		return list.subList(start, end);
    	}else {
    		end = start+PageSize;
    		return list.subList(start, end);
    	}
	}

}
