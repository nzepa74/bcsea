/**
 * Created by USER on 8/22/2019.
 */

trackApplication = (function () {
    var form = $('#trackApplicationFormId');

    function _baseURL() {
        return 'trackApplication/';
    }

    function getTrackApplicationDetail() {
        $('#btnView').on('click', function () {
            var applicationNo = $('#applicationNo').val();
            var url = _baseURL() + 'getTrackApplicationDetail';
            $.ajax({
                url: url
                , type: 'GET'
                , data: {applicationNo: applicationNo}
                , success: function (res) {
                    var data = res.responseDTO;
                    if (res.responseStatus == 1) {
                        $('#applicationDetailTableId').show();
                        $('#submissionDate').text(formatAsDate(data.submissionDate));
                        $('#serviceName').text(data.serviceName);
                        $('#applicationStatus').text(data.statusName);
                    } else {
                        warningMsg('No information found matching application no ' + applicationNo);
                        $('#applicationDetailTableId').hide();
                        $('.field').text('');
                    }
                }
            });
        });
    }

    return {
        getTrackApplicationDetail: getTrackApplicationDetail
    }
})
();
$(document).ready(
    function () {
        trackApplication.getTrackApplicationDetail();
    });


