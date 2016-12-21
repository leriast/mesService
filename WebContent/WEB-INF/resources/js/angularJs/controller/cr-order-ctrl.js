var module = angular.module('crOrder', ['ui.bootstrap']);

module.controller('crOrd', ['$compile', '$scope', '$http', function($compile, $scope, $http){
    $scope.delivery_methods = [{id:0, val:'skype'}, {id:1, val:'viber'}, {id:2, val:'sms'}, {id:3, val:'bird'}, {id:4, val:'fish'}];
    $scope.langs = [{id:'0', val:'uk'}, {id:1, val:'ru'}, {id:2, val:'en'}, {id:3, val:'es'}];
    $scope.templateList = [{id:0, val:'first'}, {id:1, val:'second'}, {id:2, val:'third'}]
    $scope.choosen_del_types = [];
  	$scope.tabs = [];
  	$scope.send_data = {};
	console.log(window.gen_data);
	$scope.select_delyvery_type = function(){
		if($scope.choosen_del_types.indexOf($scope.selected_delyvery_type.id)<0){
			$scope.choosen_del_types.push($scope.selected_delyvery_type.id);
			$scope.tabs.push({title:$scope.selected_delyvery_type.val, templateList:$scope.templateList, langs:$scope.langs, id:$scope.selected_delyvery_type.id});
		}
	}

	$scope.remove_variant = function(el, index){
		var pos = $scope.tabs.indexOf(el)
		$scope.tabs.splice(pos, 1);
		$scope.choosen_del_types.splice($scope.choosen_del_types.indexOf(el.id), 1);
		$scope.active = 0;
	}

	$scope.send_data = function(){
		var prefix = {};
		var res = [];
		for (var i =0; i<$scope.choosen_del_types.length; i++) {
			console.log()
			for(var j=0; j<$scope.delivery_methods.length; j++){
				if($scope.delivery_methods[j]['id']==$scope.choosen_del_types[i]){
					prefix = $scope.delivery_methods[j];
					console.log('#'+prefix['val']+'_lang')
					console.log(angular.element('#'+prefix['val']+'_lang'))
					var tmp = angular.element('#'+prefix['val']+'_lang').val();
					console.log(tmp)
					console.log(angular.element('#'+prefix['val']+'_lang option:selected').text())	
					var delay = angular.element('#'+prefix['val']+'_f_d').val()*24*60*60*1000
						+ angular.element('#'+prefix['val']+'_f_d').val()*60*60*1000
						+ angular.element('#'+prefix['val']+'_f_d').val()*60*1000
						+ angular.element('#'+prefix['val']+'_f_d').val()*1000;
					res.push({
						delivery_method_id:prefix['id'],
						priority:angular.element('#'+prefix['val']+'_priority').val(),
						lang:angular.element('#'+prefix['val']+'_lang').val(),
						delivery_time:angular.element('#'+prefix['val']+'_delivery_time').val(),
						frequency:angular.element('#'+prefix['val']+'_frequency').val(),
						time_to_live:angular.element('#'+prefix['val']+'_time_to_live').val(),
						template_id:angular.element('#'+prefix['val']+'_template_id').val(),
						template_body:angular.element('#'+prefix['val']+'_template_body').val(),
						delay: delay
					});
				}
			}
		}
		$http({
			url:'/eventStencil',
			method:'POST',
			data: {data: res}
		}).tHen(function (res) {
			console.log(res)
		})
	}



}]).directive('orderForm', ['$compile', function($compile){
	return{
		templateUrl:"order_template.html",
		restrict: 'E',
		replace: 'true',
		scope:{
			prefix:'@',
			templateList:'=',
			langs:'='
		},
		controller: ['$scope', '$http', function($scope, $http){
			$scope.changeLang = function(){
				$http({
					url:'/getStencils',
					method:'POST',
					data: {lang:angular.element('#'+$scope.prefix['val']+'_lang').val()}
				}).then(function(res){
					$scope.templateList = res['data'];
				})
			}

			$scope.getTemplate = function(){
				$http({
					url:'#',
					method:'POST',
					data: {tid:angular.element('#'+$scope.prefix['val']+'_template_id').val()}
				}).then(function(res){
					angular.element('#'+prefix['val']+'_template_body').val(res['data']);
				})
			}


		}],
	}
}])