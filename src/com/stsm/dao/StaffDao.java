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
import com.stsm.bean.Staff;
import com.stsm.util.DBUtil;



public class StaffDao {
	/**
	* 装载数据
	* @param rs 结果集
	* @return 职位对象
	*/
	public Staff loadData(ResultSet rs) throws SQLException {
		Staff staff = new Staff();
		staff.setID(rs.getInt("staff_id"));
		staff.setName(rs.getString("staff_name"));
		staff.setNumber(rs.getString("staff_number"));
		staff.setSex(rs.getInt("staff_sex"));
		staff.setAge(rs.getInt("staff_age"));
		staff.setPosition(rs.getInt("staff_position"));
		staff.setPerformance(rs.getDouble("staff_performance"));
		staff.setBonus(rs.getDouble("staff_bonus"));
		staff.setHouse(rs.getString("staff_house"));
		staff.setLastIn(rs.getDate("staff_last_in"));
		staff.setLastOut(rs.getDate("staff_last_out"));
		staff.setCreated(rs.getDate("created_at"));
    	return staff;
	}
	/**
	 * 员工信息添加
	 * @param staff
	 * @return 员工ID
	 */
	public int createStaff(Staff staff){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int index=1;
	    int id = 0;
	    try{
			String sql = "";
			sql="INSERT "
				+ "INTO staff"
				+ "(staff_name,"
				+ "staff_number,"
				+ "staff_sex,"
				+ "staff_age,"
				+ "staff_position,"
				+ "staff_performance,"
				+ "staff_bonus,"
				+ "staff_house,"
				+ "staff_last_in,"
				+ "staff_last_out)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			if(staff.getName()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getName());
			if(staff.getNumber()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getNumber());
			pstmt.setInt(index++, staff.getSex());
			pstmt.setInt(index++, staff.getAge());
			pstmt.setInt(index++, staff.getPosition());
			pstmt.setDouble(index++, staff.getPerformance());
			pstmt.setDouble(index++, staff.getBonus());
			if(staff.getHouse()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getHouse());
			if(staff.getLastIn()==null) 			pstmt.setNull(index++, Types.DATE); 	else 	pstmt.setTimestamp(index++,new Timestamp(staff.getLastIn().getTime()));
			if(staff.getLastOut()==null) 			pstmt.setNull(index++, Types.DATE); 	else 	pstmt.setTimestamp(index++,new Timestamp(staff.getLastOut().getTime()));
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
	 * 删除员工信息
	 * @param staff_id 员工id
	 * @return true： 删除成功 false：删除失败
	 */
	public boolean deleteStaff(int staff_id){
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "DELETE FROM staff WHERE staff_id=?";
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
	* 获取所有员工信息
	* @return 员工对象List
	*/
	public List<Staff> getStaff() {
		List<Staff> list = new ArrayList<Staff>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from staff";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Staff staff = loadData(rs);
	        	list.add(staff);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
    }
	/**
	 *  单项数据查询员工信息
	 * @param str 查询字段
	 * @param value 查询数据
	 * @return
	 */
	public List<Staff> queryStaffBySingleData(String str,String value){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Staff> staff = new ArrayList<Staff>();
		try{
			String sql="select * from staff where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				staff.add(loadData(rs));
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return staff;
	}
	/**
	* 按员工ID查找信息
	* @param staff_id
	* @return 员工对象
	*/
	public Staff queryStaffByID(int staff_id){
		List<Staff> staff = queryStaffBySingleData("staff_id",String.valueOf(staff_id));
		return staff.size()==0?null:staff.get(0);
	}
	/**
	 * 按员工NUMBER查找信息
	 * @param staff_number 
	 * @return 员工对象
	 */
	public Staff queryStaffByNUMBER(int staff_number){
		List<Staff> staff = queryStaffBySingleData("staff_number",String.valueOf(staff_number));
		return staff.size()==0?null:staff.get(0);
	}
	/**
	* 按员工SEX查找信息
	* @param staff_sex
	* @return 员工对象集合
	*/
	public List<Staff> queryStaffBySEX(int staff_sex){
		List<Staff> staff = queryStaffBySingleData("staff_sex",String.valueOf(staff_sex));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工AGE查找信息
	* @param staff_age
	* @return 员工对象集合
	*/
	public List<Staff> queryStaffByAGE(int staff_age){
		List<Staff> staff = queryStaffBySingleData("staff_age",String.valueOf(staff_age));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工POSITION查找职位信息
	* @param staff_position
	* @return 员工对象
	*/
	public List<Staff> queryStaffByPOSITION(int staff_position){
		List<Staff> staff = queryStaffBySingleData("staff_position",String.valueOf(staff_position));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工PREFORMANCE查找职位信息
	* @param staff_performance
	* @return 员工对象
	*/
	public List<Staff> queryStaffByPREFORMANCE(int staff_performance){
		List<Staff> staff = queryStaffBySingleData("staff_performance",String.valueOf(staff_performance));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工BOUNS查找职位信息
	* @param staff_bonus
	* @return 员工对象
	*/
	public List<Staff> queryStaffByBOUNS(int staff_bonus){
		List<Staff> staff = queryStaffBySingleData("staff_bonus",String.valueOf(staff_bonus));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工HOUSE查找职位信息
	* @param staff_house
	* @return 员工对象
	*/
	public List<Staff> queryStaffByHOUSE(int staff_house){
		List<Staff> staff = queryStaffBySingleData("staff_house",String.valueOf(staff_house));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工LASTIN查找职位信息
	* @param staff_last_in
	* @return 员工对象
	*/
	public List<Staff> queryStaffByLASTIN(Date staff_last_in){
		List<Staff> staff = queryStaffBySingleData("staff_last_in",String.valueOf(staff_last_in));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工LASTOUT查找职位信息
	* @param staff_last_out
	* @return 员工对象
	*/
	public List<Staff> queryStaffByLASTOUT(Date staff_last_out){
		List<Staff> staff = queryStaffBySingleData("staff_last_out",String.valueOf(staff_last_out));
		return staff.size()==0?null:staff;
	}
	/**
	 * 按员工ID修改员工信息
	 * @param staff 
	 * @return true 修改成功 false 修改失败
	 */
	public boolean updataStaff(Staff staff){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int index =1;
	    try{
			String sql = "";
			sql="UPDATE staff SET "
			+ "staff_name=?,"
			+ "staff_number=?,"
			+ "staff_sex=?,"
			+ "staff_age=?,"
			+ "staff_position=?,"
			+ "staff_performance=?,"
			+ "staff_bonus=?,"
			+ "staff_house=?,"
			+ "staff_last_in=?,"
			+ "staff_last_out=? "
			+ "WHERE staff_id=?";
			pstmt=conn.prepareStatement(sql);
			if(staff.getName()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getName());
			if(staff.getNumber()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getNumber());
			pstmt.setInt(index++, staff.getSex());
			pstmt.setInt(index++, staff.getAge());
			pstmt.setInt(index++, staff.getPosition());
			pstmt.setDouble(index++, staff.getPerformance());
			pstmt.setDouble(index++, staff.getBonus());
			if(staff.getHouse()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getHouse());
			if(staff.getLastIn()==null) 			pstmt.setNull(index++, Types.DATE); 	else 	pstmt.setTimestamp(index++,new Timestamp(staff.getLastIn().getTime()));
			if(staff.getLastOut()==null) 			pstmt.setNull(index++, Types.DATE); 	else 	pstmt.setTimestamp(index++,new Timestamp(staff.getLastOut().getTime()));
			pstmt.setInt(index++, staff.getID());
			pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	        return false;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return true;
	}
	
//	public static void main(String [] args){
//		StaffDao dao = new StaffDao();
//		Staff staff = new Staff();
//		staff = dao.queryStaffByID(2);
//		staff.setNumber("12");
//		System.out.println(dao.updataStaff(staff));
//	}
	
}
