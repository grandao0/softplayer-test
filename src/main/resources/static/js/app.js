var app = angular.module('myApp', [
    'ngRoute',
    'app.controllers'
]);
app.config(function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider
        .when("/save", {
            templateUrl: "./views/insert.html",
            controller: 'saveController'
        })
        .when("/save/:idCiente", {
            templateUrl: "./views/insert.html",
            controller: 'saveController'
        })
        .when("/list", {
            templateUrl: "./views/list.html",
            controller: 'listController'
        })
        .when("/", {
            templateUrl: "./views/list.html",
            controller: 'listController'
        })
        .otherwise({redirectTo: '/'});
});

app.run(['$rootScope', function ($rootScope) {

}]);

app.filter('booleano', function () {
    return function (input) {
        return input ? "Sim" : "Não"
    }
});

app.directive("mwConfirmClick", [
    function () {
        return {
            priority: -1,
            restrict: 'A',
            scope: {confirmFunction: "&mwConfirmClick"},
            link: function (scope, element, attrs) {
                element.bind('click', function (e) {
                    // message defaults to "Are you sure?"
                    var message = attrs.mwConfirmClickMessage ? attrs.mwConfirmClickMessage : "Are you sure?";
                    // confirm() requires jQuery
                    if (confirm(message)) {
                        scope.confirmFunction();
                    }
                });
            }
        }
    }
]);

app.constant('SETTINGS_SYSTEM', {
    clientesApi: 'http://localhost:8080/api/softplan/v1/clientes'
});
