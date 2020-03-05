<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Registration</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container text-center">
    <h3>Update User</h3>
    <hr>
    <form class="form-horizontal" method="POST" action="/user/save-user">
        <input type="hidden" name="userId" value="${user.userId}" />
        <div class="form-group">
            <label class="control-label col-md-3">Login</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="login"
                       value="${user.login}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Email</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="email"
                       value="${user.email}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">First Name</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="firstName"
                       value="${user.firstName}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Last Name</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="lastName"
                       value="${user.lastName}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Date Of Birth</label>
            <div class="col-md-3">
                <input type="date" class="form-control" name="dateOfBirth"
                       value="${user.dateOfBirth}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">User Role</label>
            <div class="col-md-7">
                <select id="userRole" class="form-control" name="userRole" value="${user.userRole}">
                    <option value="SUBSCRIBER">subscriber</option>
                    <option value="AUTHOR">author</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Password</label>
            <div class="col-md-7">
                <input type="password" class="form-control" name="password"
                       value="${user.password}"/>
            </div>
        </div>
        <div class="form-group ">
            <a href="/user/show-users" class="btn btn-info" role="button">Back</a>
            <input type="submit" class="btn btn-primary" value="Update" />
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="static/js/jquery-1.11.1.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
