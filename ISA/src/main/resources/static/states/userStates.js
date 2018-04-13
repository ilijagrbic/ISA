angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider.state('user', {
            url: '/user',
            views: {
                'header': {
                	 templateUrl: 'homePage/header/header.html',
                     controller: 'headerController'
                },
                'content': {
                    templateUrl: 'user/user.html',
                    controller: 'userController'
                }
            }
        });
    });