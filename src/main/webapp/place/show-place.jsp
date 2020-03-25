<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Places</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<jsp:include page="/include/navbar.jsp"/>

<div class="container text-center" id="tasksDiv">
    <h3>
        <a onclick="history.go(-1)" class="btn btn-default" role="button" style="float: left">Back</a>
        ${place.placeName}
    </h3>
    <b>Type:</b> ${place.placeType}<br>
    <b>Address:</b> ${place.address.city.cityName}, ${place.address.street}, ${place.address.numberHouse}
    <hr>
</div>
<div class="container text-center" id="tasksDiv">
    <c:forEach var="image" items="${images}">
<%--        <c:if test="${image.place.idPlace == place.idPlace}">--%>
            <img class="align-self-center mr-3" src="data:image/jpg;base64,${image.image}" height="150px" style="padding-bottom: 10px">
<%--        </c:if>--%>
    </c:forEach>
</div>
<div class="container text-left" id="tasksDiv">
    <p>
        ${place.placeDescription}
    </p>
</div>
<div class="container text-center" id="tasksDiv">
    <hr>
    <h3>Comments</h3>
    <hr>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<jsp:include page="/include/footer.jsp"/>
</body>
</html>
