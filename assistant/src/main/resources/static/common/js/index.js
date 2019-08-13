$(document).ready(function (e) {
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


    //菜单添加样式
    $(".menu-tabbable").find("li").each(function () {
        var a = $(this).find("a:first")[0];
        var activeUrl = location.pathname.lastIndexOf('/') > 0 ?
            location.pathname.slice(0, location.pathname.lastIndexOf('/')) : location.pathname
        if (activeUrl.includes($(a).attr("href"))) {
            $(this).addClass("active");
        } else {
            $(this).removeClass("active");
        }
    });

    // 搜索
    $("#js-search").click(function () {
        // $("#search-main").fadeToggle(300);
    });

    // vueTagFunction("#vueTag", e);
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

function jumpPage(url) {
    window.location.href = url
}

function showModal(url) {
    var mainContent = document.getElementById('iframe-page-one');
    mainContent.src = url;//嵌套网址
    $("#myModal").modal('show');
}

var vueTagObj;
function vueTagFunction(el, e) {
    vueTagObj = new Vue({
            el: el,
            data: {
                filterText: '',
                tagInfo: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
            },
            e: e,
            watch: {
                filterText(val) {
                    this.$refs.tree.filter(val);
                }
            },
            created() {
                this.initTagInfo();
            },
            methods: {
                filterNode(value, data) {
                    if (!value) return true;
                    return data.label.indexOf(value) !== -1;
                },
                initTagInfo() {
                    $.ajax({
                        url: getRootPath() + "/getTreeData",
                        type: "post",
                        dataType: "json",
                        enable: true,
                        success: function (data) {
                            for (var i = 0; i < data.length; i++) {
                                if (data[i].pid == 0) {
                                } else {
                                }
                            }
                            this.tagInfo = data;
                        }
                    })
                }
            },
        }
    )
}