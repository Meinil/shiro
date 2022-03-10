<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <input type="text" name="username"> <br />
        <input type="password" name="password"> <br />
        <input type="submit" value="提交"> <br />
    </form>
    <a href="register.jsp">注册</a>
</body>
</html>