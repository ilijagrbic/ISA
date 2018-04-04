angular.module('app')
    .controller('headerController', function ($scope, $state) {
    	//uraditi na pocetku
    	if($state.$current=="defaultState"){
    		$scope.headerState="home";
    	}
    	else if($state.$current=="cinemas"){
    		$scope.headerState="cine";
    	}
    	else if($state.$current=="theatres"){
    		$scope.headerState="thea";
    	}
    	
    	
    	
    	//funkcije kontrolera
    	$scope.headerSwitch=function(clicked){
    		$scope.headerState=clicked;
    	}
    });