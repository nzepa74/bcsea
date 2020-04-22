<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 05-Jun-18
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<sec:authentication var="auth" property="principal"/>

<c:url value="/logout" var="logoutUrl"/>

<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<div class="header py-4" style="background: #120f65;">
    <div class="container">
        <div class="d-flex">
            <a class="header-brand" href="/">
                <img src="/resources/images/logo.png" class="header-brand-img" alt="BCSEA LOGO"
                     style="height:60px;width:60px">
            </a>

            <%--<h3 class="text-white organization-name">Government to Citizen Service Delivery Initiative</h3>--%>
            <h3 class="text-white organization-name">RGOB</h3>

            <div class="d-flex order-lg-2 ml-auto">
                <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                        <span class="fa fa-user"></span>
                    <span class="ml-2 d-none d-lg-block">
                      <span class="text-muted">${auth.fullName}</span>
                      <small class="text-muted d-block mt-1">${auth.roleName}</small>
                    </span>
                    </a>

                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" href="">
                            <i class="dropdown-icon fe fe-help-circle"></i> Need help?
                        </a>
                        <a class="dropdown-item" href="javascript:$('#logoutForm').submit();">
                            <i class="dropdown-icon fe fe-log-out"></i> Sign out
                        </a>
                    </div>
                </div>
            </div>
            <a href="#" class="header-toggler d-lg-none ml-3 ml-lg-0" data-toggle="collapse"
               data-target="#headerMenuCollapse"> <span class="header-toggler-icon"></span>
            </a>
        </div>
    </div>
</div>
