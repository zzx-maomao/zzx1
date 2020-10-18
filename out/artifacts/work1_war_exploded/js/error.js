window.setInterval(changetime,1000);
function changetime() {
    var time=parseInt(document.getElementById("leaveTime").innerText);
    time=time-1;
    if (time==0){
        window.location.href="DengLu.html";
    }else{
        document.getElementById("leaveTime").innerText=time;
    }
}