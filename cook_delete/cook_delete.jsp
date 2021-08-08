<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/test.css"> --%>
<script src="<%= request.getContextPath() %>/js/cook_delete.js"></script>
<%@page import="java.util.*" %>

<% List<String> cooks = (List<String>)request.getAttribute("cooks"); %>

<%-- <p id="cook_display">料理一覧を表示する</p> --%>

<p>削除できる料理一覧</p>

<form action="delete" method="post">
<% for(int i = 1; i <= cooks.size(); i++) { %>
<p><input type="radio" name="cook_name" value="<%= cooks.get(i - 1)%>"><%= cooks.get(i - 1) %></p>
<% } %>
<input type="submit" value="削除する">
</form>

<%@include file="../footer.html" %>
