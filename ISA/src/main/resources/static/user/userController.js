angular.module('app')
    .controller('userController', function ($rootScope, $scope, $state, userService) {
    	 if ($rootScope.USER == null || $rootScope.USER.role != 'REG_USER') {
             $state.go("signin");
         } else {
        	 userService.findById($rootScope.USER.id, function (res) {
                 $scope.user = res.data;
                 $scope.notChanged = true;
                 $scope.changed = false;
                 $scope.editPassword = false;
                 $scope.editedPassword = true;
             }, function (res) {
                 $scope.user = {};
                 toastr.error(res.data.error);
             });
         }
    	 
    	 $scope.editUserDetails = function () {
    		 $scope.notChanged = false;
             $scope.changed = true;
         };
         
         $scope.saveUserDetails = function () {
    		 $scope.notChanged = true;
             $scope.changed = false;
      
             alert(JSON.stringify($scope.user))
             
             userService.update($scope.user.id, $scope.user, function (res) {
                  $scope.user = res.data;
                  
              }, function (res) {
                 
            	 // alert(res.data.error);
              });
         };
         
         $scope.editPass = function () {
        	 $scope.editPassword = true;
        	 $scope.editedPassword = false;
        	 
         };
         
         $scope.savePass = function () {
        	 $scope.editedPassword = true;
        	 $scope.editPassword = false;
        	 userService.updatePassword($scope.passwords, $scope.id,
                     function (res) {
                         
                         if ($rootScope.USER === null) {
                             $rootScope.USER = {
                                 id: res.data.id,
                                 role: res.data.role,
                                 firstName: res.data.firstName,
                                 lastName: res.data.lastName,
                                 email: res.data.email,
                                 city: res.data.city,
                                 phone: res.data.phone,
                                 password: res.data.password
                             };
                             
                         }
                     }, function (res) {
                 
                     });
          
         };
         
         
        
     
     });
