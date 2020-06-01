<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Places</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container text-center" id="tasksDiv">
    <h3>All Places</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Type</th>
                <th>Number Image</th>
                <th>User</th>
                <th>City</th>
                <th>Street</th>
                <th>House</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="place" items="${places}">
                <tr>
                    <td>${place.idPlace}</td>
                    <td>${place.placeName}</td>
                    <td>${place.placeDescription}</td>
                    <td>${place.placeType}</td>
                    <c:set var="count" value="0"/>
                    <c:forEach var="image" items="${images}">
                        <c:if test="${image.place.idPlace == place.idPlace}">
                            <c:set var="count" value="${count + 1}"/>
                        </c:if>
                    </c:forEach>
                    <td>${count}</td>
                    <td>${place.user.login}</td>
                    <td>${place.address.city.cityName}</td>
                    <td>${place.address.street}</td>
                    <td>${place.address.numberHouse}</td>
                    <td><a href="/place/delete-place/${place.idPlace}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/include/footer.jsp" />

</body>
</html>
