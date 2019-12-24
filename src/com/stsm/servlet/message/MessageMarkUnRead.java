package com.stsm.servlet.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stsm.bean.Message;

/**
 * Servlet implementation class MessageMarkUnRead
 */
@WebServlet("/MessageMarkUnRead")
public class MessageMarkUnRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageMarkUnRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("message_id")==null){
			response.sendRedirect("/STSM/index");
			return;
		}
		Integer message_id = Integer.valueOf(request.getParameter("message_id"));
		Message mes = new Message().queryMessageByID(message_id);
		
		boolean isOK = false;
		
		isOK = mes.updateMessageOfReaded(false);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isOK", isOK);
		
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
