function changeImg() {
    document.getElementById("vcodeImg").src="CreatVcodeImageServlet?t="+Math.random();
}
var xmlHttp;
function getxmlHttp() {
    if (window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }else
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
}


function ajaxchecklogin() {
    var userName=document.getElementById("usename").value;
    var password=document.getElementById("password").value;
    var vcode=document.getElementById("vcode").value;
    var loginradio=document.getElementById("loginradio").value;
    getxmlHttp();
    xmlHttp.open("post","AjaxLoginServlet",true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.send("usename="+ userName +"&password="+ password +"&vcode="+ vcode +"&loginradio="+ loginradio);
    xmlHttp.onreadystatechange=function () {
        if (xmlHttp.readyState==4&&xmlHttp.status==200){
            var response=xmlHttp.responseText;
            var json=JSON.parse(response);
            if (json.code==0){
                window.location.herf="main.jsp";
            }else
                document.getElementById("error").innerText=json.info;
        }
    }
}