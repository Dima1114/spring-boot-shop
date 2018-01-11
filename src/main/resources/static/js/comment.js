var baseUrl = $('#baseUrl').attr('content');

function showArea() {
    $('.comment-btns').css('display', 'none');
    $('.comment-area-div').css('display', 'block');
}

function hideArea() {
    $('.comment-btns').css('display', 'block');
    $('.comment-area-div').css('display', 'none');
}

function postComment(itemId) {

    var text = $('.comment-area').val();

    $.ajax({
        url: baseUrl + 'comments/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            itemId: itemId,
            text: text
        })
    })
        .done(function () {
            location.reload();
        });
}

function deleteComment(commentId) {

    $.ajax({
        url: baseUrl + 'comments/delete/' + commentId,
        type: 'DELETE',
        contentType: 'application/json'
    }).done(function () {
        location.reload();
    });
}
