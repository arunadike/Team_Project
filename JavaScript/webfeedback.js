$(document).ready(function () {
    // --- Get URL parameters ---
    const urlParams = new URLSearchParams(window.location.search);
    const bookingPaymentId = urlParams.get('bookingPaymentId');
    const userId = urlParams.get('userId');
    const packageId = urlParams.get('packageId');

    const jwt=localStorage.getItem('JWT');

    // --- Set the hidden field values ---
    $('#bookingPaymentId').val(bookingPaymentId);
    $('#userId').val(userId);
    $('#packageId').val(packageId);

    // --- Form submission ---
    $('#reviewForm').submit(function (event) {
        event.preventDefault();

        var formData = {
            rating: $('input[name="rating"]:checked').val(),
            reviewComment: $("#comments").val(),
            package1: { // Create the package1 object
                packageId: $("#packageId").val() // Include the packageId here
            },
            booking: { // Backend expects a 'booking' object
                bookingId: $("#bookingPaymentId").val()
            },
            user: { // Assuming your backend might use a 'user' object as well
                userId: $("#userId").val()
            }
        };

        $.ajax({
            url: 'http://localhost:8081/api/reviewPost',
            method: 'POST',
            headers:{
                "Authorization": "Bearer " + jwt
            },
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                console.log('Review submitted successfully:', response);
                alert('Thank you for your review!');

            },
            error: function (xhr, status, error) {
                console.error('Error submitting review:', status, error);
                alert('Failed to submit your review. Please try again.');
            }
        });
        console.log('Submit button clicked');
    });
});