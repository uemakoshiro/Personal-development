<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
  <div class="header">
    <h1 class="site_logo">家計簿支援システム</h1>
  </div>

<div class="btn"><a class="basic_btn regist" href="/create_user">アカウント作成</a></div>

  <div class="login_form">
    <img src="./images/logo.png" class="login_logo">
    <p class="error">${ msg }</p>

    <form:form action="/login" modelAttribute="index" method="post">
      <fieldset>
        <div class="cp_iptxt">
          <form:input class="base_input" type="text" path="loginId" placeholder="ID"/>
          <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
          <div class="error"><form:errors path="loginId" cssStyle="color: red"/></div>
        </div>

        <div>
          <form:input class="base_input" type="password" path="pass" placeholder="PASS"/>
          <div class="error"><form:errors path="pass" cssStyle="color: red"/></div>
        </div>
      </fieldset>
      <form:button class="logout_btn" type="submit">ログイン</form:button>
    </form:form>
  </div>
</body>
</html>