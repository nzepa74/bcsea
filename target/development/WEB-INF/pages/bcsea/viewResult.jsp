<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 8/22/2019
  Time: 11:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Result</title>
</head>
<body class="">
<div class="my-3 my-md-5">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form class="card form-horizontal">
                    <div class="card-body">
                        <h2 class="card-title"><b>BCSEA Service</b> >> View Result</h2>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-5">
                                                <select class="form-control" name="classTypeId" id="classType">
                                                    <option value="">-- Please Select --</option>
                                                    <option value="10">Class 10</option>
                                                    <option value="12">Class 12</option>
                                                </select>
                                                <span id="classTypeErrorMsg" class="text-danger"></span>
                                            </label>
                                            <label class="col-sm-5">
                                                <input type="text" name="indexNo" placeholder="Enter Index Number"
                                                       id="indexNo" class="form-control numeric">
                                                <span id="indexNoErrorMsg" class="text-danger"></span>
                                            </label>

                                            <div class="col-sm-2">
                                                <button type="button" id="btnView" class="btn btn-primary">
                                                    <i class="fa fa-eye">View Result</i></button>
                                            </div>
                                        </div>
                                        <div style="display: none" id="resultDetail">
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-sm-2">Index Number : </label>

                                                <div class="col-sm-4">
                                                    <label id="indexNoDisplay"></label>
                                                </div>
                                                <label class="col-sm-2">School Name : </label>

                                                <div class="col-sm-4">
                                                    <label id="schoolName"></label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Exam Year : </label>

                                                <div class="col-sm-4"><label id="examYear"></label>
                                                </div>
                                                <label class="col-sm-2">Class & Stream : </label>

                                                <div class="col-sm-4">
                                                    <label id="classAndStream"></label>
                                                </div>
                                            </div>
                                            <table border="1" class="table table-responsive table-bordered"
                                                   id="resultTableId">
                                                <thead>
                                                <tr>
                                                    <th style="width: 5%">SL NO.</th>
                                                    <th style="width: 20%">SUBJECT</th>
                                                    <th style="width: 10%">MARKS</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                            <br>

                                            <div class="form-group">
                                                <label class="col-sm-2">SUPW : </label>

                                                <div class="col-sm-4">
                                                    <label id="supwGrade"></label>
                                                </div>
                                                <label class="col-sm-2">Result : </label>

                                                <div class="col-sm-4">
                                                    <label id="resultRemarks"></label>
                                                </div>
                                            </div>
                                            <div class="form-group pull-right" id="exportButtons"></div>
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
