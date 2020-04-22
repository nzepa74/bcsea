/**
 * Created by N-Zepa on 21-Jun-19.
 */
issueDuplicateCertificate = (function () {
    "use strict";
    var form = $('#issueDuplicateCertificateFormId');
    var isSubmitted = false;

    function _baseURL() {
        return 'issueDuplicateCertificate/';
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
        $('#btnSubmit').on('click', function () {
            saveIssueDuplicateCertificate();
        });
    }

    function validatePersonalDetail() {
        var retval = true;
        if ($('#cidNo').val() == "") {
            $('#cidNo').addClass('error');
            $('#cidNoErrorMsg').html('CID is required');
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

    function validateOtherInformation() {
        var retval = true;
        if ($('#documentCollection').val() == "") {
            $('#documentCollection').addClass('error');
            $('#documentCollectionErrorMsg').html('This field is required');
            retval = false
        }
        if ($('#documentCollection').val() == "Y") {
            if ($('#byWhom').val() == "") {
                $('#byWhom').addClass('error');
                $('#byWhomErrorMsg').html('This field is required');
                retval = false;
            }
        }
        if ($('#reasonWithOriginalDoc').val() == "") {
            $('#reasonWithOriginalDoc').addClass('error');
            $('#reasonWithOriginalDocErrorMsg').html('This field is required');
            retval = false
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

    function validateCid(cid) {
        //age verification
        //security validation
        var retval = true;
        if (cid.substring(0, 1) >= 3) {
            $('#cidNo').addClass('error');
            $('#cidNoErrorMsg').html('Cid starting with greater than 3 is not allow');
            $('#fullName').val('');
            retval = false;
        }
        else if (cid.length != 11 && cid != '') {
            $('#cidNo').addClass('error');
            $('#cidNoErrorMsg').html('Bhutanese CID should have 11 digit long');
            $('#fullName').val('');
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
                $('#cidNo').removeClass('error');
                $('#cidNoErrorMsg').text('');
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
        $('#address').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#address').removeClass('error');
                $('#addressErrorMsg').text('');
            }
        });
        $('#email').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#email').removeClass('error');
                $('#emailErrorMsg').text('');
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
            }
        });
        $('#byWhom').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#byWhom').removeClass('error');
                $('#byWhomErrorMsg').text('');
            }
        });
        $('#reasonWithOriginalDoc').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#reasonWithOriginalDoc').removeClass('error');
                $('#reasonWithOriginalDocErrorMsg').text('');
            }
        });
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

    function saveIssueDuplicateCertificate() {
        form.validate({
            submitHandler: function (form) {
                var isValid = validateOtherInformation();
                if (isValid) {
                    var url = _baseURL() + 'saveIssueDuplicateCertificate';
                    var data = $(form).serializeArray();
                    if (isSubmitted) {
                        errorMsg('Your request is processing. Please wait...');
                        return;
                    }
                    isSubmitted = true;
                    $('#btnSubmit').attr('disabled', true);
                    $.ajax({
                        url: url,
                        type: 'post',
                        data: data,
                        processData: true,
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
                        }, error: function (res) {
                            warningMsg(res.responseText);
                        }
                    });
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
        , onChangeOriginalDocument: onChangeOriginalDocument
        , removeErrorMsg: removeErrorMsg
        , fetchName: fetchName
        , indexNoValidation: indexNoValidation
        , getDocumentList: getDocumentList
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        issueDuplicateCertificate.disableTabs();
        issueDuplicateCertificate.btnNext();
        issueDuplicateCertificate.btnPrevious();
        issueDuplicateCertificate.onChangeOriginalDocument();
        issueDuplicateCertificate.removeErrorMsg();
        issueDuplicateCertificate.fetchName();
        issueDuplicateCertificate.indexNoValidation();
        issueDuplicateCertificate.getDocumentList();
    });
