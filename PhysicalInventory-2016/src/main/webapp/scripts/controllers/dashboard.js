'use strict';

/**
 * @ngdoc function
 * @name inventoryApp.controller:MainCtrl
 * @description # MainCtrl Controller of the inventoryApp
 */
angular.module('inventoryApp').controller('DashboardCtrl',
		[ '$scope', 'DashBordService', function($scope, DashBordService) {
			console.log("dashbord controller");
			var self = this;
			self.user = {
				pid : null,
				processName : '',
				status : ''
			};
			self.users = [];
			self.fetchAllProcess = function() {
				DashBordService.fetchAllProcess().then(function(d) {
					console.log("calling fetch service >> ");
					self.users = d;
					$scope.varcosting = "Costing";
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			$scope.idSelectedUser = null;
			 $scope.setSelected = function(idSelectedUser) {
			       $scope.idSelectedUser = idSelectedUser;
			       console.log(idSelectedUser);
			    };
			 self.fetchAllProcess();
		} ]);
