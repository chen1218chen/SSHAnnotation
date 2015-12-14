package com.ssh.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.model.Student;
import com.ssh.service.IStudentService;
@ParentPackage(value="json-default")
@Controller
public class StudentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Resource
	private IStudentService studentServiceImpl;//服务类（函数集）
	
	private int id;
	private String name;
	private String pwd;
	private JSONObject jsonObject;//返回给页面的JSON对象
	private JSONArray jsonArray;//返回给页面的JSON对象数组
	//获取数据表中所有数据并传到JSP显示
	@Action(value = "/demoAction", results = {
		@Result(name = ERROR, location="/error.jsp"),
		@Result(name = SUCCESS, location = "/success.jsp")}, exceptionMappings = { @ExceptionMapping(exception = "java.lang.Exception", result = "error")})
	@Override
	public String execute() {
		System.out.println(studentServiceImpl.getName());
		return SUCCESS;
	}
	
	//获取数据表中所有数据并传到JSP显示
	@Action(value = "/queryByIDAction", results = {
		@Result(name = "queryByID", type="json")})
	public String queryByID() {
		Student student=studentServiceImpl.queryByID(id);
		if(student==null)
			jsonObject=null;
		else
			jsonObject=JSONObject.fromObject(student);
		return "queryByID";
	}

	@Action(value = "/queryByNameAction", results = {
		@Result(name = "queryByName", type="json")})
	public String queryByName() {
		List<Student> list=studentServiceImpl.queryByName(name);
		if(list==null)
			jsonArray=null;
		else
			jsonArray=JSONArray.fromObject(list);
		return "queryByName";
	}
	
	@Action(value = "/queryAllAction", results = {
		@Result(name = "queryAll", type="json")})
	public String queryAll() {
		List<Student> list=studentServiceImpl.queryAll();
		if(list==null)
			jsonArray=null;
		else
			jsonArray=JSONArray.fromObject(list);
		return "queryAll";
	}
	
	@Action(value = "/insertAction",results = { 
		@Result(name = ERROR, location="/error.jsp"),
		@Result(name = SUCCESS, location = "/success.jsp")}, exceptionMappings = { @ExceptionMapping(exception = "java.lang.Exception", result = "error")})
	public String insert() {
		Student student=new Student(name,pwd);
		studentServiceImpl.insert(student);
		return SUCCESS;
	}
	
	@Action(value = "/updateAction",results = { 
		@Result(name = ERROR, location="/error.jsp"),
		@Result(name = SUCCESS, location = "/success.jsp")}, exceptionMappings = { @ExceptionMapping(exception = "java.lang.Exception", result = "error")})
	public String update() {
		Student student=new Student();
		student.setId(id);
		student.setName(name);
		student.setPwd(pwd);
		studentServiceImpl.update(student);
		return SUCCESS;
	}
	
	@Action(value = "/deleteAction",results = { 
		@Result(name = ERROR, location="/error.jsp"),
		@Result(name = SUCCESS, location = "/success.jsp")}, exceptionMappings = { @ExceptionMapping(exception = "java.lang.Exception", result = "error")})
	public String delete() {
		Student student=new Student();
		student.setId(id);
		studentServiceImpl.delete(student);
		return SUCCESS;
	}
	
	@Action(value = "/deleteByIDAction",results = { 
		@Result(name = ERROR, location="/error.jsp"),
		@Result(name = SUCCESS, location = "/success.jsp")}, exceptionMappings = { @ExceptionMapping(exception = "java.lang.Exception", result = "error")})
	public String deleteByID() {
		studentServiceImpl.deleteByID(id);
		return SUCCESS;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}
}