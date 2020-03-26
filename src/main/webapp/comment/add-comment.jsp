<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
    <title>UA Tourist Assistant | Main</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container text-center">
    <h3>Add Comment</h3>
    <hr>

    <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/comment/save-comment">
        <div class="form-group">
            <label class="control-label col-md-3">Login</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="userLogin" readonly
                       value="<% out.print(session.getAttribute("userLogin")); %>" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">PlaceId</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="id"
                       value="${comment.place}"/>
            </div>
        </div>
        <input type="hidden" name="commentId" value="${comment.commentId}"/>
        <div class="form-group">
            <label class="control-label col-md-3">Add Comment</label>
            <div class="col-md-7">
                <textarea class="form-control" name="comment" rows="3" placeholder="Enter your comment" value="${comment.comment}"></textarea>

            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3">Rate </label>
            <div class="col-md-7">
                <select id="RateType" class="form-control" name="rateType" value="${comment.rateType}">
                    <option value="VERY_UNSATISFACTORY">VERY_UNSATISFACTORY</option>
                    <option value="BAD">BAD</option>
                    <option value="GOOD">GOOD</option>
                    <option value="VERY_GOOD">VERY_GOOD</option>
                    <option value="EXCELLENT">EXCELLENT</option>
                </select>
            </div>
        </div>
        <div class="form-group ">
            <input type="submit" class="btn btn-primary" value="Save Comment"/>
        </div>




    </form>
</div>


<jsp:include page="/include/footer.jsp" />

</body>
</html>