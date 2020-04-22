<html>
<title>BCSEA</title>
<%--<meta name="decorator" content="/layout/open-layout.jsp"/>--%>
<body>
<%--<h1>${message}</h1>--%>
<form action="" id="indexFormId" type="POST">
    <div class="card card-primary">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                    <h5>Note:</h5>
                    <label>
                        <u>Duplicate & Replacement : </u>
                        A duplicate document is issued to the candidates in the event of loss or damage of the original one.
                        A document is replaced when the Name or Date of Birth of a candidate requires to be changed.
                        Name and Date of Birth will be changed as per the CID of the candidate.
                        A concern letter either from school or census must be attached while applying for replacement of document.
                    </label>
                    <label>
                        <u>Language Proficiency Certificate : </u>
                        English Language Proficiency Certificate is issued to those students who have successfully completed their class X or XII from schools affiliated with BCSEA or any other board recognised by the BCSEA.
                    </label>
                    <label>
                        <u>Clerical Re-check : </u>
                        Candidates who wish to re-check their marks for personal satisfaction can apply for the same. However, rechecking refers to re-tabulation of marks only, and does not include re-evaluation of answer scripts.
                    </label>
                </div>
                <div class="col-md-6 col-sm-6 col-lg-6 col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <h5>List of online BCSEA services:</h5>
                            <ol>
                                <li><a href="/issueDuplicateCertificate">Apply for Duplicate Documents</a></li>
                                <li><a href="/issueReplacementCertificate">Apply for Replacement Documents</a></li>
                                <li><a href="/issueEnglishLanProCertificate">Apply for English Language Proficiency Certificate</a></li>
                                <li><a href="/recheckApplication">Apply for Clerical Re-check</a></li>
                                <li><a href="/chargeCalculation">Check the Service Charge</a></li>
                                <li><a href="/viewResult">View Result (Both Class 10 & 12)</a></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>