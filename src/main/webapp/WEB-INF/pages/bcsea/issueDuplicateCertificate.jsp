<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 23-Jul-19
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Issuance Of Duplicate Certificate Registration Form</title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >>Issuance Of Duplicate Certificate Registration Form </span>

    <div class="col-12">
        <form class="card form-horizontal" id="issueDuplicateCertificateFormId"
              action="<c:url value='/applicationDetail'/>" method="post">
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
                                            <a href="#personalDetailTab" id="personalDetailTabId" data-toggle="tab"
                                               data-placement="top" title='Please use buttons to change tabs.'><i
                                                    class="fa fa-user"></i>&nbsp;Personal Details<span
                                                    class="text-info" style="font-size: 20px"
                                                    id="personalDetailsCheck"></span></a>
                                        </li>
                                        <li id="documentSelection">
                                            <a href="#documentSelectionTab" id="documentSelectionTabId"
                                               data-toggle="tab" data-placement="top"
                                               title='Please use buttons to change tabs.'><i
                                                    class="fa fa-book"></i>&nbsp; Student Detail and Document
                                                Selection<span class="text-info" style="font-size: 20px"
                                                               id="documentSelectionCheck"></span></a>
                                        </li>
                                        <li id="otherInformation">
                                            <a href="#documentSelectionTab" id="otherInformationTabId"
                                               data-toggle="tab" data-placement="top"
                                               title='Please use buttons to change tabs.'><i
                                                    class="fa fa-paperclip"></i>&nbsp; Other Information
                                                 <span class="text-info" style="font-size: 20px"
                                                       id="otherInformationCheck"></span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane" id="personalDetailTab">
                                        <br/>

                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label class="col-md-2 required">
                                                    Cid No:
                                                    <a data-toggle="tooltip" data-placement="top"
                                                       title='Please provide 11 digit cid number.'
                                                       class="tooltipCSSSelector">
                                                        <img src="/resources/images/questionMark.jpg" class="user-image"
                                                             width="20px">
                                                        <i class='fa fa-spinner fa-spin hidden' id="spinnerIconId"></i>
                                                    </a>
                                                </label>

                                                <div class="col-md-4">
                                                    <input type="text" class="form-control field numeric" id="cidNo"
                                                           name="cidNo" maxlength="11"/>
                                                    <span id="cidNoErrorMsg" class="text-danger"></span>
                                                </div>
                                                <label class="col-md-2">Full Name:</label>

                                                <div class="col-md-4">
                                                    <input type="text" readonly="true" class="form-control field"
                                                           name="fullName" id="fullName">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 required">Mobile Number:<a data-toggle="tooltip"
                                                                                                  data-placement="top"
                                                                                                  title='Please provide your 8 digit bhutanese mobile number. system will use this number for your sms notification'
                                                                                                  class="tooltipCSSSelector">
                                                    <img src="/resources/images/questionMark.jpg" class="user-image"
                                                         id="instrumentCountryimg" width="20px">
                                                </a>

                                                </label>

                                                <div class="col-md-4">
                                                    <input type="text" class="form-control field numeric"
                                                           name="mobileNo"
                                                           id="mobileNo" maxlength="8">
                                                    <span id="mobileNoErrorMsg" class="text-danger"></span>
                                                </div>
                                                <label class="col-md-2">Email:</label>

                                                <div class="col-md-4">
                                                    <input type="text" class="form-control field" id="email"
                                                           name="email" maxlength="100">
                                                    <span class="text-danger" id="emailErrorMsg"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 required">Father's Name: </label>

                                                <div class="col-md-4">
                                                    <input type="text" class="form-control field" name="fatherName"
                                                           id="fatherName" maxlength="50">
                                                    <span class="text-danger" id="fatherNameErrorMsg"></span>
                                                </div>
                                                <label class="col-md-2 required">Address: </label>

                                                <div class="col-md-4">
                                                    <textarea class="form-control field" name="address" id="address"
                                                              placeholder="Present address. Village, Gewog, Dzongkhag"
                                                              maxlength="250"></textarea>
                                                    <span class="text-danger" id="addressErrorMsg"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">THereh
                                        <br>
                                        <br>
                                        <br>
                                        <br>
                                        <br>
                                            there
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
                                                <input type="text" class="form-control field numeric" name="indexNo"
                                                       id="indexNo" maxlength="12">
                                                <span id="indexNoErrorMsg" class="text-danger"></span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="documentList">
                                            <table class="col-sm-offset-2 table" id="documentListTableId">
                                                <tbody>
                                                </tbody>
                                            </table>
                                            <span class="col-sm-offset-2 text-danger" id="documentErrorMsg"></span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2"> Charged Applied(Nu): </label>

                                            <div class="col-sm-4">
                                                <input type="text" class="form-control field" name="chargeApplied"
                                                       id="chargeApplied" readonly>
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
                                                <select class="form-control field" name="documentCollection"
                                                        id="documentCollection">
                                                    <option value="" selected>--SELECT--</option>
                                                    <option value="Y">Yes</option>
                                                    <option value="N">No</option>
                                                </select>
                                                <span class="text-danger" id="documentCollectionErrorMsg"></span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="whenAndByWhomDiv" style="display: none;">
                                            <label class="col-sm-6 required">
                                                If Yes, When and by Whom ?
                                            </label>

                                            <div class="col-sm-6">
                                                    <textarea class="form-control field" name="collectedFrom"
                                                              id="byWhom" maxlength="250"></textarea>
                                                <span class="text-danger" id="byWhomErrorMsg"></span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-6 required">
                                                What happened to the original documents ?
                                            </label>

                                            <div class="col-sm-6">
                                                    <textarea class="form-control field" name="reasonWithOriginalDoc"
                                                              id="reasonWithOriginalDoc" maxlength="250"></textarea>
                                                <span class="text-danger" id="reasonWithOriginalDocErrorMsg"></span>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="form-group">
                                            <div class="col-sm-3 pull-right">
                                                <button type="button" id="btnPrevious_2" class="btn btn-success">
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
