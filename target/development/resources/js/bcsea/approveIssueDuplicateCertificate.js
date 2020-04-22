/**
 * Created by USER on 8/2/2019.
 */

approveIssueDuplicateCertificate = (function () {
    var form = $('#approveIssueDuplicateCertificateFormId');

    function _baseURL() {
        return 'approveIssueDuplicateCertificate/';
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
                    if (data.documentCollection == "Y") {
                        $('#whenAndByWhomDiv').show();
                    }
                    else {
                        $('#whenAndByWhomDiv').hide();
                    }
                    $('.readonly-field').prop('readonly', true);
                    $('#documentCollection').prop('disabled', true);
                }
            }
        });
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


    function getAppliedDocumentList() {
        var applicationNo = $('#applicationNo').val();
        var url = _baseURL() + 'getAppliedDocumentList';
        $.ajax({
            url: url
            , type: 'GET'
            , data: {applicationNo: applicationNo}
            , success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    for (var i = 0; i < data.length; i++) {
                        var documentTypeId = data[i].valueInteger;
                        var documentName = data[i].text;
                        var row = '<tr style="border-top: hidden">' +
                            '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;' +
                            '<input type="checkbox" checked disabled/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '<label>' + documentName + '</label></td>' +
                            '</tr>';
                        $('#documentListTableId tbody').append(row);
                    }
                } else {
                    $('#documentList').append('<div class="col-sm-6"><font color="red">Document list not available at the moment.</font></div>');
                }
            }
        });
    }

    function getChargedApplied() {
        var url = _baseURL() + 'getChargedApplied';
        var applicationNo = $('#applicationNo').val();
        $.ajax({
            url: url
            , type: 'GET'
            , data: {applicationNo: applicationNo}
            , success: function (res) {
                $('#totalAmountCharged').val(res);
            }
        });
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

    function approveOrReject() {
        $('.btnSave').on('click', function () {
            var btnType = $(this).val();
            var applicationNo = $('#applicationNo').val();
            var remarks = $('#remarks').val();
            var rejectReasonId = $('#rejectReason').val();
            var rejectRemarks = $('#rejectRemarks').val();

            if (btnType == 'btnApprove') {
                $('#rejectReason').val('');
                $('#rejectRemarks').val('');
                rejectReasonId = null;
                rejectRemarks = null;
            } else {
                if (rejectReasonId == '') {
                    $('#rejectReason').addClass('error');
                    $('#rejectReasonErrorMsg').text('Reject reason is required');
                    return;
                }
                if (rejectReasonId == 4 && rejectRemarks == '') {
                    $('#rejectRemarks').addClass('error');
                    $('#rejectRemarksErrorMsg').text('Reject remarks is required');
                    return;
                }
            }
            var url = _baseURL() + 'saveApproveIssueDuplicateCertificate';
            $.ajax({
                url: url,
                type: 'POST',
                data: {
                    applicationNo: applicationNo
                    , remarks: remarks
                    , rejectReasonId: rejectReasonId
                    , rejectRemarks: rejectRemarks
                },
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
                    errorMsg(res.responseText)
                }
            });
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
        , getApplicationDetail: getApplicationDetail
        , btnNext: btnNext
        , btnPrevious: btnPrevious
        , getAppliedDocumentList: getAppliedDocumentList
        , getChargedApplied: getChargedApplied
        , showHideRejectRemarks: showHideRejectRemarks
        , removeErrorMsg: removeErrorMsg
        , approveOrReject: approveOrReject
        , downloadApplicationNo: downloadApplicationNo
        , downloadApplicationNoReject: downloadApplicationNoReject
    }
})
();
$(document).ready(
    function () {
        approveIssueDuplicateCertificate.disableTabs();
        approveIssueDuplicateCertificate.getApplicationDetail();
        approveIssueDuplicateCertificate.btnNext();
        approveIssueDuplicateCertificate.btnPrevious();
        approveIssueDuplicateCertificate.getAppliedDocumentList();
        approveIssueDuplicateCertificate.getChargedApplied();
        approveIssueDuplicateCertificate.showHideRejectRemarks();
        approveIssueDuplicateCertificate.removeErrorMsg();
        approveIssueDuplicateCertificate.approveOrReject();
        approveIssueDuplicateCertificate.downloadApplicationNo();
        approveIssueDuplicateCertificate.downloadApplicationNoReject();
    });

