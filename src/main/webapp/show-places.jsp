<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Add Place</title>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div role="navigation">
    <div class="navbar navbar-inverse">
        <a href="/" class="navbar-brand">UA Tourist Assistant</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/login">Login</a></li>
                <li><a href="/registration">New Registration</a></li>
                <li><a href="/show-users">All Users</a></li>
                <li><a href="/show-places">All Places</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center" id="tasksDiv">
    <h3>All Users</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Type</th>
                <th>Image</th>
                <th>User</th>
                <th>City</th>
                <th>Street</th>
                <th>House</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="place" items="${places}">
                <tr>
                    <td>${place.idPlace}</td>
                    <td>${place.placeName}</td>
                    <td>${place.placeDescription}</td>
                    <td>${place.placeType}</td>
                    <td id="placeListTg"><img src="images/place_img/${place.imagePath}" id="placeListImg" class="img-fluid" alt="img"/></td>
                    <td>${place.userName}</td>
                    <td>${place.address.city.cityName}</td>
                    <td>${place.address.street}</td>
                    <td>${place.address.numberHouse}</td>
                    <td><a href="/delete-place?idPlace=${place.idPlace}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                    <td><a href="#"><span
                            class="glyphicon glyphicon-pencil"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="static/js/jquery-1.11.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
