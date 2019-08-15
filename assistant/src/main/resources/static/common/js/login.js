//服务器校验
function severCheck() {
    if (check()) {
        var loginName = $("#loginName").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: 'checkUser',
            data: {
                userName: loginName,
                password: password,
                verifyInput: $("#code").val(),
                tm: new Date().getTime()
            },
            dataType: 'json',
            cache: false,
            success: function (data) {
                if ("success" == data.result) {
                    saveCookie();
                    var language = $("#Language").text();
                    var lang = language == 'Chinese' ? 'en_US':'zh_CN';
                    window.location.href = "index?Lang=" + lang;
                } else if ("userError" == data.result) {
                    $("#loginName").tips({
                        side: 1,
                        msg: "用户名或密码有误",
                        bg: '#FF5080',
                        time: 15
                    });
                    showfh();
                    $("#loginName").focus();
                } else if ("codeError" == data.result) {
                    $("#code").tips({
                        side: 1,
                        msg: "验证码输入有误",
                        bg: '#FF5080',
                        time: 15
                    });
                    showfh();
                    $("#code").focus();
                } else {
                    $("#loginName").tips({
                        side: 1,
                        msg: "缺少参数",
                        bg: '#FF5080',
                        time: 15
                    });
                    showfh();
                    $("#loginName").focus();
                }
            }
        });
    }
}

$(document).ready(function () {
    changeCode1();
    $("#codeImg").bind("click", changeCode1);
    $("#zcodeImg").bind("click", changeCode2);
});

$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        $(".to-recover").trigger("click");
    }
});

function genTimestamp() {
    var time = new Date();
    return time.getTime();
}

function changeCode1() {
    $("#codeImg").attr("src", "getVerifyCode?t=" + genTimestamp());
}

function changeCode2() {
    $("#zcodeImg").attr("src", "getVerifyCode?t=" + genTimestamp());
}

//客户端校验
function check() {

    if ($("#loginName").val() == "") {
        $("#loginName").tips({
            side: 2,
            msg: '用户名不得为空',
            bg: '#AE81FF',
            time: 3
        });
        showfh();
        $("#loginName").focus();
        return false;
    } else {
        $("#loginName").val(jQuery.trim($('#loginName').val()));
    }
    if ($("#password").val() == "") {
        $("#password").tips({
            side: 2,
            msg: '密码不得为空',
            bg: '#AE81FF',
            time: 3
        });
        showfh();
        $("#password").focus();
        return false;
    }
    if ($("#code").val() == "") {
        $("#code").tips({
            side: 1,
            msg: '验证码不得为空',
            bg: '#AE81FF',
            time: 3
        });
        showfh();
        $("#code").focus();
        return false;
    }
    $("#loginbox").tips({
        side: 1,
        msg: '正在登录 , 请稍后 ...',
        bg: '#68B500',
        time: 10
    });

    return true;
}

function savePaw() {
    if (!$("#saveid").attr("checked")) {
        $.cookie('loginName', '', {
            expires: -1
        });
        $.cookie('password', '', {
            expires: -1
        });
        $("#loginName").val('');
        $("#password").val('');
    }
}

function saveCookie() {
    if ($("#saveid").attr("checked")) {
        $.cookie('loginName', $("#loginName").val(), {
            expires: 7
        });
        $.cookie('password', $("#password").val(), {
            expires: 7
        });
    }
}

jQuery(function () {
    var loginName = $.cookie('loginName');
    var password = $.cookie('password');
    if (typeof(loginName) != "undefined"
        && typeof(password) != "undefined") {
        $("#loginName").val(loginName);
        $("#password").val(password);
        $("#saveid").attr("checked", true);
        $("#code").focus();
    }
});

//登录注册页面切换
function changepage(value) {
    if (value == 1) {
        $("#windows1").hide();
        $("#windows2").show();
        changeCode2();
    } else {
        $("#windows2").hide();
        $("#windows1").show();
        changeCode1();
    }
}

//注册
function rcheck() {
    if ($("#newName").val() == "") {
        $("#newName").tips({
            side: 3,
            msg: '输入用户名',
            bg: '#AE81FF',
            time: 2
        });
        $("#newName").focus();
        $("#newName").val('');
        return false;
    } else {
        $("#newName").val(jQuery.trim($('#newName').val()));
    }
    if ($("#newPwd").val() == "") {
        $("#newPwd").tips({
            side: 3,
            msg: '输入密码',
            bg: '#AE81FF',
            time: 2
        });
        $("#newPwd").focus();
        return false;
    }
    if ($("#newPwd").val() != $("#chkpwd").val()) {
        $("#chkpwd").tips({
            side: 3,
            msg: '两次密码不相同',
            bg: '#AE81FF',
            time: 3
        });
        $("#chkpwd").focus();
        return false;
    }
    if ($("#name").val() == "") {
        $("#name").tips({
            side: 3,
            msg: '输入姓名',
            bg: '#AE81FF',
            time: 3
        });
        $("#name").focus();
        return false;
    }
    if ($("#EMAIL").val() == "") {
        $("#EMAIL").tips({
            side: 3,
            msg: '输入邮箱',
            bg: '#AE81FF',
            time: 3
        });
        $("#EMAIL").focus();
        return false;
    } else if (!ismail($("#EMAIL").val())) {
        $("#EMAIL").tips({
            side: 3,
            msg: '邮箱格式不正确',
            bg: '#AE81FF',
            time: 3
        });
        $("#EMAIL").focus();
        return false;
    }
    if ($("#rcode").val() == "") {
        $("#rcode").tips({
            side: 1,
            msg: '验证码不得为空',
            bg: '#AE81FF',
            time: 3
        });
        $("#rcode").focus();
        return false;
    }
    return true;
}

//提交注册
function register() {
    if (rcheck()) {
        $.ajax({
            type: "POST",
            url: 'registerUser',
            data: {
                userName: $("#newName").val(),
                password: $("#newPwd").val(),
                name: $("#name").val(),
                email: $("#EMAIL").val(),
                rcode: $("#rcode").val()
            },
            dataType: 'json',
            cache: false,
            success: function (data) {
                if ("success" == data.result) {
                    $("#registerBox").tips({
                        side: 1,
                        msg: '注册成功,请登录',
                        bg: '#68B500',
                        time: 10
                    });
                    $("#windows2").hide();
                    $("#windows1").show();
                    changeCode1();
                } else if ("existUser" == data.result) {
                    $("#newName").tips({
                        side: 1,
                        msg: "用户名已存在",
                        bg: '#FF5080',
                        time: 15
                    });
                    showfh();
                    $("#newName").focus();
                } else if ("codeError" == data.result) {
                    $("#rcode").tips({
                        side: 1,
                        msg: "验证码输入有误",
                        bg: '#FF5080',
                        time: 15
                    });
                    showfh();
                    $("#rcode").focus();
                }
            }
        });
    }
}

//邮箱格式校验
function ismail(mail) {
    return (new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
}