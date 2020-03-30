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

//��̨ע��

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
		// ��ѯ�Ƿ��Ѵ����û���
		String strSQL = "select username from user where username = ?";
		String[] params = {username};
		//��ȡ����������
		ArrayList<HashMap<String, Object>>  result = Dao.query(strSQL, params);
		if(result.size()>0) {
			System.out.println("���û�����ע�ᣡ");
			request.setAttribute("title", "ע��ʧ��");
			request.setAttribute("info", "�û����Ѵ��ڣ�ע��ʧ��");
			request.setAttribute("httpUrl", "/admin/register");
		}else {
			System.out.println("ע��ɹ�");
			//ע��ɹ��������ݿ��в�������
			HashMap<String, Object> user =  new HashMap<>();
			user.put("username", username);
			user.put("password", password);
			//׷����ݱ�ʶ
			user.put("userType","admin");
			//���뵽user��
			Dao.insertObj("user", user);
			
			request.setAttribute("title", "ע��ɹ�");
			request.setAttribute("info", "ע��ɹ���������ת��¼ҳ��");
			request.setAttribute("httpUrl", "/admin/login");
		}
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}

}
