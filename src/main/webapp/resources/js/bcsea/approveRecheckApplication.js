/**
 * Created by USER on 8/20/2019.
 */
approveRecheckApplication = (function () {
    var form = $('#approveRecheckApplicationFormId');

    function _baseURL() {
        return 'approveRecheckApplication/';
    }

    function disableTabs() {
        $('#personalDetails').prop('class', 'active');
        $('#personalDetails').not('.active').addClass('disabled');
        $('#personalDetailTab').prop('class', 'tab-pane active');

        $("#personalDetailTabId").css("color", "white");
        $("#personalDetailTabId").css("background-color", "rgb(18, 18, 19)");

        $('#documentSelection').not('.active').addClass('disabled');
        $('#documentSelection').not('.active').find('a').removeAttr("data-toggle");

        $('#otherInformation').not('.active').addClass('disabled');
        $('#otherInformation').not('.active').find('a').removeAttr("data-toggle");
    }

    function btnNext() {
        $('#btnNext_1').on('click', function () {
            $('#documentSelectionTab').prop('class', 'tab-pane active');
            $('#personalDetails').removeClass("active");
            $('#personalDetailTab').removeClass("active");
            $("#personalDetailTabId").css("color", "white");
            $('#personalDetailsCheck').html('<i class="fa fa-check text-white"></i>');
            $("#personalDetailTabId").css("background-color", "#120f65");
            $("#documentSelectionTabId").css("background-color", "rgb(18, 18, 19)");
            $("#documentSelectionTabId").css("color", "white");
            $('#personalDetails').addClass('disabled');
        });
        $('#btnNext_2').on('click', function () {
            $('#otherInformationTab').prop('class', 'tab-pane active');
            $('#documentSelection').removeClass("active");
            $('#documentSelectionTab').removeClass("active");
            $('#documentSelectionCheck').html('<i class="fa fa-check text-white"></i>');
            $("#personalDetailTabId").css("color", "white");
            $("#personalDetailTabId").css("background-color", "#120f65");
            $("#documentSelectionTabId").css("background-color", "#120f65");
            $("#otherInformation").css("background-color", "rgb(18, 18, 19)");
            $("#otherInformation").css("color", "white");
        });
        $('#btnSubmit').on('click', function () {

        });
    }

    function btnPrevious() {
        $('#btnPrevious_1').on('click', function () {
            $('#personalDetailTab').prop('class', 'tab-pane active');
            $('#documentSelection').removeClass("active");
            $('#documentSelectionTab').removeClass("active");
            $("#personalDetailTabId").css("background-color", "rgb(18, 18, 19)");
            $("#documentSelectionTabId").css("background-color", "#120f65");
        });

        $('#btnPrevious_2').on('click', function () {
            $('#documentSelectionTab').prop('class', 'tab-pane active');
            $('#otherInformation').removeClass("active");
            $('#otherInformationTab').removeClass("active");
            $("#documentSelectionTabId").css("background-color", "rgb(18, 18, 19)");
            $("#otherInformation").css("background-color", "#120f65");
        });
    }

    function getApplicationDetail() {
        var applicationNo = $('#applicationNo').val();
        var url = _baseURL() + 'getApplicationDetail';
        $.ajax({
            url: url
            , type: 'GET'
            , data: {applicationNo: applicationNo}
            , success: function (res) {
                var data = res.responseDTO;
                if (res.responseStatus == 1) {
                    populate(data);
                    var monthName = getMonthName(data.examMonth);
                    $('#examMonth').val(monthName);
                    getRecheckPapers(applicationNo);
                    $('.readonly-field').prop('readonly', true);
                    $('#documentCollection').prop('disabled', true);
                }
            }
        });
    }

    function getRecheckPapers(applicationNo) {
        var url = _baseURL() + 'getRecheckPapers';
        $.ajax({
            url: url
            , type: 'GET'
            , data: {applicationNo: applicationNo}
            , success: function (res) {
                var data = res.responseDTO;
                if (res.responseStatus == 1) {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr>" +
                            "<td class='recheckDetailId hidden'>" + "<input type='text' id ='recheckDetailId' value='" + data[i].recheckDetailId + "' class='col-sm-4' name='subjectDTOs[0].recheckDetailId'>" + "</td>" +
                            "<td class='subjectId hidden'>" + data[i].subjectId + "</td>" +
                            "<td class='paperId hidden'>" + data[i].paperId + "</td>" +
                            "<td class='slNo'>" + parseInt(i + 1) + "</td>" +
                            "<td class='menuTypeName'>" + isSubjectSame(data[i].subjectId, data[i].subjectName) + "</td>" +
                            "<td class='paperName'>" + data[i].paperName + "</td>" +
                            "<td class='oldMarks'>" + isNull(data[i].oldMarks) + "</td>" +
                            "<td class='newMarks'>" + "<input type='text' id ='newMarks' class='form-control col-sm-4 newMarks' name='subjectDTOs[0].newMarks'>" + "</td>" +
                            "</tr>";
                        $('#recheckPaperListTableId tbody').append(row);
                        formIndexing($('#recheckPaperListTableId tbody'), $('#recheckPaperListTableId tbody').find('tr'));
                    }
                    getTotalChargedApplied();
                }
            }
        });
    }

    function getTotalChargedApplied() {
        var rowNumber = $('#recheckPaperListTableId tbody> tr').length;
        var url = _baseURL() + 'getRecheckCharge';
        $.ajax({
            url: url
            , type: 'GET'
            , success: function (res) {
                var data = res.responseDTO;
                if (res.responseStatus == 1) {
                    var totalAmountCharged = rowNumber * data.amountCharge;
                    $("#chargeApplied").val(totalAmountCharged);
                }
            }
        });
    }

    function isSubjectSame(subjectId, subjectName) {
        if (!duplicateMenuType(subjectId)) {
            return subjectName;
        } else {
            return '';
        }
    }

    function duplicateMenuType(subjectId) {
        var isExist = false;
        $('#recheckPaperListTableId').find('tbody tr').each(function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            if (selectedRow.find('.subjectId').text() == subjectId) {
                isExist = true;
            }
            row.removeClass('selected');
        });
        return isExist;
    }

    function isNull(data) {
        if (data == null) {
            return '';
        }
    }

    function getMonthName(monthId) {
        var monthName;
        if (monthId == 1) {
            monthName = 'January';
        } else if (monthId == 2) {
            monthName = 'February';
        } else if (monthId == 3) {
            monthName = 'March';
        } else if (monthId == 4) {
            monthName = 'April';
        } else if (monthId == 5) {
            monthName = 'May';
        } else if (monthId == 6) {
            monthName = 'June';
        } else if (monthId == 7) {
            monthName = 'July';
        } else if (monthId == 8) {
            monthName = 'August';
        } else if (monthId == 9) {
            monthName = 'September';
        } else if (monthId == 10) {
            monthName = 'October';
        } else if (monthId == 11) {
            monthName = 'November';
        } else if (monthId == 12) {
            monthName = 'December';
        }
        return monthName;
    }

    function showHideRejectRemarks() {
        $('#rejectReason').on('change', function () {
            var rejectReason = $(this).val();
            if (rejectReason == 4) { //if the selected value is Other show this div
                $('.rejectRemarksDiv').show();
                $('#rejectRemarksErrorMsg').text('');
            } else {
                $('.rejectRemarksDiv').hide();
            }
        });
    }

    function removeErrorMsg() {
        $('#rejectReason').on('change', function () {
            var value = $(this).val();
            if (value != '') {
                $('#rejectReason').removeClass('error');
                $('#rejectReasonErrorMsg').text('');
            }
        });
        $('#rejectRemarks').on('keyup blur', function () {
            var value = $(this).val().trim();
            if (value != '') {
                $('#rejectRemarks').removeClass('error');
                $('#rejectRemarksErrorMsg').text('');
            }
        });
    }

    function btnSubmitReject() {
        $('.btnSave').on('click', function () {
            var btnType = $(this).val();
            var applicationNo = $('#applicationNo').val();
            var remarks = $('#remarks').val();
            var rejectReasonId = $('#rejectReason').val();
            var rejectRemarks = $('#rejectRemarks').val();
            if (btnType == 'btnApprove') {
                $('#rejectReason').val('');
                $('#rejectRemarks').val('');
            } else {
                if (rejectReasonId == '') {
                    $('#rejectReason').addClass('error');
                    $('#rejectReasonErrorMsg').text('Reject reason is required');
                    return false;
                }
                if (rejectReasonId == 4 && rejectRemarks == '') {
                    $('#rejectRemarks').addClass('error');
                    $('#rejectRemarksErrorMsg').text('Reject remarks is required');
                    return false;
                }
            }
            approveOrReject();
        });
    }

    function approveOrReject() {
        form.validate({
            submitHandler: function (form) {
                var url = _baseURL() + 'saveApproveRecheckApplication';
                var data = $(form).serializeArray();
                $.ajax({
                    url: url,
                    type: 'post',
                    data: data,
                    processData: true,
                    success: function (res) {
                        if (res.responseStatus == 3) {//reject
                            $('#registration').addClass('hidden');
                            $('#approveAcknowledgement').removeClass('hidden');
                            $('#serviceNameApprove').text(res.serviceName);
                            $('#applicationNoApprove').text(res.applicationNumber);
                            $('.field').val('');
                        } else if (res.responseStatus == 4) {//approve
                            $('#registration').addClass('hidden');
                            $('#rejectAcknowledgement').removeClass('hidden');
                            $('#serviceNameReject').text(res.serviceName);
                            $('#applicationNoReject').text(res.applicationNumber);
                            $('#rejectReasonReject').text(res.rejectReason);
                            $('.field').val('');
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
    }

    function downloadApplicationNo() {
        var doc = new jsPDF();
        var specialElementHandlers = {
            '#editor': function (element, renderer) {
                return true;
            }
        };
        $('#btnDownload').click(function () {
            doc.fromHTML($('#downloadAsPdf').html(), 15, 15, {
                'width': 170,
                'elementHandlers': specialElementHandlers
            });
            doc.save('Ack_Replacement_Certificate.pdf');
        });
    }

    function downloadApplicationNoReject() {
        var doc = new jsPDF();
        var specialElementHandlers = {
            '#editor': function (element, renderer) {
                return true;
            }
        };
        $('#btnDownloadReject').click(function () {
            doc.fromHTML($('#downloadAsPdfReject').html(), 15, 15, {
                'width': 170,
                'elementHandlers': specialElementHandlers
            });
            doc.save('Ack_Replacement_Certificate.pdf');
        });
    }

    return {
        disableTabs: disableTabs
        , btnNext: btnNext
        , btnPrevious: btnPrevious
        , getApplicationDetail: getApplicationDetail
        , showHideRejectRemarks: showHideRejectRemarks
        , removeErrorMsg: removeErrorMsg
        , btnSubmitReject: btnSubmitReject
        , downloadApplicationNo: downloadApplicationNo
        , downloadApplicationNoReject: downloadApplicationNoReject
    }
})
();
$(document).ready(
    function () {
        approveRecheckApplication.disableTabs();
        approveRecheckApplication.btnNext();
        approveRecheckApplication.btnPrevious();
        approveRecheckApplication.getApplicationDetail();
        approveRecheckApplication.showHideRejectRemarks();
        approveRecheckApplication.removeErrorMsg();
        approveRecheckApplication.btnSubmitReject();
        approveRecheckApplication.downloadApplicationNo();
        approveRecheckApplication.downloadApplicationNoReject();
    });
