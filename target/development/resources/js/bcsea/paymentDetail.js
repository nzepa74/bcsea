/**
 * Created by USER on 8/20/2019.
 */
paymentDetail = (function () {
    var form = $('#paymentDetailFormId');

    function _baseURL() {
        return 'paymentDetail/';
    }

    function getPaymentList() {
        var url = _baseURL() + 'getPaymentList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {
                            "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {"data": "paymentDetailId", class: "paymentDetailId hidden"}
                        , {
                            "data": "amountCharge", class: "amountCharge hidden",
                            "mRender": function (data) {
                                return data.toFixed(2);
                            }
                        }
                        , {"data": "applicationNo", class: "applicationNo"}
                        , {"data": "serviceName", class: "serviceName"}
                        , {"data": "indexNo", class: "indexNo"}
                        , {"data": "cidNo", class: "cidNo"}
                        , {"data": "schoolName", class: "schoolName"}
                        , {
                            "data": "null", class: "btnUpdate align-middle",
                            "mRender": function () {
                                return "<a id='btnUpdate' class='btn btn-info btn-sm'><i class='fa fa-usd'></i>Update Payment</a>";
                            }
                        }
                    ];
                    $('#paymentListTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                    });
                }
            }
        });
    }

    function showModel() {
        $('#paymentListTableId tbody').on('click', 'tr #btnUpdate', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var paymentDetailId = selectedRow.find('.paymentDetailId').text();
            var applicationNo = selectedRow.find('.applicationNo').text();
            var amountCharge = selectedRow.find('.amountCharge').text();
            selectedRow.removeClass('selected');
            $('#paymentDetailId').val(paymentDetailId);
            $('#applicationNo').val(applicationNo);
            $('#amountCharge').val(amountCharge);
            $('#amountChargeInWords').text(convertNumberToWord(amountCharge));
            $('#updatePaymentModal').modal('show');
            $('.field').val('');
        });
    }

    function updatePaymentDetail() {
        $('#btnUpdate').on('click', function () {
            form.validate({
                submitHandler: function (form) {
                    if (!validateFields()) {
                        return false;
                    }
                    var url = _baseURL() + 'updatePaymentDetail';
                    var data = $(form).serializeArray();
                    $.ajax({
                        url: url,
                        type: 'post',
                        data: data,
                        processData: true,
                        success: function (res) {
                            if (res.responseStatus == 1) {
                                successMsg(res.responseText);
                                $('#updatePaymentModal').modal('hide');
                                getPaymentList();
                            } else {
                                warningMsg(res.responseText);
                            }
                        }
                        , error: function (res) {
                            errorMsg(res.responseText);
                        }
                    });
                }
            });
        });
    }

    function validateFields() {
        var retval = true;
        if ($('#receiptNo').val() == "") {
            $('#receiptNo').addClass('error');
            $('#receiptNoErrorMsg').html('Receipt no is required');
            retval = false;
        }
        if ($('#depositDate').val() == "") {
            $('#depositDate').addClass('error');
            $('#depositDateErrorMsg').html('Deposit date is required');
            retval = false;
        }
        return retval;
    }

    function removeErrorMsg() {
        $('#receiptNo').on('keyup blur', function () {
            var receiptNo = $(this).val();
            if (receiptNo != '') {
                $('#receiptNo').removeClass('error');
                $('#receiptNoErrorMsg').text('');
            }
        });

        $('#depositDate').on('keyup blur change', function () {
            var depositDate = $(this).val();
            if (depositDate != '') {
                $('#depositDate').removeClass('error');
                $('#depositDateErrorMsg').text('');
            }
        });

    }

    return {
        getPaymentList: getPaymentList
        , showModel: showModel
        , removeErrorMsg: removeErrorMsg
        , updatePaymentDetail: updatePaymentDetail
    }
})
();
$(document).ready(
    function () {
        paymentDetail.getPaymentList();
        paymentDetail.showModel();
        paymentDetail.removeErrorMsg();
        paymentDetail.updatePaymentDetail();
    });
