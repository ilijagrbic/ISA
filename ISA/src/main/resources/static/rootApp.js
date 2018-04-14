var app = angular.module('app', ['ui.router', 'ui.bootstrap']); 
app.factory('authInterceptor', ['$q', '$injector', function ($q, $injector) {
    var authInterceptor = {
        responseError: function (response) {
            var $state = $injector.get('$state');
            if ($state.current.name !== 'signin' && response.status == 401) {
                $state.transitionTo('signin');
            }
            return $q.reject(response);
        }
    };
    return authInterceptor;
}]);

app.config(function($stateProvider, $urlRouterProvider,$httpProvider){
		$httpProvider.interceptors.push('authInterceptor');
    	$urlRouterProvider.otherwise("/defaultState");
    }).run(function ($rootScope) {
    	/*
            $rootScope.USER = {
                role:"CINEMA_THEATRE_ADMIN"
            }*/
        })