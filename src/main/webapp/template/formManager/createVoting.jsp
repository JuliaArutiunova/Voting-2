<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

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
<h2>Создать новое голосование</h2>
<p>В каждой категории должно быть минимум 2 участника</p>
<br>
<form action="create" method="POST">
    <div id="artist-container">
        Создать артиста:  <button type="button" onclick="addFieldArtist()">+</button><br><br>
        <label for="artist">Artist:</label>
        <input type="text" name="artist"><br>
        <label for="artist">Artist:</label>
        <input type="text" name="artist"><br>
    </div>
    <br>
    <br>
    <div id="genre-container">
        Создать жанр:  <button type="button" onclick="addFieldGenre()">+</button><br><br>
        <label for="genre">Genre:</label>
        <input type="text" name="genre"><br>
        <label for="genre">Genre:</label>
        <input type="text" name="genre"><br>


    </div>
<br>
    <input type='submit' value='Готово'>
</form>

<script>

    function addFieldArtist() {
        const container = document.getElementById("artist" + '-container');
        const newField = document.createElement('div');

        newField.innerHTML = `
            <label for="artist">Artist:</label>
            <input type="text" name="artist">
            <button type="button" onclick="removeField(this)">-</button>
        `;

        container.appendChild(newField);
    }

    function addFieldGenre() {
        const container = document.getElementById("genre" + '-container');
        const newField = document.createElement('div');

        newField.innerHTML = `
            <label for="genre">Genre:</label>
            <input type="text" name="genre">
            <button type="button" onclick="removeField(this)">-</button>
        `;

        container.appendChild(newField);
    }

    function removeField(button) {
        button.parentNode.remove();
    }

</script>

</body>
</html>