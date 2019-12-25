package com.stsm.bean;

import java.util.Date;

/**
* 用户类
* @author 节奏葳
* @version 1.0
*/
public class User{
	//用户标识
	private int ID;
	//用户类型：1(员工)、8(管理员)
	private int type;
	//用户名
	private String name;
	//用户账号(员工工号)
	private	String account;
	//用户邮箱
    private String email;
    //用户手机号
    private String phone;
    //用户密码
    private String password;
    //新增时间
	private Date created_at;
	//修改时间
    private Date updated_at;
    
    public User() {}
    
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getTypeStr() {
		String typeStr = "";
		switch(type) {
		case 1:typeStr = "员工";break;
		case 8:typeStr = "管理员";break;
		}
		return typeStr;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created_at;
	}

	public void setCreated(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated() {
		return updated_at;
	}

	public void setUpdated(Date updated_at) {
		this.updated_at = updated_at;
	}

	public static void main(String[] args) {
	}
}