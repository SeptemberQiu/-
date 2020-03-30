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
		
		//������Ӧ������Ϊjson
		response.setContentType("text/json");
		//��ȡ���е����Ź���
		/* String sqlStr = "select * from article limit 0,10"; */
		String sqlStr = "select id,title from article limit 0,10";
		ArrayList<HashMap<String, Object>> articlelist =  Dao.query(sqlStr);
		//����ȡ�����ݸ�json
		String jsonString = JSON.toJSONString(articlelist);
		response.getWriter().println(jsonString);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
