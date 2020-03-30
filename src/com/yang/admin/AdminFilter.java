package com.yang.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yang.db.Dao;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {


    public AdminFilter() {
        
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("admin filter");
		//获取路径信息
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		//通过路径匹配放行,注册和登陆页面，其余页面一律需要判断登录信息和权限
		if(servletPath.equals("/admin/register")||servletPath.equals("/admin/login")) {
			chain.doFilter(request, response);
		}else {
			//获取session对象
			HttpSession session = req.getSession();
			if(session.getAttribute("username")!=null) {
				
				String username = (String) session.getAttribute("username");
				//查找此用户的用户类型
				String sqlStr = "select * from user where username = ?";
				String[] params = {username};
				ArrayList<HashMap<String, Object>>  res = Dao.query(sqlStr, params);
				HashMap<String, Object> user = res.get(0);
				
				System.out.println(user.get("userType"));
				//根据是否有访问权限，判断
				if(user.get("userType").equals("admin")) {
					//类型正确，放行
					chain.doFilter(request, response);
					//System.out.println("servlet path:"+req.getServletPath());
				}else {
					req.setAttribute("httpUrl", "/admin/login");
					req.setAttribute("info", "您没有权限访问，即将跳转登陆页面！");
					req.setAttribute("title", "访问失败");
					req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
					
				}
			}else {
				//如果没有登录，跳转登录页面
				req.setAttribute("httpUrl", "/admin/login");
				req.setAttribute("info", "您没有登录，即将跳转登陆页面！");
				req.setAttribute("title", "未登录");
				req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
			}
		}
		
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
