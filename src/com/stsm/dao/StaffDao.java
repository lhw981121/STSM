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
				+ "staff_house)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			if(staff.getName()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getName());
			if(staff.getNumber()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getNumber());
			pstmt.setInt(index++, staff.getSex());
			if(staff.getAge()==0)                   pstmt.setNull(index++,Types.INTEGER);	else	pstmt.setInt(index++, staff.getAge());
			pstmt.setInt(index++, staff.getPosition());
			pstmt.setDouble(index++, staff.getPerformance());
			pstmt.setDouble(index++, staff.getBonus());
			if(staff.getHouse()==null)				pstmt.setNull(index++, Types.VARCHAR);	else	pstmt.setString(index++, staff.getHouse());
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
	        pstmt.setInt(1, staff_id);
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
	    List<Staff> list = new ArrayList<Staff>();
		try{
			String sql="select * from staff where "+str+"=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(loadData(rs));
			}
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
		return list;
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
	 * 按员工number 查找信息
	 * @param staff_number 
	 * @return 员工对象
	 */
	public Staff queryStaffByNumber(String staff_number){
		List<Staff> staff = queryStaffBySingleData("staff_number",String.valueOf(staff_number));
		return staff.size()==0?null:staff.get(0);
	}
	/**
	* 按员工sex查找信息
	* @param staff_sex
	* @return 员工对象集合
	*/
	public List<Staff> queryStaffBySex(int staff_sex){
		List<Staff> staff = queryStaffBySingleData("staff_sex",String.valueOf(staff_sex));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工age查找信息
	* @param staff_age
	* @return 员工对象集合
	*/
	public List<Staff> queryStaffByAge(int staff_age){
		List<Staff> staff = queryStaffBySingleData("staff_age",String.valueOf(staff_age));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工position查找职位信息
	* @param staff_position
	* @return 员工对象
	*/
	public List<Staff> queryStaffByPosition(int staff_position){
		List<Staff> staff = queryStaffBySingleData("staff_position",String.valueOf(staff_position));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工performance查找职位信息
	* @param staff_performance
	* @return 员工对象
	*/
	public List<Staff> queryStaffByPerformance(int staff_performance){
		List<Staff> staff = queryStaffBySingleData("staff_performance",String.valueOf(staff_performance));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工bonus查找职位信息
	* @param staff_bonus
	* @return 员工对象
	*/
	public List<Staff> queryStaffByBonus(int staff_bonus){
		List<Staff> staff = queryStaffBySingleData("staff_bonus",String.valueOf(staff_bonus));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工house查找职位信息
	* @param staff_house
	* @return 员工对象
	*/
	public List<Staff> queryStaffByHouse(int staff_house){
		List<Staff> staff = queryStaffBySingleData("staff_house",String.valueOf(staff_house));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工last_in查找职位信息
	* @param staff_last_in
	* @return 员工对象
	*/
	public List<Staff> queryStaffByLastIn(Date staff_last_in){
		List<Staff> staff = queryStaffBySingleData("staff_last_in",String.valueOf(staff_last_in));
		return staff.size()==0?null:staff;
	}
	/**
	* 按员工last_out查找职位信息
	* @param staff_last_out
	* @return 员工对象
	*/
	public List<Staff> queryStaffByLastOut(Date staff_last_out){
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
	/**
	 * 分页结果集合
	 * @param pageNo 当前页
	 * @param pageSize 每页记录数
	 * @param queryStr 查询字段
	 * @param sortField  排序字段及排序方式 ASC(升序)、DESC(降序)
	 * @return 分页结果集合
	 */
	public List<Staff> getPageDataStaff(int pageNo,int pageSize,String queryStr,String sortField)
	{
		List<Staff> list = new ArrayList<Staff>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT * "
	        		+ "FROM staff "
	        		+ "WHERE CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,''),IFNULL(staff_age,'')) LIKE ? "
	        		+ "ORDER BY "+(sortField.length()==0?"staff_id":sortField)+" LIMIT ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
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
	 * 分页总数
	 * @param queryStr 查询字段
	 * @return
	 */
	public int getPageDataStaffCount(String queryStr) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT COUNT(*) "
	        		+ "FROM staff "
	        		+ "WHERE CONCAT(IFNULL(staff_name,''),IFNULL(staff_number,''),IFNULL(staff_age,'')) LIKE  ?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
            	count = rs.getInt(1);
            }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return 0;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return count;
    }
	
	/**
	 * 分页结果集合
	 * @param pageNo 当前页
	 * @param pageSize 每页记录数
	 * @param staff_sex 员工性别 3(不考虑)
	 * @param staff_position 员工职位 10(不考虑)
	 * @param last_in 员工是否已打卡(上班) 0(不考虑)、1(是)、2(否)
	 * @param last_out 员工是否已打卡(下班) 0(不考虑)、1(是)、2(否)、3(上班或下班没打卡)
	 * @param queryStr 查询字段
	 * @param sortField 排序字段及排序方式 ASC(升序)、DESC(降序)
	 * @return 分页结果集合
	 */
	public List<Staff> getPageDataStaff(int pageNo,int pageSize,int staff_sex,int staff_position,int last_in,int last_out,String queryStr,String sortField){
		List<Staff> list = new ArrayList<Staff>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT * FROM staff WHERE "
	        		+ "CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,''),IFNULL(staff_age,'')) LIKE ? "
	        		+ (staff_sex==3?"":"AND staff_sex=? ")
	        		+ (staff_position==10?"":"AND staff_position=? ")
	        		+ (last_in==0?"":"AND to_days(staff_last_in) "+(last_in==1?"=":"!=")+" to_days(now()) ")
	        		+ (last_out==0?"":(last_out!=3?("AND to_days(staff_last_out) "+(last_out==1?"=":"!=")+" to_days(now()) ")
	        				:"AND (to_days(staff_last_in) != to_days(now()) or to_days(staff_last_out) != to_days(now())) "))
	        		+ "ORDER BY "+(sortField.length()==0?"":sortField)+" LIMIT ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        if(staff_sex!=3)		pstmt.setInt(index++, staff_sex);
	        if(staff_position!=10)	pstmt.setInt(index++, staff_position);
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
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
	 * 分页总数
	 * @param staff_sex 员工性别 3(不考虑)
	 * @param staff_position 员工职位 10(不考虑)
	 * @param last_in 员工是否已打卡(上班) 0(不考虑)、1(是)、2(否)
	 * @param last_out 员工是否已打卡(下班) 0(不考虑)、1(是)、2(否)、3(上班或下班没打卡)
	 * @param queryStr 查询字段
	 * @return 分页数据总量
	 */
	public int getPageDataStaffCount(int staff_sex,int staff_position,int last_in,int last_out,String queryStr) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	    	String sqlQuery ="SELECT COUNT(*) FROM staff WHERE "
	    		+ "CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,''),IFNULL(staff_age,'')) LIKE ? "
				+ (staff_sex==3?"":"AND staff_sex=? ")
				+ (staff_position==10?"":"AND staff_position=? ")
				+ (last_in==0?"":"AND to_days(staff_last_in) "+(last_in==1?"=":"!=")+" to_days(now()) ")
				+ (last_out==0?"":(last_out!=3?("AND to_days(staff_last_out) "+(last_out==1?"=":"!=")+" to_days(now()) ")
						:"AND (to_days(staff_last_in) != to_days(now()) or to_days(staff_last_out) != to_days(now())) "));
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        if(staff_sex!=3)		pstmt.setInt(index++, staff_sex);
	        if(staff_position!=10)	pstmt.setInt(index++, staff_position);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
            	count = rs.getInt(1);
            }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return 0;
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return count;
    }
	
	public static void main(String [] args){
		StaffDao dao = new StaffDao();
		System.out.print(dao.getPageDataStaffCount("波"));
		dao.getPageDataStaff(2, 10, "", "staff_age");
	}
}
