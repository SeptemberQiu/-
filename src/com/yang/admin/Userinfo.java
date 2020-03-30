package com.yang.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yang.db.Dao;


@WebServlet("/admin/userinfo")
public class Userinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Userinfo() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���Ϣ
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String sqlStr = "select * from user where username = ?";
		String[] params = {username};
		ArrayList<HashMap<String, Object>> result = Dao.query(sqlStr, params);
		//��������ĵ�һ�����ݼ����û���map����
		HashMap<String, Object> user = result.get(0);
		//��usermap������õ�request
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/admin/userinfo.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		//��ȡ�޸ĺ�ı�����
		/*
		 * Map<String, String[]> user = request.getParameterMap(); String[] username =
		 * user.get("username"); System.out.println(username[0]);
		 */
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		
		
		//������Ϣ���µ����ݿ�
		String sqlStr = "update user set username=?,name=?,password=?,userType=?,sex=?,age=?,mobile=?,email=? where username=?";
		String[] params = {username,name,password,userType,sex,age,mobile,email,username};
		int execute = Dao.execute(sqlStr, params);
		System.out.println(execute);
		//�û��޸����
		request.setAttribute("httpUrl", "/admin/edituser?username="+username);
		request.setAttribute("info", "�޸ĳɹ����������޸�ҳ�棡");
		request.setAttribute("title", "�޸ĳɹ���");
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}

}
