<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 10.03.2020
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Add City</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="../navbar.jsp"/>

<div class="container text-center">
    <h3>New City</h3>
    <hr>
    <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/city/add-city">
        <input type="hidden" name="idCity" value="${city.idCity}"/>
        <div class="form-group">
            <label class="control-label col-md-3">City name</label>
            <div class="col-md-7">
                <input class="form-control" type="text" name="cityName" placeholder="Enter city"
                       value="${city.cityName}"/>
            </div>
        </div>


        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Add City"/>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
</body>
</html>

