package com.yang.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/loginout")
public class LoginOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginOut() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����¼����Ϣ�����session
		HttpSession session = request.getSession();
		session.invalidate();
		//�ض��򵽵�¼ҳ��
		response.sendRedirect("./login");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
