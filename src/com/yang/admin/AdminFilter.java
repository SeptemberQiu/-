package com.yang.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yang.db.Dao;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {


    public AdminFilter() {
        
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("admin filter");
		//��ȡ·����Ϣ
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		//ͨ��·��ƥ�����,ע��͵�½ҳ�棬����ҳ��һ����Ҫ�жϵ�¼��Ϣ��Ȩ��
		if(servletPath.equals("/admin/register")||servletPath.equals("/admin/login")) {
			chain.doFilter(request, response);
		}else {
			//��ȡsession����
			HttpSession session = req.getSession();
			if(session.getAttribute("username")!=null) {
				
				String username = (String) session.getAttribute("username");
				//���Ҵ��û����û�����
				String sqlStr = "select * from user where username = ?";
				String[] params = {username};
				ArrayList<HashMap<String, Object>>  res = Dao.query(sqlStr, params);
				HashMap<String, Object> user = res.get(0);
				
				System.out.println(user.get("userType"));
				//�����Ƿ��з���Ȩ�ޣ��ж�
				if(user.get("userType").equals("admin")) {
					//������ȷ������
					chain.doFilter(request, response);
					//System.out.println("servlet path:"+req.getServletPath());
				}else {
					req.setAttribute("httpUrl", "/admin/login");
					req.setAttribute("info", "��û��Ȩ�޷��ʣ�������ת��½ҳ�棡");
					req.setAttribute("title", "����ʧ��");
					req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
					
				}
			}else {
				//���û�е�¼����ת��¼ҳ��
				req.setAttribute("httpUrl", "/admin/login");
				req.setAttribute("info", "��û�е�¼��������ת��½ҳ�棡");
				req.setAttribute("title", "δ��¼");
				req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
			}
		}
		
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
