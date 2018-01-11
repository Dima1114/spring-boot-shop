
$(document).ready(function () {

    var category = $('#selectCategory').find(":selected").attr('class');
    display(category);
});

$('#selectCategory').on('change', function () {

    var category = $('#selectCategory').find(":selected").attr('class');
    showSizes(category);
});

function showSizes(category) {

    $('.sizeOption').each(function () {
        $(this).css('display', 'none');
    });

    $('#selectSize').val('');
    display(category);
}

function display(category) {
    $('.sizeOption').each(function () {

        if ($(this).hasClass(category)) {
            $(this).css('display', 'flex');
        }
    });
}

