package cn.toprs.projectmanagenment.filter;

import cn.toprs.projectmanagenment.servlet.XssHttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 
 * xss过滤器
 * @author KevinBlandy
 *
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequest((HttpServletRequest) request),response);
	}

	@Override
	public void destroy() {
		
	}
}
