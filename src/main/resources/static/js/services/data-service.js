angular.module('myApp').service('dataService', function (SETTINGS_SYSTEM, $http) {

    this.cliente = {
        findAll: function () {
            return $http({method: 'GET', url: SETTINGS_SYSTEM.clientesApi});
        },
        findById: function (id) {
            return $http({method: 'GET', url: SETTINGS_SYSTEM.clientesApi + "/" + id});
        },
        save: function (cliente) {
            return $http.post(SETTINGS_SYSTEM.clientesApi, cliente);
        },
        delete: function (idCliente) {
            return $http.delete(SETTINGS_SYSTEM.clientesApi + "/" + idCliente);
        }
    }
});
