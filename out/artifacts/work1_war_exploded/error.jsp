<%--
  Created by IntelliJ IDEA.
  User: zhixing.zzx
  Date: 2020/9/27
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>出错啦</title>
    <link href="css/error.css" rel="stylesheet"/>
    <script language="JavaScript" src="js/error.js"></script>
</head>
<body>
<div id="errorDiv">
    <div id="errorHint">
        <p id="errorInfo">${info}</p>
        <p><span id="leaveTime">10</span>秒后自动返回到登录页面</p>
        <p>不能跳转，请<a href="DengLu.html">点击这里</a></p>
    </div>
</div>


</body>
</html>
