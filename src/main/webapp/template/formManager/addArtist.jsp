<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
<head>
<meta charset="UTF-8">
</head>
<h2>Добавить артиста</h2>
<form action="addArtist" method="POST">

        Артист:<br>
        <input type="text" name="newArtist"/><br><br>

<input type='submit' value='Готово'>
</form>

</body>
</html>