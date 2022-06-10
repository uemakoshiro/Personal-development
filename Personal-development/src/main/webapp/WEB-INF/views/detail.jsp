<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
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

  <div class="update">
    <div class="form_body">
      <form:form action="delete" modelAttribute="delete" method="post">
        <fieldset class="label-130 product_block">
          <p class="error"><!-- エラーメッセージ --></p>
          
          <div>
          	<label>ID</label>
            <form:input type="text" path="id" readonly="true" class="base-text"/>
            <span class="error"><form:errors path="id" cssStyle="color: red"/></span>
          </div>
          <div>
            <label>日付</label>
            <input type="text" name="date" value=${ info.getDate() } readonly class="base-text">
          </div>
          <div>
            <label>カテゴリ</label>
            <input type="text" name="categoryName" value=${ info.getCategoryName() } readonly class="base-text">
          </div>
          <div>
            <label>出費</label>
            <input type="text" name="expense" value=${ info.getExpense() } readonly class="base-text">
          </div>
          <div>
            <label>メモ</label>
            <textarea name="memo" readonly class="base-text" style="background-color: rgb(209, 209, 209);">${ info.getMemo() }
            </textarea>
          </div>
        </fieldset>
        <div>
          <div class="btns">
          	
            <input type="button" onclick="openModal()" value="削除" class="basic_btn">
            <input type="button" onclick="location.href='./update?id=${ info.getId() }'" value="編集" class="basic_btn">
            
            <input type="button" onclick="location.href='./top'" value="戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">削除しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">削除</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
        </div>
      </form:form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>