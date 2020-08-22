package com.mi.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "CheckFrontFilter", value ="/front/*" )
public class CheckFrontFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			if (req.getSession().getAttribute("currentLoginMember")==null) {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				String  basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/";
				out.print("<script>alert('请先登录...');location.href='"+basePath+"login.html'</script>");
				out.flush();
				return;
			}
			String path = req.getRequestURI();
			String base = req.getContextPath();
			path = path.replace(base, "");
			req.getRequestDispatcher("../WEB-INF"+path).forward(req, resp);
			return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
