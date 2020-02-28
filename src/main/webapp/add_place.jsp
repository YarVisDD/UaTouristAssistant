<!DOCTYPE html>
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
    <div class="navbar navbar-in">
        <a href="/" class="navbar-brand">UA Tourist Assistant</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/login">Login</a></li>
                <li><a href="/registration">New Registration</a></li>
                <li><a href="/show-users">All Users</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <h3>New Place</h3>
    <hr>
    <form class="form-horizontal" method="post" action="/place/addPlace">
        <input type="hidden" name="id" value="${place.idPlace}"/>
        <div class="form-group">
            <div class="col-md-7">
                <input type="text" name="placeName" placeholder="Enter place name" value="${place.}"/>
            </div>
        </div>
        <div>
            <input type="text" name="placeDescription" placeholder="Enter place description"/>
        </div>
        <div>

        </div>
        <div>
            <input type="file" name="image" id="imageFile"/>
            <label for="imageFile">choose image</label>
        </div>
        <div>
            <input type="text" name="userName" placeholder="Enter username"/>
        </div>
        <div>
            <input type="text" name="cityName" placeholder="Enter city"/>
        </div>
        <div>
            <input type="text" name="streetName" placeholder="Enter street"/>
        </div>
        <div>
            <input type="text" name="numberHouse" placeholder="Enter numberHouser"/>
        </div>
        <div>
            <button type="submit">Add Place</button>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="static/js/jquery-1.11.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
