<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 10.03.2020
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Show-Cities</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container text-center" id="tasksDiv">
    <h3>All Cities</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id City</th>
                <th>City Name</th>
               <%-- <th>Delete</th>--%>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="city" items="${cities}">
                <tr>
                    <td>${city.idCity}</td>
                    <td>${city.cityName}</td>
                  <%--  <td><a href="/user/delete-user/${user.login}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>--%>
                    <td><a href="/city/update-city/${city.cityName}"><span
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
