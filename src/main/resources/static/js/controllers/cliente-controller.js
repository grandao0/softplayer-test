angular.module('app.controllers', [])

//insert.html
    .controller('saveController', function ($scope, $location, dataService, $routeParams, $rootScope, $route, $http, SETTINGS_SYSTEM) {

        var getCliente = function () {
            return {
                id: $routeParams.idCliente | 0,
                nome: '',
                sexo: '',
                email: '',
                dataNascimento: '',
                naturalidade: '',
                nacionalidade: '',
                cpf: ''
            }
        };

        var loadCliente = function () {
            $scope.cliente = getCliente();
            if ($scope.cliente.id != 0) {
                dataService.cliente.findById($scope.cliente.id).then(function (response) {
                    $scope.cliente = response.data;
                }, function (error) {
                    if (error.status == 404) {
                        alert("Não foi possivel localizar API -> " + SETTINGS_SYSTEM.clientesApi)
                    } else {
                        alert("Erro HttpResponse[" + error.status + "]-> " + error.message)
                    }
                });
            }
        };
        $scope.saveValidation = function () {
            var retorno = {
                status: true,
                msg: []
            };
            if ($scope.cliente.dataNascimento >= new Date()) {
                retorno.status = false;
                retorno.msg.push("Data de Nascimento-> Data de Nascimento do cliente inválida!")
            }

            return retorno
        };

        $scope.save = function () {
            var valid = $scope.saveValidation();
            if (valid.status) {
                dataService.cliente.save($scope.cliente).then(function (result) {
                    if (result.data.id > 0) {
                        $location.url("/list");
                    }
                }, function (erro) {
                    if (erro.status == 404) {
                        alert("Não foi possivel localizar API -> " + SETTINGS_SYSTEM.clientesApi)
                    } else {
                        alert("Erro HttpResponse[" + erro.status + "]-> " + erro.message)
                    }
                });
            } else {
                var msgErro = "";
                valid.msg.forEach(function (erro) {
                    msgErro = msgErro + erro + '\n'
                });
                alert(msgErro)
            }
        };

        $scope.go = function (path) {
            $location.path(path);
        };

        loadCliente();
    })

    //list.html
    .controller('listController', function ($scope, $location, $http, dataService, $rootScope, SETTINGS_SYSTEM) {

        $scope.clientes = [];

        $scope.navegateToForm = function (novo, idCliente) {
            if (novo) {
                $location.url("/save");
            } else {
                $location.url("/save/" + idCliente);
            }
        };

        $scope.delete = function (idCliente) {
            dataService.cliente.delete(idCliente).then(function (response) {
                console.log("Deletado");
                loadClientes();
            }, function (error) {
                if (error.status == 404) {
                    alert("Não foi possivel localizar API -> " + SETTINGS_SYSTEM.clientesApi)
                } else {
                    alert("Erro HttpResponse[" + error.status + "]-> " + error.message)
                }
            });
        };

        var loadClientes = function () {
            dataService.cliente.findAll().then(function (response) {
                console.log(response.data);
                $scope.clientes = response.data._embedded.clientes;
            }, function (error) {
                if (error.status == 404) {
                    alert("Não foi possivel localizar API -> " + SETTINGS_SYSTEM.clientesApi)
                } else {
                    alert("Erro HttpResponse[" + error.status + "]-> " + error.message)
                }
            });
        };

        loadClientes();

        $scope.go = function (path) {
            $location.path(path);
        };
    });