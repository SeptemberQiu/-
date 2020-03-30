package com.yang.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;

//后台注册

@WebServlet("/admin/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Register() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		// 查询是否已存在用户名
		String strSQL = "select username from user where username = ?";
		String[] params = {username};
		//获取到的是数组
		ArrayList<HashMap<String, Object>>  result = Dao.query(strSQL, params);
		if(result.size()>0) {
			System.out.println("此用户名已注册！");
			request.setAttribute("title", "注册失败");
			request.setAttribute("info", "用户名已存在，注册失败");
			request.setAttribute("httpUrl", "/admin/register");
		}else {
			System.out.println("注册成功");
			//注册成功，往数据库中插入数据
			HashMap<String, Object> user =  new HashMap<>();
			user.put("username", username);
			user.put("password", password);
			//追加身份标识
			user.put("userType","admin");
			//插入到user表
			Dao.insertObj("user", user);
			
			request.setAttribute("title", "注册成功");
			request.setAttribute("info", "注册成功，即将跳转登录页面");
			request.setAttribute("httpUrl", "/admin/login");
		}
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}

}
