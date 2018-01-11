$(document).ready(function () {

    var size = $('.sizeOption').parent().text();
    if (size.trim() === 'UniSize') {
        $('.shopping').removeClass("disabled");
    }
});

function undisableSelect() {
    var selected = $('#sizeSelector').val();

    if (selected !== '') {
        $('.shopping').removeClass("disabled")
    } else {
        $('.shopping').addClass("disabled")
    }
}

function undisableRadio() {
    $('.shopping').removeClass("disabled")
}