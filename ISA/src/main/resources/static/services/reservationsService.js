angular.module('app')
    .service('reservationsService', function ($http) {
        return {
            findByName: function (name, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/findCinemaTheatreByName/' + name,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };
                $http(req).then(onSuccess, onError);
            }
     
        }
    });