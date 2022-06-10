<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="top">家計簿支援システム</a></h1>
    <div class="user">
      <p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
      <form class="logout_form" action="logout" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
      <p>${ msg }</p>
      <p class="error">${ error }</p>
	
      <form:form action="updateexe" modelAttribute="update" method="post">
        <fieldset class="label-130">
          <div>
            <label>ID</label>
            <form:input type="text" path="id" readonly="true" class="base-text"/>
            <span class="error"><form:errors path="id" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>日付</label>
            <form:input type="date" path="date" class="base-text"/>
            <span class="error"><form:errors path="date" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>カテゴリ</label><form:select path="category" class="base-text">
              
              <form:options items="${categoryList}" itemLabel="name" itemValue="id"/>
	            
            </form:select>
            
          </div>
          <div>
            <label>出費</label> 
            <form:input type="text" path="expense" class="base-text"/>
            <span class="error"><form:errors path="expense" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>メモ</label>
            <form:textarea path="memo" class="base-text"></form:textarea>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./top'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form:form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>