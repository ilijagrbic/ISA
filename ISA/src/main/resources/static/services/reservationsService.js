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
            getAllReservations(onSuccess, onError) {
                var req = {
                        method: 'GET',
                        url: '/api/getAllReservations' ,
                    };
                    console.log("Url " + req.url);
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
	        },
	        getReservations: function (id, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/getReservation/' + id,
                };
                console.log("Url " + req.url);
                $http(req).then(onSuccess, onError);
            },
            cancelReservation: function (userId, reservationId, onSuccess, onError) {
                var req = {
                    method: 'DELETE',
                    url: '/api/reservations/' + userId + '/cancelReservation/' + reservationId,
                };
            
                $http(req).then(onSuccess, onError);
            },
            acceptReservation: function (userId, reservationId, onSuccess, onError) {
                var req = {
                    method: 'PUT',
                    url: '/api/reservations/' + userId + '/acceptReservation/' + reservationId,
                };
               
                $http(req).then(onSuccess, onError);
            },
            getHistory: function (userId, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/getHistory/' + userId,
                };
          
                $http(req).then(onSuccess, onError);
            }
	        
	        
	        
     
        }
    });