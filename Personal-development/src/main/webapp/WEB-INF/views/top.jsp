<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
  <div id="app">

    <div class="header">
      <h1 class="site_logo">家計簿支援システム</h1>
      <div class="user">
        <p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
        <form class="logout_form" action="logout" method="get">
          <button class="logout_btn" type="submit">
            <img src="images/ドアアイコン.png">ログアウト</button>
        </form>
      </div>
    </div>

    <hr>
	
    <div class="btn"><a class="basic_btn regist" href="/insert">新規登録</a> </div>
    
    <p>${ msg }</p>
    <form method="get" action="/search">
    <div class="search_container">
      <label>年/月</label>
            <select name="month" class="base-text">
	            <c:forEach var="yearMonth" items="${YearMonthList}">
	              <option value=${ yearMonth.getMonth() } <c:if test="${ yearMonth.getMonth() eq keyword }">selected</c:if>>${ yearMonth.getYear() }/${ yearMonth.getMonth() }</option>
	            </c:forEach>
            </select>
      <input type="submit" value="&#xf002">
    </div> 
    <div class="caption"><p>検索結果：${ SearchResult.size() }件</p></div><div class="order"><a class="basic_btn regist" href="/graph">グラフ</a></div>
    </form>
    <br>
    
    <table>
		
        
      <thead>
        <tr>
          <th>日付</th>
          <th>カテゴリ</th>
          <th>出費</th>
          <th>詳細</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="data" items="${SearchResult}">
          <tr>
            <td>${ data.getDate() }</td>
            <td>${ data.getCategoryName() }</td>
            <td>${ data.getExpense() }円</td>
            <td><a class="detail_btn" href="./detail?id=${ data.getId() }">詳細</a></td>
          </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <footer></footer>

  
</body>
</html>