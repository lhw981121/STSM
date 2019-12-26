package com.stsm.bean;

import java.util.Date;

public class Staff {
	//员工主键
	private int ID;
	//员工姓名
	private String name;
	//员工工号
	private String number;
	//员工性别
	private int sex;
	//员工年龄
	private int age;
	//员工职位
	private int position;
	//员工业绩
	private double performance;
	//员工奖金
	private double bonus;
	//员工家庭住址
	private String house;
	//最近一次考勤时间(上班)
	private Date lastIn;
	//最近一次考勤时间(下班)
	private Date lastOut;
	//添加时间
	private Date created_at;
	//修改时间
	private Date updated_at;
	
	public Staff(int ID ,String name,String number,int sex,int age,int position,double performance,double bonus,String house)
	{
		this.ID = ID;
		this.name = name;
		this.number = number;
		this.sex = sex;
		this.age = age;
		this.position = position;
		this.performance = performance;
		this.bonus = bonus;
		this.house = house;
	}
	
	public Staff() {
		
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public double getPerformance() {
		return performance;
	}
	
	public void setPerformance(double performance) {
		this.performance = performance;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public String getHouse() {
		return house;
	}
	
	public void setHouse(String house) {
		this.house = house;
	}

	public Date getLastIn() {
		return lastIn;
	}

	public void setLastIn(Date lastIn) {
		this.lastIn = lastIn;
	}

	public Date getLastOut() {
		return lastOut;
	}

	public void setLastOut(Date lastOut) {
		this.lastOut = lastOut;
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
}
