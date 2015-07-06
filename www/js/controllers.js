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
    }, 
    function err() {  
      localStorageService.remove('authToken');
      delete $rootScope.authToken;
      console.log('erro autenticacao');
    });
  };
}) 
.controller('HomeCtrl', function($rootScope, $scope, $timeout) {
})
/*
.controller('ChamadaTurmasCtrl', function($scope, $state, $http, myService, ChamadaService) {

    // verificar se existe chamadas abertas
    ChamadaService.abertas({},{},
      function success(data) {
          console.dir(data);
            if (data.length > 0)
            {
              console.log("chamadas abertas");
              myService.set(data);
              $state.go('professor.chamada-aberta');
              return;
            }
            else
            {
              $scope.turmas = ChamadaService.turmas();
            }
        }, function err() {  }
    ); 
    
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
}) */

.controller('ChamadaTurmasCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.turmas = ChamadaService.turmas(); 
    $scope.formData = {};
    $scope.checkbox = [];
    $scope.parametros = ChamadaService.parametros();  

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

    $scope.confirmParametros = function() {
         ChamadaService.alterarparametros({},$scope.formData, 
            function success(data) {
                $scope.parametros = data;
            }, function err() {  }
        );                    
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

.controller('ChamadaPresencaCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.formData = {};
    //$scope.presencas = myService.get();
    $scope.presencas = ChamadaService.presenca();
    console.log("Presença");
    console.dir($scope.presencas);
})

// controller para exibir dados da chamada que está recenbendo ticks
/*
.controller('chamadaAberta_professorCtrl', function($scope, $state, $http, myService, ChamadaService) {
  $scope.chamadaAberta_professor = ChamadaService.aberta();
  console.log("chamadaAberta_professor");
  console.dir($scope.chamadaAberta_professor);
})
*/

.controller('disciplinasCtrl', function($scope, $state, $http, myService, ChamadaService) {
  $scope.disciplinas = ChamadaService.disciplinas();
  console.log("Disciplina");
  console.dir($scope.disciplinas);
})

.controller('alunosMatriculadosCtrl', function($scope, $state, $http, myService, ChamadaService) {
  $scope.alunos_matriculados = ChamadaService.alunos_matriculados();
  console.log("alunos_matriculados");
  console.dir($scope.alunos_matriculados);
})

.controller('frequenciaAluno_professorCtrl', function($scope, $state, $http, myService, ChamadaService) {
  $scope.frequencia_aluno = ChamadaService.frequencia_aluno();
  console.log("Frequência");
  console.dir($scope.frequencia_aluno);
})

.controller('AlunoChamadaCtrl', function($scope, $state, $http, myService, AlunoService) {    

    // checar se o aluno fez checkin
    AlunoService.presenca({},{},
      function success(data) {
          console.dir(data);
          if (data.length > 0)
          {
            myService.set(data);
            $state.go('aluno.checkin');
          }
          else
          {
            // busca turmas do aluno com chamada aberta
            AlunoService.chamada({},{},
              function success(data) {
                  $scope.chamada = data;

                  // javascript function para o button
                  $scope.confirmCheckIn = function() {
                      AlunoService.checkin({},{ "idChamada": $scope.chamada.idChamada }, 
                        function success(data) {
                          myService.set(data);
                          $state.go('aluno.checkin');
                        }
                      );      
                   }  
              }
            ); 
          }
        }, function err() {  }
    );         
})
.controller('AlunoCheckInCtrl', function($scope, $state, $http, myService, AlunoService, localStorageService) {


    $scope.tick = myService.get();
    console.dir($scope.tick);

    /**
    * This callback will be executed every time a geolocation is recorded in the background.
    */
    var callbackFn = function(location) {
        console.log('[js] BackgroundGeoLocation callback:  ' + location.latitude + ',' + location.longitude);
        

        // After you Ajax callback is complete, you MUST signal to the native code, which is running a background-thread, that you're done and it can gracefully kill that thread.
        yourAjaxCallback.call(this);
    };

    var failureFn = function(error) {
        console.log('BackgroundGeoLocation error');
    };        
    if (window.plugins != undefined)
    {
      var bgGeo = window.plugins.backgroundGeoLocation;

      // BackgroundGeoLocation is highly configurable.
      bgGeo.configure(callbackFn, failureFn, {
          url: exampleAppConfig.host+'/aluno/chamada/tick', // <-- Android ONLY:  your server url to send locations to
          params: {
              idPresenca: $scope.tick.idPresenca
          },
          headers: {
              "X-Auth-Token": localStorageService.get("authToken")
          },
          desiredAccuracy: 0,
          stationaryRadius: 50,
          distanceFilter: 50,
          notificationTitle: 'Enviando Ticks', // <-- android only, customize the title of the notification
          notificationText: 'Ativo', // <-- android only, customize the text of the notification
          activityType: 'AutomotiveNavigation',
          debug: true, // <-- enable this hear sounds for background-geolocation life-cycle.
          stopOnTerminate: false // <-- enable this to clear background location settings when the app terminates
      });
          
      bgGeo.start();
    }

    $scope.confirmCheckOut = function() {
        if (window.plugins != undefined)
        {
          var bgGeo = window.plugins.backgroundGeoLocation;
          bgGeo.stop();
        }
        AlunoService.checkout({},{ "idPresenca": $scope.tick.idPresenca }, 
          function success(data) {
            myService.set(data);
            $state.go('aluno.checkout');
          }
        );         
      }
})
.controller('AlunoCheckOutCtrl', function($scope, $state, $http, myService) {      
    $scope.formData = {};
    $scope.tick = myService.get();
    console.dir($scope.tick);
})

.controller('disciplinasAlunoCtrl', function($scope, $state, $http, myService, AlunoService) {
  $scope.disciplinasAluno = AlunoService.disciplinas_aluno();
  console.log("Disciplinas");
  console.dir($scope.disciplinasAluno);
})

.controller('frequenciaAlunoCtrl', function($scope, $state, $http, myService, AlunoService) {
  $scope.frequenciaAluno = AlunoService.frequencia();
  console.log("Frequencia");
  console.dir($scope.frequenciaAluno);
});




