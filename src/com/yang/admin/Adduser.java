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


@WebServlet("/admin/adduser")
public class Adduser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Adduser() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/adduser.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		//获取提交表单数据
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		//判断用户名是否已经存在
		String sqlStr = "select * from user where username=?";
		String[] params = {username};
		ArrayList<HashMap<String, Object>>  result = Dao.query(sqlStr, params);
		if(result.size()==0 ) {
			//制作hashmap添加数据
			HashMap<String, Object> user = new HashMap<>();
			user.put("username",username);
			user.put("name",name);
			user.put("password",password);
			user.put("userType",userType);
			user.put("sex",sex);
			user.put("age",age);
			user.put("mobile",mobile);
			user.put("email",email);
			
			Dao.insertObj("user", user);
			//告知用户添加成功
			request.setAttribute("httpUrl", "/admin/userlist");
			request.setAttribute("info", "添加成功！返回至用户页面！");
			request.setAttribute("title", "添加成功！");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else if(result.get(0).get("isdelete")!=null&&result.get(0).get("isdelete").equals("true")){
			//将表单信息更新到数据库
			sqlStr = "update user set username=?,name=?,password=?,userType=?,sex=?,age=?,mobile=?,email=? where username=?";
			String[] params1 = {username,name,password,userType,sex,age,mobile,email,username};
			int execute = Dao.execute(sqlStr, params1);
		}
		else {
			//告知用户添加失败
			request.setAttribute("httpUrl", "/admin/userlist");
			request.setAttribute("info", "此用户名已存在，添加失败！返回至用户页面！");
			request.setAttribute("title", "添加失败！");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
		
	}

}
