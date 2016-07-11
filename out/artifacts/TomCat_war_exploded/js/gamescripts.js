/**
 * Created by pooja on 6/11/16.
 */







function setSquare(div, value) {
    if (value.length==0) {
        div.setAttribute("contentEditable", "true");
    }
    else {

        div.innerHTML = value;
        div.setAttribute("contentEditable", "false");
    }
}

function setGame(document, game) {
   

    var obj = game;
    for (var i=0; i<obj.game.length; i++) {
        setSquare(document.getElementById(obj.game[i][0]), obj.game[i][1]);
    }
}
/*
$(".checkBtn").click(function(){

    for(var i = 1; i <10; i++)
        for(var j = 1; j <10; j++)
        {
            var num = document.getElementById(i+"-"+j).innerTML;
            if(!(typeof num == "number")||num<1||num>9)
                document.getElementById(i+"-"+j).innerTML="NO";
            else

        }

});
    */