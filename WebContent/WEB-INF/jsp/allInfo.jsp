<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>LeaKInfo列表</title>
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	table-layout: fixed;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {
	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	height:50px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	white-space: nowrap;
　　overflow: hidden;
　　text-overflow: ellipsis;
}
</style>
</head>
<body>
<table class="hovertable">
<tr><td>包名：<select><option></option></select></td>
<td>版本号：<select><option></option></select></td></tr>
</table>
<table class="hovertable">
<tr><th>包名</th><th>可能的内存泄露信息</th><th>操作</th></tr>
<c:forEach var="info" items="${infos}">
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"><td>${info.packagename}</td><td>${info.leakContext}</td>
<td></td>
</tr>
</c:forEach>
</table>
</body>
</html>