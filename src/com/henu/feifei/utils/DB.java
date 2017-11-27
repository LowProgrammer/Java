package com.henu.feifei.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class config {
	public static final String DB_URL="jdbc:mysql://123.206.59.63:3306/javaweb";
	public static final String DB_NAME="root";
	public static final String DB_PASSWORD="root";

}
public class DB {
	private static Connection con=null;
	private static PreparedStatement state;
	private static ResultSet rs;
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(config.DB_URL, config.DB_NAME, config.DB_PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 更新，删除操作
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static boolean update(String sql,List<Object> params)throws Exception {
		boolean result=false;
		state=con.prepareStatement(sql);
		int index=1;
		if(params!=null&&!params.isEmpty()) {
			for(int i=0;i<params.size();i++) {
				state.setObject(index++,params.get(i));
			}
		}
		int count=state.executeUpdate();
		result=count>0?true:false;
		return result;
	}
	/**
	 * 查询一条数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static  Map<String,Object> find(String sql,List<Object> params)throws Exception{
		Map<String, Object> map=new HashMap<>();
		state=con.prepareStatement(sql);
		int index=1;
		if(params!=null&&!params.isEmpty()) {
			for(int i=0;i<params.size();i++) {
				state.setObject(index++,params.get(i));
			}
		}
		rs=state.executeQuery();
		ResultSetMetaData metaData=rs.getMetaData();
		int col_len=metaData.getColumnCount();
		while(rs.next()) {
			for(int i=0;i<col_len;i++) {
				String col_name=metaData.getColumnName(i+1);
				Object col_value=rs.getObject(col_name);
				if(col_value==null) {
					col_value="";
				}
				map.put(col_name, col_value);
			}
		}
		return map;
	}
	/**
	 * 查询多条数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> select(String sql, List<Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        state = con.prepareStatement(sql);  
        if(params != null && !params.isEmpty()){  
            for(int i = 0; i<params.size(); i++){  
            	state.setObject(index++, params.get(i));  
            }  
        }  
        rs = state.executeQuery();  
        ResultSetMetaData metaData = rs.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(rs.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(int i=0; i<cols_len; i++){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = rs.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
  
        return list;  
	}
	/**
	 * 通过反射查询一条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T find(String sql, List<Object> params, Class<T> cls )throws Exception{  
        T resultObject = null;  
        int index = 1;  
        state = con.prepareStatement(sql);  
        if(params != null && !params.isEmpty()){  
            for(int i = 0; i<params.size(); i++){  
                state.setObject(index++, params.get(i));  
            }  
        }  
        rs = state.executeQuery();  
        ResultSetMetaData metaData  = rs.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(rs.next()){  
            resultObject = cls.newInstance();  
            for(int i = 0; i<cols_len; i++){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = rs.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                Field field = cls.getDeclaredField(cols_name);  
                field.setAccessible(true);   
                field.set(resultObject, cols_value);  
            }  
        }  
        return resultObject;  
    }
	/**
	 * 通过反射查询多条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> select(String sql, List<Object> params,  Class<T> cls )throws Exception {  
        List<T> list = new ArrayList<T>();  
        int index = 1;  
        state = con.prepareStatement(sql);  
        if(params != null && !params.isEmpty()){  
            for(int i = 0; i<params.size(); i++){  
                state.setObject(index++, params.get(i));  
            }  
        }  
        rs = state.executeQuery();  
        ResultSetMetaData metaData  = rs.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(rs.next()){  
            T resultObject = cls.newInstance();  
            for(int i = 0; i<cols_len; i++){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = rs.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                Field field = cls.getDeclaredField(cols_name);  
                field.setAccessible(true);   
                field.set(resultObject, cols_value);  
            }  
            list.add(resultObject);  
        }  
        return list;  
    }  
	/**
	 * 关闭数据库连接
	 */
	public static void disConnect(){
		if(rs!=null) {
			try {
				rs.close();
				state.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
