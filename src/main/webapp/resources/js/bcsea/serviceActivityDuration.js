/**
 * Created by USER on 8/20/2019.
 */
serviceActivityDuration = (function () {
    "use strict";
    var form = $('#serviceActivityDurationFormId');
    var isSubmitted = false;


    function _baseURL() {
        return 'serviceActivityDuration/';
    }

    function validateFields() {
        var retval = true;
        if ($('#serviceId').val() == "") {
            $('#serviceId').addClass('error');
            $('#serviceIdErrorMsg').html('Service name is required');
            retval = false;
        }
        if ($('#classType').val() == "") {
            $('#classType').addClass('error');
            $('#classTypeErrorMsg').html('Class type is required');
            retval = false;
        }
        if ($('#examYear').val() == "") {
            $('#examYear').addClass('error');
            $('#examYearErrorMsg').html('Exam year is required');
            retval = false;
        }
        if ($('#activeFrom').val() == "") {
            $('#activeFrom').addClass('error');
            $('#activeFromErrorMsg').html('From date is required');
            retval = false;
        }
        if ($('#activeTo').val() == "") {
            $('#activeTo').addClass('error');
            $('#activeToErrorMsg').html('To date is required.');
            retval = false;
        }
        return retval;
    }

    function removeErrorMsg() {
        $('#serviceId').on('change', function () {
            var serviceId = $(this).val();
            if (serviceId != '') {
                $('#serviceId').removeClass('error');
                $('#serviceIdErrorMsg').text('');
            }
        });
        $('#classType').on('change', function () {
            var classType = $(this).val();
            if (classType != '') {
                $('#classType').removeClass('error');
                $('#classTypeErrorMsg').text('');
            }
        });
        $('#examYear').on('keyup blur', function () {
            var examYear = $(this).val();
            if (examYear != '') {
                $('#examYear').removeClass('error');
                $('#examYearErrorMsg').text('');
            }
        });
        $('#activeFrom').on('change keyup blur', function () {
            var activeFrom = $(this).val();
            if (activeFrom != '') {
                $('#activeFrom').removeClass('error');
                $('#activeFromErrorMsg').text('');
            }
        });
        $('#activeTo').on('change keyup blur', function () {
            var activeTo = $(this).val();
            if (activeTo != '') {
                $('#activeTo').removeClass('error');
                $('#activeToErrorMsg').text('');
            }
        });
    }

    function saveServiceActivityDuration() {
        $('#btnSave').on('click', function () {
            form.validate({
                submitHandler: function (form) {
                    if (!validateFields()) {
                        return false;
                    }
                    var url = _baseURL() + 'saveServiceActivityDuration';
                    var data = $(form).serializeArray();
                    if (isSubmitted) {
                        errorMsg('Your request is processing. Please wait...');
                        return;
                    }
                    isSubmitted = true;
                    $('#btnSave').attr('disabled', true);
                    $.ajax({
                        url: url,
                        type: 'post',
                        data: data,
                        processData: true,
                        success: function (res) {
                            if (res.responseStatus == 1) {
                                getServiceActivityDurationList();
                                successMsg(res.responseText);
                                $('.field').val('');
                                $('#statusTagInactive').prop('checked', true);
                                $('#statusTagActive').prop('checked', false);
                            } else {
                                warningMsg(res.responseText);
                            }
                        }, complete: function () {
                            isSubmitted = false;
                            $('#btnSave').attr('disabled', false);
                        }
                    });
                }
            });
        });
    }

    function getServiceActivityDurationList() {
        var url = _baseURL() + 'getServiceActivityDurationList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {"data": "statusTag", class: "statusTag hidden"}
                        , {"data": "serviceId", class: "serviceId hidden"}
                        , {"data": "autoIndex", class: "autoIndex hidden"}
                        , {
                            class: "slNo"
                            , "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {"data": "serviceName", class: "serviceName"}
                        , {"data": "classType", class: "classType"}
                        , {"data": "examYear", class: "examYear"}
                        , {
                            "data": "activeFrom", class: "activeFrom align-middle",
                            "mRender": function (data) {
                                return formatAsDate(data);
                            }
                        }
                        , {
                            "data": "activeTo", class: "activeTo align-middle",
                            "mRender": function (data) {
                                return formatAsDate(data);
                            }
                        }
                        , {"data": "statusTagName", class: "middle-align statusTagName"}
                        , {
                            "data": "null", class: "btnEdit align-middle",
                            "mRender": function () {
                                return "<a id='btnEdit' class='btn btn-sm btn-info align-middle'>Edit</a>";
                            }
                        }
                    ];
                    $('#serviceActivityListTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                        //, pageLength: 50
                    });
                }
            }
        });
    }

    function btnEdit() {
        $('#serviceActivityListTableId tbody').on('click', 'tr #btnEdit', function () {
            $(':input').removeClass('error');
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var statusTag = selectedRow.find('.statusTag').text();
            var autoIndex = selectedRow.find('.autoIndex').text();
            var serviceId = selectedRow.find('.serviceId').text();
            var classType = selectedRow.find('.classType').text();
            var examYear = selectedRow.find('.examYear').text();
            var activeFrom = selectedRow.find('.activeFrom').text();
            var activeTo = selectedRow.find('.activeTo').text();
            selectedRow.removeClass('selected');
            $('#autoIndex').val(autoIndex);
            $('#serviceId').val(serviceId);
            $('#classType').val(classType);
            $('#examYear').val(examYear);
            $('#activeFrom').val(activeFrom);
            $('#activeTo').val(activeTo);
            $('.text-danger').text('');
            if (statusTag == 'A') {
                $('#statusTagActive').prop('checked', true);
                $('#statusTagInactive').prop('checked', false);
            } else {
                $('#statusTagInactive').prop('checked', true);
                $('#statusTagActive').prop('checked', false);
            }
        });
    }

    function btnReset() {
        $('#btnReset').on('click', function () {
            getServiceActivityDurationList();
            $('.text-danger').text('');
            $(':input').removeClass('error');
        });
    }

    return {
        getServiceActivityDurationList: getServiceActivityDurationList
        , removeErrorMsg: removeErrorMsg
        , btnEdit: btnEdit
        , btnReset: btnReset
        , saveServiceActivityDuration: saveServiceActivityDuration
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        $('#statusTagInactive').prop('checked', true);
        $('#statusTagActive').prop('checked', false);
        serviceActivityDuration.getServiceActivityDurationList();
        serviceActivityDuration.btnEdit();
        serviceActivityDuration.btnReset();
        serviceActivityDuration.removeErrorMsg();
        serviceActivityDuration.saveServiceActivityDuration();
    });
