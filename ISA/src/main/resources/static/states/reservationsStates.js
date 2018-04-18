angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider.state('reservations', {
            url: '/reservations',
            views: {
                'header': {
                	 templateUrl: 'homePage/header/header.html',
                     controller: 'headerController'
                },
                'content': {
                    templateUrl: 'reservations/reservations.html',
                    controller: 'reservationsController'
                }
            }
        })
         .state('listOfReservations', {
                url: '/listOfReservations',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                        templateUrl: 'reservations/reservationList.html',
                        controller: 'reservationListController'
                    }
                }
            })
    });