var app = angular.module('app', ['ui.router', 'ui.bootstrap']) 
/////// Za logovanje    
app.factory('authInterceptor', [function() {  
    return {
        // Send the Authorization header with each request
            'request': function(config) {
                config.headers = config.headers || {};
                var encodedString = btoa("bill:abc123");
                config.headers.Authorization = 'Basic '+encodedString;
               return config;
            }
        };
    }]);
///////
app.config(function($stateProvider, $urlRouterProvider,$httpProvider){
		$httpProvider.interceptors.push('authInterceptor');
    	$urlRouterProvider.otherwise("/defaultState");
    	// Interceptor
    	
    }).run(function ($rootScope) {
    	
            /*$rootScope.USER = {
                role:"CINEMA_THEATRE_ADMIN"
            }*/
        })