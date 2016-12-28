<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="utf-8"/>
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
<script type="text/javascript" src="http://malsup.github.io/min/jquery.form.min.js"></script>




    <%--<link rel="stylesheet" href="/bootstrap.min.css">--%>
    <%--<!-- Optional theme -->--%>
    <%--<link rel="stylesheet" href="/bootstrap-theme.min.css">--%>
    <%--<script--%>
            <%--src="https://code.jquery.com/jquery-1.12.4.min.js"--%>
            <%--integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="--%>
            <%--crossorigin="anonymous">--%>
    <%--</script>--%>
    <%--<script src="/bootstrap.min.js"></script>--%>
    <%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>--%>
    <%--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.3.1/ui-bootstrap.min.js"></script>--%>
    <%--<script type="text/javascript" src="/ui-bootstrap-tpls-2.3.1.min.js"></script>--%>
    <%--<script type="text/javascript" src="/app.js"></script>--%>
    <%--<script type="text/javascript" src="/cr-order-ctrl.js"></script>--%>
</head>
<body>
<script>
     window.gen_data = ${R2D2}
</script>
<div ng-controller="crOrd" class="row main-container">
    <select
            class="form-control"
            ng-model="data.id"
            ng-change="choose_order()"
            ng-options="ord.id as ord.val for ord in order_list track by ord.id">
        <option value="" selected>New</option>
    </select>
    <input type="text" ng-model="data.label" placeholder="Input label for you order" class="form-control">
    <div class="col-xs-2">
        <select class="delivery_type form-control"
                ng-model="selected_delyvery_type"
                ng-change="select_delyvery_type()">
            <option value="" selected disabled>Choose</option>
            <option ng-repeat="(key, val) in delivery_methods track by val" value="{{val}}">{{key}}</option>
        </select>
    </div>
    <div class="col-xs-10">
        <uib-tabset active="active">
            <uib-tab index="$index" ng-repeat="tab in tabs" disable="tab.disabled" class="">
                <uib-tab-heading>
                    {{tab.title}} <a class="remove-btn" ng-click="remove_variant(tab)">x</a>
                </uib-tab-heading>
                <order-form prefix="{{tab.title}}"></order-form>
            </uib-tab>
        </uib-tabset>
    </div>
    <input type="button" value="Send" ng-click="send_data()" class="btn btn-default" ng-hide="tabs.length<1">

    <form class="fileUpd" id="fileUpd" enctype="multipart/form-data" method="post" accept-charset="UTF-8">
        <input type="file" name="file" id="fileObj" onchange="angular.element(this).scope().uploadFile()">
        <form>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</div>
</body>
</html>