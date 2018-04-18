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
    }).run(["$rootScope", function ($rootScope) {
    	//console.log(!angular.isDefined($rootScope.USER));
    	//console.log(JSON.parse(localStorage.getItem("user")));
    	
        if(!angular.isDefined($rootScope.USER) && localStorage.getItem("user")){
            // UserInfo exists in localstorate but not on $rootScope. This means the page was reloaded or the user is returning.
            $rootScope.USER = JSON.parse(localStorage.getItem("user"));
            //console.log(localStorage.getItem("user"));

        }else if(!angular.isDefined($rootScope.USER) && !localStorage.getItem("user")){
            // User is not logged at all. Send him back to login page
        	//$state.go("signin");
        
        }else if(angular.isDefined($rootScope.USER)){
            // User is logged in. You can run some extra validations in here.
        }
    	/*
            $rootScope.USER = {
                role:"CINEMA_THEATRE_ADMIN"
            }*/
        }])