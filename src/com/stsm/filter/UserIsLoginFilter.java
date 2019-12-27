package com.stsm.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stsm.bean.User;


/**
 * Servlet Filter implementation class UserIsCookieFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { @WebInitParam(name = "loginPage", value = "login") }, 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserIsLoginFilter implements Filter {
	
	private String loginPage = "login";

    public UserIsLoginFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
		// 获取当请求被拦截时转向的页面
		loginPage = fConfig.getInitParameter("loginPage");
		if (null == loginPage) {
			loginPage = "login";
		}
	}
    
	public void destroy() {
		this.loginPage = null;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
        boolean flag = false;
        //如果是.jsp文件需要过滤
        if (req.getServletPath().indexOf(".jsp") != -1) {
            flag =true;
        }
        //不过滤登录页面和忘记密码页面
        if(req.getServletPath().indexOf("login") != -1||req.getServletPath().indexOf("lostPassword") != -1) {
        	flag = false;
        }
        if (flag) {
        	// 判断被拦截的请求用户是否处于登录状态
    		if (session.getAttribute("user") == null) {
    			// 获取被拦截的请求地址及参数
    			String requestPath = req.getRequestURI();
    			if (req.getQueryString() != null) {
    				requestPath += "?" + req.getQueryString();
    			}
    			// 将请求地址保存到request对象中转发到登录页面
    			req.setAttribute("requestPath", requestPath);
    			request.getRequestDispatcher( "/" + loginPage).forward(request, response);
    			return;
    		} else {
    			//检测用户在线状态
    	        ServletContext application = session.getServletContext();
    	        Map<String,Object> userMap = application.getAttribute("userMap")==null?new HashMap<String,Object>():(Map<String,Object>)application.getAttribute("userMap");
    	        if(userMap.containsKey(session.getId())) {//用户在线
    	        	//更新Cookie中的sessionid
        	        Cookie JSESSIONID = new Cookie("JSESSIONID",session.getId());
        	        JSESSIONID.setMaxAge(60*30);//保存时间为session存活时间
        	        JSESSIONID.setPath("/STSM");
        	        resp.addCookie(JSESSIONID);
    	        }
    	        //访问权限
    	        User user = (User)session.getAttribute("user");
    			//拒绝非管理员访问管理员页面
    			if(req.getServletPath().indexOf("/admin") != -1 && user.getType() != 8) {
    				req.setAttribute("limitedAccess", 1);
    				request.getRequestDispatcher("/index").forward(request, response);
    				return;
    			}
    			//管理员访问求职者或企业页面
    			if((req.getServletPath().indexOf("/staff") == 0)&&user.getType()==8) {
    				resp.sendRedirect("/SWRW/index");
    				return;
    			}
    			chain.doFilter(request, response);
    		}
        }else{
            chain.doFilter(request, response);  
        }
	}

}
