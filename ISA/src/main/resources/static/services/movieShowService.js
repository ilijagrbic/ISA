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
	    }
    }
});