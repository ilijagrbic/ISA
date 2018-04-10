angular.module('app', ['ui.router']) 
    .config(function($stateProvider, $urlRouterProvider){
    	$urlRouterProvider.otherwise("/defaultState");
    }).run(function ($rootScope) {
            /*$rootScope.USER = {
                role:"CINEMA_THEATRE_ADMIN"
            }*/
        })