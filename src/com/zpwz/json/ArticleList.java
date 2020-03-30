package com.zpwz.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yang.db.Dao;


@WebServlet("/json/articlelist")
public class ArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ArticleList() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//设置响应的类型为json
		response.setContentType("text/json");
		//获取所有的新闻公告
		/* String sqlStr = "select * from article limit 0,10"; */
		String sqlStr = "select id,title from article limit 0,10";
		ArrayList<HashMap<String, Object>> articlelist =  Dao.query(sqlStr);
		//将获取的数据给json
		String jsonString = JSON.toJSONString(articlelist);
		response.getWriter().println(jsonString);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
