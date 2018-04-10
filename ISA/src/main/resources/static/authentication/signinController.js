angular.module('app')
    .controller('signinController', function ($rootScope, $scope, $state, authenticationService) {
    	$scope.signin = function () {
    		authenticationService.signin(
                $scope.user,
                function (res) {
                    $rootScope.USER = {
                        id: res.data.id,
                        role: res.data.role,
                        name: res.data.name,
                        surname: res.data.surname,
                        email: res.data.email,
                        activated: res.data.activated,
                        city: res.data.city,
                        phone: res.data.phone,
                        //sifra,pozivi,rezervacije,verificationCode,bodovi,firstTime
                    };


                    $scope.user = {};
                    $state.go('regUser'); // Ide na usera
                    

                }, function (res) {
                    $rootScope.USER = null;
                });
        };
    });