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
import com.stsm.bean.Message;
import com.stsm.bean.Staff;
import com.stsm.bean.User;
import com.stsm.dao.StaffDao;
import com.stsm.service.StaffService;

/**
 * Servlet implementation class StaffClockIn
 */
@WebServlet("/StaffClock")
public class StaffClock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffClock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null) {
			response.sendRedirect("/STSM/login");
			return;
		}
		String mode = request.getParameter("mode");
		
		HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int staff_id = new StaffDao().queryStaffByNumber(user.getAccount()).getID();
        Staff staff = new StaffDao().queryStaffByID(staff_id);
        
        boolean isOK = false;
        if(mode.equals("clockIn")) {
        	isOK = new StaffService().staffClockIn(staff_id);
        }else if(mode.equals("clockOut")) {
        	isOK = new StaffService().staffClockOut(staff_id);
        }
        
        if(isOK) {
        	/*发送消息给职位所属的企业用户*/
        	Message mes = new Message();
        	//消息概述
        	String message_summary = "员工 "+staff.getName()+" 完成上班打卡";
        	//消息内容
        	String message_content = "员工 "+staff.getName()+" 已完成今日上班打卡，请注意核实。";
        	//消息发送者用户ID
        	int sender_id = 1;//系统消息
        	//消息接收者用户ID
        	int receiver_id = 2;//管理员
        	//发送消息
        	mes.sendSingleMessage(0, message_summary, message_content, sender_id, receiver_id);
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		map.put("successMes",(mode.equals("clockIn")?"上班":"下班")+"打卡成功！");
		map.put("errorMes",(mode.equals("clockIn")?"上班":"下班")+"打卡失败，可能是服务器出错或打卡超时！");
        
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
