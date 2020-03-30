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
		//��ȡ�ύ������
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		
		//�ж��û����Ƿ��Ѿ�����
		String sqlStr = "select * from user where username=?";
		String[] params = {username};
		ArrayList<HashMap<String, Object>>  result = Dao.query(sqlStr, params);
		if(result.size()==0 ) {
			//����hashmap�������
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
			//��֪�û���ӳɹ�
			request.setAttribute("httpUrl", "/admin/userlist");
			request.setAttribute("info", "��ӳɹ����������û�ҳ�棡");
			request.setAttribute("title", "��ӳɹ���");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else if(result.get(0).get("isdelete")!=null&&result.get(0).get("isdelete").equals("true")){
			//������Ϣ���µ����ݿ�
			sqlStr = "update user set username=?,name=?,password=?,userType=?,sex=?,age=?,mobile=?,email=? where username=?";
			String[] params1 = {username,name,password,userType,sex,age,mobile,email,username};
			int execute = Dao.execute(sqlStr, params1);
		}
		else {
			//��֪�û����ʧ��
			request.setAttribute("httpUrl", "/admin/userlist");
			request.setAttribute("info", "���û����Ѵ��ڣ����ʧ�ܣ��������û�ҳ�棡");
			request.setAttribute("title", "���ʧ�ܣ�");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
		
	}

}
