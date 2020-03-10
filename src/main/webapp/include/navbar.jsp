<div role="navigation">
    <div class="navbar navbar-inverse">
        <div class="container">
            <a href="/" class="navbar-brand">UA Tourist Assistant</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/homepage">Home</a></li>
                    <li><a href="/user/show-users">All Users</a></li>

                    <%--                    <li><a href="/place/show-places">All Place</a></li>--%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%
                        Object userLogin = session.getAttribute("userLogin");
                        if (userLogin == null) {
                            out.print("<li><a href=\"/login\">Login</a></li>\n");
                            out.print("<li><a href=\"/user/registration\">Registration</a></li>");
                        } else {
                            out.print("<li><a href=\"/place/add-place\">Add Place</a></li>");
                            out.print("<li><a href=\"/user/update-user/" + userLogin + "\">Profile</a></li>");
                            out.print("<li><a href=\"/logout\">Log Out</a></li>");
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</div>
