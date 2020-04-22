/**
 * Created by USER on 8/15/2019.
 */

approveIssueEnglishLanProCertificate = (function () {
    var form = $('#approveIssueEnglishLanProCertificateFormId');

    function _baseURL() {
        return 'approveIssueEnglishLanProCertificate/';
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
                    if (data.isEmployed == 'Y') {
                        $('#employmentInfoDiv').show();
                    }
                    else {
                        $('#employmentInfoDiv').hide();
                    }
                    $('.readonly-field').prop('readonly', true);
                    $('#isEmployed').prop('disabled', true);
                }
            }
        });
    }

    function showHideRejectRemarks() {
        $('#rejectReason').on('change', function () {
            var rejectReason = $(this).val();
            if (rejectReason == 4) { //if the selected value is Other show this div
                $('.rejectRemarksDiv').show();
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
            var url = _baseURL() + 'saveApproveIssueEnglishLanProCertificate';
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
        , btnNext: btnNext
        , btnPrevious: btnPrevious
        , getApplicationDetail: getApplicationDetail
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
        $('.field').val('');
        approveIssueEnglishLanProCertificate.disableTabs();
        approveIssueEnglishLanProCertificate.btnNext();
        approveIssueEnglishLanProCertificate.btnPrevious();
        approveIssueEnglishLanProCertificate.getApplicationDetail();
        approveIssueEnglishLanProCertificate.showHideRejectRemarks();
        approveIssueEnglishLanProCertificate.removeErrorMsg();
        approveIssueEnglishLanProCertificate.approveOrReject();
        approveIssueEnglishLanProCertificate.downloadApplicationNo();
        approveIssueEnglishLanProCertificate.downloadApplicationNoReject();
    });
