var module = angular.module('crOrder', ['ui.bootstrap']);

module.controller('crOrd', ['$compile', '$scope', '$http', function ($compile, $scope, $http) {
    $scope.delivery_methods = {'skype': 4, 'viber': 5, 'sms': 6, 'bird': 7, 'fish': 8};
    $scope.langs = [{id: '3', val: 'uk'}, {id: 4, val: 'ru'}, {id: 5, val: 'en'}, {id: 5, val: 'es'}];
    // $scope.templateList = {}//[{id:0, val:'first'}, {id:1, val:'second'}, {id:2, val:'third'}]
    $scope.order_list = [{id: 0, val: "first_order_templ"}, {id: 1, val: 'second_ord_templ'}, {
        id: 2,
        val: 'third_ord_templ'
    }]
    $scope.choosen_del_types = [];
    $scope.tabs = [];
    $scope.send_data = {};
    $scope.data = {label: '', id: '', duct: {}};
    $scope.templateLists = []
    console.log(window.gen_data);
    $scope.select_delyvery_type = function () {
        if ($scope.choosen_del_types.indexOf($scope.selected_delyvery_type) < 0 && $scope.selected_delyvery_type) {
            $scope.data.duct[angular.element('.delivery_type option:selected').text()] = {duct_id: $scope.selected_delyvery_type};
            $scope.choosen_del_types.push($scope.selected_delyvery_type);
            $scope.tabs.push({
                title: angular.element('.delivery_type option:selected').text(),
                templateList: $scope.templateList,
                langs: $scope.langs,
                id: $scope.selected_delyvery_type
            });
            $scope.prefix = angular.element('.delivery_type option:selected').text();
        }

    }

    $scope.remove_variant = function (el) {
        var pos = $scope.tabs.indexOf(el)
        delete $scope.data.duct[el.title]
        $scope.tabs.splice(pos, 1);
        $scope.choosen_del_types.splice($scope.choosen_del_types.indexOf(el.id), 1);
        $scope.active = 0;
    }

    $scope.send_data = function () {
        $scope.data_to_send = {}
        angular.copy($scope.data, $scope.data_to_send)
        $.each($scope.data_to_send.duct, function (idx) {
            $scope.data_to_send.duct[idx].delay =
                $scope.data_to_send.duct[idx].f_d * 24 * 60 * 60 * 1000 +
                $scope.data_to_send.duct[idx].f_h * 60 * 60 * 1000 +
                $scope.data_to_send.duct[idx].f_m * 60 * 1000 +
                $scope.data_to_send.duct[idx].f_s * 1000;
            delete $scope.data_to_send.duct[idx].f_d;
            delete $scope.data_to_send.duct[idx].f_h;
            delete $scope.data_to_send.duct[idx].f_m;
            delete $scope.data_to_send.duct[idx].f_s;
            $scope.data_to_send.duct[idx].delivery_time = $scope.data_to_send.duct[idx].delivery_time.getTime();
            $scope.data_to_send.duct[idx].time_to_live = $scope.data_to_send.duct[idx].time_to_live.getTime();
        });

        $('#fileUpd').ajaxSubmit({
            url: '/uploadFile',
            type: 'POST',
            resetForm: true,
            headers: {
                "contentType": "application/x-www-form-urlencoded;charset=UTF-8"
            },
            data: {format: 'json', data: angular.toJson($scope.data_to_send)},
            dataType: 'json',
            resetForm: true,
            success: function (res) {
            }
        })

        console.log($scope.data_to_send)
        // $http({
        // 	url:'/eventStencil',
        // 	method:'POST',
        // 	data: $scope.data_to_send
        // }).then(function (res) {
        // 	console.log(res)
        // })
    }

    $scope.changeLang = function (prefix) {
        console.log(prefix)
        $http({
            url: '/getStencils',
            method: 'POST',
            data: {lang: $scope.data.duct[prefix].lang}
        }).then(function (res) {
            $scope.templateLists[prefix] = res['data'];
            console.log($scope.templateLists['viber'])
        })
    }

    $scope.getTemplate = function (prefix) {
        console.log(prefix)
        $http({
            url: '/getStencilEntity',
            method: 'POST',
            data: {tid: $scope.data.duct[prefix].template_id}
        }).then(function (res) {
            $scope.data.duct[prefix].template_body = res['data']['data'];
        })
    }


    $scope.choose_order = function () {
        $http({
            url: '#',
            method: 'POST',
            data: {oid: $scope.data.id}
        }).then(function (res) {
            res = jQuery.parseJSON('{"viber":{"duct_id":"5","priority":2,"lang":4,"delivery_time":"2016-02-01T23:00:00.000Z","frequency":2,"time_to_live":"2016-01-01T23:01:00.000Z","template_id":1,"template_body":"1321","delay":176461000},"sms":{"duct_id":"6","priority":3,"lang":5,"delivery_time":"2016-01-01T23:00:00.000Z","frequency":3,"time_to_live":"2015-12-31T23:01:00.000Z","template_id":2,"template_body":"12131","delay":176521000}}');
            $scope.choosen_del_types = [];
            $scope.tabs = [];
            $scope.data = {label: '', id: '', duct: {}};
            $.each(res, function (ind, val) {
                var delay = parseInt(val.delay);
                var d_d = parseInt(delay / 24 / 60 / 60 / 1000);
                delay -= d_d * 24 * 60 * 60 * 1000;
                var d_h = parseInt(delay / 60 / 60 / 1000);
                delay -= d_h * 60 * 60 * 100
                var d_m = parseInt(delay / 60 / 1000);
                delay -= d_m * 60 * 1000;
                var d_s = parseInt(delay / 1000);
                $scope.data.duct[ind] = {
                    "duct_id": parseInt(val.duct_id),
                    "priority": parseInt(val.priority),
                    "lang": val.lang,
                    "delivery_time": new Date(val.delivery_time),
                    "frequency": 2, "time_to_live": new Date(val.time_to_live),
                    "template_id": parseInt(val.template_id),
                    "template_body": "1321",
                    "delay": 176461000,
                    "f_d": d_d,
                    "f_h": d_h,
                    "f_m": d_m,
                    "f_s": d_s,
                }

                $scope.choosen_del_types.push(val.duct_id);
                $scope.tabs.push({title: ind, id: val.duct_id});
            })
        })
    }

    $scope.uploadFile = function () {
        console.log('dfddd')
        // $('#fileUpd').ajaxSubmit({
        // 	url: '/uploadFile',
        // 	type: 'POST',
        // 	resetForm: true,
        // 	data: {format: 'json'},
        // 	dataType: 'json',
        // 	resetForm: true,
        // 	success: function (res) {}
        // })

    };

}]).directive('orderForm', ['$compile', function ($compile) {
    return {
        restrict: 'E',
        replace: 'true',
        link: function ($scope, element, attrs) {
            var prefix = attrs['prefix']
            var template = '\
		        <div class="row">\
		            <div class="col-xs-6">\
		                <div class="row_label"><span>Priority</span>\
		                    <input type="number" ng-model="data.duct.' + prefix + '.priority" min="1" class="form-control short-field">\
		                </div>\
		                <div class="row_label"><span>Lang</span>\
		                    <select class="lang short-field form-control" , ng-model="data.duct.' + prefix + '.lang"\
		                            ng-change="changeLang(\'' + prefix + '\')"\
		                            ng-options="lang.id as lang.val for lang in langs">\
		                        <option value="" selected disabled>Choose</option>\
		                    </select>\
		                </div>\
		                <div class="row_label"><span>Delivery date</span>\
		                    <input type="datetime-local" ng-model="data.duct.' + prefix + '.delivery_time" class="form-control short-field">\
		                </div>\
		                <div class="row_label"><span>Frequency</span>\
		                    <input type="number" ng-model="data.duct.' + prefix + '.frequency" class="form-control short-field" min="0" max="10">\
		                </div>\
		                <div class="row_label"><span>Delay</span>\
		                    D: <input type="number" class="delay d_day mini-field form-control" min="0" max="3"  ng-model="data.duct.' + prefix + '.f_d">\
		                    H: <input type="number" class="delay d_hour mini-field form-control" min="0" max="24" ng-model="data.duct.' + prefix + '.f_h">\
		                    M: <input type="number" class="delay d_minute mini-field form-control" min="0" max="60" ng-model="data.duct.' + prefix + '.f_m">\
		                    S: <input type="number" class="delay d_seconds mini-field form-control" min="0" max="60" ng-model="data.duct.' + prefix + '.f_s">\
		                </div>\
		                <div class="row_label"><span>Time to live</span>\
		                    <input type="datetime-local" ng-model="data.duct.' + prefix + '.time_to_live" class="form-control short-field">\
		                </div>\
		            </div>\
		            <div class="col-xs-4">\
		                <select class="template-list form-control" ng-model="data.duct.' + prefix + '.template_id"\
		                        ng-change="getTemplate(\'' + prefix + '\')"\
		                        ng-options="item.id as item.val for item in templateLists.' + prefix + '">\
		                    <option value="" disabled>Choose</option>\
		                </select>\
		                <textarea class="template-body form-control" ng-model="data.duct.' + prefix + '.template_body"></textarea>\
		            </div>\
		        </div>';
            var linkFn = $compile(template);
            var content = linkFn($scope);
            element.append(content);
            $.each($scope.langs, function (ind, val) {
                console.log('index == ' + ind)
                console.log('lang_id == ' + val.id)
                console.log('def_lang_model == ' + $scope.data.duct[prefix].lang)
                console.log('lang_id_from_source == ' + $scope.langs[ind].id);
                if (val.id == $scope.data.duct[prefix].lang) {
                    console.log('yeah')
                    $scope.data.duct[prefix].lang = $scope.langs[ind].id
                }
            })
        }
    }
}])

    .controller('orderGrid', ['$compile', '$scope', '$http', function ($compile, $scope, $http) {
        $scope.companyList = [{
            id_company: 1,
            company_name: 'test1',
            address: 'some addr',
            orders: [{id: 1, label: 'test label', template_name: 'pushkin'}]
        }, {
            id_company: 2,
            company_name: 'test2',
            address: 'some addr 2',
            orders: [{id: 1, label: 'test label 1', template_name: 'pushkin 1'}]
        }, {
            id_company: 3,
            company_name: 'test3',
            address: 'some addr 3',
            orders: [{id: 2, label: 'test label 2', template_name: 'pushkin 2'}]
        }, {
            id_company: 4,
            company_name: 'test4',
            address: 'some addr 4',
            orders: [{id: 3, label: 'test label 3', template_name: 'pushkin 3'}]
        }];
        $scope.goToEditionTemplaye = function (id) {
            window.location.href = "/event/?eid=" + id;
        }
    }])