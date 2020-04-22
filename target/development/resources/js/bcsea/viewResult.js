/**
 * Created by USER on 8/22/2019.
 */

viewResult = (function () {
    var form = $('#viewResultFormId');

    function _baseURL() {
        return 'viewResult/';
    }

    function removeErrorMsg() {
        $('#classType').on('change', function () {
            var cid = $(this).val();
            if (cid != '') {
                $('#classType').removeClass('error');
                $('#classTypeErrorMsg').text('');
            }
        });
        $('#indexNo').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#indexNo').removeClass('error');
                $('#indexNoErrorMsg').text('');
            }
        });
    }

    function validateReqFields(classType, indexNo) {
        var retval = true;

        if (classType == '') {
            $('#classType').addClass('error');
            $('#classTypeErrorMsg').text('Please select the class');
            retval = false;
        }
        if (indexNo == '') {
            $('#indexNo').addClass('error');
            $('#indexNoErrorMsg').text('Please enter index number');
            retval = false;
        }
        return retval;
    }

    function getResult() {
        $('#btnView').on('click', function () {
            var indexNo = $('#indexNo').val();
            var classType = $('#classType').val();
            if (!validateReqFields(classType, indexNo)) {
                return false;
            }

            var url = _baseURL() + 'getResult';
            $.ajax({
                url: url
                , type: 'GET'
                , data: {indexNo: indexNo, classType: classType}
                , success: function (res) {
                    if (res.responseStatus == 1) {
                        var data = res.responseDTO;
                        $('#resultDetail').show();
                        var schoolName = res.schoolName;
                        var examYear = res.examYear;
                        var cType = res.classType;
                        var stream = res.streamName;
                        var classAndStream = cType + '(' + stream + ')';
                        var supwGrade = res.supwGrade;
                        var resultRemarks = res.resultRemarks;
                        $('#indexNoDisplay').text(indexNo);
                        $('#schoolName').text(schoolName);
                        $('#examYear').text(examYear);
                        $('#classAndStream').text(classAndStream);
                        $('#supwGrade').text(supwGrade);
                        $('#resultRemarks').text(resultRemarks);

                        var dataTableDefinition = [
                            {
                                class: "slNo"
                                , "mRender": function (data, type, row, meta) {
                                return meta.row + 1;
                            }
                            }
                            , {"data": "subjectName", class: "subjectName"}
                            , {"data": "marksObtained", class: "marksObtainedStudent"}
                        ];
                        $('#resultTableId').DataTable({
                            data: data
                            , columns: dataTableDefinition
                            , destroy: true
                            , bSort: false
                            , filter: false
                            , pageLength: 100
                            , paginate: false
                            , bInfo: false
                        });
                        showExportButton(indexNo, schoolName, examYear, classAndStream, supwGrade, resultRemarks);
                    } else {
                        warningMsg('No result found matching index no ' + indexNo + ' and class type ' + classType);
                        $('#resultDetail').hide();
                    }
                }
            });
        });
    }

    function showExportButton(indexNo, schoolName, examYear, classAndStream, supwGrade, resultRemarks) {
        var topMessage = '<div class="form-group"> <label class="col-sm-2">Index Number : </label><div class="col-sm-4"> <label id="indexNoDisplay">' + indexNo + '</label> </div><label class="col-sm-2">School Name : </label> <div class="col-sm-4"> <label id="schoolName">' + schoolName + '</label> </div></div>' +
            '<div class="form-group"> <label class="col-sm-2">Exam Year : </label> <div class="col-sm-4"><label id="examYear">' + examYear + '</label> </div>' +
            '<label class="col-sm-2">Class & Stream : </label> <div class="col-sm-4"> <label id="classAndStream">' + classAndStream + '</label> </div> </div>';
        var tableId = $('#resultTableId').DataTable();
        new $.fn.dataTable.Buttons(tableId, {
                buttons: [
                    {
                        extend: 'print'
                        ,
                        header: false
                        ,
                        footer: false
                        ,
                        title: ''
                        ,
                        messageTop: '<b> Index Number : ' + indexNo + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;School Name : ' + schoolName + '<br><br>' +
                        ' Exam Year : ' + examYear + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + ' Class & Stream : ' + classAndStream + '</b><br><br>'
                        ,
                        messageBottom: '<br><b>' + ' SUPW : ' + supwGrade + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Result :  ' + resultRemarks + '</b><br>'
                        ,
                        text: '<i class="fa fa-print btn btn-info">Print</i>'
                        ,
                        pageSize: 'A4'
                        ,
                        exportOptions: {
                            columns: [0, 1, 2]
                            , search: 'applied'
                        }
                        ,
                        orientation: 'portrait'
                        ,
                        customize: function (win) {
                            //$(win.document.body).prepend(topMessage);
                            //$(win.document.body).append('Message here');
                            $(win.document.body).find('.slNo')
                                .css(
                                {
                                    width: '5%'
                                });
                            $(win.document.body).find('.subjectName')
                                .css(
                                {
                                    width: '20%'
                                });
                            $(win.document.body).find('.marksObtainedStudent')
                                .css(
                                {
                                    width: '10%'
                                });
                        }
                    }
                    , {
                        extend: 'pdfHtml5'
                        ,
                        messageBottom: '\n SUPW : ' + supwGrade + '                                                                                                 Result :  ' + resultRemarks
                        ,
                        text: '<i class="fa fa-download btn btn-primary">Download</i>'
                        ,
                        filename: indexNo + '_result'
                        ,
                        extension: '.pdf'
                        ,
                        pageSize: 'A4'
                        ,
                        exportOptions: {
                            columns: [0, 1, 2]
                            , search: 'applied'
                        }
                        ,
                        orientation: 'portrait'
                        ,
                        customize: function (doc) {
                            doc.content[1].table.widths = ['10%', '70%', '18%'];
                            doc.styles.tableHeader = {
                                color: 'black'
                                , bold: true
                            };
                            doc.styles.message = {
                                bold: true
                            };
                            doc.content.splice(0, 1);
                            doc.pageMargins = [30, 80, 20, 30];

                            doc['header'] = (function () {
                                return {
                                    alignment: 'left'
                                    ,
                                    text: ' Index Number : ' + indexNo + '                              School Name : ' + schoolName + '\n\n' +
                                    ' Exam Year : ' + examYear + '                                                      Class & Stream : ' + classAndStream + '\n\n'
                                    ,
                                    margin: [30, 30, 0, 30]
                                    ,
                                    bold: true
                                }
                            });
                            var objLayout = {};
                            objLayout['hLineWidth'] = function (i) {
                                return .5;
                            };
                            objLayout['vLineWidth'] = function (i) {
                                return .5;
                            };
                            objLayout['hLineColor'] = function (i) {
                                return '#aaa';
                            };
                            objLayout['vLineColor'] = function (i) {
                                return '#aaa';
                            };
                            objLayout['paddingLeft'] = function (i) {
                                return 6;
                            };
                            objLayout['paddingRight'] = function (i) {
                                return 6;
                            };
                            objLayout['paddingTop'] = function (i) {
                                return 6;
                            };
                            objLayout['paddingBottom'] = function (i) {
                                return 6;
                            };
                            //objLayout['fillColor'] = function (i) {
                            //    return '#F0FFF0';
                            //};
                            doc.content[0].layout = objLayout;
                        }
                    }
                ]
            }
        )
        ;
        tableId.buttons().container().appendTo('#exportButtons');
    }


    return {
        getResult: getResult
        , removeErrorMsg: removeErrorMsg
    }
})
();
$(document).ready(
    function () {
        viewResult.getResult();
        viewResult.removeErrorMsg();
    });


