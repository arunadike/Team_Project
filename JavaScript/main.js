$(document).ready(function() {

    $("#logout-btn").click(function(event) {
      event.preventDefault();

    const JWT=localStorage.getItem('JWT');
    console.log("JWT token: ", JWT);
    localStorage.clear(); // Clear all local storage items
    window.location.href = "loginOrSignUpPage.html";
  });

});
