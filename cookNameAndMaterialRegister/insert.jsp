<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<script src="<%= request.getContextPath() %>/js/cookNameAndMaterialRegister.js"></script>

<h2>追加する料理と材料を入力してください。</h2>

<p id="button">登録する材料を追加するならここをクリック！</p>

<form id="form" action="insert" method="post">
    <p>料理名</p>
    <input type="text" name="name"><br>
    <p>材料名/量</p>
    材料<input type="text" name="material1">：量<input type="text" name="amount1"><br>
    材料<input type="text" name="material2">：量<input type="text" name="amount2"><br>
    材料<input type="text" name="material3">：量<input type="text" name="amount3"><br>
    <input id="input" type="submit" value="追加">
</form>

<%@include file="../footer.html" %>
