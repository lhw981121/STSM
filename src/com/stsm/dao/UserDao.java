package com.stsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.stsm.bean.User;
import com.stsm.util.DBUtil;

/**
* User类Dao层
* @author 节奏葳
* @version 1.0
*/
public class UserDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 用户对象
	*/
	public User loadData(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setID(rs.getInt("user_id"));
    	obj.setType(rs.getInt("user_type"));
    	obj.setName(rs.getString("user_name"));
    	obj.setAccount(rs.getString("user_account"));
    	obj.setEmail(rs.getString("user_email"));
    	obj.setPhone(rs.getString("user_phone"));
    	obj.setPassword(rs.getString("user_password"));
    	obj.setCreated(rs.getTimestamp("created_at"));
    	obj.setUpdated(rs.getTimestamp("updated_at"));
    	return obj;
	}
	
	/**
	* 获取所有用户信息
	* @return 用户对象List
	*/
	public List<User> getUserInfo() {
		List<User> list = new ArrayList<User>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from user";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	User obj = loadData(rs);
	        	list.add(obj);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
    }
	
	/**
	* 新增用户
	* @param user 用户对象
	* @return 新增用户的主键
	*/
	public int createUser(User user){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int user_id = 0;
		try{
			String sql =
			"insert into user("
			+ "user_type,"
			+ "user_name,"
			+ "user_account,"
			+ "user_email,"
			+ "user_phone,"
			+ "user_password)"
    		+ "values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			pstmt.setInt(index++, user.getType());
			if(user.getName()==null)	pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++,user.getName());
			if(user.getAccount()==null)	pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++,user.getAccount());
			if(user.getEmail()==null)	pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++,user.getEmail());
			if(user.getPhone()==null)	pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++,user.getPhone());
			if(user.getPassword()==null)pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++,user.getPassword());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				user_id = rs.getInt(1);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return user_id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return user_id;
	}
	
	/**
  	* 修改用户
  	* @param user 用户对象
  	* @return 是否成功
  	*/
    public boolean updateUser(User user) {
    	Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
        try{
			String sql = null;
			sql=
			"update user set "
			+ "user_type=?,"
			+ "user_name=?,"
			+ "user_account=?,"
			+ "user_email=?,"
			+ "user_phone=?,"
			+ "user_password=? "
			+ "where user_id=?";
			int index = 1;
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(index++,user.getType());
			if(user.getName()==null)	pstmt.setNull(index++, Types.VARCHAR);	else pstmt.setString(index++,user.getName());
			if(user.getAccount()==null)	pstmt.setNull(index++, Types.VARCHAR);	else pstmt.setString(index++,user.getAccount());
			if(user.getEmail()==null)	pstmt.setNull(index++, Types.VARCHAR);	else pstmt.setString(index++,user.getEmail());
			if(user.getPhone()==null)	pstmt.setNull(index++, Types.VARCHAR);	else pstmt.setString(index++,user.getPhone());
			if(user.getPassword()==null)pstmt.setNull(index++, Types.VARCHAR);	else pstmt.setString(index++,user.getPassword());
			pstmt.setInt(index++,user.getID());
			pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return true;
    }
    
    /**
	* 单项数据查询用户
	* @param str 查询字段
	* @param value 查询数据
	* @return 用户对象
	*/
	public List<User> queryUserBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		List<User> obj = new ArrayList<User>();
		try{
			String sql="select * from user where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			if(rs.next()){
				obj.add(loadData(rs));
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return obj;
	}
	
	/**
	* 按用户ID查找用户
	* @param user_id 
	* @return 用户对象
	*/
	public User queryUserByID(int user_id){
		List<User> obj = queryUserBySingleData("user_id",String.valueOf(user_id));
		return obj.size()==0?null:obj.get(0);
	}
	
	/**
	* 按用户名查找用户
	* @param user_name 用户名
	* @return 用户对象
	*/
	public List<User> queryUserByName(String user_name){
		return queryUserBySingleData("user_name",user_name);
	}
	
	/**
	* 按用户账号查找用户
	* @param user_account 用户账号
	* @return 用户对象
	*/
	public User queryUserByAccount(String user_account){
		List<User> obj = queryUserBySingleData("user_account",String.valueOf(user_account));
		return obj.size()==0?null:obj.get(0);
	}
	
	/**
	* 按邮箱查找用户
	* @param user_email 用户邮箱
	* @return 用户对象
	*/
	public User queryUserByEmail(String user_email){
		List<User> obj = queryUserBySingleData("user_email",String.valueOf(user_email));
		return obj.size()==0?null:obj.get(0);
	}
	
	/**
	* 按手机号查找用户
	* @param user_phone 用户手机号
	* @return 用户对象
	*/
	public User queryUserByPhone(String user_phone){
		List<User> obj = queryUserBySingleData("user_phone",String.valueOf(user_phone));
		return obj.size()==0?null:obj.get(0);
	}
	
	public static void main(String[] args) {
	}
}
