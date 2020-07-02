/*====================
JS Table of content: 
======================

	#== Sticky Header
	#== Mainmenu
	#== Vagas Slider
	#== Home Version Three Slider
	#== Home Version One
	#== js Instagram Feed Slide
	#== Recent Photo Gallery Slider
	#== js single album list
	#== js-playlists-slider
	#== js-hot-tracks-slider
	#== js single album list
	#== js artist video slider
	#== artist video slider Style 2
	#== js album-tab-slider
	#== js singer slider
	#== Shpo slider
	#== singer slider style2
	#== Audio Player
	#== Select Option
	#== Scroll To Top
	#== Magnific Popup
	#== magnificPopup videos popup   
	#== Header Script 
 

*/


(function ($) {
    "use strict";

    // Sticky Header
    $(".sticky-on").sticky({topSpacing: 0});

    $(window).on('load', function() {
        pageLoader();
    });

    /*------------------------------------------
            = HIDE PRELOADER
        -------------------------------------------*/
    function pageLoader() {
        if ($('.page-loader').length) {
            $('.page-loader').delay(100).fadeOut(500, function () {
                // Home Version One
                $(".slider-carousel").owlCarousel({
                    items: 1,
                    loop: true,
                    autoplay: true,
                    autoplaySpeed: 5000,
                    autoplayHoverPause: true,
                    arrows: true,
                    dots: true,
                    fade: true,
                    animateOut: 'fadeOut',
                    nav: true,
                    navText: ['<i class="pe-7s-angle-left"></i>', '<i class="pe-7s-angle-right"></i>'],
                });
            });
        }
    }

    // Mainmenu
    $(".cssmenu").each(function () {
        var title = ($(this).data('title')) ? $(this).data('title') : "Menu";
        $(this).menumaker({
            title: title,
            format: "multitoggle"
        });
    })

    // Home Version Three Slider
    $(".slideslow-style3").owlCarousel({
        items: 1,
        loop: true,
        autoplay: false,
        dots: false,
        animateOut: 'fadeOut',
        nav: true,
        navText: ['PREV', 'NEXT'],
    });

    // js single album list
    $("#js-single-album-slider").owlCarousel({
        items: 4,
        loop: false,
        autoplay: true,
        dots: true,
        margin: 30,
        smartSpeed: 1500,
        autoplayHoverPause: false,
        animateOut: 'fadeOut',
        nav: false,
        responsive: {
            0: {
                items: 2,
                nav: false
            },
            600: {
                items: 2,
                nav: false
            },
            1000: {
                items: 4,
                nav: false,
            }
        }
    });

    // js-playlists-slider
    $("#js-playlists-slider").owlCarousel({
        items: 1,
        loop: false,
        autoplay: true,
        dots: true,
        margin: 0,
        smartSpeed: 1500,
        animateOut: 'fadeOut',
        nav: false
    });

    // js-hot-tracks-slider
    $("#js-hot-tracks-slider").owlCarousel({
        items: 1,
        loop: false,
        autoplay: false,
        dots: true,
        margin: 0,
        smartSpeed: 1500,
        autoplayHoverPause: false,
        animateOut: 'fadeOut',
        nav: false
    });

    // js album-tab-slider
    $("#js-album-tab-slider").owlCarousel({
        items: 4,
        loop: false,
        autoplay: false,
        dots: false,
        margin: 0,
        smartSpeed: 1500,
        autoplayHoverPause: false,
        animateOut: 'fadeOut',
        nav: true,
        navText: ['<i class="pe-7s-angle-left"></i>', '<i class="pe-7s-angle-right"></i>'],
        responsive: {
            0: {
                items: 1,
                nav: true
            },
            600: {
                items: 3,
                nav: true
            },
            1000: {
                items: 4,
                nav: true,
            }
        }
    });


    // Audio Player
    $('audio').audioPlayer();

    // Scroll To Top
    if ($('.scrolltop').length > 0) {
        $(".scrolltop").on("click", function () {
            $('html,body').animate({scrollTop: 0}, 1000);
        });

        $(window).scroll(function () {
            if ($(window).scrollTop() > 400) {
                $(".scrolltop").fadeIn();
            }
            else {
                $(".scrolltop").fadeOut();
            }
            return false;
        });
    }

    // Magnific Popup
    $('.js-gallary-list').magnificPopup({
        delegate: '.js-full-size-image',
        type: 'image',
        mainClass: 'mfp-with-zoom mfp-img-mobile',
        image: {
            verticalFit: true,
            titleSrc: function (item) {
                return item.el.attr('title') + ' &middot; <a class="image-source-link" href="' + item.el.attr('data-source') + '" target="_blank">image source</a>';
            }
        },
        gallery: {
            enabled: true
        },
        zoom: {
            enabled: true,
            easing: 'ease-in-out',
            duration: 300,
            opener: function (element) {
                return element.closest('.js-gallary-list').find('img');
            }
        }
    });

    // magnificPopup videos popup
    $('.js-video-popup').magnificPopup({
        disableOn: 315,
        type: 'iframe',
        mainClass: 'mfp-fade',
        removalDelay: 160,
        preloader: true,
        fixedContentPos: false
    });


    // Header Script
    $(".js-offcanvas-close").on("click", function () {
        $("#js_offcanvas_menu").css('right', '-500px');
    });

    $("#sidebar_menu_icon").on("click", function () {
        $("#js_offcanvas_menu").css('right', '0');
    });


    $("#js-music").on("click", function () {
        $("#js-info-album-player").slideToggle("slow");
    });


    $(".js-play-left-arrow").on("click", function () {
        $(".js-song-list").css('left', '260px');
    });
    $(".js-play-right-arrow").on("click", function () {

        $(".js-song-list").css('left', '-480px');
    });


}(jQuery));

