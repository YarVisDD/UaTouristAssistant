<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Add Place</title>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container text-center">
    <h3>New Place</h3>
    <hr>
    <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/place-img/save-img">
        <input type="hidden" name="id" value="${img.idImage}"/>
        <div class="form-group">
            <label class="control-label col-md-3">Choose image</label>
            <div class="col-md-7">
                <input class="form-control" type="file" name="image" value="${img.image}" multiple/>
            </div>
        </div>
        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Add Img"/>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
</body>
</html>
