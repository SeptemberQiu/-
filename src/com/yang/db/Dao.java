package com.yang.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Dao {
	//execute方法之间输入sql语句，直接执行数据库的插入更嗨和删除
	public static int execute(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		int resultSet = 0;
		conn = DButil.con();
		
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(ps.toString());
			resultSet = ps.executeUpdate();
			//System.out.println("excute:"+resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButil.close(conn);
		}
		
		return resultSet;
	}
	
	public static int execute(String sql,Object[] params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int resultSet = 0;
		conn = DButil.con();
		
		try {
			
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			System.out.println(ps.toString());
			resultSet = ps.executeUpdate();
			
//			System.out.println("excute:"+resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	public static int insertObj(String tableName,Map<String, Object> dataItem) {
		String fieldStr = "";
		String valueStr = "";
		
		Object[] valueObjs = new Object[dataItem.size()];
		int i = 0;
		for (String key : dataItem.keySet()) {
			fieldStr = fieldStr + key + ",";
			valueStr = valueStr + "?" + ",";
			valueObjs[i] = dataItem.get(key);
			i++;
		}
		fieldStr = fieldStr.substring(0, fieldStr.length()-1);
		valueStr = valueStr.substring(0, valueStr.length()-1);
	//	System.out.println(fieldStr);
	//	System.out.println(valueStr);
		String sqlStr = "insert into " + tableName + "(" +fieldStr +") values (" + valueStr +")"; 
		int exe = execute(sqlStr,valueObjs);
	//	System.out.println(exe);
		return exe;
	}
	
	
	//query方法：通过输入查询语句自动生成hashmap对象组合的数组
	public static ArrayList<HashMap<String, Object> > query (String sql){
		ResultSet result = null;
		Connection conn = null;
		PreparedStatement pStatement = null;
		
		ArrayList<HashMap<String, Object>> tempArray = new ArrayList<HashMap<String,Object>>();
		
		try {
			conn = DButil.con();
			pStatement = conn.prepareStatement(sql);
			System.out.println(pStatement.toString());
			result = pStatement.executeQuery();
			
			ResultSetMetaData metaData = result.getMetaData();
			int columuCount = metaData.getColumnCount();
			
			while(result.next()) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				for(int i=1;i<=columuCount;i++) {
					hashMap.put(metaData.getColumnName(i), result.getObject(i));			
				}
				tempArray.add(hashMap);
			}
			return tempArray;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButil.close(conn);
		}
		return tempArray;
	}
	
	public static ArrayList<HashMap<String, Object>> query(String sql , Object[] params){
		ResultSet result = null;
		Connection conn = null;
		PreparedStatement pStatement = null;
		
		ArrayList<HashMap<String, Object>> tempArray = new ArrayList<HashMap<String,Object>>();
		
		try {
			conn = DButil.con();
			pStatement = conn.prepareStatement(sql);
			//System.out.println(pStatement.toString());
			for(int i=0;i<params.length;i++) {
				//Object item = params[i];
				pStatement.setObject(i+1, params[i]);
			}
			System.out.println(pStatement.toString());
			
			result = pStatement.executeQuery();
			
			ResultSetMetaData metaData = result.getMetaData();
			int columuCount = metaData.getColumnCount();
			
			while(result.next()) {
				HashMap<String, Object> hashMap = new HashMap<>();
				for(int i=1;i<=columuCount;i++) {
					hashMap.put(metaData.getColumnName(i), result.getObject(i));			
				}
				tempArray.add(hashMap);
			}
			return tempArray;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DButil.close(conn);
		} finally {
			DButil.close(conn);
		}
		return tempArray;
	}
	
	public static void main(String[] args) {
		
		/*
		 * String strSql =
		 * "insert into user (username,password) values (\"小红\",\"123456\")"; int
		 * execute = Dao.execute(strSql);
		 */
		
		
		/*
		 * String strSql = "insert into user(username,password) values(?,?)"; Object[]
		 * arrayList = {"小黑","12345"}; int execute = execute(strSql,arrayList);
		 * System.out.println(execute);
		 */
		
		/*
		 * String strSql = "select * from user where id>?"; Object[] arrayList = {1};
		 * ArrayList<HashMap<String, Object>> query = Dao.query(strSql, arrayList);
		 * System.out.println(query.size());
		 * System.out.println(JSON.toJSONString(query));
		 */
		
		/*
		 * HashMap<String, Object> hashMap = new HashMap<>(); hashMap.put("username",
		 * "小王"); hashMap.put("password", "123"); insertObj("user", hashMap);
		 */
		
	}
}
