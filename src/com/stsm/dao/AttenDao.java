package com.stsm.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.stsm.bean.Atten;
import com.stsm.bean.AttenInfo;
import com.stsm.util.COMUtil;
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
	 * 查询所有考勤信息
	 * @return
	 */
	public List<Atten> getAtten()
	{
		List<Atten> list = new ArrayList<Atten>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	        String sqlQuery = "select * from atten";
	        pstmt = conn.prepareStatement(sqlQuery);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Atten atten = loadData(rs);
	        	list.add(atten);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
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
			if(atten.getDate()==null)               pstmt.setNull(index++, Types.VARCHAR);  else    pstmt.setString(index++,COMUtil.dataToStr(atten.getDate()));
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
	 * 单项数据查询考勤信息
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
	* 按考勤Date查找信息
	* @param atten_date
	* @return 考勤对象
	*/
	public Atten queryAttenByDate(Date atten_date){
		List<Atten> atten = queryAttenBySingleData("atten_date",String.valueOf(COMUtil.dataToStr(atten_date)));
		return atten.size()==0?null:atten.get(0);
	}
	/**
	* 按考勤period查找信息
	* @param atten_period
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByPeriod(String atten_period){
		List<Atten> atten = queryAttenBySingleData("atten_period",String.valueOf(atten_period));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤start_staff查找信息
	* @param atten_start_staff
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByStartStaff(String atten_start_staff){
		List<Atten> atten = queryAttenBySingleData("atten_start_staff",String.valueOf(atten_start_staff));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤start_time查找信息
	* @param atten_start_time
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByStartTime(String atten_start_time){
		List<Atten> atten = queryAttenBySingleData("atten_start_time",String.valueOf(atten_start_time));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤end_staff查找信息
	* @param atten_end_staff
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByEndStaff(String atten_end_staff){
		List<Atten> atten = queryAttenBySingleData("atten_end_staff",String.valueOf(atten_end_staff));
		return atten.size()==0?null:atten;
	}
	/**
	* 按考勤end_time查找信息
	* @param atten_end_time
	* @return 考勤对象
	*/
	public List<Atten> queryAttenByEndTime(String atten_end_time){
		List<Atten> atten = queryAttenBySingleData("atten_end_time",String.valueOf(atten_end_time));
		return atten.size()==0?null:atten;
	}
	
	/*************************************
	 * 通过过去的天数获取员工考勤记录
	 * @param pastDay 要查询的过去天数
	 * @return 考勤表
	 */
	public List<Atten> getAttenByPastDay(int pastDay){
		List<Atten> list = new ArrayList<Atten>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery =
	        		"SELECT * FROM atten WHERE "
	        		+ "DATEDIFF(atten_date,NOW())<=0 AND DATEDIFF(atten_date,NOW())>-? "
	        		+ "ORDER BY atten_date";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setInt(index++, pastDay);
	        rs = pstmt.executeQuery();
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
				+ "atten_period=?,"
				+ "atten_start_staff=?,"
				+ "atten_start_time=?,"
				+ "atten_end_staff=?,"
				+ "atten_end_time=? "
				+ "WHERE atten_id=?";
			pstmt=conn.prepareStatement(sql);
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
	
	/**
	 * 分页结果集合
	 * @param pageNo 当前页
	 * @param pageSize 每页记录数
	 * @param queryStr 查询字段
	 * @param sortField  排序字段及排序方式 ASC(升序)、DESC(降序)
	 * @return 分页结果集合
	 */
	public List<Atten> getPageDataAtten(int pageNo,int pageSize,String queryStr,String sortField)
	{
		List<Atten> list = new ArrayList<Atten>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT * "
	        		+ "FROM atten "
	        		+ "WHERE CONCAT(IFNULL(atten_date,''),IFNULL(atten_period,'')) LIKE ?"
	        		+ "ORDER BY "+(sortField.length()==0?"atten_id":sortField)+" LIMIT ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Atten atten = loadData(rs);
	        	list.add(atten);
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
	public int getPageDataAttenCount(String queryStr) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT COUNT(*) "
	        		+ "FROM atten "
	        		+ "WHERE CONCAT(IFNULL(atten_date,''),IFNULL(atten_period,'')) LIKE ?";
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
	 * 所有考勤信息统计分页list
	 * @param pageNo
	 * @param pageSize
	 * @param queryStr
	 * @param sortField
	 * @param str_id
	 * @param str_in
	 * @param str_out
	 * @param type 1:考勤统计 2：已考勤统计 3:未考勤统计
	 * @return
	 */
	public List<AttenInfo> getPageDateAttenInfo(int pageNo,int pageSize,String queryStr,String sortField,String[] str_inid,String[] str_in,String[] str_outid,String[] str_out){
		List<AttenInfo> list = new ArrayList<AttenInfo>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT staff_id,staff_name,staff_number "
	        		+ "FROM staff "
	        		+ "WHERE CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,'')) LIKE ? "
	        		+ "ORDER BY "+(sortField.length()==0?"staff_id":sortField)+" LIMIT ?,?";
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        pstmt.setInt(index++, pageSize * (pageNo-1));
	        pstmt.setInt(index++, pageSize);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
		        AttenInfo atteninfo = new AttenInfo();
		        atteninfo.setAttenInfo_name(rs.getString("staff_name"));
		        atteninfo.setAttenInfo_number(rs.getString("staff_number"));
		        atteninfo.setAttenInfo_in(COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))>-1?str_in[COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))]:"");
		        atteninfo.setAttenInfo_out(COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))>-1?str_out[COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))]:"");
		        list.add(atteninfo);	
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
	}
	
	/**
	 * 已经考勤信息、未考勤信息
	 * @param queryStr
	 * @param sortField
	 * @param str_inid
	 * @param str_in
	 * @param str_outid
	 * @param str_out
	 * @param type 1：已经考勤信息  2：未考勤信息
	 * @return
	 */
	public List<AttenInfo> getPageDateAttenInfo(String queryStr,String sortField,String[] str_inid,String[] str_in,String[] str_outid,String[] str_out,int type){
		List<AttenInfo> list = new ArrayList<AttenInfo>();
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT staff_id,staff_name,staff_number "
	        		+ "FROM staff "
	        		+ "WHERE CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,'')) LIKE ? "
	        		+ "ORDER BY "+(sortField.length()==0?"staff_id":sortField);
	        pstmt = conn.prepareStatement(sqlQuery);
	        pstmt.setString(index++, "%"+queryStr+"%");
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	if(type==1) //已经考勤
	        	{
	        		AttenInfo atteninfo = new AttenInfo();
		        	atteninfo.setAttenInfo_name(rs.getString("staff_name"));
		        	atteninfo.setAttenInfo_number(rs.getString("staff_number"));
		        	if(COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))>-1 && COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))>-1) {
		        		atteninfo.setAttenInfo_in(str_in[COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))]);
		        		atteninfo.setAttenInfo_out(str_out[COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))]);
		        		list.add(atteninfo);
		        	}else
		        		continue;
	        	}else if(type==2) //未考勤
	        	{
	        		AttenInfo atteninfo = new AttenInfo();
		        	if(COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))>-1 && COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))>-1) {
		        	}else {
		        		atteninfo.setAttenInfo_name(rs.getString("staff_name"));
			        	atteninfo.setAttenInfo_number(rs.getString("staff_number"));
		        		atteninfo.setAttenInfo_in(COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))>-1?
		        				str_in[COMUtil.printArray(str_inid, String.valueOf(rs.getInt("staff_id")))]:"");
			        	atteninfo.setAttenInfo_out(COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))>-1?
			        			str_out[COMUtil.printArray(str_outid, String.valueOf(rs.getInt("staff_id")))]:"");
			        	list.add(atteninfo);
		        	}
	        	}
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	       DBUtil.closeJDBC(rs, pstmt, conn);
	    }
	    return list;
	}
	/**
	 * 
	 * @param queryStr
	 * @return
	 */
	public int getPageDataAttenInfoCount(String queryStr) {
		int count = 0;
	    Connection conn = DBUtil.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try{
	    	int index = 1;
	        String sqlQuery ="SELECT COUNT(*) "
	        		+ "FROM staff "
	        		+ "WHERE CONCAT(IFNULL(staff_number,''),IFNULL(staff_name,'')) LIKE ?";
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
	
	
//	public static void main(String [] args)
//	{
//		AttenDao dao = new AttenDao();
//		List<Atten> atten = dao.getPageDataAtten(2, 3, "12", "atten_date");
//		System.out.println(atten.size());
//	}
	
}