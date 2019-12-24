package com.stsm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.stsm.bean.User;
import com.stsm.dao.UserDao;
import com.stsm.util.DBUtil;

/**
* User类Service层
* @author 节奏葳
* @version 1.0
*/
public class UserService {
	private User user = new User();
	private UserDao userDao = new UserDao();
	private Logger logger = Logger.getLogger(getClass());
	
	/**
	* 用户注册
	* @param user 用户对象
	* @return 新增用户的主键
	*/
	public int register(User user){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int user_id = 0;
		try{
			String sql = "";
			conn.setAutoCommit(false);//开启事务
			if(user.getType()==1) {//新增员工
				
			}
			//添加用户信息
			sql=
			"insert into user("
			+ "user_type,"
			+ "user_name,"
			+ "user_email,"
			+ "user_phone,"
			+ "user_password,"
    		+ "applicant_id,"
    		+ "company_id)"
    		+ "values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, user.getType());
			pstmt.setString(2, user.getName());
			if(user.getEmail()==null)	pstmt.setNull(3, Types.VARCHAR);	else	pstmt.setString(3,user.getEmail());
			if(user.getPhone()==null)	pstmt.setNull(4, Types.VARCHAR);	else	pstmt.setString(4,user.getPhone());
			pstmt.setString(5, user.getPassword());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				user_id = rs.getInt(1);
			}
			conn.commit();//提交事务 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	try {
	    		conn.rollback();//回滚事务
	    		return 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	        return user_id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return user_id;
	}
	
	/**
	* 用户登录
	* @param account 账号
	* @param password 密码
	* @return 用户对象
	*/
	public User login(String account,String password){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		User obj = null;
		try{
			String sql="select * from user where (user_account=? or user_email=? or user_phone=?) and user_password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, account);
			pstmt.setString(3, account);
			pstmt.setString(4, password);
			rs=pstmt.executeQuery();
			if(rs.next()&&(account.equals(rs.getString("user_account"))||account.equals(rs.getString("user_email"))||account.equals(rs.getString("user_phone")))){
				obj = userDao.loadData(rs);
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		if(obj!=null) {
			logger.info("用户 "+obj.getID()+user.getName()+" 成功登录系统");
		}
		return obj;
	}
	
	/**
	* 按用户账号查找用户
	* @param account 账号
	* @return 用户对象
	*/
	public User queryUserByAccount(String account){
		user = userDao.queryUserByAccount(account);
		if(user==null) {
			user = userDao.queryUserByPhone(account);
		}
		if(user==null) {
			user = userDao.queryUserByEmail(account);
		}
		return user;
	}
	
	/**
	* 修改用户名
	* @param user 用户对象
	* @param user_name 用户名
	* @return 是否成功
	*/
    public boolean updateUserName(User user,String user_name) {
    	user.setName(user_name);
    	if(userDao.updateUser(user)) {
    		logger.info("用户 "+user.getID()+user.getName()+" 成功修改用户名为 "+user_name);
    		return true;
    	}else {
    		logger.error("用户 "+user.getID()+user.getName()+" 修改用户名为 "+user_name+" 失败");
    		return false;
    	}
    }
	
	/**
	* 修改密码
	* @param user 用户对象
	* @param user_password 密码
	* @return 是否成功
	*/
    public boolean updateUserPassword(User user,String user_password) {
    	user.setPassword(user_password);
    	if(userDao.updateUser(user)) {
    		logger.info("用户 "+user.getID()+user.getName()+" 成功修改密码为 "+user_password);
    		return true;
    	}else {
    		logger.error("用户 "+user.getID()+user.getName()+" 修改密码为 "+user_password+" 失败");
    		return false;
    	}
    }
    
    /**
	* 修改用户手机号
	* @param user 用户对象
	* @param user_phone 手机号
	* @return 是否成功
	*/
    public boolean updateUserPhone(User user,String user_phone) {
    	user.setPhone(user_phone);
    	if(userDao.updateUser(user)) {
    		logger.info("用户 "+user.getID()+user.getName()+" 成功修改手机号为 "+user_phone);
    		return true;
    	}else {
    		logger.error("用户 "+user.getID()+user.getName()+" 修改手机号为 "+user_phone+" 失败");
    		return false;
    	}
    }
    
    /**
	* 修改用户邮箱
	* @param user 用户对象
	* @param user_email 邮箱
	* @return 是否成功
	*/
    public boolean updateUserEmail(User user,String user_email) {
    	user.setEmail(user_email);
    	if(userDao.updateUser(user)) {
    		logger.info("用户 "+user.getID()+user.getName()+" 成功修改邮箱为 "+user_email);
    		return true;
    	}else {
    		logger.error("用户 "+user.getID()+user.getName()+" 修改邮箱为 "+user_email+" 失败");
    		return false;
    	}
    }

}
