<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
    <div class="container">
        <div class="row pull-right">
            <div class="col-lg order-lg-first">
                <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                    <li class="nav-item ">
                        <a href="/" class="nav-link" style="color:black">
                            <i class="fa fa-home"></i>Home</a>
                    </li>
                    <li class="nav-item" style="color:black">
                        <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fe fe-edit"></i>
                            Application <i class="fa fa-chevron-down"></i></a>

                        <div class="dropdown-menu dropdown-menu-arrow">
                            <a href="/issueDuplicateCertificate" class="dropdown-item">
                                <i class="fe fe-copy"></i> Issuance
                                Of Duplicate Certificate</a>
                            <a href="/issueReplacementCertificate" class="dropdown-item">
                                <i class="fe fe-repeat"></i>Issuance Of
                                Replacement Certificate</a>
                            <a href="/issueEnglishLanProCertificate" class="dropdown-item ">
                                <i class="fe fe-folder"></i>Issue
                                Of English Language Proficiency Certificate</a>
                            <a href="/recheckApplication" class="dropdown-item">
                                <i class="fe fe-check"></i>Application
                                Form For Clerical Re-Check Of Answer Scripts</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a href="/trackApplication" class="nav-link" style="color:black"> <strong><i
                                class="fe fe-search"></i>Track Your Application</strong></a>
                    </li>
                    <li class="nav-item">
                        <a href="/viewResult" class="nav-link" style="color:black"> <strong><i
                                class="fa fa-eye"></i>View Result</strong></a>
                    </li>
                    <li class="nav-item">
                        <a href="/chargeCalculation" class="nav-link" style="color:black"> <strong><i
                                class="fa fa-calculator"></i>Charge Calculator</strong></a>
                    </li>
                    <%--<li class="nav-item">
                        <a href="/operatorsTaskList" class="dropdown-item">
                            <i class="fa fa-list"></i>Task List Operators</a>
                    </li>
                    <li class="nav-item">
                        <a href="/paymentDetail" class="dropdown-item">
                            <i class="fa fa-paper-plane"></i>Payment Detail</a>
                    </li>
                    <li class="nav-item">
                        <a href="/reports" class="dropdown-item">
                            <i class="fa fa-file-pdf-o"></i>Reports</a>
                    </li>

                    <li class="nav-item">
                        <a href="/printCertificate" class="dropdown-item">
                            <i class="fa fa-print"></i>Print Certificate</a>
                    </li>

                    <li class="nav-item" style="color:black">
                        <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown"><i class="fe fe-edit"></i>
                            Admin Tasks <i class="fa fa-chevron-down"></i></a>

                        <div class="dropdown-menu dropdown-menu-arrow">
                            <a href="/serviceActivityDuration" class="dropdown-item">
                                <i class="fe fe-watch"></i> Allocate Time for Services</a>
                            <a href="/chargeAllocation" class="dropdown-item">
                                <i class="fa fa-dollar"></i>Charge Allocation</a>
                        </div>
                    </li>--%>
                </ul>
            </div>
        </div>
    </div>
</div>