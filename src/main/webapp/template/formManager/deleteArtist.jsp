<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
<head>
    <meta charset="UTF-8">
</head>
<h2>Удалить артиста</h2>
<form action="deleteArtist" method="POST">
 <p style="color:Red;">Внимание! При удалении артиста, его результаты также будут удалены!</p>
<select name="deleteArtist">
            <option value="">Не выбран</option>
            <c:forEach items="${deleteArtists}" var="item">
                <option value="<c:out value="${item.key}"/>"><c:out value="${item.value}"/></option>
            </c:forEach>
        </select><br>

    <input type='submit' value='Готово'>
</form>
</body>
</html>