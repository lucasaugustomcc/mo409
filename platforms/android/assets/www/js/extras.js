//Cronômetro
var intervalo;

function startTimer(op) {
    if (op == 1) {
		document.getElementById('parar').style.display = "block";
		document.getElementById('comeca').style.display = "none";
	}
	var s = 1;
	var m = 0;
	var h = 0;
	intervalo = window.setInterval(function() {
		if (s == 60) { m++; s = 0; }
		if (m == 60) { h++; s = 0; m = 0; }
		if (h < 10) document.getElementById("hora").innerHTML = "0" + h + "h"; else document.getElementById("hora").innerHTML = h + "h";
		if (s < 10) document.getElementById("segundo").innerHTML = "0" + s + "s"; else document.getElementById("segundo").innerHTML = s + "s";
		if (m < 10) document.getElementById("minuto").innerHTML = "0" + m + "m"; else document.getElementById("minuto").innerHTML = m + "m";		
		s++;
	},1000);
}

function stopTimer()
{
	clearInterval(intervalo);
}

//Data

function data(){
 Data_Atual = new Date();
 Data = Data_Atual.getDate();
 Dia = Data_Atual.getDay();
 Mes = Data_Atual.getMonth();
 Ano = Data_Atual.getFullYear();
 
 if(Data < 10) {
 Data = "0" + Data;
 }
 Dia_Atual = new Array(7)
 Dia_Atual[0] = "Domingo"
 Dia_Atual[1] = "Segunda-feira"
 Dia_Atual[2] = "Terça-feira"
 Dia_Atual[3] = "Quarta-feira"
 Dia_Atual[4] = "Quinta-feira"
 Dia_Atual[5] = "Sexta-feira"
 Dia_Atual[6] = "Sábado"
 
 Mes_Atual = new Array(12)
 Mes_Atual[0] = "Janeiro"
 Mes_Atual[1] = "Fevereiro"
 Mes_Atual[2] = "Março"
 Mes_Atual[3] = "Abril"
 Mes_Atual[4] = "Maio"
 Mes_Atual[5] = "Junho"
 Mes_Atual[6] = "Julho"
 Mes_Atual[7] = "Agosto"
 Mes_Atual[8] = "Setembro"
 Mes_Atual[9] = "Outubro"
 Mes_Atual[10] = "Novembro"
 Mes_Atual[11] = "Dezembro"
 
 document.write(Dia_Atual[Dia] +", "+ Data + " de " + Mes_Atual[Mes] + " de " + Ano);
}

function resourceErrorHandler(e)
{
  alert(e.data.message);
}

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

