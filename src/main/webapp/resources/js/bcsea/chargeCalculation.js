/**
 * Created by USER on 8/20/2019.
 */
chargeCalculation = (function () {
    var form = $('#chargeCalculationFormId');

    function _baseURL() {
        return 'chargeCalculation/';
    }

    function onChangeServiceId() {
        $('#serviceId').on('change', function () {
            var serviceId = $(this).val();
            var documentId = $('#documentId');
            var totalChargeInWordsDiv = $('#totalChargeInWordsDiv');
            var duplicateOrReplacementDiv = $('#duplicateOrReplacementDiv');
            var languageProficiencyDiv = $('#languageProficiencyDiv');
            var clericalRecheckDiv = $('#clericalRecheckDiv');
            $('#totalChargeInWords').text('');

            if (serviceId == 311 || serviceId == 312) {
                duplicateOrReplacementDiv.show();
                languageProficiencyDiv.hide();
                clericalRecheckDiv.hide();
                totalChargeInWordsDiv.show();
                $('.field').val('');
            }
            else if (serviceId == 313) {
                languageProficiencyDiv.show();
                duplicateOrReplacementDiv.hide();
                clericalRecheckDiv.hide();
                totalChargeInWordsDiv.show();
                $('.field').val('');
            }
            else if (serviceId == 314) {
                clericalRecheckDiv.show();
                languageProficiencyDiv.hide();
                duplicateOrReplacementDiv.hide();
                totalChargeInWordsDiv.show();
                $('.field').val('');
            } else {
                languageProficiencyDiv.hide();
                duplicateOrReplacementDiv.hide();
                clericalRecheckDiv.hide();
                clericalRecheckDiv.hide();
                totalChargeInWordsDiv.hide();
                $('.field').val('');
            }
        });
    }

    function getDuplicateOrReplacementDocCharge() {
        $('#documentId').on('change', function () {
            var serviceId = $('#serviceId').val();
            var indexNo = $('#indexNo').val();
            var documentId = $('#documentId').val();
            var url = _baseURL() + 'getDuplicateOrReplacementDocCharge';
            if (documentId != '') {
                $.ajax({
                    url: url
                    , type: 'GET'
                    , data: {serviceId: serviceId, indexNo: indexNo, documentId: documentId}
                    , success: function (res) {
                        var data = res.responseDTO;
                        if (res.responseStatus == 1) {
                            populate(data);
                            $('#documentTotalCharge').val(data.documentTotalCharge.toFixed(2));
                            $('#totalChargeInWords').text(convertNumberToWord(data.documentTotalCharge.toFixed(2)));
                        }
                    }
                });
            } else {
                $('#totalChargeInWords').text('');
                $('.dupRep').val('');
            }
        });
    }

    function getEnglishLanProCharge() {
        $('#cidNo').on('blur', function () {
            var cidNo = $(this).val();
            if (validateCid(cidNo)) {
                if (cidNo != '') {
                    var url = _baseURL() + 'isValidCid';
                    $.ajax({
                        url: url
                        , type: 'GET'
                        , data: {cidNo: cidNo}
                        , success: function (res) {
                            var data = res.responseDTO;
                            if (res.responseStatus == 1) {
                                getLanProCharge(cidNo);
                            } else {
                                $('#cidNoErrorMsg').html(res.responseText);
                                $('.languagePro').val('');
                                $('#totalChargeInWords').text('');
                            }
                        }
                    });
                }
            }
        });
    }

    function getLanProCharge(cidNo) {
        var serviceId = $('#serviceId').val();
        var url = _baseURL() + 'getEnglishLanProCharge';
        $.ajax({
            url: url
            , type: 'GET'
            , data: {serviceId: serviceId, cidNo: cidNo}
            , success: function (res) {
                var data = res.responseDTO;
                if (res.responseStatus == 1) {
                    populate(data);
                    $('#lanCertificateTotalCharge').val(data.lanCertificateTotalCharge.toFixed(2));
                    $('#totalChargeInWords').text(convertNumberToWord(data.lanCertificateTotalCharge.toFixed(2)));
                } else {
                    $('#cidNoErrorMsg').html(res.responseText);
                    $('.languagePro').val('');
                    $('#totalChargeInWords').text('');
                }
            }
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
                    $("#chargePerPaper").val(data.amountCharge);
                }
            }
        });
    }

    function calculateRecheckCharge() {
        $('#noOfRecheckPaper').on('keyup blur', function () {
            var noOfRecheckPaper = $(this).val();
            var recheckPaperTotalCharge;
            if (noOfRecheckPaper != '') {
                var url = _baseURL() + 'getRecheckCharge';
                $.ajax({
                    url: url
                    , type: 'GET'
                    , success: function (res) {
                        var data = res.responseDTO;
                        if (res.responseStatus == 1) {
                            recheckPaperTotalCharge = parseInt(noOfRecheckPaper * data.amountCharge);
                            $('#recheckPaperTotalCharge').val(recheckPaperTotalCharge.toFixed(2));
                            $('#totalChargeInWords').text(convertNumberToWord(recheckPaperTotalCharge.toFixed(2)));
                        }
                    }
                });
            } else {
                $('#recheckPaperTotalCharge').val('');
                $('#totalChargeInWords').text('');
            }
        });
    }

    function validateCid(cid) {
        var retval = true;
        if (cid.substring(0, 1) >= 3) {
            $('#cidNoErrorMsg').html('Cid starting with greater than 3 is not allow');
            $('.languagePro').val('');
            retval = false;
        }
        else if (cid.length != 11 && cid != '') {
            $('#cidNoErrorMsg').html('Bhutanese CID should have 11 digit long');
            $('.languagePro').val('');
            retval = false;
        }
        return retval;
    }

    function removeErrorMsg() {
        $('#cidNo').on('keyup blur', function () {
            var cid = $(this).val();
            if (cid != '') {
                $('#cidNoErrorMsg').text('');
            }
        });
    }

    return {
        onChangeServiceId: onChangeServiceId
        , getDuplicateOrReplacementDocCharge: getDuplicateOrReplacementDocCharge
        , getEnglishLanProCharge: getEnglishLanProCharge
        , getRecheckCharge: getRecheckCharge
        , calculateRecheckCharge: calculateRecheckCharge
        , removeErrorMsg: removeErrorMsg
    }
})
();
$(document).ready(
    function () {
        $('.field').val('');
        $('#serviceId').val('');
        chargeCalculation.onChangeServiceId();
        chargeCalculation.getDuplicateOrReplacementDocCharge();
        chargeCalculation.getEnglishLanProCharge();
        chargeCalculation.getRecheckCharge();
        chargeCalculation.calculateRecheckCharge();
        chargeCalculation.removeErrorMsg();
    });
