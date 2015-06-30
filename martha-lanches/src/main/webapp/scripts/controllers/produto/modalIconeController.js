angular.module('marthalanches').controller('ModalIconeController', function ($scope, $modalInstance, icones) {
	$scope.icones = icones;
	
	$scope.selected = {
			icone: $scope.icones[0]
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});