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
    <title>Pla demo</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/theme.css" rel="stylesheet">
    <link href="${ctx}/css/docs.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx}/js/html5shiv.min.js"></script>
    <script src="${ctx}/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<jsp:include page="/includes/top.inc.jsp"/>
<div class="container theme-showcase" role="main">
    <p>
        <button type="button" class="btn btn-primary" onclick="location.href='${ctx}/dic/create'">新建数据字典</button>
    </p>
    <form:form class="form-inline" action="${ctx}/dic/list" method="post" commandName="dic">
        <div class="form-group">
            <label for="dicKey">键：</label>
            <form:input  cssClass="form-control input-sm" path="dicKey" />&nbsp;&nbsp;
        </div>
        <div class="form-group">
            <label for="dicValue">值：</label>
            <form:input  cssClass="form-control input-sm" path="dicValue"/>&nbsp;&nbsp;
        </div>
        <div class="form-group">
            <label for="dicContent">内容：</label>
            <form:input cssClass="form-control input-sm" path="dicContent" />&nbsp;&nbsp;
        </div>
        <button type="submit" class="btn btn-success btn-sm">查找</button>
    </form:form>
    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>键</th>
                <th>值</th>
                <th>内容</th>
                <th>键描述</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="dic" items="${pager.list}" varStatus="status">
                <tr>
                    <td>${dic.id}</td>
                    <td>${dic.dicKey}</td>
                    <td>${dic.dicValue}</td>
                    <td>${dic.dicContent}</td>
                    <td>${dic.keyDesc}</td>
                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                操作 <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="${ctx}/dic/edit/${dic.id}">修改</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="javascript:;" onclick="if(confirm('确认删除？')){location.href='${ctx}/dic/delete/${dic.id}'}">删除</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
</body>
</html>
