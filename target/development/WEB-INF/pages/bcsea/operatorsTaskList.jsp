<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 8/2/2019
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Task List</title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;Bhutan Council for School Examination and Assessment</b> >> Tasklist</span>

    <div class="col-12">
        <form class="card form-horizontal" id="operatorsTaskListFormId" action="<c:url value='/operatorsTaskList'/>"
              method="post">

            <div class="card-body">
                <div class="card">
                    <div class="card-header" style="background: #898da0;">
                        <h3 class="card-title text-white">Group Task</h3>
                    </div>
                    <div class="card-body">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="operatorsTaskListTableId">
                                <thead>
                                <tr>
                                    <th class="hidden">Service SLNo</th>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Index Number</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" style="background: #5369d4;">
                        <h3 class="card-title text-white">My Task</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="claimedTaskListTableId">
                                <thead>
                                <tr>
                                    <th class="hidden">Service SLNo</th>
                                    <th>Sl No.</th>
                                    <th>Un Claim</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Index Number</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
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
</body>
</html>
