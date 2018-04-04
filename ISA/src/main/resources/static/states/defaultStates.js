angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('defaultState', {
                url: '/defaultState',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: ''
                    }
                }
            })
    });