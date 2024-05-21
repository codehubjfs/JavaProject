package com.mms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mms.beans.Login;
import com.mms.util.DBUtil;

public class LoginDAO {
	
	public String loginCheck(Login loginBean) {
		String query="select * from database.login where email=? and password=?";
		
		try{
			Connection con=DBUtil.openConnection();
			PreparedStatement ps=con.prepareStatement(query);
			//System.out.println(loginBean.getEmail());
			ps.setString(1,loginBean.getEmail());
			ps.setString(2,loginBean.getPassword());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				return "true";
			}
			else{
				return "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "error";
	}
}
