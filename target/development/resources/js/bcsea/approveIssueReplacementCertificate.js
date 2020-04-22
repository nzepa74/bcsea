/**
 * Created by USER on 8/12/2019.
 */
approveIssueReplacementCertificate = (function () {
    var form = $('#approveIssueReplacementCertificateFormId');

    function _baseURL() {
        return 'approveIssueReplacementCertificate/';
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
                    $('#isOldDocReturned').prop('disabled', true);
                    $('#purpose').prop('disabled', true);
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

        $('#btnSubmit').on('click', function () {
            //saveapproveIssueReplacementCertificate();
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

    function getAttachedFiles() {
        var url = _baseURL() + 'getAttachedFiles';
        var applicationNo = $('#applicationNo').val();
        $.ajax({
            url: url
            , type: 'GET'
            , data: {applicationNo: applicationNo}
            , success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {"data": "documentId", class: "documentId hidden"}
                        , {"data": "uploadUrl", class: "uploadUrl hidden"}
                        , {
                            "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {"data": "documentName", class: "documentName"}
                        , {
                            "data": "", class: "btnView",
                            "defaultContent": "<button class='btn btn-sm btn-success btnView' type='button' id='btnView'><i class='fa fa-eye'>View</i></button>"
                        }, {
                            "data": "", class: "btnDownload",
                            "defaultContent": "<button class='btn btn-sm btn-success btnDownload' type='button' id='btnDownload'><i class='fa fa-download'>Download</i></button>"
                        }
                    ];
                    $('#attachmentDocumentTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                        , search: false
                    });
                }
            }
        });
    }

    function downloadFile() {
        $('#attachmentDocumentTableId tbody').on('click', 'tr .btnDownload', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var documentId = selectedRow.find('.documentId').text();
            var uploadUrl = selectedRow.find('.uploadUrl').text();
            selectedRow.removeClass('selected');
            var url = _baseURL() + 'downloadFile/' + documentId;
            $.ajax({
                url: url,
                type: 'GET',
                data: {documentId: documentId},
                success: function () {
                    window.location.href = url;
                }
            });
        });

        $('#attachmentDocumentTableId tbody').on('click', 'tr .btnView', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var documentId = selectedRow.find('.documentId').text();
            var uploadUrl = selectedRow.find('.uploadUrl').text();
            selectedRow.removeClass('selected');
            window.open(uploadUrl, '_blank');
            //window.open(uploadUrl, '_blank');
            //var w = window.open(url, '_blank');
            //w.focus();
        });
    }

    function showHideRejectRemarks() {
        $('#rejectReason').on('change', function () {
            var rejectReason = $(this).val();
            if (rejectReason == 4) { //if the selected value is Other show this div, need to change based on values from domain table
                $('.rejectRemarksDiv').show();
            } else {
                $('.rejectRemarksDiv').hide();
                $('#rejectRemarks').val('');
            }
        });
    }

    function onChangeIfAny() {
        $('#changes').on('change', function () {
            var changesId = $(this).val();
            if (changesId == 1) {
                $('.nameChange').show();
                $('.dobChange').hide();
            } else if (changesId == 2) {
                $('.dobChange').show();
                $('.nameChange').hide();
            } else if (changesId == 3) {
                $('.dobChange').show();
                $('.nameChange').show();
            } else {
                $('.dobChange').hide();
                $('.nameChange').hide();
            }
        });
    }

    /*
     var changesValue = $('#changes');
     var nameChange = $('#nameChange');
     var dobChange = $('#dobChange');
     if (changesValue.val() == "") {
     $('#changesErrorMsg').html('Change if any is required');
     retval = false;
     }
     if (changesValue.val() == 1 && nameChange.val() == '') {
     $('#nameChangeErrorMsg').html('Name change is required');
     retval = false;
     }
     if (changesValue.val() == 2 && dobChange.val() == '') {
     $('#dobChangeErrorMsg').html('DOB change is required');
     retval = false;
     }
     if (changesValue.val() == 3) {
     if (nameChange.val() == '') {
     $('#nameChangeErrorMsg').html('Name change is required');
     retval = false;
     }
     if (dobChange.val() == '') {
     $('#dobChangeErrorMsg').html('DOB change is required');
     retval = false;
     }
     }
     */
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
        $('#changes').on('change', function () {
            var value = $(this).val().trim();
            if (value != '') {
                $('#changesErrorMsg').text('');
                $('#nameChangeErrorMsg').text('');
                $('#dobChangeErrorMsg').text('');
                $('#nameChange').val('');
                $('#dobChange').val('');
                $('#changes').removeClass('error');
                $('#dobChange').removeClass('error');
                $('#nameChange').removeClass('error');
            }
        });
        $('#nameChange').on('keyup blur', function () {
            var value = $(this).val().trim();
            if (value != '') {
                $('#nameChange').removeClass('error');
                $('#nameChangeErrorMsg').text('');
            }
        });
        $('#dobChange').on('change blur keyup', function () {
            var value = $(this).val().trim();
            if (value != '') {
                $('#dobChange').removeClass('error');
                $('#dobChangeErrorMsg').text('');
            }
        });
    }

    function validateChanges() {
        var retval = true;
        var changesValue = $('#changes');
        var nameChange = $('#nameChange');
        var dobChange = $('#dobChange');
        if (changesValue.val() == "") {
            $('#changes').addClass('error');
            $('#changesErrorMsg').html('Change if any is required');
            retval = false;
        }
        if (changesValue.val() == 1 && nameChange.val() == '') {
            $('#nameChange').addClass('error');
            $('#nameChangeErrorMsg').html('Name change is required');
            retval = false;
        }
        if (changesValue.val() == 2 && dobChange.val() == '') {
            $('#dobChange').addClass('error');
            $('#dobChangeErrorMsg').html('DOB change is required');
            retval = false;
        }
        if (changesValue.val() == 3) {
            if (nameChange.val() == '') {
                $('#nameChange').addClass('error');
                $('#nameChangeErrorMsg').html('Name change is required');
                retval = false;
            }
            if (dobChange.val() == '') {
                $('#dobChange').addClass('error');
                $('#dobChangeErrorMsg').html('DOB change is required');
                retval = false;
            }
        }
        return retval;
    }


    function approveOrReject() {
        $('.btnSave').on('click', function () {
            var btnType = $(this).val();
            var applicationNo = $('#applicationNo').val();
            var remarks = $('#remarks').val();
            var rejectReasonId = $('#rejectReason').val();
            var rejectRemarks = $('#rejectRemarks').val();

            var nameChange = $('#nameChange').val();
            var dobChange = $('#dobChange').val();

            if (btnType == 'btnApprove') {
                if (!validateChanges()) {
                    return false;
                }
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
                $('#changes').val('');
                $('#nameChange').val('');
                $('#dobChange').val('');
            }
            var url;
            if (dobChange != '') {
                url = _baseURL() + 'saveApproveIssueReplacementCertificate';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {
                        applicationNo: applicationNo
                        , remarks: remarks
                        , rejectReasonId: rejectReasonId
                        , rejectRemarks: rejectRemarks
                        , nameChange: nameChange
                        , dobChange: dobChange
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
                    },
                    error: function (res) {
                        errorMsg(res.responseText)
                    }
                });
            } else {
                url = _baseURL() + 'saveApproveIssueReplacementCertificateWithoutDate';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {
                        applicationNo: applicationNo
                        , remarks: remarks
                        , rejectReasonId: rejectReasonId
                        , rejectRemarks: rejectRemarks
                        , nameChange: nameChange
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
        , getApplicationDetail: getApplicationDetail
        , btnNext: btnNext
        , btnPrevious: btnPrevious
        , getAppliedDocumentList: getAppliedDocumentList
        , getChargedApplied: getChargedApplied
        , getAttachedFiles: getAttachedFiles
        , downloadFile: downloadFile
        , showHideRejectRemarks: showHideRejectRemarks
        , onChangeIfAny: onChangeIfAny
        , removeErrorMsg: removeErrorMsg
        , approveOrReject: approveOrReject
        , downloadApplicationNo: downloadApplicationNo
        , downloadApplicationNoReject: downloadApplicationNoReject
    }
})
();
$(document).ready(
    function () {
        approveIssueReplacementCertificate.disableTabs();
        approveIssueReplacementCertificate.getApplicationDetail();
        approveIssueReplacementCertificate.btnNext();
        approveIssueReplacementCertificate.btnPrevious();
        approveIssueReplacementCertificate.getAppliedDocumentList();
        approveIssueReplacementCertificate.getChargedApplied();
        approveIssueReplacementCertificate.getAttachedFiles();
        approveIssueReplacementCertificate.downloadFile();
        approveIssueReplacementCertificate.showHideRejectRemarks();
        approveIssueReplacementCertificate.onChangeIfAny();
        approveIssueReplacementCertificate.removeErrorMsg();
        approveIssueReplacementCertificate.approveOrReject();
        approveIssueReplacementCertificate.downloadApplicationNo();
        approveIssueReplacementCertificate.downloadApplicationNoReject();
    });

