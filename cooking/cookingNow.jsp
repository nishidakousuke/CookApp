<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/test.css"> --%>
<script src="<%= request.getContextPath() %>/js/cooking.js"></script>
<%@page import="java.util.*" %>

<% List<String> processes = (List<String>)request.getAttribute("processes"); %>
<% List<String> times = (List<String>)request.getAttribute("times"); %>

<%-- <p id="cook_display">料理一覧を表示する</p> --%>

<p>調理工程</p>

<% for(int i = 1; i <= processes.size(); i++) { %>
<p id="process<%= i %>" class="process">工程：<%= processes.get(i - 1) %></p>
<p id="time<%= i %>" style="display: none;"><%= times.get(i - 1) %></p>
<% } %>

<p id="time_count" style="display: none;">時間の計測を開始する</p>
<p id="count_up" style="display: none;"></p>

<p id="prev">前へ</p>
<p id="next">次へ</p>

<%@include file="../footer.html" %>
