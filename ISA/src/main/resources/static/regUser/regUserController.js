angular.module('app')
    .controller('regUserController', function ($rootScope, $scope, $state, regUserService) {
    	 if ($rootScope.USER == null || $rootScope.USER.role != 'REG_USER') {
             $state.go("signin");
         } else {
        	 regUserService.findById($rootScope.USER.id, function (res) {
                 $scope.user = res.data;
             }, function (res) {
                 $scope.user = {};
                 toastr.error(res.data.error);
             });
         }
    });