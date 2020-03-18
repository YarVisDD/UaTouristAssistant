<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | HomePage</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="include/navbar.jsp"/>

<div class="container" id="homediv">
    <div class="jumbotron text-center">
        <h1>Welcome,
            <% out.print(session.getAttribute("userLogin")); %>
        </h1>
        <h3>to<br>UA Tourist Assistant</h3>
    </div>
</div>

<div class="container text-center">
    <h3>Please choose where you want to spend your time</h3>
    <hr>
    <form class="form-horizontal" method="post" action="/place/new-place-by-type">
        <div class="form-group">
            <label class="control-label col-md-3">City</label>
            <div class="col-md-7">
                <select id="city" class="form-control" name="city" value="city">
                    <c:forEach items="${listCities}" var="city">
                        <option value="${city.cityName}">${city.cityName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Place type</label>
            <div class="col-md-7">
                <select id="placeType" class="form-control" name="placeType" value="${place.placeType}">
                    <option value="ALL">All</option>
                    <option value="HOTELS">hotels</option>
                    <option value="CAFE_RESTAURANTS">cafe or restaurants</option>
                    <option value="MONUMENTS_OBJECTS">monuments and objects</option>
                    <option value="ENTERTAINMENT">entertainment</option>
                    <option value="MUSEUMS">museums</option>
                </select>
            </div>
        </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Show places"/>
            </div>
    </form>
</div>

<%--<div class="container text-centered">--%>
<%--    <center><img src="/images/careerbuilder-original-3120.gif"></center>--%>
<%--    <br>--%>
<%--    <center><img src="/images/careerbuilder-original-3110.gif"></center>--%>
<%--</div>--%>

<jsp:include page="include/footer.jsp"/>

</body>
</html>