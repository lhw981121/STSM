package com.stsm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.stsm.bean.Atten;
import com.stsm.util.DBUtil;

public class AttenDao {	
	/**
	* 装载数据
	* @param rs 结果集
	* @return 职位对象
	*/
	public Atten loadData(ResultSet rs) throws SQLException {
		Atten atten = new Atten();
		atten.setID(rs.getInt("atten_id"));
		atten.setDate(rs.getDate("atten_date"));
		atten.setPeriod(rs.getString("atten_period"));
		atten.setStartStaff(rs.getString("atten_start_staff"));
		atten.setStartTime(rs.getString("atten_start_time"));
		atten.setEndStaff(rs.getString("atten_end_staff"));
		atten.setEndTime(rs.getString("atten_end_time"));
		atten.setCreated(rs.getDate("created_at"));
		atten.setUpdated(rs.getDate("updated_at"));
    	return atten;
	}
	/**
	 * 考勤纪律添加
	 * @param atten
	 * @return 考勤ID
	 */
	public int createAtten(Atten atten)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int index=1;
	    int id = 0;
	    try{
			String sql = "";
			sql="INSERT INTO atten"
				+ "(atten_date,"
				+ "atten_period,"
				+ "atten_start_staff,"
				+ "atten_start_time,"
				+ "atten_end_staff,"
				+ "atten_end_time)"
				+ "VALUES(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			if(atten.getDate()==null)               pstmt.setNull(index++, Types.VARCHAR);  else    pstmt.setTimestamp(index++,new Timestamp(atten.getDate().getTime()));
			if(atten.getPeriod()==null )            pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getPeriod());
			if(atten.getStartStaff()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getStartStaff());
			if(atten.getStartTime()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getStartTime());
			if(atten.getEndStaff()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getEndStaff());
			if(atten.getEndTime()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getEndTime());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return id;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return id;
	}
	/**
	 * 删除考勤记录信息
	 * @param atten_id 考勤id
	 * @return true： 删除成功 false：删除失败
	 */
	public boolean deleteAtten(int atten_id){
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "DELETE FROM atten WHERE atten_id=?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	    }catch(Exception e) {
	        e.printStackTrace();
	    	return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return true;
	}
	/**
	 *  单项数据查询考勤信息
	 * @param str 查询字段
	 * @param value 查询数据
	 * @return
	 */
	public List<Atten> queryAttenBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Atten> atten = new ArrayList<Atten>();
		try{
			String sql="select * from atten where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				atten.add(loadData(rs));
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return atten;
	}
	/**
	* 按考勤ID查找信息
	* @param atten_id
	* @return 考勤对象
	*/
	public Atten queryAttenByID(int atten_id){
		List<Atten> atten = queryAttenBySingleData("atten_id",String.valueOf(atten_id));
		return atten.size()==0?null:atten.get(0);
	}
	/**
	* 按考勤DATE查找信息
	* @param atten_date
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByDATE(Date atten_date){
		List<Atten> atten = queryAttenBySingleData("atten_date",String.valueOf(atten_date));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤PERIOD查找信息
	* @param atten_period
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByPERIOD(String atten_period){
		List<Atten> atten = queryAttenBySingleData("atten_period",String.valueOf(atten_period));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤STARTSTAFF查找信息
	* @param atten_start_staff
	* @return 考勤对象
	*/
	public List<Atten> queryAttenBySTARTSTAFF(String atten_start_staff){
		List<Atten> atten = queryAttenBySingleData("atten_start_staff",String.valueOf(atten_start_staff));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤STARTTIME查找信息
	* @param atten_start_time
	* @return 考勤对象
	*/
	public List<Atten> queryAttenBySTARTTIME(String atten_start_time){
		List<Atten> atten = queryAttenBySingleData("atten_start_time",String.valueOf(atten_start_time));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤ENDSTAFF查找信息
	* @param atten_end_staff
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByENDSTAFF(String atten_end_staff){
		List<Atten> atten = queryAttenBySingleData("atten_end_staff",String.valueOf(atten_end_staff));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤ENDTIME查找信息
	* @param atten_end_time
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByENDTIME(String atten_end_time){
		List<Atten> atten = queryAttenBySingleData("atten_end_time",String.valueOf(atten_end_time));
		return atten.size()==0?null:atten;
	}
	/**
	 * 按员工ID修改员工信息
	 * @param staff 
	 * @return true 修改成功 false 修改失败
	 */
	public boolean updataStaff(Atten atten){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int index =1;
	    try{
			String sql = "";
			sql="UPDATE atten SET "
				+ "atten_date=?,"
				+ "atten_period=?,"
				+ "atten_start_staff=?,"
				+ "atten_start_time=?,"
				+ "atten_end_staff=?,"
				+ "atten_end_time=? "
				+ "WHERE atten_id=?";
			pstmt=conn.prepareStatement(sql);
			if(atten.getDate()==null)               pstmt.setNull(index++, Types.VARCHAR);  else    pstmt.setTimestamp(index++,new Timestamp(atten.getDate().getTime()));
			if(atten.getPeriod()==null )            pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getPeriod());
			if(atten.getStartStaff()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getStartStaff());
			if(atten.getStartTime()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getStartTime());
			if(atten.getEndStaff()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getEndStaff());
			if(atten.getEndTime()==null)			pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, atten.getEndTime());
			pstmt.setInt(index++, atten.getID());
			pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return true;
	}
	
//	public static void main(String [] args)
//	{
//		AttenDao dao = new AttenDao();
//		Atten atten = new Atten();
//		atten=dao.queryAttenByID(1);
//		atten.setID(4);
//		atten.setPeriod("321");
//		System.out.println(dao.createAtten(atten));
//	}
}