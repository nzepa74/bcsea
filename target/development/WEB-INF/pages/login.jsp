<%@page contentType="text/html" session="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<meta name="decorator" content="/layout/login-layout.jsp"/>

<div class="row">
    <div class="col col-login mx-auto">
        <form class="card" action="<c:url value='/auth'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="card-body p-6">
                <div class="card-title">Login to your account</div>
                <hr/>
                <div class="form-group">
                    <c:if test="${not empty error}">
                        <label class="form-label" style="color: red;"><spring:message code="${error}"/></label>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" autofocus="true" class="form-control tabable"
                           tabindex="1" required="true" autocomplete="off" placeholder="Enter Your Username"/>
                </div>
                <div class="form-group">
                    <label class="form-label"> Password </label>
                    <input type="password" name="password" class="form-control tabable" tabindex="2"
                           required="true" autocomplete="off" placeholder="Enter Your Password"/>
                </div>
                <div class="form-footer">
                    <input type="submit" class="btn btn-primary btn-block tabable" tabindex="4"
                           value="SIGN IN" style="color: #fff;"/>
                    <button type="reset" class="btn btn-warning btn-block">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>