/**
 * Created by pooja on 6/11/16.
 */







function setSquare(div, value) {
    if (value.length==0) {
        div.setAttribute("contentEditable", "true");
        div.className="editable";
        div.innerHTML="";
        div.addEventListener("click",onClick);
        div.addEventListener("keypress",onKeypressDiv);
    }
    
    
        
    else {

        div.innerHTML = value;
        div.setAttribute("contentEditable", "false");
        div.className = "square";
        div.removeEventListener("click",onClick);
        div.removeEventListener("keypress",onKeypressDiv);
    }
}

function setSolutionSquare(div, value){
    if(div.hasAttribute("contentEditable" )&& div.getAttribute("contentEditable")=="true"){
        div.setAttribute("contentEditable", "true");
        div.className="editable";
        div.innerHTML=value;
        div.addEventListener("click",onClick);
        div.addEventListener("keypress",onKeypressDiv);
    }   
}

function setIncorrect(div,value){
    if(div.hasAttribute("contentEditable" )&& div.getAttribute("contentEditable")=="true")
        if(div.innerHTML!=value)
            div.className ="editablehighlight";
        else 
            div.className = "editable";
}


function set(document, game, funcName) {

    
    for (var i=0; i<game.game.length; i++) {
        if(funcName=="setSquare")
            setSquare(document.getElementById(game.game[i][0]), game.game[i][1]);
        else if(funcName=="setSolutionSquare")
            setSolutionSquare(document.getElementById(game.game[i][0]), game.game[i][1]);
        else if(funcName == "setIncorrect")
            setIncorrect(document.getElementById(game.game[i][0]), game.game[i][1]);

    }
}

function onClick()
{
    this.focus();
}

function onKeypressDiv(obj)
{
    var key = event.keyCode;
    if(!(key >= 49 && key <= 57)||(this.innerHTML.length>0))
    {
 //       window.alert(String.fromCharCode(key));
        event.preventDefault();
    }

    else
    {

        var stringID = this.id;
        var row = parseInt(stringID);
        var column = stringID.substring(2);
        column = parseInt(column);
       // window.alert((row-1)*9+column-1);
        obj["game"][(row-1)*9+column-1][1]=String.fromCharCode(key);
       // window.alert( obj.game[(row-1)*9+column-1][1]);
    }

}

/*
function addEventListeners()
{
    var x = document.getElementsByClassName("editable");

    for(var i = 0; i <x.length; i++)
    {
            x[i].addEventListener("click",onClick);

    }
}

    */

/*
game: [[1,2,3,4,5,6,7,8, 9],
 [1,2,3,4,5,6,7,8, 9],
 [1,2,3,4,5,6,7,8, 9]]


 */