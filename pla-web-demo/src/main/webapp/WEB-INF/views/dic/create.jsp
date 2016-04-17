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
    <link href="${ctx}/css/docs.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx}/js/html5shiv.min.js"></script>
    <script src="${ctx}/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<jsp:include page="/includes/top.inc.jsp"/>
<div class="container" role="main">
    <h3>新建数据字典</h3>
    <form:form id="dicForm" cssClass="form-horizontal" action="${ctx}/dic/save" method="post" commandName="dic">
        <div class="form-group">
            <label class="col-md-2 control-label" for="dicKey">键：</label>
            <div class="col-md-5">
                <form:input path="dicKey" cssClass="form-control" id="dicKey" placeholder="数据字典键，必填项"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label" for="dicValue">值：</label>
            <div class="col-md-5">
                <form:input path="dicValue" cssClass="form-control" id="dicKey" placeholder="数据字典值，必填项"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label" for="dicContent">内容：</label>
            <div class="col-md-5">
                <form:input path="dicContent" cssClass="form-control" id="dicContent" placeholder="数据字典内容"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label" for="keyDesc">键描述：</label>
            <div class="col-md-5">
                <form:input path="keyDesc" cssClass="form-control" id="keyDesc" placeholder="数据字典键描述"/>
            </div>
        </div>
        <div class="col-md-offset-2">
            <button type="submit" class="btn btn-success">Submit</button>
            <button type="reset" class="btn btn-warning">Reset</button>
        </div>
    </form:form>
</div>
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
</body>
</html>
