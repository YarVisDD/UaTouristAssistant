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
        <img class="align-self-center mr-3" src="data:image/jpg;base64,${image.image}" height="150px"
             style="padding-bottom: 10px">
    </c:forEach>
</div>
<div class="container text-left" id="tasksDiv">
    <p>
        ${place.placeDescription}
    </p>
</div>
<div class="container" id="tasksDiv">
    <hr>
    <h3>Comments</h3>
    <hr>

    <c:forEach var="comment" items="${comments}">
        <div class="panel panel-default" style="width: 50%">
            <table class="table">
                <thead>
                <tr class="active">
                    <td>Login: <b>${comment.userLogin}</b><br></td>
                    <td class="text-right">${comment.rateType}</td>
                </tr>
                </thead>
                <tr class="text-left">
                    <td colspan="2">${comment.comment}</td>
                </tr>
            </table>
        </div>
    </c:forEach>

    <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/comment/save-comment">
        <h3>Add Comment</h3>
        <div class="form-group">
            <label class="control-label col-md-3">Login</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="userLogin" readonly
                       value="<% out.print(session.getAttribute("userLogin")); %>"/>
            </div>
        </div>

        <input type="hidden" class="form-control" name="idPlace" value="${place.idPlace}"/>
        <input type="hidden" name="commentId" value="${comment.commentId}"/>
        <div class="form-group">
            <label class="control-label col-md-3">Comment</label>
            <div class="col-md-7">
                <textarea class="form-control" name="comment" rows="3" placeholder="Enter your comment"
                          value="${comment.comment}"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3">Rate </label>
            <div class="col-md-7">
                <select id="RateType" class="form-control" name="rateType" value="${comment.rateType}">
                    <option value="VERY_UNSATISFACTORY">VERY UNSATISFACTORY</option>
                    <option value="BAD">BAD</option>
                    <option value="GOOD">GOOD</option>
                    <option value="VERY_GOOD">VERY GOOD</option>
                    <option value="EXCELLENT">EXCELLENT</option>
                </select>
            </div>
        </div>
        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Save Comment"/>
        </div>


    </form>
</div>

<jsp:include page="/include/footer.jsp"/>

</body>
</html>
