(function() {

	var app = angular.module("cepApp", []);

	app.config(function($locationProvider) {
		$locationProvider.html5Mode(true);
	});

	app.controller("listaController", function($http) {

		var searchText = "";
		var errorMessage = "";
		var thisController = this;
		thisController.registros = [];

		this.search = function() {

			var termo = thisController.searchText == undefined ? ""
					: thisController.searchText;

			$http.get("/cep/rest/endereco?cep=" + termo).success(
					function(data) {
						thisController.registros = data;
					}).error(function(data, status, headers, config) {
				thisController.errorMessage = "status " + status;
			});

		};

		
		this.search();

	});

	app.controller("cadastroController", function($http, $location, $window) {

		var paramValue = $location.search()['cep'];
		var registros = [];
		var cep = "";
		var logradouro = "";
		var cidade = "";
		var estado = "";
		var bairro = "";
		var id = "";

		var thisController = this;

		this.search = function(paramValue) {

			$http.get("/cep/rest/endereco?cep=" + paramValue).success(
					function(data) {

						thisController.cep = data[0].cep;
						thisController.logradouro = data[0].logradouro;
						thisController.cidade = data[0].cidade;
						thisController.estado = data[0].estado;
						thisController.bairro = data[0].bairro;
						thisController.id = data[0].id;

					}).error(function(data, status, headers, config) {
				thisController.errorMessage = "status " + status;
			});

		};
		this.salvar = function() {
			var json = {
				id : thisController.id,
				cep : thisController.cep,
				logradouro : thisController.logradouro,
				cidade : thisController.cidade,
				estado : thisController.estado,
				bairro : thisController.bairro
			};

			if (thisController.id != null && thisController.id != undefined) {
				$http.put("/cep/rest/endereco", json).success(function(data) {
					alert('Alteração com Sucesso');	$window.location.href="/cep/cadastracep.html";
				}).error(function(data, status, headers, config) {
					alert('Alteração não Efetuada');
				});
			} else {
				$http.post("/cep/rest/endereco", json).success(function(data) {
					alert('Inclusão com Sucesso');	$window.location.href="/cep/cadastracep.html";
				}).error(function(data, status, headers, config) {
					alert('Inclusão não efetuada');
				});
			}
		};
		
		this.deletar = function() {
			$http.delete("/cep/rest/endereco/?id=" +  thisController.id)
				.success(function(data) {
					alert('Exclusão com Sucesso');$window.location.href="/cep/cadastracep.html";
				}).error(function(data, status, headers, config) {
					alert('Não efetuar a Exclusão');
			});

		};


		if (paramValue != null && paramValue != undefined) {
			this.search(paramValue);
		}

	});
	
	this.cancelar = function() {
		$window.location.href="/cep/cadastracep.html";
	};

})();