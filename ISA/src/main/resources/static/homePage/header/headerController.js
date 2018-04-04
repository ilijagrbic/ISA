angular.module('app')
    .controller('headerController', function ($scope, $state) {
    	if($state.$current=="defaultState"){
    		$scope.headerState="home";
    	}
    	else if($state.$current=="cinemas"){
    		$scope.headerState="cine";
    	}
    	else if($state.$current=="theatres"){
    		$scope.headerState="thea";
    	}
    	$scope.headerSwitch=function(clicked){
    		$scope.headerState=clicked;
    	}
    });