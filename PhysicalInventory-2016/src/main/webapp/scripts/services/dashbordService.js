'use strict';


angular.module('inventoryApp')
  .service('DashBordService', function ($http, $q) {
console.log("dashbord service >> ");
    return {
    	
    	fetchAllProcess: function () {
    		console.log("fetchProcess >> ");
        return $http({
          method: 'GET',
          url: 'tasks'
          //url: ''
        }).then(function successCallback(response) {
        	console.log(response);
          return response.data;
        }, function errorCallback(response) {
        	console.log("error sercice >>");
        	console.log(response);
          return $q.reject(response);
        });
      }

    	
    	/*fetchAllProcess: function() {
    		console.log("fetch process");
			return $http.get('http://localhost:8080/PhysicalInventory/tasks')
					.then(
							function(response){
								console.log("coming here ");
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							}
					);
	},*/
    };

  });
