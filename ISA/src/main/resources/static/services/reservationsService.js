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
            },
        
	        reservate: function (data, onSuccess, onError) {
	            var req = {
	                method: 'POST',
	                url: '/api/reservation',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: data
	            };
	            $http(req).then(onSuccess, onError);
	        }
	        
	        
     
        }
    });