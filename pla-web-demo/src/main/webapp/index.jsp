<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--<link rel="icon" href=""${ctx}/favicon.ico">-->
    <title>Plasticene demo</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/theme.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx}/js/html5shiv.min.js"></script>
    <script src="${ctx}/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<jsp:include page="/includes/top.inc.jsp" />
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Plasticene</h1>
        <p>Plasticene是一个深度集成了Hibernate4(JPA)、Spring的持久化框架，以类似ROR的形式对持久层进行简单清晰的操作，可减少80%以上
            的DAO层的代码，大大增加了开发效率。支持原生SQL的操作及映射，并提供了一套完整简便的事务处理的方法。</p>
        <p>
            <a class="btn btn-lg btn-primary" href="./#navbar" role="button">下载文档 &raquo;</a>
        </p>
    </div>
</div>
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
</body>
</html>
