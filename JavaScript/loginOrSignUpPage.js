// You can add your custom JavaScript here
$(document).ready(function() {
    // Example: Alert when the form is submitted
    $('form').on('submit', function(event) {
        event.preventDefault();
        alert('Form submitted!');
    });
     $("#login").click(function () {
        // Replace 'next-page.html' with the URL of your next page
        window.location.href = "index.html";
    });
});