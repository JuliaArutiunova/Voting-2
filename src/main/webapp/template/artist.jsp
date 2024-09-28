<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    input {
        margin-bottom: 10px;
    }
</style>
</head>
<body>

<br>
<form action="editArtist" method="POST">
    <fieldset>
       <legend> Создать артиста:</legend>
       <input type="text" name="CreateArtist"><br>
    </fieldset>
  <br>
    <fieldset>
        <legend> Удалить артиста:</legend>
        <select name="deleteArtist">
                    <option disabled selected value></option>
                    <c:forEach items="${deleteArtists}" var="item">
                        <option value="<c:out value="${item.key}"/>"><c:out value="${item.value}"/></option>
                    </c:forEach>
                </select><br>
    </fieldset>

  <br>
<input type='submit' value='Готово'>
</form>


</body>
</html>