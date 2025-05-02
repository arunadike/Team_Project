$(document).ready(function() {
    $("#signupButton").click(function(event) {
        event.preventDefault();

        const email = $("#email").val();
        const contactNumber = $("#contactnumber").val();
        const username = $("#username").val();
        const password = $("#password").val();
        const confirmPassword = $("#confirmPassword").val();
        const role = $("#role").val();

        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        if (!role) {
            alert("Please select a role.");
            return;
        }

        $.ajax({
            url: "http://localhost:8081/register", // Replace with your actual registration endpoint
            type: "POST",
            contentType: "application/json", // Or "application/json"
            data: JSON.stringify({
                email: email,
                contact_number: contactNumber,
                username: username,
                password: password,
                role: role
            }),
            xhrFields: {
                withCredentials: true
            },
            success: function(response) {
                console.log("Sign Up successful:", response);
                alert("Sign Up successful! You can now log in.");
                window.location.href = "loginOrSignUpPage.html";
            },
            error: function(xhr, status, error) {
                console.error("Sign Up failed:", xhr, status, error);
                let errorMessage = "Sign Up failed.";
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage += " " + xhr.responseJSON.message;
                } else if (xhr.responseText) {
                    errorMessage += " " + xhr.responseText;
                } else {
                    errorMessage += " An unexpected error occurred.";
                }
                alert(errorMessage);
            }
        });
    });
});