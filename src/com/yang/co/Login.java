package com.yang.co;

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


@WebServlet("/co/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/co/login.jsp").forward(request, response);
		response.getWriter().println("���ǵ�¼ҳ��");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ύ���û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//�������ݿ��Ƿ��ж�Ӧ�û���Ϣ
		String sqlStr = "select * from user where username=? and password=?";
		String[] params = {username,password};
		//����ֵ������,�ж��Ƿ������
		ArrayList<HashMap<String, Object>> query = Dao.query(sqlStr, params);
		if(query.size()>0) {
			//�ж��û�����
			//�������ݿ��Ƿ��ж�Ӧ�û���Ϣ
			String sqlStr2 = "SELECT userType FROM USER WHERE username="+params[0];
			if(sqlStr2.equals("admin")) {
				System.out.println("��½�ɹ�");
				//��session������¼״̬�����õ�¼״̬
				HttpSession session = request.getSession();
				session.setAttribute("isLogin",true);
				session.setAttribute("username",username);
				
				//��ʾ��¼�ɹ���Ϣ
				request.setAttribute("httpUrl", "/co/index");
				request.setAttribute("info", "��½�ɹ���������ת����Ա��̨ҳ�棡");
				request.setAttribute("title", "��½�ɹ���");
				request.getRequestDispatcher("/co/info.jsp").forward(request, response);
			} else {
				System.out.println("��½�ɹ�");
				//��session������¼״̬�����õ�¼״̬
				HttpSession session = request.getSession();
				session.setAttribute("isLogin",true);
				session.setAttribute("username",username);
				
				//��ʾ��¼�ɹ���Ϣ
				request.setAttribute("httpUrl", "/co/index");
				request.setAttribute("info", "��½�ɹ���������ת��ҵ��̨ҳ�棡");
				request.setAttribute("title", "��½�ɹ���");
				request.getRequestDispatcher("/co/info.jsp").forward(request, response);
			}
			
		}else {
			System.out.println("��½ʧ��");
			//��ʾ��¼ʧ��ҳ��
			request.setAttribute("httpUrl", "/co/login");
			request.setAttribute("info", "��½ʧ�ܣ�������ת��¼ҳ�棡");
			request.setAttribute("title", "��½ʧ�ܣ�");
			request.getRequestDispatcher("/co/info.jsp").forward(request, response);
		}
	}

}
