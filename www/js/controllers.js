var getIndexIfObjWithAttr = function(array, attr, value) {
    for(var i = 0; i < array.length; i++) {
        if(array[i][attr] === value) {
            return i;
        }
    }
    return -1;
}
var onSuccess = function(position) {
    alert('Latitude: '          + position.coords.latitude          + '\n' +
          'Longitude: '         + position.coords.longitude         + '\n' +
          'Altitude: '          + position.coords.altitude          + '\n' +
          'Accuracy: '          + position.coords.accuracy          + '\n' +
          'Altitude Accuracy: ' + position.coords.altitudeAccuracy  + '\n' +
          'Heading: '           + position.coords.heading           + '\n' +
          'Speed: '             + position.coords.speed             + '\n' +
          'Timestamp: '         + position.timestamp                + '\n');
};

// onError Callback receives a PositionError object
//
function onError(error) {
    alert('code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');
}

angular.module('exampleApp.controllers', ['LocalStorageModule', 'exampleApp.services'])
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
    $scope.turmas = ChamadaService.turmas(); 

    $scope.checkbox = [];

    $scope.appendList = function(id){
      var index = getIndexIfObjWithAttr($scope.checkbox, "idTurma", id);
      if (index == -1) {
          $scope.checkbox.push({ "idTurma": id });
      } else {
          $scope.checkbox.splice(index, 1);
      }
    }

    $scope.confirmChamada = function() {
      if ($scope.checkbox.length > 0)
      {
        // Your app must execute AT LEAST ONE call for the current position via standard Cordova geolocation,
        //  in order to prompt the user for Location permission.
        $scope.getPosition = function(position){
            $scope.coords = position.coords;
            $scope.$apply();
            ChamadaService.abrir({},{ 'latitude': $scope.coords.latitude.toString(), 'longitude': $scope.coords.longitude.toString(), "turmas":$scope.checkbox }, 
                function success(data) {
                    myService.set(data);
                    $state.go('professor.chamada-aberta');
                }, function err() {  }
            );  
        };
        $scope.getPositionErr = function(error){
            alert('code: '    + error.code    + '\n' +
                      'message: ' + error.message + '\n');
        };
        navigator.geolocation.getCurrentPosition($scope.getPosition, $scope.getPositionErr, {maximumAge: 0, timeout: 6000, enableHighAccuracy:false});        
      }           
    }
})
.controller('ChamadaAbertaCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.chamadas = myService.get();
    $scope.checkbox = [];
    for (var i = 0; i < $scope.chamadas.length; i++)
    {      
      $scope.checkbox.push({ "idChamada": $scope.chamadas[i]["idChamada"] });
    }

    $scope.confirmEncerrarChamada = function() {
      $scope.turmas = ChamadaService.encerrar({},$scope.checkbox, 
        function success(data) {
          myService.set(data);
          $state.go('professor.chamada-presenca');
        });       
    }
})
.controller('ChamadaPresencaCtrl', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.presencas = myService.get();
    console.log("Presença");
    console.dir($scope.presencas);
})
.controller('AlunoChamadaCtrl', function($scope, $state, $http, myService, AlunoService) {
    $scope.chamada = {};

    //$http.defaults.headers.get = {'X-Auth-Token':'okasd'};
    AlunoService.chamada({},{},
        function success(data) {
            $scope.chamada = data;
            $scope.confirmCheckIn = function() {
                AlunoService.checkin({},{ "idChamada": $scope.chamada.idChamada }, 
                  function success(data) {
                    myService.set(data);
                    $state.go('aluno.checkin');
                  }
                );      
             }  
        }); 

                
})
.controller('AlunoCheckInCtrl', function($scope, $state, $http, myService) {
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
})
.controller('AlunoCheckOutCtrl', function($scope, $state, $http, myService) {
    $scope.formData = {};
    $scope.tick = myService.get();
    console.dir($scope.tick);
});