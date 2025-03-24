// You can add your custom JavaScript here
$(document).ready(function() {
    // Example: Alert when the form is submitted
    $('form').on('submit', function(event) {
        event.preventDefault();
        alert('Form submitted!');
    });
});