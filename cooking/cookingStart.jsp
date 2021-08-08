<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/test.css"> --%>
<%-- <script src="<%= request.getContextPath() %>/js/cooking.js"></script> --%>
<%@page import="java.util.*" %>

<% List<String> CookingNames = (List<String>)request.getAttribute("CookingNames"); %>

<%-- <p id="cook_display">料理一覧を表示する</p> --%>

<p>調理始める料理名を選択してください。</p>
<form id="cooking_form" action="process_search" method="post">
<% for(int i = 1; i <= CookingNames.size(); i++) { %>
<p id="cooking<%= i %>">
<input class="cooking" type="radio" name="cooking" value="<%= CookingNames.get(i - 1) %>"><%= CookingNames.get(i - 1) %><br>
</p>
<% } %>

<input id="cooking_input" type="submit" value="確定">
</form>
<%@include file="../footer.html" %>
