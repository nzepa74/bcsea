<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 27-Jul-19
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Application Form For Clerical Re-Check Of Answer Scripts Form</title>
</head>

<body>
<center><span id="recheckNotice" style="color: red; font-size: large"></span></center>
<center><span id="recheckNoticeOpen" style="color: darkgreen; font-size: large"></span></center>

<div class="my-3 my-md-5" id="registration">
    <div class="container">
        <div class="row">
            <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >>Application Form For Clerical Re-Check Of Answer Scripts Form </span>

            <div class="col-12">
                <form class="card form-horizontal" id="recheckApplicationFormId"
                      action="<c:url value='/recheckApplication'/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

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
                                                            class="fa fa-dollar"></i>&nbsp;Payment Information
                                                        <span class="text-info" style="font-size: 20px"></span></a>
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
                                                        <input type="text" class="form-control field numeric"
                                                               name="indexNo" id="indexNo" maxlength="12">
                                                        <span id="indexNoErrorMsg" class="text-danger"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Full Name:</label>

                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control std-detail field"
                                                               name="studentName" id="studentName" readonly>
                                                    </div>
                                                    <label class="col-sm-2">Examination Center:</label>

                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control std-detail field"
                                                               name="schoolName" id="schoolName" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Examination Month :</label>

                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control std-detail field"
                                                               name="examMonth" id="examMonth" readonly>
                                                    </div>
                                                    <label class="col-sm-2">Examination Year :</label>

                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control std-detail field"
                                                               name="examYear" id="examYear" readonly>
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
                                                        <input type="text" class="form-control field numeric"
                                                               name="mobileNo" id="mobileNo" maxlength="8">
                                                        <span id="mobileNoErrorMsg" class="text-danger"></span>
                                                    </div>
                                                    <label class="col-sm-2">Email:</label>

                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control field" id="email"
                                                               name="email" maxlength="50">
                                                        <span class="text-danger" id="emailErrorMsg"></span>
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
                                                <strong>Note : </strong><span class="text-info">Nu. <span
                                                    id="recheckAmount"></span> per paper is charged. You are allow to re-check all the papers.</span>
                                                <hr/>
                                                <div class="card-body">
                                                    <div class="form-group table-responsive" id="subjectList">
                                                        <table class="table card-table table-vcenter text-nowrap"
                                                               id="subjectListTableId">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    <span class="col-sm-offset-2 text-danger"
                                                          id="subjectErrorMsg"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3"> Total Amount Charged(in Nu): </label>

                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control field"
                                                               name="chargeApplied" id="chargeApplied" readonly>
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
                                                <strong>Note : </strong><span class="text-info">TO PROCEED WITH THE RE-CHECK OF PAPERS, YOU HAVE TO MAKE PAYMENT. PAYMENT CAN BE DONE EITHER ONLINE OR VISIT THE BCSEA OFFICE.</span>
                                                <hr/>
                                                <br>

                                                <%--<div class="form-group">--%>
                                                <%--<label class="col-sm-6">Total Amount Charged(in Nu) : </label>--%>

                                                <%--<div class="col-sm-6">--%>
                                                <%--<input type="text" class="form-control field"--%>
                                                <%--id="chargeApplied" readonly="true">--%>
                                                <%--</div>--%>
                                                <%--</div>--%>
                                                <hr/>
                                                <div class="form-group">
                                                    <div class="col-sm-3 pull-right">
                                                        <button type="button" id="btnPrevious_2"
                                                                class="btn btn-success">
                                                            <i class="fa fa-arrow-left"></i>
                                                            Previous
                                                        </button>
                                                        <button type="submit" id="btnSubmit" class="btn btn-primary">
                                                            <i class="fa fa-check"></i>
                                                            Submit
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
    </div>
</div>
<div class="row hidden" id="acknowledgement">
    <div class="col-12">
        <form action="#" method="post" class="card form-horizontal">
            <div class="card-body">
                <h2 class="card-title"><b>BCSEA Service</b> >> Acknowledgement</h2>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-12">
                                        Thank you for applying <span id="serviceName"></span>. Your application number
                                        generated by
                                        system is <span id="applicationNo"></span> and this application is forwarded to
                                        BCSEA head
                                        office for verification and approval. You may track your application status
                                        using this application number from Track Your Application.<br/><br/>
                                        Thank you.
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
