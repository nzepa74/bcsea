<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page session="false" %>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <link rel="shortcut icon" class="fa fa-leaf" href="<c:url value='/resources/images/logo.png' />"
    <jsp:include page="include/css.jsp"/>
    <jsp:include page="include/js.jsp"/>
</head>
<jsp:include page="include/close-header.jsp"/>
<jsp:include page="include/close-menu.jsp"/>
<body class="">
<div class="my-3 my-md-5">
    <div class="container">
        <sitemesh:write property="body"/>
    </div>
</div>
<jsp:include page="include/close-footer.jsp"/>
</body>
</html>
