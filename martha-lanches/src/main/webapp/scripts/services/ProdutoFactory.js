angular.module('marthalanches').factory('ProdutoResource', function($resource){
    var resource = $resource('rest/produtos/:ProdutoId',{ProdutoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
}).factory('Utils', function(){
	return {
		selecionarIcone: function (element, $scope) {
			var result = $(element).find('.icone-food').text();
			$scope.produto.icone = result;
			$('#modalIconesProduto').modal('hide');
		}
	};
});