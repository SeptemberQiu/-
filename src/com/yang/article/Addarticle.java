package com.yang.article;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.db.Dao;


@WebServlet("/admin/article/addarticle")
public class Addarticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Addarticle() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/admin/article/addarticle.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content= request.getParameter("content");
		long time = new Date().getTime();
		
		HashMap<String, Object> article = new HashMap<>();
		article.put("title",title);
		article.put("author",author);
		article.put("content",content);
		article.put("time",time);
		
		Dao.insertObj("article", article);
		
		//告知成功
		request.setAttribute("httpUrl", "/admin/article/articlelist");
		request.setAttribute("info", "添加成功！返回至文章页面！");
		request.setAttribute("title", "添加成功！");
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}

}
