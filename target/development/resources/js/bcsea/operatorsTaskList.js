/**
 * Created by USER on 8/2/2019.
 */
operatorsTaskList = (function () {
    var form = $('#operatorsTaskListFormId');

    function _baseURL() {
        return 'operatorsTaskList/';
    }

    function getOperatorsTaskList() {
        var url = _baseURL() + 'getOperatorsTaskList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {"data": "serviceSlNo", class: "serviceSlNo hidden"}
                        , {
                            "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {"data": "applicationNo", class: "applicationNo menu-link"}
                        , {"data": "serviceName", class: "serviceName"}
                        , {"data": "indexNo", class: "indexNo"}
                        , {"data": "mobileNo", class: "mobileNo"}
                        , {
                            "data": "statusId", class: "statusId",
                            "mRender": function (data) {
                                if (data == '1') {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Submitted";
                                } else if (data == '2') {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Approved";
                                } else {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Rejected";
                                }
                            }
                        }
                        , {
                            "data": "submissionDate", class: "submissionDate align-middle",
                            "mRender": function (data) {
                                return formatAsDate(data);
                            }
                        }
                    ];
                    $('#operatorsTaskListTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                    });
                }
            }
        });
    }

    function getClaimedTaskList() {
        var url = _baseURL() + 'getClaimedTaskList';
        $.ajax({
            url: url,
            type: 'GET',
            success: function (res) {
                if (res.responseStatus == 1) {
                    var data = res.responseDTO;
                    var dataTableDefinition = [
                        {"data": "serviceSlNo", class: "serviceSlNo hidden"}
                        , {
                            "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                        }
                        , {
                            "data": "",
                            "mRender": function () {
                                return " <button class='btn btn-sm btn-danger' id='unClaim' type='button'><i class='fa fa-times'></i></button>";
                            }
                        }
                        , {"data": "applicationNo", class: "applicationNo menu-link"}
                        , {"data": "serviceName", class: "serviceName"}
                        , {"data": "indexNo", class: "indexNo"}
                        , {"data": "mobileNo", class: "mobileNo"}
                        , {
                            "data": "statusId", class: "statusId",
                            "mRender": function (data) {
                                if (data == '1') {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Submitted";
                                } else if (data == '2') {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Claimed";
                                } else {
                                    return "<i class='status-icon bg-success'>" + "</i>" + "Rejected";
                                }
                            }
                        }
                        , {
                            "data": "submissionDate", class: "submissionDate align-middle",
                            "mRender": function (data) {
                                return formatAsDate(data);
                            }
                        }
                    ];
                    $('#claimedTaskListTableId').DataTable({
                        data: data
                        , columns: dataTableDefinition
                        , destroy: true
                        , bSort: false
                    });
                }
            }
        });
    }

    function saveClaimTask() {
        $('#operatorsTaskListTableId tbody').on('click', 'tr .applicationNo', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var applicationNo = selectedRow.find('.applicationNo').text();
            var serviceSlNo = selectedRow.find('.serviceSlNo').text();
            selectedRow.removeClass('selected');
            confirmMsg("Are you sure you want to claim application no " + applicationNo + "?", function () {
                var url = _baseURL() + 'saveClaimTask';
                var data = $(form).serializeArray();
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {applicationNo: applicationNo},
                    success: function (res) {
                        if (res.responseStatus == 1) {
                            getClaimedTaskList();
                            getOperatorsTaskList();
                            successMsg(applicationNo + " claimed successfully.");
                        } else {
                            errorMsg(res.responseText);
                        }
                    }, error: function (res) {
                        errorMsg(res.responseText);
                    }
                });
            });
        });
    }

    function saveUnClaimTask() {
        $('#claimedTaskListTableId tbody').on('click', 'tr #unClaim', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var applicationNo = selectedRow.find('.applicationNo').text();
            var serviceSlNo = selectedRow.find('.serviceSlNo').text();
            selectedRow.removeClass('selected');
            confirmMsg("Are you sure you want to un claim application no " + applicationNo + "?", function () {
                var url = _baseURL() + 'saveUnClaimTask';
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {applicationNo: applicationNo},
                    success: function (res) {
                        if (res.responseStatus == 1) {
                            getClaimedTaskList();
                            getOperatorsTaskList();
                            successMsg(applicationNo + " un claimed successfully.");
                        } else {
                            errorMsg(res.responseText);
                        }
                    }, error: function (res) {
                        errorMsg(res.responseText);
                    }
                });
            });
        });
    }

    function navigateToApplicationScreen() {
        $('#claimedTaskListTableId tbody').on('click', 'tr .applicationNo', function () {
            var row = $(this).closest('tr');
            var selectedRow = row.addClass('selected');
            var applicationNo = selectedRow.find('.applicationNo').text();
            var serviceSlNo = selectedRow.find('.serviceSlNo').text();
            selectedRow.removeClass('selected');

            if (serviceSlNo == "311") {
                window.location.href = _baseURL() + '/navigateToApproveIssueDuplicateCertificate?applicationNo=' + applicationNo
            }
            if (serviceSlNo == "312") {
                window.location.href = _baseURL() + '/navigateToApproveIssueReplacementCertificate?applicationNo=' + applicationNo
            }
            if (serviceSlNo == "313") {
                window.location.href = _baseURL() + '/navigateToApproveEnglishLanProCertificate?applicationNo=' + applicationNo
            }
            if (serviceSlNo == "314") {
                window.location.href = _baseURL() + '/navigateToApproveRecheckApplication?applicationNo=' + applicationNo
            }
        });
    }

    return {
        getOperatorsTaskList: getOperatorsTaskList
        , getClaimedTaskList: getClaimedTaskList
        , saveClaimTask: saveClaimTask
        , saveUnClaimTask: saveUnClaimTask
        , navigateToApplicationScreen: navigateToApplicationScreen
    }
})
();
$(document).ready(
    function () {
        operatorsTaskList.getOperatorsTaskList();
        operatorsTaskList.getClaimedTaskList();
        operatorsTaskList.saveClaimTask();
        operatorsTaskList.saveUnClaimTask();
        operatorsTaskList.navigateToApplicationScreen();
    });

