<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 09.03.2020
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Update City</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="../include/navbar.jsp"/>

<div class="container text-center">
    <h3>Update City</h3>
    <hr>
    <form class="form-horizontal" method="POST" action="/city/save-city">
        <input type="hidden" name="idCity" value="${city.idCity}"/>
        <div class="form-group">
            <label class="control-label col-md-3">City Name</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="cityName"
                       value="${city.cityName}"/>
            </div>
        </div>

        <div class="form-group ">
            <a href="/city/show-cities" class="btn btn-info" role="button">Back</a>
            <input type="submit" class="btn btn-primary" value="Update" />
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
</body>
</html>
