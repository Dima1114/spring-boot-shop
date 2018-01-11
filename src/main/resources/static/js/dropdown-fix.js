$(window).on('click', function () {
    //IT WORKS!!
    if ($('.dropdown').hasClass('show') !== true) {
        $('.dropdown-menu').removeClass('show')
    }
});
