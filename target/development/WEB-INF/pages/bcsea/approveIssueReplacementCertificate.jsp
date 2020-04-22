<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 26-Jul-19
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Issuance Of Replacement Document Approval </title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >> Issuance Of Replacement Document Approval  </span>

    <div class="col-12">
        <form class="card form-horizontal" id="approveIssueReplacementCertificateFormId"
              action="<c:url value='/issueReplacementCertificate'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" class="form-control hidden" name="applicationNo" id="applicationNo"
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
                                            <a href="#personalDetailTab" id="personalDetailTabId" data-toggle="tab"
                                               data-placement="top" title='Please use buttons to change tabs.'><i
                                                    class="fa fa-user"></i>&nbsp;Personal Details<span
                                                    class="text-info" style="font-size: 20px"
                                                    id="personalDetailsCheck"></span></a>
                                        </li>
                                        <li id="documentSelection">
                                            <a href="#documentSelectionTab" id="documentSelectionTabId"
                                               data-toggle="tab"
                                               data-placement="top" title='Please use buttons to change tabs.'><i
                                                    class="fa fa-book"></i>&nbsp; Student Detail and Document
                                                Selection<span class="text-info" style="font-size: 20px"
                                                               id="documentSelectionCheck"></span></a>
                                        </li>
                                        <li id="otherInformation">
                                            <a href="#otherInformationTab" id="otherInformationTabId"
                                               data-toggle="tab" data-placement="top"
                                               title='Please use buttons to change tabs.'>
                                                <i class="fe fe-paperclip"> </i>&nbsp;Other Information<span
                                                    class="text-info" style="font-size: 20px"></span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane" id="personalDetailTab">
                                        <br/>

                                        <div class="form-group">
                                            <label class="col-sm-2 required">
                                                Cid No:
                                                <a href="#" data-toggle="tooltip" data-placement="top"
                                                   title='Please provide 11 digit cid number.'
                                                   class="tooltipCSSSelector">
                                                    <img src="/resources/images/questionMark.jpg" class="user-image"
                                                         width="20px">
                                                    <i class='fa fa-spinner fa-spin hidden' id="spinnerIconId"></i>
                                                </a>
                                            </label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field" id="cidNo"
                                                       name="cidNo" placeholder="Cid/Passport Number"/>
                                            </div>
                                            <label class="col-sm-2">Full Name:</label>

                                            <div class="col-sm-4">
                                                <input type="text" readonly="true" class="form-control readonly-field"
                                                       name="fullName" id="fullName">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 required">Mobile Number:
                                                <a data-toggle="tooltip" data-placement="top"
                                                   title='Please provide your 8 digit bhutanese mobile number. system will use this number for your sms notification'
                                                   class="tooltipCSSSelector"><img
                                                        src="/resources/images/questionMark.jpg" class="user-image"
                                                        id="instrumentCountryimg" width="20px">
                                                </a></label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field" name="mobileNo"
                                                       id="mobileNo">
                                            </div>
                                            <label class="col-sm-2">Email:</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field" id="email"
                                                       name="email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2">Father's Name: </label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="guardianName" id="fatherName">
                                            </div>
                                            <label class="col-sm-2 required">Address:</label>

                                            <div class="col-sm-4">
                                                <textarea class="form-control readonly-field" name="address"
                                                          id="address"></textarea>
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
                                        <br/>

                                        <div class="form-group">
                                            <label class="col-sm-2 required">
                                                Index Number:
                                                <a data-toggle="tooltip" data-placement="top"
                                                   title='Your Index number provided by BCSEA office during the board exam.'
                                                   class="tooltipCSSSelector">
                                                    <img src="/resources/images/questionMark.jpg" class="user-image"
                                                         width="20px">
                                                </a>
                                            </label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field" name="indexNo"
                                                       id="indexNo">
                                            </div>
                                            <label class="col-sm-2">Examination Center:</label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control readonly-field"
                                                       name="lastSchoolName" id="examinationCenter">
                                            </div>
                                        </div>
                                        <div class="form-group" id="documentList">
                                            <label class="col-sm-2">Duplicate Documents selected:</label>

                                            <div class="col-sm-3">
                                                <table class="table" id="documentListTableId">
                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <hr/>

                                        <div class="form-group">
                                            <div class="col-sm-3 pull-right">
                                                <button type="button" id="btnPrevious_1" class="btn btn-success">
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
                                            <label class="col-sm-6 required">
                                                Did you collect your original document from the school ?
                                            </label>

                                            <div class="col-sm-6">
                                                <select class="form-control readonly-field" name="documentCollection"
                                                        id="documentCollection">
                                                    <option value="">--SELECT--</option>
                                                    <option value="Y">Yes</option>
                                                    <option value="N">No</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group" id="whenAndByWhomDiv" style="display: none;">
                                            <label class="col-sm-6 required">
                                                If Yes, When and by Whom ?
                                            </label>

                                            <div class="col-sm-6">
                                                <textarea class="form-control readonly-field" name="collectedFrom"
                                                          id="byWhom"> </textarea>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-6 required">
                                                Have you returned the Original Document for Replacement ?
                                            </label>

                                            <div class="col-sm-6">
                                                <select class="form-control readonly-field" name="isOldDocReturned"
                                                        id="isOldDocReturned">
                                                    <option value="Y">Yes</option>
                                                    <option value="N">No</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-6 required">
                                                What happened to the original documents ?
                                            </label>

                                            <div class="col-sm-6">
                                                    <textarea class="form-control readonly-field"
                                                              name="reasonWithOriginalDoc"
                                                              id="reasonWithOriginalDoc"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-6 required">
                                                Specify the changes required in the documents
                                            </label>

                                            <div class="col-sm-6">
                                                <textarea class="form-control readonly-field" name="changesRequired"
                                                          id="changesRequired"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-6 required">
                                                Purpose of Document Replacement ?
                                            </label>

                                            <div class="col-sm-6">
                                                <select class="form-control readonly-field" name="purpose" id="purpose">
                                                    <option value="">--SELECT--</option>
                                                    <option value="Minor Name Spelling Change">Minor Name Spelling
                                                        Change
                                                    </option>
                                                    <option value="Date of Birth Change">Date of Birth Change</option>
                                                    <option value="Complete Name Change">Complete Name Change</option>
                                                    <option value="Both Name and DOB Change">Both Name and DOB Change
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="form-group">
                                            <label class="col-sm-6">
                                                Total Amount Charged(Nu):</label>

                                            <div class="col-sm-6">
                                                <input type="text" class="form-control readonly-field"
                                                       id="totalAmountCharged" name="totalAmountCharged">
                                            </div>
                                        </div>

                                        <div class="col-sm-offset-6">
                                            <div class="card">
                                                <div class="card-header" style="background: #898da0;">
                                                    <h3 class="card-title text-white">Attached Files</h3>
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table border card-table table-vcenter text-nowrap"
                                                               id="attachmentDocumentTableId">
                                                            <thead>
                                                            <tr>
                                                                <th class="hidden">document Id</th>
                                                                <th class="hidden">Upload URL</th>
                                                                <th>Sl No</th>
                                                                <th>Document Name</th>
                                                                <th></th>
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
                                        <br/>

                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label class="col-sm-4">Change if any :</label>

                                                <div class="col-sm-8">
                                                    <select class="form-control" name="changes" id="changes">
                                                        <option value="">--SELECT--</option>
                                                        <option value="1">Name Change</option>
                                                        <option value="2">DOB Change</option>
                                                        <option value="3">Both Name and DOB Change</option>
                                                    </select>
                                                    <span id="changesErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4">Remarks :</label>

                                                <div class="col-sm-8">
                                                    <textarea class="form-control field" name="remarks"
                                                              id="remarks"></textarea>
                                                    <span id="remarksErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div class="form-group nameChange" style="display: none;">
                                                <label class="col-sm-4 nameChange">Name Change :</label>

                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control nameChange"
                                                           name="nameChange" id="nameChange">
                                                    <span id="nameChangeErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div class="form-group dobChange" style="display: none;">
                                                <label class="col-sm-4 dobChange">DOB Change :</label>

                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control datepicker dobChange"
                                                           name="dobChange" id="dobChange">
                                                    <span id="dobChangeErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4"> Reject Reason: </label>

                                                <div class="col-sm-8">
                                                    <form:select id="rejectReason" name="rejectReason"
                                                                 path="rejectReasons" class="form-control field">
                                                        <form:option value="" label="--SELECT--"></form:option>
                                                        <form:options items="${rejectReasons}" itemValue="valueInteger"
                                                                      itemLabel="text"></form:options>
                                                    </form:select>
                                                    <span id="rejectReasonErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>

                                            <div class="form-group rejectRemarksDiv" style="display: none;">
                                                <label class="col-sm-4"> Reject Remarks:</label>

                                                <div class="col-sm-8">
                                                  <textarea class="form-control field" name="rejectRemarks"
                                                            id="rejectRemarks" maxlength="50"></textarea>
                                                    <span id="rejectRemarksErrorMsg"
                                                          class="text-danger"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <%--<hr/>--%>
                                        <div class="form-group">
                                            <div class="col-sm-5 pull-right">
                                                <button type="button" id="btnPrevious_2" class="btn btn-success">
                                                    <i class="fa fa-arrow-left"></i>
                                                    Previous
                                                </button>
                                                <button type="button" id="btnApprove" name="btnValue"
                                                        class="btn btn-primary btnSave" value="btnApprove">
                                                    <i class="fa fa-check"></i>
                                                    Verify
                                                </button>
                                                <button type="button" id="btnReject" class="btn btn-danger btnSave"
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
