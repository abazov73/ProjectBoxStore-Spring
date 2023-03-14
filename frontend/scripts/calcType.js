function calcSum(){
    fetchServer("CalcSum");
}

function calcDif(){
    fetchServer("CalcDif");
}

function calcMultiply(){
    fetchServer("CalcMultiply");
}

function calcDiv(){
    fetchServer("CalcDiv");
}

function fetchServer(adress){
    var obj1 = document.getElementById("obj1Input").value;
    var obj2 = document.getElementById("obj2Input").value;
    var type = document.getElementById("typeSelect").value;
    if (type == "int" && (isNaN(obj1) || isNaN(obj2))){
        document.getElementById("responseField").innerHTML = "Ошибка: введите число для операций с числами или измените тип данных!";
        return;
    }
    fetch("http://127.0.0.1:8080/" + adress + "?obj1=" + obj1 + "&obj2=" + obj2 + "&type=" + type)
    .then(function(response) {
        if (!response.ok){   
            return response.text().then(text => {throw new Error(text)});
        }
        return response.text();
    })
    .then((response) => {
        document.getElementById("responseField").innerHTML = "Результат: " + response;
    })
    .catch(err => {document.getElementById("responseField").innerHTML = "Ошибка: " + err;})
}