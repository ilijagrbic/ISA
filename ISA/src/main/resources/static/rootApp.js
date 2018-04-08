angular.module('app', ['ui.router']) 
    .config(function($stateProvider, $urlRouterProvider){
    	$urlRouterProvider.otherwise("/defaultState");
    })
    .run(function ($rootScope) {
    	angular.module('app', ['ui.router']) 
        .config(function($stateProvider, $urlRouterProvider){
        	$urlRouterProvider.otherwise("/defaultState");
        })
        .run(function ($rootScope, authService) {
                    $rootScope.USER = {
                        id: "",
                        role: "ADMIN",
                        type: "CINEMA_THEATRE"
                    }
        	});