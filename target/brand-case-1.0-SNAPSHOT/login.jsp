<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2022/8/28
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/brand-case/loginServlet" id="form" method="post">
        <h1 id="loginMsg">管理员登录</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <p>管理员账号:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>

        <p>密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>记住我:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
<%--            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;--%>
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>

</body>
</html>
