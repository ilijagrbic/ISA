angular.module('app')
    .controller('headerController', function ($scope, $state, $rootScope) {
    	//uraditi na pocetku
    	if($state.$current=="defaultState"){
    		$scope.headerState="home";
    	}
    	else if($state.$current=="cinemas"||$state.$current=="cinemasAdmin"){
    		$scope.headerState="cine";
    	}
    	else if($state.$current=="theatres"||$state.$current=="theatresAdmin"){
    		$scope.headerState="thea";
    	}
    	
    	//funkcije kontrolera
    	$scope.headerSwitch=function(clicked){
    		$scope.headerState=clicked;
    	}
    });