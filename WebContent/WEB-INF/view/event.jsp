<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="app">
<head>

    <title>1</title>
    <style type="text/css">
        .order-form {
            display: inline-block;
            width: 70%;
        }

        .table {
            width: 100%;
        }

        .table tr, .table th {
            height: 2em;
            text-align: center;
        }

        .delay {
            width: 4em;
        }

        .row_label span {
            width: 10em;
            display: inline-block;
        }

        .short-field, .mini-field {
            display: inline-block !important;
        }

        .short-field {
            width: 60% !important;
        }

        .mini-field {
            width: 12% !important;
        }

        .main-container {
            padding: 5%;
            box-sizing: content-box;
        }

        .template-list {
            margin-bottom: 5px;
        }

        .template-body {
            height: 10em !important;
        }

        .remove-btn:hover {
            cursor: pointer;
            color: red;
        }
    </style>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">

    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous">
    </script>
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.3.1/ui-bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/ui-bootstrap-tpls-2.3.1.min.js"></script>
    <script type="text/javascript" src="js/angularJs/app.js"></script>
    <script type="text/javascript" src="js/angularJs/controller/cr-order-ctrl.js"></script>
</head>
<body>
<script>
    window.gen_data = ${R2D2}
</script>
<%--<a>${R2D2}</a>--%>
<div ng-controller="crOrd" class="row main-container">
    <div class="col-xs-2">
        <select class="delivery_type form-control"
                ng-model="selected_delyvery_type"
                ng-change="select_delyvery_type()"
                ng-options="type as type.val for type in delivery_methods">
            <option value="" selected disabled>Choose</option>
        </select>
    </div>
    <div class="col-xs-10">
        <uib-tabset active="active">
            <uib-tab index="$index" ng-repeat="tab in tabs" disable="tab.disabled">
                <uib-tab-heading>
                    {{tab.title}} <a class="remove-btn" ng-click="remove_variant(tab, $index)">x</a>
                </uib-tab-heading>
                <order-form prefix="{{tab.title}}" template-list="tab.templateList" langs="tab.langs"></order-form>
            </uib-tab>
        </uib-tabset>
    </div>
    <input type="button" value="Send" ng-click="send_data()" class="btn btn-default" ng-hide="tabs.length<1">

    <script type="text/ng-template" id="order_template.html">
        <div class="row">
            <div class="col-xs-6">
                <div class="row_label"><span>Priority</span>
                    <input type='number' id='{{prefix}}_priority' min='1' class="form-control short-field">
                </div>
                <div class="row_label"><span>Lang</span>
                    <select class='lang short-field form-control' , id='{{prefix}}_lang'
                            ng-model="select_lang"
                            ng-change="changeLang()"
                            ng-options="lang.id as lang.val for lang in langs track by lang.id">
                        <option value="" selected disabled>Choose</option>
                    </select>
                </div>
                <div class="row_label"><span>Delivery date</span>
                    <input type='datetime-local' id='{{prefix}}_delivery_time' class="form-control short-field">
                </div>
                <div class="row_label"><span>Frequency</span>
                    <input type='number' id='{{prefix}}_frequency' class="form-control short-field">
                </div>
                <div class="row_label"><span>Delay</span>
                    D: <input type='number' class='delay d_day mini-field form-control' min='0' max='3'  id='{{prefix}}_f_d'>
                    H: <input type='number' class='delay d_hour mini-field form-control' min='0' max='24'
                              id='{{prefix}}_f_h'>
                    M: <input type='number' class='delay d_minute mini-field form-control' min='0' max='60'
                              id='{{prefix}}_f_m'>
                    S: <input type='number' class='delay d_seconds mini-field form-control' min='0' max='60'
                              id='{{prefix}}_f_s'>
                    <input type="hidden" id="{{prefix}}_delay">
                </div>
                <div class="row_label"><span>Time to live</span>
                    <input type='datetime-local' id='{{prefix}}_time_to_live' class="form-control short-field">
                </div>
            </div>
            <div class="col-xs-4">
                <select class="template-list form-control" id="{{prefix}}_template_id"
                        ng-model="selected_tmpl"
                        ng-change="getTemplate()"
                        ng-options="item as item.val for item in templateList">
                    <option value="" disabled>Choose</option>
                </select>
                <textarea class="template-body form-control" id="{{prefix}}_template_body"></textarea>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </div>
    </script>
</div>
</body>
</html>
