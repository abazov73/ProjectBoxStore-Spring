function calcSecond(){
    var num = document.getElementById("numberInput").value;
    fetch("http://127.0.0.1:8080/second?num=" + num)
    .then(function(response) {
        if (response.status != 200){   
            return response.text().then(text => {throw new Error(text)});
        }
        return response.text();
    })
    .then((response) => {
        document.getElementById("responseField").innerHTML = "Результат: " + response;
    })
    .catch(err => {document.getElementById("responseField").innerHTML = "Ошибка: " + err;})
}

function calcRoot(){
    var num = document.getElementById("numberInput").value;
    if (num < 0) {
        document.getElementById("responseField").innerHTML = "Результат: введите НЕОТРИЦАТЕЛЬНОЕ число";
        return;
    }
    fetch("http://127.0.0.1:8080/root?num=" + num)
    .then((response) => {
        if (response.status != 200){
            return response.text().then(text => {throw new Error(text)});
        }
        return response.text();
    })
    .then((response) => {
        console.log(response);
        document.getElementById("responseField").innerHTML = "Результат: " + response;
    })
    .catch(err => {document.getElementById("responseField").innerHTML = "Ошибка: " + err;})
}

function calcFact(){
    var num = document.getElementById("numberInput").value;
    if (num < 0) {
        document.getElementById("responseField").innerHTML = "Результат: введите НЕОТРИЦАТЕЛЬНОЕ число";
        return;
    }
    fetch("http://127.0.0.1:8080/fact?num=" + num)
    .then((response) => {
        if (response.status != 200){
            return response.text().then(text => {throw new Error(text)});
        }
        return response.text();
    })
    .then((response) => {
        console.log(response);
        document.getElementById("responseField").innerHTML = "Результат: " + response;
    })
    .catch(err => {document.getElementById("responseField").innerHTML = "Ошибка: " + err;})
}

function calcDigit(){
    var num = document.getElementById("numberInput").value;
    fetch("http://127.0.0.1:8080/digit?num=" + num)
    .then((response) => {
        if (response.status != 200){
            return response.text().then(text => {throw new Error(text)});
        }
        return response.text();
    })
    .then((response) => {
        console.log(response);
        document.getElementById("responseField").innerHTML = "Результат: " + response;
    })
    .catch(err => {document.getElementById("responseField").innerHTML = "Ошибка: " + err;})
}