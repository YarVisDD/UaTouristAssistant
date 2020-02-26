<%@ page import="com.main.uatouristassistant.entity.PlaceType" %><%--
  Created by IntelliJ IDEA.
  User: Yevhenii Dykalchuk
  Date: 2/23/2020
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Place</title>
</head>
<body>
<div>
    <h1>Add Place</h1>
    <form name="addPlace" method="post" action="addPlace">
        <div>
            <input type="text" name="placeName" placeholder="Enter place name"/>
        </div>
        <div>
            <input type="text" name="placeDescription" placeholder="Enter place description"/>
        </div>
        <div>
            <c:set var="placeType">
                <%=PlaceType.values()%>
            </c:set>
<%--            <input type="text" name="placeType" placeholder="Enter place name"/>--%>
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
</body>
</html>
