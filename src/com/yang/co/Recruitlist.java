package com.yang.co;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;


@WebServlet("/co/recruitlist")
public class Recruitlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Recruitlist() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���е���Ƹ����ȡ���½�����5����Ϣ
		String page = request.getParameter("page");
		//Ĭ�ϻ�ȡ�����ڼ�ҳ�Ĳ�������ôĬ�ϴ򿪵�һҳ
		if(page==null) {
			page="1";
		}
		int num = (Integer.parseInt(page)-1)*5;
	
		//��ҵ��Ϣ�б�Ļ�ȡ
		String sqlStr = "SELECT * FROM recruit WHERE isdelete IS NULL order by id desc limit "+num+",5";
		System.out.println(sqlStr);
		ArrayList<HashMap<String, Object>> recruitList  = Dao.query(sqlStr);
		request.setAttribute("recruitlist", recruitList);
		request.setAttribute("page", page);
		String strSql = "select id from recruit WHERE isdelete IS NULL";
		ArrayList<HashMap<String, Object>>  alllist = Dao.query(strSql);
		int allnum = alllist.size();
		int allpage = (int) Math.ceil((double)allnum/5);
		request.setAttribute("allnum",allnum);
		request.setAttribute("allpage", allpage);
		request.getRequestDispatcher("/co/recruitlist.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
