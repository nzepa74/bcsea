$(document).ready(function () {
    if (document.URL.search("issueDuplicateCertificate") > 1)
        scriptLoader("/resources/js/bcsea/issueDuplicateCertificate.js");
    if (document.URL.search("issueReplacementCertificate") > 1)
        scriptLoader("/resources/js/bcsea/issueReplacementCertificate.js");
    if (document.URL.search("issueEnglishLanProCertificate") > 1)
        scriptLoader("/resources/js/bcsea/issueEnglishLanProCertificate.js");
    if (document.URL.search("recheckApplication") > 1)
        scriptLoader("/resources/js/bcsea/recheckApplication.js");
    if (document.URL.search("operatorsTaskList") > 1)
        scriptLoader("/resources/js/bcsea/operatorsTaskList.js");
    if (document.URL.search("approveIssueDuplicateCertificate") > 1)
        scriptLoader("/resources/js/bcsea/approveIssueDuplicateCertificate.js");
    if (document.URL.search("approveIssueReplacementCertificate") > 1)
        scriptLoader("/resources/js/bcsea/approveIssueReplacementCertificate.js");
    if (document.URL.search("approveIssueEnglishLanProCertificate") > 1)
        scriptLoader("/resources/js/bcsea/approveIssueEnglishLanProCertificate.js");
    if (document.URL.search("approveRecheckApplication") > 1)
        scriptLoader("/resources/js/bcsea/approveRecheckApplication.js");

    if (document.URL.search("trackApplication") > 1)
        scriptLoader("/resources/js/bcsea/trackApplication.js");
    if (document.URL.search("viewResult") > 1)
        scriptLoader("/resources/js/bcsea/viewResult.js");
    if (document.URL.search("chargeCalculation") > 1)
        scriptLoader("/resources/js/bcsea/chargeCalculation.js");

    if (document.URL.search("serviceActivityDuration") > 1)
        scriptLoader("/resources/js/bcsea/serviceActivityDuration.js");
    if (document.URL.search("chargeAllocation") > 1)
        scriptLoader("/resources/js/bcsea/chargeAllocation.js");

    if (document.URL.search("paymentDetail") > 1)
        scriptLoader("/resources/js/bcsea/paymentDetail.js");

    if (document.URL.search("reports") > 1)
        scriptLoader("/resources/js/bcsea/reports.js");

    if (document.URL.search("printCertificate") > 1)
        scriptLoader("/resources/js/bcsea/printCertificate.js");

    if (document.URL.search("home") > 1)
        scriptLoader("/resources/js/bcsea/home.js");

    if (document.URL.search("login") > 1)
        scriptLoader("/resources/js/bcsea/home.js");
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

