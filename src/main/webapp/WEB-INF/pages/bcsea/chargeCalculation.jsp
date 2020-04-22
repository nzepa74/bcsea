<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 9/6/2019
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Charge Calculation</title>
</head>
<body class="">
<div class="my-3 my-md-5">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form class="card form-horizontal" id="chargeCalculationFormId"
                      action="<c:url value='/chargeCalculation'/>" method="post">
                    <div class="card-body">
                        <h2 class="card-title"><b>BCSEA Service</b> >> Charge Calculator</h2>

                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 required">Service Type:</label>

                                            <div class="col-sm-5">
                                                <form:select id="serviceId" name="serviceId" path="serviceList"
                                                             class="form-control">
                                                    <form:option value="" label="--SELECT--"></form:option>
                                                    <form:options items="${serviceList}" itemValue="valueInteger"
                                                                  itemLabel="text"></form:options>
                                                </form:select>
                                                <span id="serviceIdErrorMsg" class="text-danger"></span>
                                            </div>
                                        </div>

                                        <div id="duplicateOrReplacementDiv" style="display: none">
                                            <div class="form-group">
                                                <label class="col-md-3 required">Index Number:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field numeric" name="indexNo"
                                                           id="indexNo" maxlength="12"/>
                                                </div>
                                                <label class="col-md-3 required">Document Type:</label>

                                                <div class="col-md-3">
                                                    <form:select id="documentId" name="documentId" path="documentList"
                                                                 class="form-control field">
                                                        <form:option value="" label="--SELECT--"></form:option>
                                                        <form:options items="${documentList}" itemValue="valueInteger"
                                                                      itemLabel="text"></form:options>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3">No. of times Mark Sheet issued:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control dupRep field numeric"
                                                           readonly
                                                           name="noOfTimesDocIssued" id="noOfTimesDocIssued"/>
                                                </div>
                                                <label class="col-md-3">Charge for a Mark Sheet:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control dupRep field" readonly
                                                           name="chargeForDocument" id="chargeForDocument"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3">Total Charge:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control dupRep field" readonly
                                                           name="documentTotalCharge" id="documentTotalCharge"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="languageProficiencyDiv" style="display: none">
                                            <div class="form-group">
                                                <label class="col-md-3 required">CID Number:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field numeric" name="cidNo"
                                                           id="cidNo" maxlength="11"/>
                                                    <span id="cidNoErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3">No. of times Certificate issued:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field languagePro numeric"
                                                           readonly name="noOfTimesLanCertificateIssued"
                                                           id="noOfTimesLanCertificateIssued"/>
                                                </div>
                                                <label class="col-md-3">Charge:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field languagePro" readonly
                                                           name="chargeForLanCertificate" id="chargeForLanCertificate"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3">Total Charge:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field languagePro" readonly
                                                           name="lanCertificateTotalCharge"
                                                           id="lanCertificateTotalCharge"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="clericalRecheckDiv" style="display: none">
                                            <div class="form-group">
                                                <label class="col-md-3">No of Recheck Paper(s):</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field numeric"
                                                           name="noOfRecheckPaper" id="noOfRecheckPaper"/>
                                                </div>
                                                <label class="col-md-3">Charge Per Paper Recheck:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" readonly
                                                           name="amountCharge" id="chargePerPaper"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3">Total Charge:</label>

                                                <div class="col-md-3">
                                                    <input type="text" class="form-control field" readonly
                                                           name="recheckPaperTotalCharge" id="recheckPaperTotalCharge"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group" id="totalChargeInWordsDiv" style="display: none">
                                            <label class="col-md-3">Total Charge(in words):</label>

                                            <div class="col-md-9">
                                                <span id="totalChargeInWords"></span>
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
