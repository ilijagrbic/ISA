angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('friendship', {
                url: '/friendship',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                        templateUrl: 'friendship/friendship.html',
                        controller: 'friendshipController'
                    }
                }
            });
    });