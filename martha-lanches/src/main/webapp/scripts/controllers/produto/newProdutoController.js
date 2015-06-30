
angular.module('marthalanches').controller('NewProdutoController', function ($scope, $location, locationParser, ProdutoResource, Utils) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produto = $scope.produto || {};
    $scope.icones = Utils.getListaIcones();

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Produtos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProdutoResource.save($scope.produto, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produtos");
    };

    $scope.selecionarIcone = function(modalIcones, iconeSelecionado) {
    	Utils.selecionarIcone(angular.element(modalIcones), iconeSelecionado, $scope);
	};
    
});