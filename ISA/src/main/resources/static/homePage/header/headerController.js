angular.module('app')
    .controller('headerController', function ($scope, $state) {
    	//uraditi na pocetku
    	if($state.$current=="defaultState"){
    		$scope.headerState="home";
    	}
    	else if($state.$current=="cinemas"){
    		$scope.headerState="cine";
    		cinemaTheatreService.getCinemas(
    				function(info){//succes function
    					$scope.cinemas=info.data;
    					
    				},
    				function(info){//fail function
    					$scope.cinemas=[];
    					
    				}
    		);
    	}
    	else if($state.$current=="theatres"){
    		$scope.headerState="thea";
    		cinemaTheatreService.getTheatres(
    				function(info){//succes function
    					$scope.theatres=info.data;
    					
    				},
    				function(info){//fail function
    					$scope.theatres=[];
    					
    				}
    		);
    	}
    	
    	
    	
    	//funkcije kontrolera
    	$scope.headerSwitch=function(clicked){
    		$scope.headerState=clicked;
    	}
    });