<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/includes/taglibs.inc.jsp" %>
<%
    String path = request.getServletPath();
    int type = 1;
    if (path.equals("/index.jsp")) {
        type = 1;
    } else if (path.equals("/about.jsp")) {
        type = 3;
    } else {
        type = 2;
    }
%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx}/">Plasticene</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="<%=(type==1?"active":"")%>"><a href="${ctx}/">首页</a></li>
                <li class="<%=(type==2?"active":"")%> dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">模块展示 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/dic/list">数据字典管理</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">角色管理</a></li>
                        <li><a href="#">用户管理</a></li>
                    </ul>
                </li>
                <li class="<%=(type==3?"active":"")%>"><a href="#about">关于</a></li>
            </ul>
        </div>
    </div>
</nav>