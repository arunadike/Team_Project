$(document).ready(function() {
    $("#loginButton").click(function(event) {
        event.preventDefault(); // Prevent the default form submission

        const username = $("#username").val();
        const password = $("#password").val();
        const statusDiv = $("#status");

        $.ajax({
            url: "http://localhost:8081/login",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
            data: {
                username: username,
                password: password
            },
            xhrFields: {
                withCredentials: true
            },
            success: function(response) {
                console.log("Login successful:", response.userId);
                // statusDiv.text("Login successful! Welcome " + response.userId);
                const userJson = JSON.stringify(response);
                localStorage.setItem('user', userJson);
                localStorage.setItem('userId', response.userId);
                localStorage.setItem('JWT', response.token);
                window.location.href = "index.html";
            },
            error: function(xhr, status, error) {
                console.error("Login failed:", xhr, status, error);
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    statusDiv.text("Login failed: " + xhr.responseJSON.message);
                } else if (xhr.responseText) {
                    statusDiv.text("Login failed: " + xhr.responseText);
                } else {
                    statusDiv.text("Login failed: An unexpected error occurred.");
                }
            }
        });
    });
});