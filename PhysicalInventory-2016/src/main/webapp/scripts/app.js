'use strict';

/**
 * @ngdoc overview
 * @name inventoryApp
 * @description
 * # inventoryApp
 *
 * Main module of the application.
 */
angular
  .module('inventoryApp', [


    //Third-Party modules
    'ui.router',
    'ui.bootstrap',

    'ui.grid',
    'ui.grid.pagination'
  ])
  .config(function ($urlRouterProvider, $stateProvider) {

    $urlRouterProvider.otherwise('/dashboard');


    $stateProvider
      .state('dashboard', {
        url: '/dashboard',
        templateUrl: 'views/dashbord.jsp',
        controller: 'DashboardCtrl'
      })
      .state('process', {
        url: '/process',
        templateUrl: 'views/process.html',
        controller: 'ProcessCtrl'
      })
      .state('obsolescence', {
        url: '/obsolescence',
        templateUrl: 'views/obsolescence.html',
        controller: 'ObsolescenceCtrl'
      })
      .state('costing', {
        url: '/costing',
        templateUrl: 'views/costing.html',
        controller: 'CostingCtrl'
      })
      .state('inventoryReport', {
          url: '/inventoryReport',
          templateUrl: 'views/inventoryReport.html',
          controller: 'InventoryReportCtrl'
        });

  })
  .run(function ($rootScope, $state){
    $rootScope.$state = $state
  });

