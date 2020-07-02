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

/*------------------------------------------
        = HIDE PRELOADER
    -------------------------------------------*/
function pageLoader() {
    if($('.page-loader').length) {
        $('.page-loader').delay(100).fadeOut(500, function() {

            //Active heor slider
            heroSlider();

        });
    }
}