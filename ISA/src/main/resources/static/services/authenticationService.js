angular.module('app')
    .service('authenticationService', function ($http) {
        return {
            signup: function (user, onSuccess, onError) {
                var req = {
                    method: 'POST',
                    url: '/api/authentication/signup',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: user
                };
                $http(req).then(onSuccess, onError);
            },
            signin: function (user, onSuccess, onError) {
                var req = {
                    method: 'POST',
                    url: '/api/authentication/signin',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: user
                };

                $http(req).then(onSuccess, onError);
            },
            signout: function (onSuccess, onError) {
                var req = {
                    method: 'POST',
                    url: '/api/authentication/signout'
                };
                $http(req).then(onSuccess, onError);
            },
            getuser: null
        }
    });