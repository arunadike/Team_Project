$(document).ready(function () {
    $('.faq-question').click(function () {
        $(this).toggleClass('active');
        $(this).find('span').toggleClass('active');
        $(this).next('.faq-answer').toggleClass('show');
    });
});
