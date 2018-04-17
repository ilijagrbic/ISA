angular.module('app')
.service('movieShowService', function ($http) {
    return {
        getMovieShow: function (cinemaId, movieId, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/movies/'+movieId
            };
            $http(req).then(onSuccess, onError);
        },
        getMoviesShow: function (idCinnemas, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/cinnemas/' + idCinnemas + '/movies'
            };
            $http(req).then(onSuccess, onError);
        },
	    postMovieShow: function (cinemaId, data, onSuccess, onError) {
	        var req = {
	            method: 'POST',
	            url: '/api/cinnemas/'+cinemaId+'/movies/',
	            headers: {
                    'Content-Type': 'application/json'
                },
                data: data
	        };
	        $http(req).then(onSuccess, onError);
	    },
	    putMovieShow: function (cinemaId, movieId, data, onSuccess, onError) {
	        var req = {
	            method: 'PUT',
	            url: '/api/movies/'+movieId,
	            headers: {
                    'Content-Type': 'application/json'
                },
                data: data
	        };
	        $http(req).then(onSuccess, onError);
	    },
	    deleteMovieShow: function (movieId, onSuccess, onError) {
	        var req = {
	            method: 'DELETE',
	            url: '/api/movies/'+movieId,
	            headers: {
                    'Content-Type': 'application/json'
                }
	        };
	        $http(req).then(onSuccess, onError);
	    }
    }
});