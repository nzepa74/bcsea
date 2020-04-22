<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 8/19/2019
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Clerical Re-Check Of Answer Scripts Approval</title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >>Clerical Re-Check Of Answer Scripts Approval</span>

    <div class="col-12">
        <form class="card form-horizontal" id="approveRecheckApplicationFormId"
              action="<c:url value='/approveRecheckApplication'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" class="form-control field hidden" name="applicationNo" id="applicationNo"
                   value="${applicationNo}">

            <div class="card-body">
                <span class="text-danger">
              &nbsp;&nbsp;&nbsp;&nbsp;NOTE:  Label which are mark with * are mandatory fields, you cannot proceed further without those fields.
              Please follow the instruction in &nbsp;&nbsp;<img src="/resources/images/questionMark.jpg"
                                                                class="user-image" width="20px">
          </span>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                        <li id="personalDetails">
                                            <a href="#personalDetailTab" id="personalDetailTabId"
                                               data-toggle="tab"
                                               data-placement="top"
                                               title='Please use buttons to change tabs.'><i
                                                    class="fa fa-user"></i>&nbsp;Personal Details<span
                                                    class="text-info" style="font-size: 20px"
                                                    id="personalDetailsCheck"></span></a>
                                        </li>
                                        <li id="documentSelection">
                                            <a href="#documentSelectionTab" id="documentSelectionTabId"
                                               data-toggle="tab"
                                               data-placement="top"
                                               title='Please use buttons to change tabs.'><i
                                                    class="fa fa-book"></i>&nbsp; Student Detail and Subject
                                                Selection<span class="text-info" style="font-size: 20px"
                                                               id="documentSelectionCheck"></span></a>
                                        </li>
                                        <li id="otherInformation">
                                            <a href="#otherInformationTab" id="otherInformationTabId"
                                               data-toggle="tab" data-placement="top"
                                               title='Please use buttons to change tabs.'><i
                                                    class="fa fa-dollar"></i>&nbsp;Payment Information<span
                                                    class="text-info" style="font-size: 20px"></span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane" id="personalDetailTab">
                                        <br/>

                                        <div class="form-group">
                                            <label class="col-sm-2 required">
                                                Index No:
                                                <a data-toggle="tooltip" data-placement="top"
                                                   title='Please provide index number provide by BCSEA office during your board exam.'
                                                   class="tooltipCSSSelector">
                                                    <img src="/resources/images/questionMark.jpg"
                                                         class="user-image" id="instrumentCountryimg"
                                                         width="20px">
                                                    <i class='fa fa-spinner fa-spin' style="display:none"></i>
                                                </a>
                                            </label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="indexNo"
                                                       id="indexNo">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2">Full Name:</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="fullName" id="fullName">
                                            </div>
                                            <label class="col-sm-2">Examination Center:</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="lastSchoolName" id="lastSchoolName">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2">Examination Month :</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="examMonth" id="examMonth">
                                            </div>
                                            <label class="col-sm-2">Examination Year :</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="examYear" id="examYear">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 required">Mobile Number:<a
                                                    data-toggle="tooltip" data-placement="top"
                                                    title='Please provide your 8 digit bhutanese mobile number. system will use this number for your sms notification'
                                                    class="tooltipCSSSelector">
                                                <img src="/resources/images/questionMark.jpg" class="user-image"
                                                     width="20px">
                                            </a></label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="mobileNo" id="mobileNo">
                                            </div>
                                            <label class="col-sm-2">Email:</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       id="email" name="email">
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="form-group pull-right">
                                            <div class="col-sm-4">
                                                <button type="button" id="btnNext_1" class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i> Next
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane" id="documentSelectionTab">
                                        <div class="card-body">
                                            <div class="form-group table-responsive" id="subjectList">
                                                <table class="table card-table table-vcenter text-nowrap"
                                                       id="recheckPaperListTableId">
                                                    <thead>
                                                    <tr>
                                                        <th class="hidden">Recheck Detail Id</th>
                                                        <th class="hidden">Subject Id</th>
                                                        <th class="hidden">Paper Id</th>
                                                        <th>SL NO.</th>
                                                        <th>SUBJECT NAME</th>
                                                        <th>PAPER NAME</th>
                                                        <th>OLD MARKS</th>
                                                        <th>MARKS OBTAINED</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <div class="col-sm-3 pull-right">
                                                <button type="button" id="btnPrevious_1"
                                                        class="btn btn-success">
                                                    <i class="fa fa-arrow-left"></i>
                                                    Previous
                                                </button>
                                                <button type="button" id="btnNext_2" class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Next
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="otherInformationTab">
                                        <br/>

                                        <div class="form-group">
                                            <label class="col-sm-3"> Total Amount Charged(in Nu): </label>

                                            <div class="col-sm-2">
                                                <input type="text" class="form-control readonly-field"
                                                       name="chargeApplied" id="chargeApplied">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2">
                                                Remarks</label>

                                            <div class="col-sm-4">
                                                <textarea class="form-control field" name="remarks"
                                                          id="remarks"></textarea>
                                                <span id="remarksErrorMsg" class="text-danger"></span>
                                            </div>
                                            <label class="col-sm-2">
                                                Reject Reason:</label>

                                            <div class="col-sm-4">
                                                <form:select id="rejectReason" name="rejectReasonId"
                                                             path="rejectReasons"
                                                             class="form-control field">
                                                    <form:option value="" label="--SELECT--"></form:option>
                                                    <form:options items="${rejectReasons}" itemValue="valueInteger"
                                                                  itemLabel="text"></form:options>
                                                </form:select>
                                                <span id="rejectReasonErrorMsg" class="text-danger"></span>
                                            </div>
                                        </div>
                                        <div class="form-group rejectRemarksDiv" style="display: none;">
                                            <label class="col-sm-2 col-sm-offset-6 required">
                                                Reject Remarks</label>

                                            <div class="col-sm-4">
                                                    <textarea class="form-control field" name="rejectRemarks"
                                                              id="rejectRemarks" maxlength="50"></textarea>
                                            </div>
                                                    <span id="rejectRemarksErrorMsg"
                                                          class="text-danger col-sm-offset-8"></span>
                                        </div>
                                        <hr/>

                                        <div class="form-group">
                                            <div class="col-sm-5 pull-right">
                                                <button type="button" id="btnPrevious_2" class="btn btn-success">
                                                    <i class="fa fa-arrow-left"></i>
                                                    Previous
                                                </button>
                                                <button type="submit" id="btnApprove" name="btnValue"
                                                        class="btn btn-primary btnSave" value="btnApprove">
                                                    <i class="fa fa-check"></i>
                                                    Verify
                                                </button>
                                                <button type="submit" id="btnReject" class="btn btn-danger btnSave"
                                                        name="btnValue" value="Reject">
                                                    <i class="fa fa-ban"></i>
                                                    Reject
                                                </button>
                                            </div>
                                        </div>
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
<div class="my-3 my-md-5 hidden" id="approveAcknowledgement">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="#" method="post" class="card form-horizontal">
                    <div class="card-body">
                        <h2 class="card-title"><b>Bhutan Council for School Examination and Assessment</b> >>
                            Acknowledgement</h2>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group" id="downloadAsPdf">
                                            <label class="col-sm-12">
                                                Application for <span id="serviceNameApprove"></span> with application
                                                number <span id="applicationNoApprove"></span> is approved. <br/>
                                                Thank you.
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <button type="button" id="btnDownload"
                                                    class="btn btn-success col-sm-offset-10">
                                                <i class="fa fa-download"></i> Download
                                            </button>
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
<div class="my-3 my-md-5 hidden" id="rejectAcknowledgement">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="#" method="post" class="card form-horizontal">
                    <div class="card-body">
                        <h2 class="card-title"><b>Bhutan Council for School Examination and Assessment</b> >>
                            Acknowledgement</h2>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group" id="downloadAsPdfReject">
                                            <label class="col-sm-12">
                                                Application for <span id="serviceNameReject"></span> with application
                                                number
                                                <span id="applicationNoReject"></span> is rejected due to <span
                                                    id="rejectReasonReject"></span>. <br/>
                                                Thank you.
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <button type="button" id="btnDownloadReject"
                                                    class="btn btn-success col-sm-offset-10">
                                                <i class="fa fa-download"></i> Download
                                            </button>
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
