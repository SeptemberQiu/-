package com.yang.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;


@WebServlet("/admin/deleteuser")
public class Deleteuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Deleteuser() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String sqlStr = "update user set isdelete='true' where id=? ";
		String[] params = {id};
		int execute = Dao.execute(sqlStr, params);
		
		request.setAttribute("httpUrl", "/admin/userlist");
		request.setAttribute("info", "ɾ���ɹ���������ת�û�ҳ�棡");
		request.setAttribute("title", "ɾ���ɹ���");
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//����userlist.jsp��ajax���ݹ���������
		String[] ids = request.getParameterValues("ids[]");
		System.out.println(ids[0]);
		//mysql in
		String sqlStr = "update user set isdelete = 'true' where id in(";
		for(int i=0;i<ids.length;i++) {	
			if(i==(ids.length-1)) { 
				sqlStr= sqlStr+ids[i]+")"; 
			}else { 
				sqlStr=sqlStr+ids[i]+",";
			}
		}
			int execute = Dao.execute(sqlStr); 
			System.out.println(execute);
		
			response.getWriter().println("success!");
	}
		
		
	}

