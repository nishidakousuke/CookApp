<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<script src="<%= request.getContextPath() %>/js/process.js"></script>

<p id="button">工程を追加する</p>
<h2>追加する工程を入力してください。</h2>

<form id="form" action="insert" method="post">
    <p>料理名</p>
    <input type="text" name="name"><br>
    <p>材料名/量</p>
    材料<input type="text" name="process1"><br>
    材料<input type="text" name="process2"><br>
    材料<input type="text" name="process3"><br>
    <input id="input" type="submit" value="追加">
</form>

<%@include file="../footer.html" %>
