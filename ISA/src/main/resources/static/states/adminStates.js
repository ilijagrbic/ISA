angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cinemasAdmin', {
                url: '/cinemasAdmin',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/cinemasAdmin.html',
                        controller: 'cinemaAdminController'
                    }
                }
            })
            .state('theatresAdmin', {
                url: '/theatresAdmin',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/theatresAdmin.html',
                        controller: 'theatresController'
                    }
                }
            })
    });