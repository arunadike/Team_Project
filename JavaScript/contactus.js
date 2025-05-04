$(document).ready(function() {

    const jwtToken = localStorage.getItem("JWT"); 
    $('#submitBtn').click(function() {
        var commenting = $('#complaint').val().trim();
        var userId=localStorage.getItem("userId");
        console.log(commenting);
        console.log(userId);
        if (commenting === '') {
            alert('Please enter your complaint or query.');
            return;
        }
        $.ajax({
            url: 'http://localhost:8081/contact/submit',
            headers: { // Add the headers option
                "Authorization": "Bearer " + jwtToken
            },
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ commenting: commenting,
                users:{
                    userId:userId
                }
             }),
            success: function(response) {
                alert('Complaint submitted successfully!');
                $('#complaint').val('');
            },
            error: function(xhr, status, error) {
                alert('Error submitting complaint: ' + xhr.responseText);
            }
        });
    });

    // Animate cards from right to left individually and stop
    const cards = document.querySelectorAll('.card');
    cards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateX(100%)';
    });

    cards.forEach((card, index) => {
        setTimeout(() => {
            card.style.transition = 'transform 0.6s ease, opacity 0.6s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateX(0)';
        }, index * 300);
    });
});