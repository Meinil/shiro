<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
</head>
<body>
    <h1>系统主页</h1>
    <ul>
        <shiro:hasAnyRoles name="user">
            <li>
                <a href="">用户管理</a>
                <ul>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href="">添加</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href="">修改</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href="">删除</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:select:*">
                        <li><a href="">查询</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>
        <shiro:hasRole name="product">
            <li><a href="">商品管理</a></li>
            <li><a href="">订单管理</a></li>
        </shiro:hasRole>
        <shiro:hasRole name="user">
            <li><a href="">物流管理</a></li>
        </shiro:hasRole>
    </ul>
    <a href="${pageContext.request.contextPath}/user/logout" >登出</a>
</body>
</html>