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
.controller('ChamadaTurmasCtrl', function($scope, $state, $http, myService, ChamadaService) {
    // verificar se existe chamadas abertas
    ChamadaService.abertas({},{},
      function success(data) {
          if (data.length > 0)
          {
            console.log("chamadas abertas em listar turmas");
            myService.set(data);
            $state.go('professor.chamadas-abertas');
            return;
          }
          else
          {
            $scope.turmas = ChamadaService.turmas();
          }
        }, function err() {  }
    ); 

    ChamadaService.criadas({},{},
      function success(data) {
          if (data.length > 0)
          {
            myService.set(data);
            $state.go('professor.chamadas-criadas');
            return;
          }
          else
          {
            $state.go("professor.chamada-turmas");
            return;
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

    $scope.confirmCriarChamadas = function() {
      if ($scope.checkbox.length > 0)
      {        
            ChamadaService.criar({},$scope.checkbox, 
                function success(data) {
                    myService.set(data);
                    $state.go('professor.chamadas-criadas');
                }, function err() {  }
            );  
      }            
    }    
})
.controller('ChamadasCriadasCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.formData = {};
    $scope.chamadas = myService.get();
    $scope.checkbox = [];

    if ($scope.chamadas == undefined)
    {
      ChamadaService.criadas({},{},
      function success(data) {
          if (data.length > 0)
          {
            $scope.chamadas = data;
          }
          else
          {
            $state.go("professor.chamada");
            return;
          }
        }, function err() {  }
      ); 

      // verificar se existe chamadas abertas
      ChamadaService.abertas({},{},
        function success(data) {
            if (data.length > 0)
            {
              console.dir(data);
              console.log("chamadas abertas em criadas");
              myService.set(data);
              $state.go('professor.chamadas-abertas');
            }
          }, function err() {  }
      );  
    }
    for (var i = 0; i < $scope.chamadas.length; i++)
    {      
      $scope.checkbox.push({ "idChamada": $scope.chamadas[i]["idChamada"] });
    }
    
    $scope.visualizarParametros = function() {
      $scope.parametros = ChamadaService.parametros({},$scope.chamadas);         
    }
    $scope.confirmAbrirChamadas = function() {      
        // Your app must execute AT LEAST ONE call for the current position via standard Cordova geolocation,
        //  in order to prompt the user for Location permission.
        $scope.getPosition = function(position){
            $scope.coords = position.coords;
            $scope.$apply();
            ChamadaService.localizacao({},{ 'latitude': $scope.coords.latitude.toString(), 'longitude': $scope.coords.longitude.toString(), "chamadas":$scope.checkbox }, 
                function success(data) {
                    myService.set(data);
                    ChamadaService.abrir({},$scope.checkbox, 
                        function success(data) {
                            myService.set(data);
                            $state.go('professor.chamadas-abertas');
                        }, function err() {  }
                    );  
                }, function err() {  }
            );  
            
        };
        $scope.getPositionErr = function(error){
            alert('code: '    + error.code    + '\n' +
                      'message: ' + error.message + '\n');
        };
        navigator.geolocation.getCurrentPosition($scope.getPosition, $scope.getPositionErr, {maximumAge: 0, timeout: 6000, enableHighAccuracy:false});                 
    }

    $scope.confirmAlterarParametros = function() {
         ChamadaService.alterarparametros({},{'chamadas':$scope.checkbox, 'duracao':$scope.formData.duracao, 'porcentagem':$scope.formData.porcentagem}, 
            function success(data) {
                $scope.parametros = data;
            }, function err() {  }
        );                    
    }
})
.controller('ChamadasParametrosCtrl', function($scope, $state, $http, myService, ChamadaService) {
    $scope.formData = {};
    $scope.checkbox = [];
    $scope.chamadas = myService.get();
    $scope.parametros = {}

    ChamadaService.criadas({},{},
      function success(data) {
          if (data.length > 0)
          {
            $scope.chamadas = data;
            for (var i = 0; i < $scope.chamadas.length; i++)
            {      
              $scope.checkbox.push({ "idChamada": $scope.chamadas[i]["idChamada"] });
            }
            $scope.parametros = ChamadaService.parametros({},$scope.checkbox);
          }
          else
          {
            $state.go("professor.chamada-turmas");
            return;
          }
        }, function err() {  }
      );     

    $scope.confirmAlterarParametros = function() {
         ChamadaService.alterarparametros({},{'chamadas':$scope.checkbox, 'duracao':$scope.formData.duracao, 'porcentagem':$scope.formData.porcentagem}, 
            function success(data) {
                $scope.parametros = data;
            }, function err() {  }
        );                    
    }
})
.controller('ChamadasAbertasCtrl', function($scope, $state, $http, myService, ChamadaService) {
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
    * This would be your own callback for Ajax-requests after POSTing background geolocation to your server.
    */
    var yourAjaxCallback = function(response) {
        bgGeo.finish();
    };

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




