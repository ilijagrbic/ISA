angular.module('app')
.service('movieShowService', function ($http) {
    return {
        getMovieShow: function (cinemaId, movieId, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/cinnemas/'+cinemaId+'/movies'+movieId
            };
            $http(req).then(onSuccess, onError);
        }
    }
});