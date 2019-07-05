$(document).ready(function () {
    var pageObj = {};
    var url = decodeURI(location.search); //获取url中"?"符后的字串 ('?modFlag=business&role=1')
    if (url.indexOf("?") != -1) {
        var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            pageObj[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    var lang = pageObj.Lang;
    if (lang == 'en_US') {
        $("#Language").text('Chinese');
    } else {
        $("#Language").text('English');
    }
});

function switchLanguage() {
    var language = $("#Language").text();
    var lang = language == 'English' ? 'en_US' : 'zh_CN';
    var urlVal = window.location.search;
    if (urlVal.indexOf("?") != -1) {
        if (urlVal.indexOf("Lang") != -1) {
            window.location.href = urlVal.slice(0, urlVal.lastIndexOf("Lang")) + "Lang=" + lang
        } else {
            window.location.href = urlVal + "&Lang=" + lang
        }
    } else {
        window.location.href = urlVal + '?Lang=' + lang;
    }
}

function linkOtherPage(url) {
    window.location.href = url
}


