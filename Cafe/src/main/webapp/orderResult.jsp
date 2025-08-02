<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, bean.MenuItem" %>
<html>
<head>
    <title>注文完了</title>
</head>
<body>
<jsp:include page="./tool/header.jsp" />
<h2>注文内容</h2>

<h3>ご注文ありがとうございました！</h3>
<p>以下がご注文の内容です。</p>


<%
	// セッションからList<MenuItem>を取得、合計金額用にtotalを初期化
    List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
    int total = 0;
    
    // カートが空だった場合
    if (cart == null || cart.isEmpty()) {
%>
    <p>カートは空です。</p>
    
<%
    } else {
%>

<ul>

<!-- 各商品名と金額を表示、合計金額も計算 -->
<% for (MenuItem item : cart) {
     total += item.getPrice(); %>
     <li><%= item.getName() %> - <%= item.getPrice() %>円</li>
<% } %>
</ul>

<!-- 合計金額の表示 -->
<p>合計金額: <%= total %>円</p>

<% } %> 



</body>
</html>
