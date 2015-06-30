angular.module('marthalanches').factory('ProdutoResource', function($resource) {
    var resource = $resource('rest/produtos/:ProdutoId',{ProdutoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
}).factory('Utils', function(){
	return {
		selecionarIcone: function (modalIcones, icone, $scope) {
			$scope.produto.icone = icone.nomeIcone;
			$(modalIcones).modal('hide');
		},
		getListaIcones: function () {
			return [
					  {nomeIcone:'asterisk', calsse:'glyphicon glyphicon-asterisk'}, 
					  {nomeIcone:'plus', calsse:'glyphicon glyphicon-plus'},
					  {nomeIcone:'euro', calsse:'glyphicon glyphicon-euro'},
					  {nomeIcone:'minus', calsse:'glyphicon glyphicon-minus'},
					  {nomeIcone:'cloud', calsse:'glyphicon glyphicon-cloud'},
					  {nomeIcone:'envelope', calsse:'glyphicon glyphicon-envelope'},
					  {nomeIcone:'pencil', calsse:'glyphicon glyphicon-pencil'},
					  {nomeIcone:'glass', calsse:'glyphicon glyphicon-glass'},
					  {nomeIcone:'music', calsse:'glyphicon glyphicon-music'},
					  {nomeIcone:'search', calsse:'glyphicon glyphicon-search'},
					  {nomeIcone:'heart', calsse:'glyphicon glyphicon-heart'},
					  {nomeIcone:'star', calsse:'glyphicon glyphicon-star'},
					  {nomeIcone:'star-empty', calsse:'glyphicon glyphicon-star-empty'},
					  {nomeIcone:'user', calsse:'glyphicon glyphicon-user'},
					  {nomeIcone:'film', calsse:'glyphicon glyphicon-film'},
					  {nomeIcone:'th-large', calsse:'glyphicon glyphicon-th-large'},
					  {nomeIcone:'th', calsse:'glyphicon glyphicon-th'},
					  {nomeIcone:'th-list', calsse:'glyphicon glyphicon-th-list'},
					  {nomeIcone:'ok', calsse:'glyphicon glyphicon-ok'},
					  {nomeIcone:'remove', calsse:'glyphicon glyphicon-remove'},
					  {nomeIcone:'zoom-in', calsse:'glyphicon glyphicon-zoom-in'},
					  {nomeIcone:'zoom-out', calsse:'glyphicon glyphicon-zoom-out'},
					  {nomeIcone:'off', calsse:'glyphicon glyphicon-off'},
					  {nomeIcone:'signal', calsse:'glyphicon glyphicon-signal'},
					  {nomeIcone:'cog', calsse:'glyphicon glyphicon-cog'},
					  {nomeIcone:'trash', calsse:'glyphicon glyphicon-trash'},
					  {nomeIcone:'home', calsse:'glyphicon glyphicon-home'},
					  {nomeIcone:'file', calsse:'glyphicon glyphicon-file'}
				  ];
		}
	};
});