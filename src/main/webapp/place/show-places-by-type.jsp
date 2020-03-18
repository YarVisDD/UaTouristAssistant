<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<jsp:include page="../include/navbar.jsp" />

<div class="container text-center" id="tasksDiv">
    <h3><%= request.getParameter("city") %> places</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Type</th>
                <th>Image</th>
                <th>City</th>
                <th>Street</th>
                <th>House</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="place" items="${places}">
                <tr>
                    <td>${place.placeName}</td>
                    <c:set var="shortDesc" value="${fn:substring(place.placeDescription, 0, 200)}" />
                    <td>${shortDesc}...</td>
                    <td>${place.placeType}</td>
                    <td>
                        <c:forEach var="image" items="${images}">
                            <c:if test="${image.place.idPlace == place.idPlace}">
                                <img src="data:image/jpg;base64,${image.image}" width="25%"/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${place.address.city.cityName}</td>
                    <td>${place.address.street}</td>
                    <td>${place.address.numberHouse}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
</body>
</html>
