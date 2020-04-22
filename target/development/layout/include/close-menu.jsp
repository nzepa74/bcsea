<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
    <div class="container">
        <div class="row pull-right">
            <div class="col-lg order-lg-first">
                <ul class="nav nav-tabs border-0 flex-column flex-lg-row">

                    <li class="nav-item ">
                        <a href="${pageContext.request.contextPath}/home" class="nav-link" style="color:black">
                            <i class="fa fa-home"></i>Home</a>
                    </li>

                    <%--<security:authorize access="hasAuthority('0101-01-VIEW')">--%>
                    <%--<li class="nav-item ">--%>
                        <%--<a href="/operatorsTaskList" class="nav-link" style="color:black">--%>
                            <%--<i class="fa fa-list"></i>Task List Operators</a>--%>
                    <%--</li>--%>
                    <%--</security:authorize>--%>

                    <%--<security:authorize access="hasAuthority('0101-02-VIEW')">--%>
                        <%--<li class="nav-item ">--%>
                            <%--<a href="/paymentDetail" class="nav-link" style="color:black">--%>
                                <%--<i class="fa fa-paper-plane"></i>Payment Detail</a>--%>
                        <%--</li>--%>
                    <%--</security:authorize>--%>

                    <security:authorize access="hasAuthority('0101-01-VIEW')">
                        <li class="nav-item ">
                            <a href="/reports" class="nav-link" style="color:black">
                                <i class="fa fa-file-pdf-o"></i>Reports</a>
                        </li>
                    </security:authorize>

                    <security:authorize access="hasAuthority('0101-01-VIEW')">
                    <li class="nav-item ">
                        <a href="/printCertificate" class="nav-link" style="color:black">
                            <i class="fa fa-print"></i>Print Certificate</a>
                    </li>
                    </security:authorize>

                    <security:authorize access="hasAuthority('0101-03-VIEW')">
                    <li class="nav-item ">
                        <a href="/serviceActivityDuration" class="nav-link" style="color:black">
                            <i class="fe fe-watch"></i> Allocate Time for Services</a>
                    </li>
                    </security:authorize>

                    <security:authorize access="hasAuthority('0101-03-VIEW')">
                    <li class="nav-item ">
                        <a href="/chargeAllocation" class="nav-link" style="color:black">
                            <i class="fa fa-dollar"></i>Charge Allocation</a>
                    </li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </div>
</div>