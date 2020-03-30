package com.yang.co;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;


@WebServlet("/co/deleterecruit")
public class Deleterecruit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Deleterecruit() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String sqlStr = "update recruit set isdelete='true' where id=? ";
		String[] params = {id};
		int execute = Dao.execute(sqlStr, params);
		
		request.setAttribute("httpUrl", "/admin/userlist");
		request.setAttribute("info", "删除成功！即将跳转招聘信息页面！");
		request.setAttribute("title", "删除成功！");
		request.getRequestDispatcher("/co/info.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] ids = request.getParameterValues("ids[]");
		System.out.println(ids[0]);
		//mysql in
		String sqlStr = "update recruit set isdelete = 'true' where id in(";
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

