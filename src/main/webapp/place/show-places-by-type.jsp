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
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/style.css" rel="stylesheet">
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
        <%= request.getParameter("city") %> places
    </h3>
    <hr>

    <div class="panel panel-default text-left">
        <table class="table">
            <thead>
            <tr class="active">
                <th class="text-center" width="20%">Photo</th>
                <th>Name & Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="place" items="${places}">
                <tr>
                    <td class="text-center">
                        <c:set var="count" value="0"/>
                        <c:forEach var="image" items="${images}">
                            <c:if test="${image.place.idPlace == place.idPlace && count eq 0}">
                                <a href="/place/show-place/${place.idPlace}">
                                    <img class="align-self-center mr-3" src="data:image/jpg;base64,${image.image}"
                                         height="150px">
                                </a>
                                <c:set var="count" value="${count + 1}"/>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <h4><a href="/place/show-place/${place.idPlace}">${place.placeName}</a></h4>
                        <p>
                                ${place.placeType}<br>
                                ${place.address.city.cityName},
                                ${place.address.street},
                                ${place.address.numberHouse}
                        </p>
                        <p>${place.placeDescription}</p>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="/include/footer.jsp"/>

</body>
</html>
