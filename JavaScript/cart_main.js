$(document).ready(function() {
    $('.delete-item').on('click', function() {
        $(this).closest('.row ').remove();
    });
});