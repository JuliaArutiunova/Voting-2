<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
</head>
<body>
<h2>Голос не принят :(</h2>
 <c:forEach items="${errorMessages}" var="item">
    <p style="color:Tomato;">${item.getMessage()}</p>
 </c:forEach>
<br>
<input type="button" onclick="history.back();" value="Вернуться к анкете"/>
</body>
</html>
