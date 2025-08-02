<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>
<jsp:include page="./tool/header.jsp" />

<form action="Cart" method="POST">
  <input type="submit" name="itemId" value="1">カフェラテ<br>
  <input type="submit" name="itemId" value="2">アールグレイ<br>
  <input type="submit" name="itemId" value="3">チーズケーキ<br>
  <input type="submit" name="itemId" value="4">ガトーショコラ<br>
  <input type="submit" name="itemId" value="0">注文終了<br>
</form>

<hr>

<form action="Cart" method="POST">
  <label for="itemIdInput">商品番号を入力してください:</label>
  <input type="text" id="itemIdInput" name="itemId" pattern="\d+" required>
  <input type="submit" value="追加">
</form>

<%
  String message = (String) session.getAttribute("message");
  if (message != null) {
%>
    <p style="color: green;"><%= message %></p>
<%
    session.removeAttribute("message"); // 一度だけ表示
  }
%>



</body>
</html>