package com.yang.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yang.db.Dao;


@WebServlet("/admin/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		response.getWriter().println("这是登录页面");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单提交的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//查找数据库是否有对应用户信息
		String sqlStr = "select * from user where username=? and password=?";
		String[] params = {username,password};
		//返回值是数组,判断是否空数组
		ArrayList<HashMap<String, Object>> query = Dao.query(sqlStr, params);
		if(query.size()>0) {
			System.out.println("登陆成功");
			//用session保留登录状态，设置登录状态
			HttpSession session = request.getSession();
			session.setAttribute("isLogin",true);
			session.setAttribute("username",username);
			//显示登录成功信息
			request.setAttribute("httpUrl", "/admin/index");
			request.setAttribute("info", "登陆成功！即将跳转后台页面！");
			request.setAttribute("title", "登陆成功！");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			
		}else {
			System.out.println("登陆失败");
			//显示登录失败页面
			request.setAttribute("httpUrl", "/admin/login");
			request.setAttribute("info", "登陆失败！即将跳转登录页面！");
			request.setAttribute("title", "登陆失败！");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
	}

}
