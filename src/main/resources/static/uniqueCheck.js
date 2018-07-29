function validation() {
    var firstNumber = Number(document.getElementById('firstNumber').value);
    var secondNumber = Number(document.getElementById('secondNumber').value);
    var thirdNumber = Number(document.getElementById('thirdNumber').value);
    var fourthNumber = Number(document.getElementById('fourthNumber').value);
    var fifthNumber = Number(document.getElementById('fourthNumber').value);

    var arrayValues = [firstNumber,secondNumber,thirdNumber,fourthNumber,fifthNumber];

    // for (var i = 0; i <arrayValues ; i++) {
    //     for (var j = 0; j <arrayValues-1-i; j++) {
    //         if(arrayValues[i]===arrayValues[j]){
    //             alert("Numbers must be unique");
    //             return false;
    //         }
    //
    //     }
    // }

    if(fifthNumber ==15){
        alert("Numbers must be unique");
        return false;
    }
    else {
        return true
    }
}
