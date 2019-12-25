<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta content="IE=edge,chrome=1" charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.3, maximum-scale=0.5, minimum-scale=0.3">
<title>神葳总局</title>
<!-- Favicon -->
<link rel="icon" href="/STSM/public/assets/img/favicon.png" type="image/png" sizes="32x32">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="/STSM/public/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/STSM/public/assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/STSM/public/assets/vendor/linearicons/style.css">
<link rel="stylesheet" href="/STSM/public/assets/vendor/chartist/css/chartist-custom.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="/STSM/public/assets/css/main.css">
<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">

<!-- Sweet Alert -->
<link rel="stylesheet" href="/STSM/public/css/sweet-alert/sweet-alert.css">

<!-- iziToast -->
<link rel="stylesheet" href="/STSM/public/css/iziToast/iziToast.css">

<!-- 初始化JS全局变量 -->
<script>
/* 页面语言 */
var language = '${language}';
/* 被登录过滤器拦截的url */
var requestPath = '${requestPath}';
if(requestPath.indexOf("WEB-INF")!=-1)requestPath='';
/* 用户访问受限 */
var limitedAccess = '${limitedAccess}';
/* 单页数据量 */
var pageSize = '${pageSize}';
</script>