$(document).ready(function () {
    role=localStorage.getItem('role');
    console.log(role);
    var link= "packages.html";
    if(role=="ROLE_AGENT"){
        link="agentcreation.html";
    }
    const package=$("#package");
    console.log(package);
    package.attr("href",link);


    function loadHeaderFooter() {
        fetch('header_footer.html')
            .then(response => response.text())
            .then(data => {
                // Separate header and footer based on a unique separator in header_footer.html
                const separator = '<div id="footer-separator"></div>';
                const parts = data.split(separator);

                if (parts.length === 2) {
                    document.getElementById('header-container').innerHTML = parts[0];
                    document.getElementById('footer-container').innerHTML = parts[1];
                } else {
                    console.error('Error: Separator not found in header_footer.html');
                    document.getElementById('header-container').innerHTML = data;
                    document.getElementById('footer-container').innerHTML = '';
                }
            })
            .catch(error => console.error('Error loading header and footer:', error));
    }

    // Load header and footer when the page loads
    window.onload = loadHeaderFooter;

    $("#logout-btn").click(function(event) {
        event.preventDefault();

      const JWT=localStorage.getItem('JWT');
      console.log("JWT token: ", JWT);
      localStorage.clear(); // Clear all local storage items
      window.location.href = "loginOrSignUpPage.html";
    });

});
