<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 8/22/2019
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Track Application</title>
</head>
<body class="">
<div class="my-3 my-md-5">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form class="card form-horizontal" id="trackApplicationFormId"
                      action="<c:url value='/trackApplication'/>" method="post">
                    <div class="card-body">
                        <h2 class="card-title"><b>BCSEA Service</b> >> Track Application</h2>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-6">
                                                <input type="text" name="applicationNo" class="form-control"
                                                       placeholder="Enter Application Id" id="applicationNo">
                                                <span id="applicationNoErrorMsg" class="text-danger"></span>
                                            </label>

                                            <div class="col-sm-6">
                                                <button type="button" class="btn btn-primary" id="btnView"><i
                                                        class="fa fa-eye">View Details</i></button>
                                            </div>
                                        </div>
                                        <div style="display: none" id="applicationDetailTableId">
                                            <div class="table-responsive">
                                                <table border="" class="table table-strive">
                                                    <tr>
                                                        <td><label>App Submission Date</label></td>
                                                        <td id="submissionDate" class="field">2019-03-23</td>
                                                    </tr>
                                                    <tr>
                                                        <td><label>Application for</label></td>
                                                        <td id="serviceName" class="field">Service Name</td>
                                                    </tr>
                                                    <tr>
                                                        <td><label>Application Status</label></td>
                                                        <td id="applicationStatus" class="field">
                                                            Submitted/Verified/approved
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
