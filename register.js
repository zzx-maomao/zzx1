
function fillProvince() {
    $.ajax({
        type: "post",
        url: "GetProvinceAndCityServlet",
        data: {},
        dataType: "json",
        success: function(response) {
            var province = document.getElementById("province");
            //清除select的所有option
            province.options.length = 0;
            //增加一个选项
            province.add(new Option("请选择省份", ""));
            //循环增加其他所有选项
            for (index = 0; index < response.length; index++) {
                province.add(new Option(response[index].name,
                    response[index].name));
            }
        }
    });
}

var usename_correct = false;
var name_correct = false;
var email_correct = false;
var province_correct = false;
var city_correct = false;
var password_correct = false;
var password1_correct = false;

$(document).ready(function() {
    fillProvince(); //调用函数，填充省份下拉框

    /**
     * 省份下拉框选择发生改变事件：
     * 清空城市下拉框选项，增加默认提示项
     * 检查是否选择了省份，没有选择则给出错误提示并返回
     * 否则，清除错误提示信息，查询被选择省份对应的城市信息，增加到城市下拉框的选择列表中
     */
    $("#province").change(function(e) {
        if ($(this).val() == "") {
            $("#provinceerr").css("color", " #c00202");
            $("#provinceerr").text("必须选择省份！");
            return;
        }
        province_correct = true;
        $("#provinceerr").text("");
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));

        var province = $("#province").val();
        $.ajax({
            type: "post",
            url: "GetProvinceAndCityServlet",
            data: { province: province },
            dataType: "json",
            success: function(response) {
                for (index = 0; index < response.length; index++) {
                    var option = $("<option>").val(response[index].name)
                        .text(response[index].name);
                    $("#city").append(option);
                }
            }
        });
    });

    $("#province").blur(function(e) {
        if ($(this).val() == "") {
            $("#provinceerr").css("color", " #c00202");
            $("#provinceerr").text("必须选择城市！");
        } else {
            $("#provinceerr").text("");
            province_correct = true;
        }
    });


      //城市下拉框选择项变化事件：检查是否选择了城市

    $("#city").blur(function(e) {
        if ($(this).val() == "") {
            $("#cityerr").css("color", " #c00202");
            $("#cityerr").text("必须选择城市！");
        } else {
            $("#cityerr").text("");
            city_correct = true;
        }
    });

    //用户名输入框离开事件
    $('#usename').blur(function(event) {
        if ($(this).val() == "") {
            $("#usenameerr").css("color", " #c00202");
            $("#usenameerr").text("用户名不能为空");
            return;
        }
        else usename_correct=true;

        $.ajax({
            type: "post",
            url: "CheckExistServlet",
            data: { usename: $(this).val() },
            dataType: "json",
            success: function(response) {
                //var json=JSON.parse(response);
                if (response.code == 0) {
                    $("#usenameerr").css("color", "green");
                    $("#usenameerr").text(response.info);
                    usename_correct = true;
                } else {
                    $("#usenameerr").css("color", "#c00202");
                    $("#usenameerr").text(response.info);
                }
            }
        });
    });
    //真实姓名输入框离开事件

    $('#name').blur(function(event) {
        if ($(this).val() == "") {
            $("#nameerr").css("color", " #c00202");
            $("#nameerr").text("真实姓名不能为空");
            return;
        }else{name_correct = true;
            $("#nameerr").text("");}



    });
    // 邮箱地址输入框离开事件

    $("#email").blur(function(event) {
        if ($(this).val() == "") {
            $("#emailerr").css("color", " #c00202");
            $("#emailerr").text("邮箱不能为空");
            return;
        }
        else  email_correct=true;



    });

    //密码输入框离开事件：
    $("#password").blur(function() {
        var password_min_length = 3
        if ($("#password").val().length >= password_min_length) {
            $("#passsworderr").css("color", "green");
            $("#passsworderr").text("密码设置成功");
            password_correct = true;
        } else {
            $("#passsworderr").css("color", "#c00202");
            $("#passsworderr").text("密码长度至少为3");
        }
    });

    //确认密码离开事件
    $("#password1").blur(function() {
        var password_min_length = 3;
        if ($("#password").val() == $("#password1").val() && $("#password").val().length >= password_min_length) {
            $("#password1err").css("color", "green");
            $("#password1err").text("密码设置成功");
            password1_correct = true;
        } else {
            $("#password1err").css("color", "#c00202");
            $("#password1err").text("密码不一致或长度不够");

        }
    });

    /**
     * 注册按钮点击事件
     */
    $("#button").click(function(e) {
        if (usename_correct && name_correct && email_correct && province_correct && city_correct && password_correct && password1_correct) {
            $.ajax({
                type: "post",
                url: "RegisterServlet",
                data: $("#registerform").serialize(), //将表单内容序列化成一个URL 编码字符串
                dataType: "json",
                success: function(response) {
                    if (response.code==1) {
                        window.location.href = "DengLu.html";
                    }
                }
            });
        }
        else {
            $("#usename").blur();
            $('#name').blur();
            $("#email").blur();
            $("#password").blur();
            $("#password1").blur();
            $("#province").blur();
            $("#city").blur();
        }
    });
});