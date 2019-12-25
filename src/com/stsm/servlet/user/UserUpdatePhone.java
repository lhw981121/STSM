package com.stsm.servlet.user;

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
import com.stsm.bean.User;
import com.stsm.service.UserService;

/**
 * Servlet implementation class UserBindPhone
 */
@WebServlet("/UserUpdatePhone")
public class UserUpdatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdatePhone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null||request.getParameter("user_phone")==null) {
			response.sendRedirect("/STSM/login");
			return;
		}
		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");
		String user_phone = request.getParameter("user_phone");
				
		UserService service = new UserService();
        User user = (User)session.getAttribute("user");
        
        boolean isOK = false;
        isOK = service.updateUserPhone(user, mode.equals("bind")?user_phone:"");

        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
		map.put("successMes","你已成功"+(mode.equals("bind")?"绑定":"解绑")+"手机号："+user_phone+"\n窗口即将关闭。。。");
		map.put("errorMes","很抱歉，遇到了未知错误，可能是服务器出错或网络问题。\n请重试或联系管理员！");

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
