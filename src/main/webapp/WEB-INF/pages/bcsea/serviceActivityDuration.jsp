<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 23-Jul-19
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Allocate Time for Services</title>
</head>
<body>
<div class="row" id="registration">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;BCSEA Service </b> >> Allocate Time for Services</span>

    <div class="col-12">
        <form class="card form-horizontal" id="serviceActivityDurationFormId"
              action="<c:url value='/serviceActivityDuration'/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" class="form-control hidden field" name="autoIndex" id="autoIndex">

            <div class="card-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 required">
                                        Service Name:
                                        <a data-toggle="tooltip" data-placement="top"
                                           title='Select service type.'
                                           class="tooltipCSSSelector">
                                            <img src="/resources/images/questionMark.jpg" class="user-image"
                                                 width="20px">
                                            <i class='fa fa-spinner fa-spin hidden' id="spinnerIconId"></i>
                                        </a>
                                    </label>

                                    <div class="col-sm-4">
                                        <select class="form-control" name="serviceId" id="serviceId" disabled>
                                            <option value="314">Clerical Re-check of Papers</option>
                                        </select>
                                        <span id="serviceIdErrorMsg" class="text-danger"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 required">Class Type:<a data-toggle="tooltip"
                                                                                   data-placement="top"
                                                                                   title='Please select class type'
                                                                                   class="tooltipCSSSelector">
                                        <img src="/resources/images/questionMark.jpg" class="user-image"
                                             id="instrumentCountryimg" width="20px">
                                    </a>

                                    </label>

                                    <div class="col-sm-4">
                                        <select class="form-control field" name="classType" id="classType" tabindex="1">
                                            <option value="">--SELECT-</option>
                                            <option value="10">Ten(10)</option>
                                            <option value="12">Twlve(12)</option>
                                        </select>
                                        <span id="classTypeErrorMsg" class="text-danger"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 required">Exam Year: <a data-toggle="tooltip"
                                                                                   data-placement="top"
                                                                                   title='Enter exam year.'
                                                                                   class="tooltipCSSSelector">
                                        <img src="/resources/images/questionMark.jpg" class="user-image"
                                             width="20px">
                                        <i class='fa fa-spinner fa-spin hidden' id="exmaY"></i>
                                    </a></label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control field numeric" name="examYear"
                                               id="examYear" maxlength="4" tabindex="2">
                                        <span class="text-danger" id="examYearErrorMsg"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 required">Start Date: <a data-toggle="tooltip"
                                                                                    data-placement="top"
                                                                                    title='Enter from date.'
                                                                                    class="tooltipCSSSelector">
                                        <img src="/resources/images/questionMark.jpg" class="user-image"
                                             width="20px">
                                        <i class='fa fa-spinner fa-spin hidden' id="frDate"></i>
                                    </a></label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control datepicker field" name="activeFrom"
                                               id="activeFrom" tabindex="3">
                                        <span class="text-danger" id="activeFromErrorMsg"></span>
                                    </div>
                                    <label class="col-sm-2 required">End Date:<a data-toggle="tooltip"
                                                                                 data-placement="top"
                                                                                 title='Enter to date.'
                                                                                 class="tooltipCSSSelector">
                                        <img src="/resources/images/questionMark.jpg" class="user-image"
                                             width="20px">
                                        <i class='fa fa-spinner fa-spin hidden' id="toDa"></i>
                                    </a> </label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control datepicker field" name="activeTo"
                                               id="activeTo" tabindex="4">
                                        <span class="text-danger" id="activeToErrorMsg"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 right-label">Status:</label>

                                    <div class="col-md-4">
                                        <input type="radio" name="statusTag" class="statusTag" id="statusTagActive"
                                               value="A" tabindex="5"/>
                                        <label for="statusTagActive"/>Active</label>

                                        <input type="radio" name="statusTag" class="statusTag" id="statusTagInactive"
                                               value="I" checked tabindex="6"/>
                                        <label for="statusTagInactive"/>Inactive</label>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <div class="col-md-2 col-md-offset-2">
                                            <input type="submit" class="btn btn-xs btn-info btn-block" value="Save"
                                                   id="btnSave" tabindex="7">
                                        </div>
                                        <div class="col-md-2">
                                            <input type="reset" class="btn btn-xs btn-info btn-block" value="Reset"
                                                   id="btnReset" tabindex="8">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div class="card-body">--%>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="serviceActivityListTableId">
                                <thead>
                                <tr>
                                    <th class="hidden">StatusTag</th>
                                    <th class="hidden">service Id</th>
                                    <th class="hidden">autoIndex</th>
                                    <th>Sl. No</th>
                                    <th>Service Name</th>
                                    <th>Class Type</th>
                                    <th>Exam Year</th>
                                    <th>From Date</th>
                                    <th>To Date</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%--</div>--%>
        </form>
    </div>
</div>
</body>
</html>
