<!DOCTYPE html>
<html ng-app="app">
<head>
    <title>2</title>
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
    <style type="text/css">
        .header-row, .sub-header-row{
            border-width: 1px 0;
            border-style: solid;
            border-color: gainsboro;
        }

        .small-coll {
            width: 5em!important;
        }

        .header-row, .sub-row, .company-row, .company-orders-row, .sub-header{
            width: 100%!important;
        }

        .header-row div, .company-row div{
            display: inline-block;
            height: 2em;
            line-height: 2em;
            width: 21.5em;
        }

        .header-row div {
            font-weight: bold;
        }

        .sub-header, .company-orders-row {
            padding-left: 5em;
        }

        .sub-header div,  .company-orders-row div {
            width: 15em;
        }

        .company-row {
            border-width: 0 0 1px 0;
            border-style: solid;
            border-color: gainsboro;
            background-color: #f7f7f7;
        }

        .sub-row, .company-orders-row {
            background-color: #e6e6e6;
        }

        .sub-header {
            font-weight: bold;
            font-size:
        }

        .company-orders-row{
            cursor: pointer;
        }



    </style>
</head>
<body>
<div class="container" ng-controller="orderGrid">
    <h1>Companies orders</h1>
    <div class="header-row">
        <div class="small-coll">ID</div>
        <div>Company name</div>
        <div>Company address</div>
    </div>
    <div class="company-row" ng-repeat="c_item in companyList">
        <div class="field small-coll">{{c_item.id_company}}</div>
        <div class="field">{{c_item.company_name}}</div>
        <div class="field">{{c_item.address}}</div>
        <div class="sub-row">
            <div class="sub-header">
                <div class="small-coll">ID</div>
                <div>Label</div>
                <div>Template</div>
            </div>
            <div class="company-orders-row" ng-repeat="o in c_item.orders" title="Duble click for open templte">
                <div class="small-coll" ng-dblclick="goToEditionTemplaye(o.id)">{{o.id}}</div>
                <div ng-dblclick="goToEditionTemplaye(o.id)">{{o.label}}</div>
                <div ng-dblclick="goToEditionTemplaye(o.id)">{{o.template_name}}</div>
            </div>
        </div>

    </div>
</div>
</body>
</html>