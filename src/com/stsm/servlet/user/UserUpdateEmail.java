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
 * Servlet implementation class UserBindEmail
 */
@WebServlet("/UserUpdateEmail")
public class UserUpdateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("mode")==null||request.getParameter("user_email")==null) {
			response.sendRedirect("/STSM/index");
			return;
		}
		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");
		String user_email = request.getParameter("user_email");
				
        User user = (User)session.getAttribute("user");
        
        boolean isOK = false;
        isOK = new UserService().updateUserEmail(user, mode.equals("bind")?user_email:"");
        
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);

		map.put("successMes","你已成功"+(mode.equals("bind")?"绑定":"解绑")+"邮箱："+user_email+"\n窗口即将关闭。。。");
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
