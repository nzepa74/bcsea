/**
 * Created by N-Zepa on 21-Jun-19.
 */
issueReplacementCertificate = (function () {
    "use strict";
    var form = $('#issueReplacementCertificateFormId');
    var isSubmitted = false;

    function _baseURL() {
        return 'issueReplacementCertificate/';
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
            var cid = $('#cidNo').val();
            if (validatePersonalDetail() && validateCid(cid)) {
                $('#documentSelectionTab').prop('class', 'tab-pane active');
                $('#personalDetails').removeClass("active");
                $('#personalDetailTab').removeClass("active");
                $("#personalDetailTabId").css("color", "white");
                $('#personalDetailsCheck').html('<i class="fa fa-check text-white"></i>');
                $("#personalDetailTabId").css("background-color", "#120f65");
                $("#documentSelectionTabId").css("background-color", "rgb(18, 18, 19)");
                $("#documentSelectionTabId").css("color", "white");
                $('#personalDetails').addClass('disabled');
            }
        });
        $('#btnNext_2').on('click', function () {
            if (validateDocumentSelection()) {
                $('#otherInformationTab').prop('class', 'tab-pane active');
                $('#documentSelection').removeClass("active");
                $('#documentSelectionTab').removeClass("active");
                $('#documentSelectionCheck').html('<i class="fa fa-check text-white"></i>');
                $("#personalDetailTabId").css("color", "white");
                $("#personalDetailTabId").css("background-color", "#120f65");
                $("#documentSelectionTabId").css("background-color", "#120f65");
                $("#otherInformation").css("background-color", "rgb(18, 18, 19)");
                $("#otherInformation").css("color", "white");
            }
        });

    }

    function saveIssueReplacementCertificate() {
        $('#btnSubmit').on('click', function () {
            $('#issueReplacementCertificateFormId').validate({
                submitHandler: function (form) {
                    if (validateOtherInformation()) {
                        var url = _baseURL() + 'saveIssueReplacementCertificate';
                        var formData = new FormData(form);
                        if (isSubmitted) {
                            errorMsg('Your request is processing. Please wait...');
                            return;
                        }
                        isSubmitted = true;
                        $('#btnSubmit').attr('disabled', true);
                        $.ajax({
                            url: url,
                            type: 'POST',
                            data: formData,
                            enctype: 'multipart/form-data',
                            contentType: false,
                            processData: false,
                            success: function (res) {
                                if (res.responseStatus == 1) {
                                    $('#acknowledgement').removeClass('hidden');
                                    $('#registration').addClass('hidden');
                                    $('#serviceName').text(res.serviceName);
                                    $('#applicationNo').text(res.applicationNumber);
                                    $('.field').val('');
                                } else {
                                    warningMsg(res.responseText);
                                }
                            },
                            complete: function () {
                                isSubmitted = false;
                                $('#btnSubmit').attr('disabled', false);
                            },
                            error: function (res) {
                                errorMsg(res.responseText);
                            }
                        });
                    }
                }
            });
        });
    }

    function validateOtherInformation() {
        var retval = true;
        if ($('#documentCollection').val() == "") {
            $('#documentCollection').addClass('error');
            $('#documentCollectionErrorMsg').html('This field is required');
            retval = false;
        }
        if ($('#documentCollection').val() == "Y") {
            if ($('#collectedBy').val() == "") {
                $('#collectedBy').addClass('error');
                $('#collectedByErrorMsg').html('This field is required');
                retval = false;
            }
        }
        if ($('#isOldDocReturned').val() == "") {
            $('#isOldDocReturned').addClass('error');
            $('#isOldDocReturnedErrorMsg').html('This field is required');
            retval = false;
        }
        if ($('#reasonWithOriginalDoc').val() == "") {
            $('#reasonWithOriginalDoc').addClass('error');
            $('#reasonWithOriginalDocErrorMsg').html('This field is required');
            retval = false;
        }
        if ($('#changesRequired').val() == "") {
            $('#changesRequired').addClass('error');
            $('#changesRequiredErrorMsg').html('This field is required');
            retval = false;
        }
        if ($('#purpose').val() == "") {
            $('#purpose').addClass('error');
            $('#purposeErrorMsg').html('This field is required');
            retval = false;
        }
        return retval;
    }

    function validatePersonalDetail() {
        var retval = true;
        if ($('#cidNo').val() == "") {
            $('#cidNoErrorMsg').html('CID is required');
            $('#cidNo').addClass('error');
            retval = false;
        }
        if ($('#fatherName').val() == "") {
            $('#fatherName').addClass('error');
            $('#fatherNameErrorMsg').html('Father name is required');
            retval = false;
        }
        if ($('#address').val() == "") {
            $('#address').addClass('error');
            $('#addressErrorMsg').html('Present address is required');
            retval = false;
        }
        if ($('#mobileNo').val() == "") {
            $('#mobileNo').addClass('error');
            $('#mobileNoErrorMsg').html('Mobile Number is required');
            retval = false;
        } else if ($('#mobileNo').val().length != "8") {
            $('#mobileNo').addClass('error');
            $('#mobileNoErrorMsg').html('Mobile Number should have 8 digit');
            retval = false;
        }
        return retval;
    }

    function validateDocumentSelection() {
        var retval = true;
        if ($('#indexNo').val() == "") {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').html('Please provide your Index Number');
            retval = false;
        }
        if ($('#indexNo').val().length != 12 && $('#indexNo').val() != '') {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').html('Index number should be 12 digit long');
            retval = false;
        }
        var checked = $('.documentTypeId:checkbox:checked').length;
        if (checked < 1) {
            $('#documentErrorMsg').html('Please check the document you want');
            retval = false;
        }
        return retval;
    }

    function validateCid(cid) {
        //age verification
        //security validation
        var retval = true;
        if (cid.substring(0, 1) >= 3) {
            $('#cidNoErrorMsg').html('Cid starting with greater than 3 is not allow');
            $('#fullName').val('');
            $('#cidNo').addClass('error');
            retval = false;
        }
        else if (cid.length != 11 && cid != '') {
            $('#cidNoErrorMsg').html('Bhutanese CID should have 11 digit long');
            $('#fullName').val('');
            $('#cidNo').addClass('error');
            retval = false;
        }
        return retval;
    }

    function validateIndexNo(indexNo) {
        var retval = true;
        if (indexNo.length != 12 && indexNo != '') {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').html('Index number should be 12 digit long');
            retval = false;
        }
        return retval;
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

    function onChangeOriginalDocument() {
        $('#documentCollection').on('change', function () {
            var val = $(this).val();
            if (val == "Y") {
                $('#whenAndByWhomDiv').show();
            }
            else {
                $('#whenAndByWhomDiv').hide();
            }
        });
    }

    function fetchName() {
        $('#cidNo').on('blur', function () {
            var cidNo = $(this).val();
            if (validateCid(cidNo)) {
                if (cidNo != '') {
                    var url = _baseURL() + 'getCitizenName';
                    $.ajax({
                        url: url,
                        type: 'GET',
                        data: {cidNo: cidNo},
                        success: function (res) {
                            var data = res.responseDTO;
                            if (res.responseStatus == 1) {
                                $('#cidNoErrorMsg').html('');
                                $('#fullName').val(data.studentName);
                            } else {
                                $('#cidNoErrorMsg').html(res.responseText);
                                $('#fullName').val('');
                                $('#cidNo').val('');
                                $('#cidNo').addClass('error');
                            }
                        }
                    });
                } else {
                    $('#fullName').val('');
                }
            }
        });
    }

    function indexNoValidation() {
        $('#indexNo').on('blur', function () {
            var indexNo = $(this).val();
            var cidNo = $('#cidNo').val();
            if (validateIndexNo(indexNo)) {
                if (indexNo != '') {
                    var url = _baseURL() + 'indexNoValidation';
                    $.ajax({
                        url: url,
                        type: 'GET',
                        data: {cidNo: cidNo, indexNo: indexNo},
                        success: function (res) {
                            if (res.responseStatus == 0) {
                                $('#indexNoErrorMsg').html(res.responseText);
                                $('#indexNo').val('');
                                $('#indexNo').addClass('error');
                            } else {
                                $('#indexNoErrorMsg').html('');
                            }
                        }
                    });
                }
            }
        });
    }

    function removeErrorMsg() {
        $('#cidNo').on('keyup blur', function () {
            var cid = $(this).val();
            if (cid != '') {
                $('#cidNoErrorMsg').text('');
                $('#cidNo').removeClass('error');
            }
        });
        $('#mobileNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#mobileNo').removeClass('error');
                $('#mobileNoErrorMsg').text('');
            }
        });
        $('#fatherName').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#fatherName').removeClass('error');
                $('#fatherNameErrorMsg').text('');
            }
        });
        $('#email').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#email').removeClass('error');
                $('#emailErrorMsg').text('');
            }
        });
        $('#address').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#address').removeClass('error');
                $('#addressErrorMsg').text('');
            }
        });

        $('#indexNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#indexNo').removeClass('error');
                $('#indexNoErrorMsg').text('');
            }
        });
        $('#documentListTableId tbody').on('click', 'tr .documentTypeId', function () {
            $('#documentErrorMsg').text('');
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var documentId = selectedRow.find('.documentTypeId').val();
            var isChecked = $(this).prop('checked');
            var url = _baseURL() + 'getChargeByDocumentId';
            $.ajax({
                url: url
                , type: 'GET'
                , data: {documentId: documentId}
                , success: function (res) {
                    calculateCharge(res, isChecked);
                }
            });
            selectedRow.removeClass('selected');
        });
        $('#documentCollection').on('change', function () {
            var val = $(this).val();
            if (val != '') {
                $('#documentCollection').removeClass('error');
                $('#documentCollectionErrorMsg').text('');
                $('#collectedByErrorMsg').text('');
            }
            if (val != '') {
                $('#collectedBy').val('');
            }
        });
        $('#collectedBy').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#collectedBy').removeClass('error');
                $('#collectedByErrorMsg').text('');
            }
        });
        $('#reasonWithOriginalDoc').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#reasonWithOriginalDoc').removeClass('error');
                $('#reasonWithOriginalDocErrorMsg').text('');
            }
        });
        $('#changesRequired').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#changesRequired').removeClass('error');
                $('#changesRequiredErrorMsg').text('');
            }
        });
        $('#purpose').on('change', function () {
            var val = $(this).val();
            if (val != '') {
                $('#purpose').removeClass('error');
                $('#purposeErrorMsg').text('');
            }
        });
    }

    function calculateCharge(charge, isChecked) {
        var chargeApplied = $('#chargeApplied').val();
        if (chargeApplied == '') {
            chargeApplied = 0;
        }
        if (isChecked) {
            $('#chargeApplied').val(parseInt(charge) + parseInt(chargeApplied));
        } else {
            $('#chargeApplied').val(parseInt(chargeApplied) - parseInt(charge));
        }
    }

    function getDocumentList() {
        var url = _baseURL() + 'getDocumentList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    for (var i = 0; i < data.length; i++) {
                        var documentTypeId = data[i].valueInteger;
                        var documentName = data[i].text;
                        var row = '<tr style="border-top: hidden">' +
                            '<td><input type="checkbox" name="appliedDocumentDTOs[' + i + '].documentTypeId" ' +
                            'value="' + documentTypeId + '" id="documentTypeId' + i + '" class="documentTypeId"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '<label for="documentTypeId' + i + '">' + documentName + '</label></td>' +
                            '</tr>';
                        $('#documentListTableId tbody').append(row);
                    }
                } else {
                    $('#documentList').append('<div class="col-sm-6"><font color="red">Document list not available at the moment.</font></div>');
                }
            }
        });
    }

    function showFileAttachmentDiv() {
        $('#purpose').on('change', function () {
            var val = $(this).val();
            if (val != "") {
                $('#fileAttachmentDiv').show();
            }
            else {
                $('#fileAttachmentDiv').hide();
            }
        });
    }

    function addMoreAttachment() {
        $('#fileAttachmentTableId tbody').on('click', '#btnAddMore', function () {
            var row = "<tr style='border-top: hidden'>" +
                "<td>" + "<input type='file' id ='attachedFile' class='attachedFile' name ='fileAttachmentDTOs[0].attachedFile' required accept='image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls'/>" + "</td>" +
                "<td>" + "<button class='btn btn-sm btn-danger' type='button' id='btnRemove'><i class='fa fa-times'>Delete</i></button> &nbsp;&nbsp;&nbsp;&nbsp;" +
                "<button class='btn btn-sm btn-success' type='button' id='btnAddMore'><i class='fa fa-plus'>Add More</i></button>" + "</td>" +
                "</tr>";
            $('#fileAttachmentTableId tbody').append(row);
            $(this).addClass('hidden');
            $('#btnRemove').removeClass('hidden');
            formIndexing($('#fileAttachmentTableId tbody'), $('#fileAttachmentTableId tbody').find('tr'));
        });
    }

    function deleteAttachment() {
        $('#fileAttachmentTableId tbody').on('click', 'tr #btnRemove', function () {
            var rowLen = $('#fileAttachmentTableId tbody tr').length;
            $(this).closest('tr').remove();
            if (rowLen == 2) {
                $('#fileAttachmentTableId tr').last().find('#btnRemove').addClass('hidden');
            }
            $('#fileAttachmentTableId tr').last().find('#btnAddMore').removeClass('hidden');
            formIndexing($('#fileAttachmentTableId tbody'), $('#fileAttachmentTableId tbody').find('tr'));
        });
    }

    function checkFileSize() {
        $('#fileAttachmentTableId tbody').on('change', 'tr input[type=file]', function () {
            if ($('#attachedFile')[0].files.length > 0) {
                if ($('#attachedFile')[0].files[0].size < 2097152 == false) {
                    warningMsg('File too large. Please attach file size less than 2 MB.');
                    $('#attachedFile').val('');
                }
            }
        });
    }

    $('#email').on('blur', function () {
        var email = $(this).val().trim();
        if (email != '' && !isEmail(email)) {
            $('#emailErrorMsg').text('Email format is invalid.');
            $('#email').val('');
            $('#email').addClass('error');
        }
    });

    return {
        disableTabs: disableTabs
        , btnNext: btnNext
        , btnPrevious: btnPrevious
        , getDocumentList: getDocumentList
        , onChangeOriginalDocument: onChangeOriginalDocument
        , removeErrorMsg: removeErrorMsg
        , indexNoValidation: indexNoValidation
        , fetchName: fetchName
        , showFileAttachmentDiv: showFileAttachmentDiv
        , addMoreAttachment: addMoreAttachment
        , checkFileSize: checkFileSize
        , deleteAttachment: deleteAttachment
        , saveIssueReplacementCertificate: saveIssueReplacementCertificate
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        issueReplacementCertificate.disableTabs();
        issueReplacementCertificate.btnNext();
        issueReplacementCertificate.btnPrevious();
        issueReplacementCertificate.getDocumentList();
        issueReplacementCertificate.onChangeOriginalDocument();
        issueReplacementCertificate.removeErrorMsg();
        issueReplacementCertificate.indexNoValidation();
        issueReplacementCertificate.fetchName();
        issueReplacementCertificate.showFileAttachmentDiv();
        issueReplacementCertificate.addMoreAttachment();
        issueReplacementCertificate.checkFileSize();
        issueReplacementCertificate.deleteAttachment();
        issueReplacementCertificate.saveIssueReplacementCertificate();
    });
