angular.module('app')
    .controller('signinController', function ($window, $rootScope, $scope, $state, authenticationService) {
    	$scope.signin = function () {
    		//alert(JSON.stringify($scope.user));
    		authenticationService.signin(
                $scope.user,
                function (res) {
                	// alert(JSON.stringify(res.data));
                	$rootScope.USER = {
                        id: res.data.id,
                        role: res.data.role,
                        name: res.data.name,
                        surname: res.data.surname,
                        email: res.data.email,
                        activated: res.data.activated,
                        city: res.data.city,
                        phone: res.data.phone,
						firstTime : res.data.firstTime
                        //sifra,pozivi,rezervacije,verificationCode,bodovi,firstTime
                    };
                	 console.log($rootScope.USER);
                      
                	 localStorage.setItem("user", JSON.stringify($rootScope.USER));
                	 
                	 console.log(JSON.stringify(localStorage.getItem("user")));
                	
                    $scope.user = {};
                    if($rootScope.USER.role!='CINEMA_THEATRE_ADMIN'){
                    	$state.go('user'); // Ide na usera
                    }
                    else{
                    	$state.go('adminLogin');
                    }
                    

                }, function (res) {
                	alert("User je null");
                    $rootScope.USER = null;
                });
        };
    });