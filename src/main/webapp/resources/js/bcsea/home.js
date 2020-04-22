$(document).ready(function () {
    scriptLoader("/resources/js/bcsea/operatorsTaskList.js");
    scriptLoader("/resources/js/bcsea/paymentDetail.js");
    scriptLoader("/resources/js/bcsea/serviceActivityDuration.js");
});

var scriptLoader = function (url) {
    $.ajax(
        {
            type: "GET",
            url: url,
            dataType: "script",
            cache: false
        });
};
