angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('defaultState', {
                url: '/defaultState',
                views: {
                    'header': {
                        templateUrl: 'header/header.html',
                        controller: 'headerController'
                    }
                }
            })
    });