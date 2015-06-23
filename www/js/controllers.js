function IndexController($scope, $routeParams, ChamadaService) {  
  $scope.turmas = ChamadaService.query();
};


function EditController($scope, $routeParams, $location, NewsService) {

  $scope.newsEntry = NewsService.get({id: $routeParams.id});
  
  $scope.save = function() {
    $scope.newsEntry.$save(function() {
      $location.path('/');
    });
  };
};




function CreateController($scope, $location, NewsService) {
  
  $scope.newsEntry = new NewsService();
  
  $scope.save = function() {
    $scope.newsEntry.$save(function() {
      $location.path('/');
    });
  };
};

function ChamadaTurmasCtrl($scope, $http, ChamadaService) {
    $scope.turmas = [];
    $scope.chamadas = [];
    $scope.formData = {};

    //$http.defaults.headers.get = {'X-Auth-Token':'okasd'};

      $scope.turmas = ChamadaService.turmas();

      $scope.confirmChamada = function() {
        console.log("Loaded. " + $scope.formData.turma);
        $scope.turmas = ChamadaService.abrir({},[{ "idTurma":1},{ "idTurma":2 }]);        
      }
};

angular.module('exampleApp.controllers', ['LocalStorageModule', 'exampleApp.services'])
.controller('MenuController', function ($scope, $location, MenuService) {
        // "MenuService" is a service returning mock data (services.js)
        $scope.list = MenuService.all();

        $scope.goTo = function(page) {
            console.log('Going to ' + page);
            //$scope.sideMenuController.toggleLeft();
            $location.url('/' + page);
        };
})
.controller('LogoutController', function($scope, $rootScope, $location, localStorageService, $state) {
 
  delete $rootScope.user;
  delete $rootScope.authToken;
  localStorageService.remove('authToken');
  $location.path("/app/login");
  $state.go("app.login");
})
.controller('LoginController', function($scope, $rootScope, $location, localStorageService, UserService, $state) {
  
  $scope.user = {};
  
  $scope.login = function() {
    console.log("login");
    console.dir($scope.user);
    UserService.authenticate($.param($scope.user), function(authenticationResult) {
      var authToken = authenticationResult.token;
      $rootScope.authToken = authToken;
      localStorageService.set('authToken', authToken);
      UserService.get(function(user) {
        $rootScope.user = user;
        $scope.user = {};
        if (user.roles.ROLE_PROFESSOR)
        {
          $state.go("professor.home");
        }
        if (user.roles.ROLE_ALUNO)
        {
          $state.go("aluno.home");
        }
      });
    });
  };
}) 
.controller('HomeCtrl', function($rootScope, $scope, $timeout) {
})
.controller('ChamadaTurmasCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.turmas = [];
    $scope.chamadas = [];
    $scope.formData = {};

    $scope.turmas = ChamadaService.turmas(); 

    $scope.confirmChamada = function() {
      console.dir($scope.formData);

      $scope.turmas = ChamadaService.abrir({},[{ "idTurma":1},{ "idTurma":2 }]); 
      $state.go('professor.chamada-aberta');
    }
})
.controller('ChamadaAbertaCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.formData = {};
    $scope.chamadas = myService.get();

    $scope.confirmEncerrarChamada = function() {
      console.log("Loaded. " + $scope.formData.turma);

      $scope.turmas = ChamadaService.encerrar({},[{ "idChamada":1},{ "idChamada":2 }]); 
      $state.go('professor.chamada-presenca');
    }
})
.controller('ChamadaPresencaCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.presencas = myService.get();
    console.log("Presença");
    console.dir($scope.presencas);
}])
.controller('AlunoChamadaCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.turmas = [];
    $scope.chamadas = [];
    $scope.formData = {};

    //$http.defaults.headers.get = {'X-Auth-Token':'okasd'};

    $http.get(exampleAppConfig.host+'/aluno/chamada')
        .success(function (data, status, headers, config) {
            $scope.chamada = data;
            console.dir(data);
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });

      $scope.confirmCheckIn = function() {

        $http.post(exampleAppConfig.host+'/aluno/chamada/checkin', 
          { headers: { 'Content-Type': 'application/json' },
            data: { "idChamada":1 }
          })
        .success(function (data, status, headers, config) {
            myService.set(data);
            console.dir(data);
            $state.go('aluno.checkin');
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });
      }
}])
.controller('AlunoCheckInCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.tick = myService.get();
    console.dir($scope.tick);

    $scope.confirmCheckOut = function() {

        $http.post(exampleAppConfig.host+'/aluno/chamada/checkout', 
          { headers: { 'Content-Type': 'application/json' },
            data: { "idChamada":1 }
          })
        .success(function (data, status, headers, config) {
            myService.set(data);
            console.dir(data);
            $state.go('aluno.checkout');
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });
      }
}])
.controller('AlunoCheckOutCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.tick = myService.get();
    console.dir($scope.tick);
}]);