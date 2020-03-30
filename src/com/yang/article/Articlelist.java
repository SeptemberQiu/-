package com.yang.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;


@WebServlet("/admin/article/articlelist")
public class Articlelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Articlelist() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		//Ĭ�ϻ�ȡ�����ڼ�ҳ�Ĳ�������ôĬ�ϴ򿪵�һҳ
		if(page==null) {
			page="1";
		}
		int num = (Integer.parseInt(page)-1)*5;
	
		//�û��б�Ļ�ȡ
		String sqlStr = "SELECT * FROM article order by id desc limit "+num+",5";
		System.out.println(sqlStr);
		//String[] params = {num+""}; 
		ArrayList<HashMap<String, Object>> articlelist  = Dao.query(sqlStr);
		//������ת����ȥ
		request.setAttribute("articlelist", articlelist);
		//�ѵڼ�ҳ����ȥ
		request.setAttribute("page", page);
		
		
		//��ȡ���û���
		String strSql = "select id from article";
		ArrayList<HashMap<String, Object>>  alllist = Dao.query(strSql);
		int allnum = alllist.size();
		//System.out.println(allnum);
		//����������
		//int allpage =   allnum/5;
		int allpage = (int) Math.ceil((double)allnum/5);
		
		
		request.setAttribute("allnum",allnum);
		request.setAttribute("allpage", allpage);
		
		request.getRequestDispatcher("/admin/article/articlelist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
