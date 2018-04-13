angular.module('app')
    .service('userService', function ($http) {
        return {
        	findById: function (id, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users/' + id
                };
                $http(req).then(onSuccess, onError);
            },
        	getRegUsers: function (onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users',
                };
                $http(req).then(onSuccess, onError);
            },
	        update: function (id, user, onSuccess, onError) {
	            var req = {
	                method: 'PUT',
	                url: '/api/users/' + id,
	                data: user
	            };
	            // console.log()
	            $http(req).then(onSuccess, onError);
	        },
	        findFriends: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/friends',
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        findNonFriends: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/nonFriends',
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        findRequests: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/findRequests',
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        updatePassword: function (passwords, id, onSuccess, onError) {
                var req = {
                    method: 'PUT',
                    url: '/api/users/changePassword' + id,
                    data: passwords
                };
                $http(req).then(onSuccess, onError);
            }
	        
        }
    });