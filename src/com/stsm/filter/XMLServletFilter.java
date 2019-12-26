package com.stsm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AsyncServletFilter
 */
@WebFilter(
		servletNames = {"" })
public class XMLServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public XMLServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestType =req.getHeader("X-Requested-With");
		if(requestType!=null&&requestType.equalsIgnoreCase("XMLHttpRequest")) {//异步请求
			chain.doFilter(request, response);
		}else {//同步请求
			request.getRequestDispatcher("/WEB-INF/view/404.jsp").forward(request, response);
			return;
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}