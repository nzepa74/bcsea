<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 23-Jul-19
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Print Certificate</title>
</head>
<body>
<div class="row">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >> Print Certificate</span>

    <div class="col-12">
        <form class="card form-horizontal" id="printCertificateFormId" action="<c:url value='/reports'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="card-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                        <form:select id="serviceId" name="serviceId" path="serviceList"
                                                     class="form-control field">
                                            <form:option value="" label="--SELECT--"></form:option>
                                            <form:options items="${serviceList}" itemValue="valueInteger"
                                                          itemLabel="text"></form:options>
                                        </form:select>
                                        <span id="serviceIdErrorMsg" class="text-danger"></span>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                        <button type="button" id="btnGetDetail" class="btn btn-primary"><i
                                                class="fa fa-arrow-down"></i>Get
                                            Details
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="printCertificateTableId">
                                <thead>
                                <tr>
                                    <th class="hidden">DocumentId</th>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>School Name</th>
                                    <th>Name</th>
                                    <th>Cid No</th>
                                    <th>Index Number</th>
                                    <th>Type</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<iframe id="printCertificateFrame" class="hidden"></iframe>
</body>
</html>
