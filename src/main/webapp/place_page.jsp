<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place</title>
</head>
<body>
<div>
    <h1>Add Place</h1>
    <form>
        <div>
            <input type="text" name="placeName" placeholder="Enter place name"/>
        </div>
        <div>
            <input type="text" name="placeDescription" placeholder="Enter place description"/>
        </div>
        <div>

        </div>
        <div>
            <input type="file" name="image" id="imageFile"/>
            <label for="imageFile">choose image</label>
        </div>
        <div>
            <input type="text" name="userName" placeholder="Enter username"/>
        </div>
        <div>
            <input type="text" name="cityName" placeholder="Enter city"/>
        </div>
        <div>
            <input type="text" name="streetName" placeholder="Enter street"/>
        </div>
        <div>
            <input type="text" name="numberHouse" placeholder="Enter numberHouser"/>
        </div>
        <div>
            <button type="submit">Add Place</button>
        </div>
    </form>
</div>

<div>Places list</div>
<div th:each="place : ${places}">
    <p> <span th:text="${place.idPlace}"> id </span> </p>
    <p> <span th:text="${place.placeName}"> id </span> </p>
</div>
</body>
</html>
