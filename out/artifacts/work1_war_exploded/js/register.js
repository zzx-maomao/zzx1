function fillprovince() {
    $.ajax({
        type:"post",
        url:"GetProvinceAndCityServlet",
        data:{},
        dataType:"json",
        success:function (response) {
            var province=document.getElementById("province");
            province.options.length=0;
            province.add(new Option("请选择省份",""));
            for (index=0;index<response.length;index++){
                province.add(new Option(response[index].getName(),response[index].getid()));
            }
        }
    })
}

$(document).ready(function () {
    fillprovince();
    $("#province").change(function (e) {
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));
        if ($(this).val()==""){
            $("#provinceerror").css("color","#c00202");
            $("#provinceerror").text("必须选择省份");
            return;
        }
        province_correct=true;
        $("#provinceerror").text("");
        var province=$("#province").val();
        $.ajax({
            type:"post",
            data:{province:province},
            dataType:"json",
            success:function (response) {
                for (index=0;index<response.length;index++){
                    var option=$("<option>").val(response[index].getId).text(response[index].getName);
                    $("#city").append(option);
                }
            }
        })
    })
})