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
		//默认获取不到第几页的参数，那么默认打开第一页
		if(page==null) {
			page="1";
		}
		int num = (Integer.parseInt(page)-1)*5;
	
		//用户列表的获取
		String sqlStr = "SELECT * FROM article order by id desc limit "+num+",5";
		System.out.println(sqlStr);
		//String[] params = {num+""}; 
		ArrayList<HashMap<String, Object>> articlelist  = Dao.query(sqlStr);
		//把数据转发过去
		request.setAttribute("articlelist", articlelist);
		//把第几页传过去
		request.setAttribute("page", page);
		
		
		//获取总用户数
		String strSql = "select id from article";
		ArrayList<HashMap<String, Object>>  alllist = Dao.query(strSql);
		int allnum = alllist.size();
		//System.out.println(allnum);
		//进行上舍入
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
