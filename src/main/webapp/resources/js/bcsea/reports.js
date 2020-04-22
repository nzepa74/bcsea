/**
 * Created by USER on 8/22/2019.
 */

reports = (function () {
    var form = $('#reportsFormId');

    function _baseURL() {
        return 'reports/';
    }

    function removeErrorMsg() {
        $('#serviceId').on('change', function () {
            var serviceId = $(this).val();
            $('#classTypeErrorMsg').text('');
            $('#documentIdErrorMsg').text('');
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
        $('#documentId').on('change', function () {
            var documentId = $(this).val();
            if (documentId != '') {
                $('#documentId').removeClass('error');
                $('#documentIdErrorMsg').text('');
            }
        });
        $('#status').on('change', function () {
            var status = $(this).val();
            if (status != '') {
                $('#status').removeClass('error');
                $('#statusErrorMsg').text('');
            }
        });
        $('#fromDate').on('keyup blur change', function () {
            var fromDate = $(this).val();
            if (fromDate != '') {
                $('#fromDate').removeClass('error');
                $('#fromDateErrorMsg').text('');
            }
        });
        $('#toDate').on('keyup blur change', function () {
            var toDate = $(this).val();
            if (toDate != '') {
                $('#toDate').removeClass('error');
                $('#toDateErrorMsg').text('');
            }
        });
    }

    function onChangeServiceId() {
        $('#serviceId').on('change', function () {
            var serviceId = $(this).val();
            var documentId = $('#documentId');
            var documentType = $('.documentType');
            var classType = $('.classType');

            if (serviceId == 311 || serviceId == 312) {
                documentType.show();
                classType.hide();
                $('#documentId').val('');
                $('#classType').val('');
            }
            else if (serviceId == 313) {
                documentType.hide();
                classType.hide();
            }
            else if (serviceId == 314) {
                documentType.hide();
                classType.show();
            } else {
                documentType.hide();
                classType.hide();
            }
        });
    }

    function validateReqFields(serviceId) {
        var retval = true;

        if ($('#serviceId').val() == '') {
            $('#serviceIdErrorMsg').text('');
            $('#serviceId').addClass('error');
            $('#serviceIdErrorMsg').text('Please select service type');
            retval = false;
        }
        if ($('#fromDate').val() == '') {
            $('#fromDateErrorMsg').text('');
            $('#fromDate').addClass('error');
            $('#fromDateErrorMsg').text('From date is required');
            retval = false;
        }
        if ($('#toDate').val() == '') {
            $('#toDateErrorMsg').text('');
            $('#toDate').addClass('error');
            $('#toDateErrorMsg').text('To date is required');
            retval = false;
        }
        if ($('#status').val() == '') {
            $('#statusErrorMsg').text('');
            $('#status').addClass('error');
            $('#statusErrorMsg').text('Status is required');
            retval = false;
        }

        if (serviceId == 311 || serviceId == 312) {
            $('#classTypeErrorMsg').text('');
            $('#documentIdErrorMsg').text('');
            if ($('#documentId').val() == '') {
                $('#documentId').addClass('error');
                $('#documentIdErrorMsg').text('Document type is required');
                retval = false;
            }
        }
        else if (serviceId == 314) {
            $('#classTypeErrorMsg').text('');
            $('#documentIdErrorMsg').text('');
            if ($('#classType').val() == '') {
                $('#classType').addClass('error');
                $('#classTypeErrorMsg').text('Class type is required');
                retval = false;
            }
        }
        return retval;
    }

    function btnSearch() {
        $('#btnSearch').on('click', function () {
            var serviceId = $('#serviceId').val();
            var classType = $('#classType').val();
            var documentId = $('#documentId').val();
            var fromDate = $('#fromDate').val();
            var toDate = $('#toDate').val();
            var statusId = $('#status').val();
            if (!validateReqFields(serviceId)) {
                return false;
            }
            var url = _baseURL() + 'getReport';
            $.ajax({
                url: url
                , type: 'GET'
                , data: {
                    serviceId: serviceId,
                    documentId: documentId,
                    classType: classType,
                    fromDate: fromDate,
                    toDate: toDate,
                    statusId: statusId
                }
                , success: function (res) {
                    if (res.responseStatus == 1) {
                        var data = res.responseDTO;
                        var dataTableDefinition = [
                            {
                                class: "slNo"
                                , "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                            }
                            , {"data": "fullName", class: "fullName"}
                            , {"data": "cidNo", class: "cidNo"}
                            , {"data": "applicationNo", class: "applicationNo"}
                            , {"data": "indexNo", class: "indexNo"}
                            , {"data": "actionTakenBy", class: "actionTakenBy"}
                            , {
                                "data": "actionTakenDate", class: "actionTakenDate align-middle",
                                "mRender": function (data) {
                                    return formatAsDate(data);
                                }
                            }
                        ];
                        $('#reportsTableId').DataTable({
                            data: data
                            , columns: dataTableDefinition
                            , destroy: true
                            , bSort: false
                            , filter: true
                        });
                        showExportButton();
                    } else {
                        warningMsg(res.responseText);
                        $('#reportsTableId').DataTable().destroy();
                        $('#reportsTableId tbody').empty();
                    }
                }
            });
        });
    }

    function showExportButton() {
        var tableId = $('#reportsTableId').DataTable();
        new $.fn.dataTable.Buttons(tableId, {
                buttons: [
                    {
                        extend: 'excelHtml5'
                        ,
                        title: ''
                        ,
                        messageTop: 'Reports'
                        ,
                        filename: 'report'
                        ,
                        extension: '.xlxs'
                        ,
                        text: '<i class="fa fa-file-excel-o btn btn-info">Excel</i>'
                        ,
                        pageSize: 'A4'
                        ,
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6]
                            , search: 'applied'
                        }
                        ,
                        orientation: 'portrait'
                    }
                    , {
                        extend: 'pdfHtml5'
                        ,
                        messageTop: 'Reports'
                        ,
                        filename: 'report'
                        ,
                        extension: '.pdf'
                        ,
                        text: '<i class="fa fa-file-pdf-o btn btn-primary">PDF</i>'
                        ,
                        pageSize: 'A4'
                        ,
                        exportOptions: {
                            columns: [0, 1, 2, 3, 4, 5, 6]
                            , search: 'applied'
                        }
                        ,
                        orientation: 'landscape'
                    }
                ]
            }
        );
        tableId.buttons().container().appendTo('#exportButtons');
    }

    return {
        onChangeServiceId: onChangeServiceId
        , removeErrorMsg: removeErrorMsg
        , btnSearch: btnSearch
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        $('.serviceId').val('');
        reports.onChangeServiceId();
        reports.removeErrorMsg();
        reports.btnSearch();
    });


