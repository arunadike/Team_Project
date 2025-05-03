$(document).ready(function() {
    function checkAuthenticationAndRedirect() {
      const authToken = localStorage.getItem('JWT'); // Use 'JWT' to be consistent with your code
      if (authToken) {
        // The user is already logged in, but we need to check if the token is valid
        //and not expired.
        //A JWT has 3 parts: Header, Payload, Signature.
        //Payload contains the exp(expiration) claim.
        try {
          const payloadBase64 = authToken.split('.')[1];
          const payloadJson = atob(payloadBase64);  // Use atob() to decode
          const payload = JSON.parse(payloadJson);
          const now = Math.floor(Date.now() / 1000); // Get current time in seconds
  
          if (payload.exp > now) {
            // Token is valid, redirect
            window.location.href = 'index.html'; // Redirect to your main page
            return; // IMPORTANT: Exit the function!
          } else {
            // Token is expired, remove it and allow the user to login again
            localStorage.removeItem('JWT');
            console.warn('JWT Token expired');
          }
        } catch (error) {
          localStorage.removeItem('JWT');
          console.error('Error decoding JWT:', error);
          // Handle the error:  The token is invalid, remove it.
        }
      }
  
      
      
    }
  
    // Call this function when the page loads
    checkAuthenticationAndRedirect();
  
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
        //   console.log("Login successful:", response.userId);
          // statusDiv.text("Login successful! Welcome " + response.userId);
          const userJson = JSON.stringify(response);
          localStorage.setItem('role', response.roles); // Store the role in localStorage
          localStorage.setItem('user', userJson);
          localStorage.setItem('userId', response.userId);
          localStorage.setItem('JWT', response.token);  // Use 'JWT' consistently
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
  