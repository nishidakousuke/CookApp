<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/test.css"> --%>
<script src="<%= request.getContextPath() %>/js/process.js"></script>
<%@page import="java.util.*" %>

<% List<String> CookingNames = (List<String>)request.getAttribute("CookingNames"); %>

<p id="cook_display">料理一覧を表示する</p>

<p>工程を登録する料理名を選択してください。</p>
<form id="process_form" action="process_register" method="post">
<% for(int i = 1; i <= CookingNames.size(); i++) { %>
<p id="cook<%= i %>">
<input class="cook" type="radio" name="cook" value="<%= CookingNames.get(i - 1) %>"><%= CookingNames.get(i - 1) %><br>
</p>
<% } %>

<h2>追加する工程を入力してください。</h2>
<p id="button">工程を追加する</p>
<div id="process_and_time">
工程：<input type="text" name="process1">時間：<input id="time1" type="text" name="time1"><br>
工程：<input type="text" name="process2">時間：<input id="time2" type="text" name="time2"><br>
工程：<input type="text" name="process3">時間：<input id="time3" type="text" name="time3"><br>
<input id="process_input" type="submit" value="確定" style="display: none;">
</div>
</form>
<%@include file="../footer.html" %>
