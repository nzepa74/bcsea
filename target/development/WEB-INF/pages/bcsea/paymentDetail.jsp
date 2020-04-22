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
    <title>Payment List</title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;Bhutan Council for School Examination and Assessment</b> >> Payment List</span>

    <div class="col-12">
        <form class="card form-horizontal" id="paymentDetailFormId" action="<c:url value='/paymentDetail'/>"
              method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="card-body">
                <div class="card">
                    <div class="card-header" style="background: #5369d4;">
                        <h3 class="card-title text-white">Update Payment List</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="paymentListTableId">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th class="hidden">Detail Id</th>
                                    <th class="hidden">Amount Charge</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Index No.</th>
                                    <th>CID No.</th>
                                    <th>School</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"
                             class="modal fade in" id="updatePaymentModal">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 id="myModalLabel" class="modal-title">Update Payment Details</h4>
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">
                                            <span></span></button>
                                    </div>
                                    <div class="modal-body form-horizontal">
                                        <div class="">
                                            <div class="form-group">
                                                <input type="text" name="paymentDetailId" id="paymentDetailId"
                                                       class="form-control hidden"/>
                                                <label class="col-lg-2">Application No:</label>

                                                <div class="col-lg-4">
                                                    <input type="text" name="applicationNo" id="applicationNo"
                                                           class="form-control" readonly/>
                                                </div>
                                                <label class="col-lg-2 required">Payment Mode:</label>

                                                <div class="col-lg-4">
                                                    <select class="form-control" name="paymentType" readonly>
                                                        <option value='C'>Cash</option>
                                                        <%--<option value="N">Online</option>--%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2">Amount Charged:</label>

                                                <div class="col-lg-4">
                                                    <input type="text" readonly="true" id="amountCharge"
                                                           class="form-control">
                                                </div>
                                                <label class="col-lg-2 required">Receipt No: </label>

                                                <div class="col-lg-4">
                                                    <input type="text" name="receiptNo" id="receiptNo"
                                                           class="form-control field"/>
                                                    <span id="receiptNoErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 required">Reciept Date:</label>

                                                <div class="col-lg-4">
                                                    <input type="text" id="depositDate" name="depositDate"
                                                           class="form-control datepicker field"/>
                                                    <span id="depositDateErrorMsg" class="text-danger"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-3">Amount Charged(In words):</label>

                                                <div class="col-lg-9">
                                                    <span id="amountChargeInWords"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" class="btn btn-primary" value="Update" id="btnUpdate">
                                        <button data-dismiss="modal" class="btn btn-warning" type="button">Close
                                        </button>
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
</body>
</html>
