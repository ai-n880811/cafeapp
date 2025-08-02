<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, bean.MenuItem" %>
<html>
<head><title>カートの中身</title></head>
<body>

<jsp:include page="./tool/header.jsp" />

<h2>カートの中身</h2>

<%
    List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
    int total = 0;
    if (cart == null || cart.isEmpty()) {
%>
    <p>カートは空です。</p>
<%
    } else {
%>
    <form action="RemoveFromCart" method="POST">
    <ul>
    <% for (int i = 0; i < cart.size(); i++) {
         MenuItem item = cart.get(i);
         total += item.getPrice();
    %>
        <li>
          <%= item.getName() %> - <%= item.getPrice() %>円
          <form action="RemoveFromCart" method="POST" style="display:inline;">
              <input type="hidden" name="index" value="<%= i %>">
              <input type="submit" value="削除">
          </form>
      </li>
    <% } %>
    </ul>
    </form>
    <p>合計金額: <%= total %>円</p>
<% } %>

</body>
</html>
