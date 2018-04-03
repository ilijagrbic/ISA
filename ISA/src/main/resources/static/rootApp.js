angular.module('app', ['ui.router']) 
    .config(function($stateProvider, $urlRouterProvider){
    	$urlRouterProvider.otherwise("/defaultState");
    })