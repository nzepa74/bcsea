/**
 * Created by N-Zepa on 21-Jun-19.
 */
issueEnglishLanProCertificate = (function () {
    "use strict";
    var form = $('#issueEnglishLanProCertificateFormId');
    var isSubmitted = false;

    function _baseURL() {
        return 'issueEnglishLanProCertificate/';
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
            saveIssueEnglishLanProCertificate();
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
        if ($('#purpose').val() == "") {
            $('#purpose').addClass('error');
            $('#purposeErrorMsg').html('Purpose of the certificate is required');
            retval = false;
        }
        if ($('#englishPassMark').val() == "" || $('#englishPassMark').val() < 40) {
            retval = false;
        }
        return retval;
    }

    function validateIndexNo(indexNo) {
        var retval = true;
        if (indexNo.length != 12 && indexNo != '') {
            $('#indexNoErrorMsg').html('index number should be 12 digit long');
            $('#indexNo').addClass('error');
            retval = false;
        }
        return retval;
    }

    function validateCid(cid) {
        var retval = true;
        if (cid.substring(0, 1) >= 3) {
            $('#cidNoErrorMsg').html('Cid starting with greater than 3 is not allow');
            $('#cidNo').addClass('error');
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

    function onChangeAreYouEmployed() {
        $('#isEmployed').on('change', function () {
            var val = $(this).val();
            if (val == "Y") {
                $('#employmentInfoDiv').show();
            }
            else {
                $('#employmentInfoDiv').hide();
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
                $('#mobileNoErrorMsg').text('');
                $('#mobileNo').removeClass('error');
            }
        });
        $('#email').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#email').removeClass('error');
                $('#emailErrorMsg').text('');
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
        $('#indexNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#indexNo').removeClass('error');
                $('#indexNoErrorMsg').text('');
            }
        });
        $('#purpose').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#purpose').removeClass('error');
                $('#purposeErrorMsg').text('');
            }
        });
        $('#isEmployed').on('change', function () {
            var val = $(this).val();
            if (val != '') {
                $('#isEmployed').removeClass('error');
                $('#isEmployedErrorMsg').text('');
            }
        });
        $('#agencyName').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#agencyName').removeClass('error');
                $('#agencyNameErrorMsg').text('');
            }
        });
        $('#designation').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#designation').removeClass('error');
                $('#designationErrorMsg').text('');
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
                                $('#englishMark').html('');
                                $('#indexNo').addClass('error');
                            } else {
                                $('#indexNoErrorMsg').html('');
                                getEnglishMarkByIndexNo(indexNo);
                            }
                        }
                    });
                }
            }
        });
    }

    function getEnglishMarkByIndexNo(indexNo) {
        var url = _baseURL() + 'getEnglishMarkByIndexNo';
        $.ajax({
            url: url,
            type: 'GET',
            data: {indexNo: indexNo},
            success: function (res) {
                if (res.responseStatus == 0) {
                    $('#indexNoErrorMsg').html(res.responseText);
                    $('#indexNo').val('');
                    $('#englishMark').html('');
                } else {
                    var englishMark = res.responseDTO.englishMark;
                    $('#englishPassMark').val(englishMark);
                    if (englishMark < 40) {
                        $('#indexNoErrorMsg').html('Your English Mark is below 40 and not eligible for this certificate');
                        $('#englishMark').addClass('error');
                        $('#englishMark').html('');
                    } else {
                        $('#indexNoErrorMsg').html('');
                        $('#englishMark').html('English Mark is ' + englishMark);
                    }
                }
            }
        });
    }

    function validateOtherInformation() {
        var retval = true;
        if ($('#isEmployed').val() == "") {
            $('#isEmployed').addClass('error');
            $('#isEmployedErrorMsg').html('Are you employed is required');
            retval = false
        }
        if ($('#isEmployed').val() == "Y") {
            if ($('#agencyName').val() == "") {
                $('#agencyName').addClass('error');
                $('#agencyNameErrorMsg').html('Orginasation Name is required');
                retval = false
            }
        }
        if ($('#isEmployed').val() == "Y") {
            if ($('#designation').val() == "") {
                $('#designation').addClass('error');
                $('#designationErrorMsg').html('Designation is required');
                retval = false
            }
        }
        return retval;
    }

    function saveIssueEnglishLanProCertificate() {
        form.validate({
            submitHandler: function (form) {
                var isValid = validateOtherInformation();
                if (isValid) {
                    var url = _baseURL() + 'saveIssueEnglishLanProCertificate';
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
                                $('#englishPassMark').val('');
                            } else {
                                warningMsg(res.responseText);
                            }
                        }, complete: function () {
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
        , onChangeAreYouEmployed: onChangeAreYouEmployed
        , removeErrorMsg: removeErrorMsg
        , fetchName: fetchName
        , indexNoValidation: indexNoValidation
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        issueEnglishLanProCertificate.disableTabs();
        issueEnglishLanProCertificate.btnNext();
        issueEnglishLanProCertificate.btnPrevious();
        issueEnglishLanProCertificate.onChangeAreYouEmployed();
        issueEnglishLanProCertificate.removeErrorMsg();
        issueEnglishLanProCertificate.fetchName();
        issueEnglishLanProCertificate.indexNoValidation();
    });
