angular.module('ionic-http-auth.controllers', [])
.controller('AppCtrl', function($scope, $state, $ionicModal) {
   
  $ionicModal.fromTemplateUrl('templates/login.html', function(modal) {
      $scope.loginModal = modal;
    },
    {
      scope: $scope,
      animation: 'slide-in-up',
      focusFirstInput: true
    }
  );
  //Be sure to cleanup the modal by removing it from the DOM
  $scope.$on('$destroy', function() {
    $scope.loginModal.remove();
  });

})
.controller('ListarTurmasCtrl', function($scope, $state, $http) {
    $scope.turmas =[];
    localStorageService.set('authorizationToken','asdasdarewrwfsfsdfsdfdsf');
    
    var req = {
     method: 'GET',
     url: 'http://www.webulando.com.br/mo409/professor/chamada/turmas',
     headers: {
       'X-Auth-Token': localStorageService.get('authorizationToken'),
       'Content-Type': 'application/json'
     }
    }

    $http(req)
        .success(function (data, status, headers, config) {
            $scope.turmas = data;
            console.log(data);
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });
})  
.controller('LoginCtrl', function($scope, $http, $state, AuthenticationService, localStorageService) {
  $scope.message = "";
  
  $scope.user = {
    username: null,
    password: null
  };
 
  $scope.login = function() {
    AuthenticationService.login($scope.user);
  };

  $scope.$on('event:auth-loginRequired', function(e, rejection) {
    console.log('handling login required');
    $scope.loginModal.show();
  });
 
  $scope.$on('event:auth-loginConfirmed', function() {
  	   $scope.username = null;
  	   $scope.password = null;
       $scope.loginModal.hide();
       // console.log('ok');
       // $scope.usuario = localStorageService.get('usuario');
       // if ($scope.usuario.papel == "ROLE_ALUNO")
       //    $state.go('aluno.home');
       // else if ($scope.usuario.papel == "ROLE_PROFESSOR")
       //    $state.go('app.home');
  });
  
  $scope.$on('event:auth-login-failed', function(e, status) {
    var error = "Login failed.";
    if (status == 401) {
      error = "Invalid Username or Password.";
    }
    $scope.message = error;
  });
 
  $scope.$on('event:auth-logout-complete', function() {
    console.log("logout complete");
    $state.go('app.home', {}, {reload: true, inherit: false});
  });    	
})
.controller('HomeCtrl', function($rootScope, $scope, $timeout) {
})
.controller('ChamadaTurmasCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.turmas = [];
    $scope.chamadas = [];
    $scope.formData = {};

    //$http.defaults.headers.get = {'X-Auth-Token':'okasd'};

    $http.get('http://www.webulando.com.br/mo409/professor/chamada/turmas')
        .success(function (data, status, headers, config) {
            $scope.turmas = data;
            console.log("Loaded. " + data);
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });

      $scope.confirmChamada = function() {
        console.log("Loaded. " + $scope.formData.turma);

        $http.post('http://www.webulando.com.br/mo409/professor/chamada/abrir', 
          { headers: { 'Content-Type': 'application/json' },
            data: [{ "idTurma":1},{ "idTurma":2 }]
          })
        .success(function (data, status, headers, config) {
            myService.set(data);
            console.dir(data);
            $state.go('professor.chamada-aberta');
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });
      }
}])
.controller('ChamadaAbertaCtrl', ['$scope', '$state', '$http', 'myService', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.chamadas = myService.get();

    $scope.confirmEncerrarChamada = function() {
      console.log("Loaded. " + $scope.formData.turma);

      $http.post('http://www.webulando.com.br/mo409/professor/chamada/encerrar', 
        { headers: { 'Content-Type': 'application/json' },
          data: [{ "idChamada":1},{ "idChamada":2 }]
        })
      .success(function (data, status, headers, config) {
          myService.set(data);
          console.dir(data);
          $state.go('professor.chamada-presenca');
      })
      .error(function (data, status, headers, config) {
          console.log("Error occurred.  Status:" + status);
      });
    }
}])
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

    $http.get('http://www.webulando.com.br/mo409/aluno/chamada')
        .success(function (data, status, headers, config) {
            $scope.chamada = data;
            console.dir(data);
        })
        .error(function (data, status, headers, config) {
            console.log("Error occurred.  Status:" + status);
        });

      $scope.confirmCheckIn = function() {

        $http.post('http://www.webulando.com.br/mo409/aluno/chamada/checkin', 
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

        $http.post('http://www.webulando.com.br/mo409/aluno/chamada/checkout', 
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
}])
.controller('LogoutCtrl', function($scope, AuthenticationService) {
    $scope.$on('$ionicView.enter', function() {
      AuthenticationService.logout();
    });
});