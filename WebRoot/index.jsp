<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>测试页面</title>
    <style type="text/css">
    	input
    	{
    		width:60px;
    	}
    </style>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript">
		function queryByID()
		{
			var id=$("#txtID").val();
			var param={id:id};
			$.ajax({
                type: 'post',
                url: "queryByIDAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("queryByID");
                	console.log(result);
                	if(result.jsonObject)
                	{
                		console.log("name:"+result.jsonObject.name+",pwd:"+result.jsonObject.pwd);
                	}
                }
            });  
		}
		function queryByName()
		{
			var name=$("#txtName").val();
			var param={name:name};
			$.ajax({
                type: 'post',
                url: "queryByNameAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("queryByName");
                	console.log(result);
                	if(result.jsonArray)
                	{
                		for(var i=0;i<result.jsonArray.length;i++){
                			console.log("name:"+result.jsonArray[i].name+",pwd:"+result.jsonArray[i].pwd);
                		}
                	}
                }
            });  
		}
		function queryAll()
		{
			$.ajax({
                type: 'post',
                url: "queryAllAction.action",
                data: null,
                dataType: 'json',
                success:function(result){
                	console.log("queryAll");
                	console.log(result);
                	if(result.jsonArray)
                	{
                		for(var i=0;i<result.jsonArray.length;i++){
                			console.log("name:"+result.jsonArray[i].name+",pwd:"+result.jsonArray[i].pwd);
                		}
                	}
                }
            });  
		}
		function insert()
		{
			var name=$("#txtName").val();
			var pwd=$("#txtPwd").val();
			var param={name:name,pwd:pwd};
			$.ajax({
                type: 'post',
                url: "insertAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("insert");
                	console.log(result);
                }
            });  
		}
		function update()
		{
			var id=$("#txtID").val();
			var name=$("#txtName").val();
			var pwd=$("#txtPwd").val();
			var param={id:id,name:name,pwd:pwd};
			$.ajax({
                type: 'post',
                url: "updateAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("update");
                	console.log(result);
                }
            });  
		}
		function deleteS()
		{
			var id=$("#txtID").val();
			var param={id:id};
			$.ajax({
                type: 'post',
                url: "deleteAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("delete");
                	console.log(result);
                }
            });  
		}
		function deleteByID()
		{
			var id=$("#txtID").val();
			var param={id:id};
			$.ajax({
                type: 'post',
                url: "deleteByIDAction.action",
                data: param,
                dataType: 'json',
                success:function(result){
                	console.log("deleteByID");
                	console.log(result);
                }
            });  
		}
	</script>
  </head>
  <body>
    <dl>
	    <dt>测试</dt>
	    <dd>
	    <a href="demoAction.action">测试(获取名称)</a><br>
	    id:<input id="txtID">name:<input id="txtName">pwd:<input id="txtPwd"><br>
	    <button onclick="queryByID()">id查询</button><br>
	   	<button onclick="queryByName()">名称查询</button><br>
	   	<button onclick="queryAll()">全部查询</button><br>
	    <button onclick="insert()">插入</button><br>
	    <button onclick="update()">更新</button><br>
	    <button onclick="deleteS()">删除</button><br>
	    <button onclick="deleteByID()">id删除</button><br>
	    </dd>
	</dl>
  </body>
</html>
