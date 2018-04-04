angular.module('app')
    .service('cinemaTheatreService', function ($http) {
        return {
            getCinemas: function (onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/onlycinnemas'
                };
                $http(req).then(onSuccess, onError);
            },
            getTheatres: function (onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/onlytheatres'
                };
                $http(req).then(onSuccess, onError);
            }
        }
    });