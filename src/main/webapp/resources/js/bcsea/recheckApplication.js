/**
 * Created by N-Zepa on 21-Jun-19.
 */
recheckApplication = (function () {
    "use strict";
    var form = $('#recheckApplicationFormId');
    var isSubmitted = false;

    function _baseURL() {
        return 'recheckApplication/';
    }

    function getServiceActivityDuration(indexNo) {
        var url = _baseURL() + 'getServiceActivityDuration';
        $.ajax({
            url: url
            , type: 'GET'
            , data: {indexNo: indexNo}
            , success: function (res) {
                if (res.responseStatus == 1) {
                    $('#recheckNoticeOpen').text('Recheck application is open till ' + res.responseDTO.activeTo + '. Please apply before the due date.');
                }
                if (res.responseStatus == 2) {
                    $('#recheckNotice').text('Recheck application will open from ' + res.responseDTO.activeFrom + ' to ' + res.responseDTO.activeTo);
                    $('#indexNo').val();
                    $('.std-detail').val('');
                }
                if (res.responseStatus == 3) {
                    $('#recheckNotice').text('Recheck application is closed on ' + res.responseDTO.activeTo);
                    $('#indexNo').val();
                    $('.std-detail').val('');
                }
                if (res.responseStatus == 4) {
                    $('#recheckNotice').text(res.responseText);
                    $('#indexNo').val();
                    $('.std-detail').val('');
                }
            }
        });
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
            var indexNo = $('#indexNo').val();
            if (validatePersonalDetail()) {
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
            if (checkSubjects()) {
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
            saveRecheckApplication();
        });
    }

    function getRecheckCharge() {
        var url = _baseURL() + 'getRecheckCharge';
        $.ajax({
            url: url
            , type: 'GET'
            , success: function (res) {
                var data = res.responseDTO;
                if (res.responseStatus == 1) {
                    $("#recheckAmount").text(data.amountCharge);
                }
            }
        });
    }

    function saveRecheckApplication() {
        form.validate({
            submitHandler: function (form) {
                var url = _baseURL() + 'saveRecheckApplication';
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
        });
    }

    function validatePersonalDetail() {
        var retval = true;
        if ($('#indexNo').val() == "") {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').html('Index No. is required');
            retval = false;
        }
        if ($('#indexNo').val().length != 12 && $('#indexNo').val() != '') {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').html('index number should be 12 digit long');
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

    function checkSubjects() {
        var retval = true;
        var checked = $('.paperId:checkbox:checked').length;
        if (checked < 1) {
            $('#subjectErrorMsg').html('Please check the subject(s) you want to recheck');
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

    function removeErrorMsg() {
        $('#indexNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#indexNoErrorMsg').text('');
                $('#indexNo').removeClass('error');
            }
        });
        $('#mobileNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#mobileNo').removeClass('error');
                $('#mobileNoErrorMsg').text('');
            }
        });
        $('#email').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#email').removeClass('error');
                $('#emailErrorMsg').text('');
            }
        });
        $('#subjectListTableId tbody').on('click', 'tr .paperId', function () {
            $('#subjectErrorMsg').text('');
            var isChecked = $(this).prop('checked');
            var url = _baseURL() + 'getRecheckCharge';
            $.ajax({
                url: url
                , type: 'GET'
                , success: function (res) {
                    var charge = res.responseDTO.amountCharge;
                    calculateCharge(charge, isChecked);
                }
            });
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

    function validateIndexNo(indexNo) {
        var retval = true;
        if (indexNo.length != 12 && indexNo != '') {
            $('#indexNoErrorMsg').html('index number should be 12 digit long');
            $('.std-detail').val('');
            $('#indexNo').addClass('error');
            retval = false;
        }
        return retval;
    }

    function getStudentInfoByIndexNo() {
        $('#indexNo').on('blur', function () {
            var indexNo = $(this).val();
            if (validateIndexNo(indexNo)) {
                if (indexNo != '') {
                    var url = _baseURL() + 'getStudentInfoByIndexNo';
                    $.ajax({
                        url: url
                        , type: 'GET'
                        , data: {indexNo: indexNo}
                        , success: function (res) {
                            var data = res.responseDTO;
                            if (res.responseStatus == 1) {
                                populate(data);
                                $('#subjectListTableId tbody').empty();
                                getSubjectsByIndexNo(indexNo);
                                getServiceActivityDuration(indexNo);
                            } else {
                                $('#indexNo').addClass('error');
                                $('#indexNoErrorMsg').text(res.responseText);
                                $('.std-detail').val('');
                                $('#indexNo').val('');
                            }
                        }
                    });
                } else {
                    $('.std-detail').val('');
                }
            }
        });
    }

    function getSubjectsByIndexNo(indexNo) {
        var url = _baseURL() + 'getSubjectsByIndexNo';
        $.ajax({
            url: url,
            type: 'GET'
            , data: {indexNo: indexNo}
            , success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    for (var i = 0; i < data.length; i++) {
                        var paperId = data[i].paperId;
                        var paperName = data[i].paperName;
                        var row = '<tr style="border-top: hidden">' +
                            '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;' +
                            '<input type="checkbox" name="subjectDTOs[' + i + '].paperId" ' +
                            'value="' + paperId + '" id="paperId' + i + '" class="paperId"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '<label for="paperId' + i + '">' + paperName + '</label></td>' +
                            '</tr>';
                        $('#subjectListTableId tbody').append(row);
                        $('#subjectErrorMsg').text('');
                    }
                } else {
                    var msgRow = '<tr style="border-top: hidden">' +
                        '<td>' + '<font color="red"><span id="subjectNotAvailableMsg">Subject not available for this index number.</span></font>' + '</td>' +
                        '</tr>';
                    $('#subjectListTableId tbody').append(msgRow);
                    $('#subjectErrorMsg').text('');
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
        , removeErrorMsg: removeErrorMsg
        , getStudentInfoByIndexNo: getStudentInfoByIndexNo
        , getRecheckCharge: getRecheckCharge
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        recheckApplication.disableTabs();
        recheckApplication.btnNext();
        recheckApplication.btnPrevious();
        recheckApplication.removeErrorMsg();
        recheckApplication.getStudentInfoByIndexNo();
        recheckApplication.getRecheckCharge();
    });
