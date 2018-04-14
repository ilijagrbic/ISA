angular.module('app')
    .service('friendshipService', function ($http) {
        return {
            friends: function (id, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users/friends/' + id,
                };
                $http(req).then(onSuccess, onError);
            },
            deleteFriend: function (id, idFriend, onSuccess, onError) {
                var req = {
                    method: 'DELETE',
                    url: '/api/friendships/' + id + "/delete/" + idFriend,
                };
                console.log("Url " + req.url);
            
                $http(req).then(onSuccess, onError);
            },
            nonFriends: function (id, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users/nonFriends/' + id,
                };
                $http(req).then(onSuccess, onError);
            },
            requests: function (id, onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users/findRequests/' + id,
                };
                $http(req).then(onSuccess, onError);
            },
            accept: function (id, idFriend, onSuccess, onError) {
                var req = {
                    method: 'PUT',
                    url: '/api/friendships/' + id + "/accept/" + idFriend,
                };
                console.log("Url " + req.url);
                $http(req).then(onSuccess, onError);
            },
            send: function (id, idFriend, onSuccess, onError) {
                var req = {
                    method: 'PUT',
                    url: '/api/friendships/' + id + "/send/" + idFriend,
                };
                console.log("Url " + req.url);
                $http(req).then(onSuccess, onError);
            }
        }
    });