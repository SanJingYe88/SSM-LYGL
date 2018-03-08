<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><img src="images/logo.png" height="100%" /></a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<!-- 一级菜单 -->
			<ul class="nav navbar-nav" id="menu-nav">
				<li class="active"><a class="icon-bar" href="#">旅游管理系统</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>欢迎您,${currentUser.username}</a></li>
				<li><a href="logout.do">安全退出</a></li>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript" src="${basePath }/ttms/common/index.js"></script>
<script type="text/javascript">
	$(function() {
		// 查询用户菜单
		doGetUserMenus();
		// 给二级菜单的 menuBtn 演示添加点击事件
		$('#menu-nav').on('click', '.menuBtn', doLoadUrl);
	});
</script>
