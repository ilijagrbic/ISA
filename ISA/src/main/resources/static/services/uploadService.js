angular.module('app')
.service('uploadService', function ($http) {
    return {
        postImage: function (file, onSuccess, onError) {
        	
        	var fd = new FormData();
        	fd.append('file', file);
        	fd.append('data', 'string');
        	$http.post('api/postFile', fd, {
        	   transformRequest: angular.identity,
        	   headers: {'Content-Type': undefined}
        	}).then(onSuccess, onError);
        	
            /*var req = {
                method: 'POST',
                url: 'api/postFile',
                headers: {'Content-Type': 'multipart/form-data'},
                data: file
            };
            $http(req).then(onSuccess, onError);*/
            
        }
    }
});