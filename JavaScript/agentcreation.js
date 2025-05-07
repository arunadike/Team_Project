function displayPackagesByUserId(userId) {
  const packagesContainer = $(".col-lg-9 .row");
  packagesContainer.empty();
//   const userId = localStorage.getItem('userId');

  const jwtToken = localStorage.getItem('JWT');

  const apiUrl = `http://localhost:8081/api/user/${userId}`;

  $.ajax({
    url: apiUrl,
    method: "GET",
    dataType: "json",
    headers: {
      "Authorization": "Bearer " + jwtToken
    },
    success: function (data) {
      if (data && data.length > 0) {
        let packagesHtml = "";
        data.forEach(pkg => {
          const serviceIcons = `
            <div class="d-flex mt-2">
              ${pkg.includedService && pkg.includedService.includes("flight") ? '<span class="badge bg-info me-2"><i class="bi bi-airplane"></i> Flight</span>' : ""}
              ${pkg.includedService && pkg.includedService.includes("hotel") ? '<span class="badge bg-success"><i class="bi bi-building"></i> Hotel</span>' : ""}
              ${pkg.includedService && pkg.includedService.includes("food") ? '<span class="badge bg-warning me-2"><i class="bi bi-egg"></i> Food</span>' : ""}
              ${pkg.includedService && pkg.includedService.includes("transport") ? '<span class="badge bg-secondary"><i class="bi bi-bus-front"></i> Transport</span>' : ""}
              ${pkg.includedService && pkg.includedService.includes("accommodation") ? '<span class="badge bg-primary"><i class="bi bi-house-door"></i> Accommodation</span>' : ""}
            </div>
          `;

          packagesHtml += `
            <div class="col-md-4 mb-4 package-card" data-id="${pkg.packageId}">
              <div class="card h-100">
                <img src="${pkg.imageUrl}" class="card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
                <div class="card-body">
                  <h5 class="package-title card-title mb-2">${pkg.title}</h5>
                  <p class="package-description card-text mb-2">${pkg.description}</p>
                  <p class="package-duration card-text"><i class="bi bi-clock"></i> ${pkg.duration} Days</p>
                  <p class="package-price card-text h5">₹${pkg.price.toLocaleString('en-IN')}</p>
                  ${serviceIcons}
                  <a href="package-details.html?id=${pkg.packageId}" class="btn btn-sm btn-primary mt-3">Know More</a>
                  <button class="btn btn-sm btn-danger mt-3 remove-from-wishlist-btn" onclick="deletePackage(${pkg.packageId})" data-package-id="${pkg.packageId}">Remove</button>
                </div>
              </div>
            </div>
          `;
        });
        packagesContainer.html(packagesHtml);
      } else {
        packagesContainer.html("<div class='col-12 text-center py-5'><h4>No packages found for this user.</h4></div>");
      }
    },
    error: function (xhr, status, error) {
      console.error("Error fetching packages:", status, error);
      packagesContainer.html("<div class='col-12 text-center py-5'><h4>Error: Could not retrieve packages.</h4></div>");
      let errorMessage = "Error: Could not retrieve packages.";
      if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMessage += "\n" + xhr.responseJSON.message;
      }
      alert(errorMessage);
      if (xhr.status === 401) {
        alert("Unauthorized. Please log in.");
      }
    }
  });
}



$(document).ready(function () {
  const userId = localStorage.getItem('userId');
  if (userId) {
    displayPackagesByUserId(userId);
  } else {
    console.log("user not logged in");
  }

  window.deletePackage = function (packageId) {
    console.log("Delete icon clicked for package ID:", packageId);

    if (confirm("Are you sure you want to delete this package?")) {
        $.ajax({
            url: `http://localhost:8081/api/delete/${packageId}`, // Corrected URL
            method: 'DELETE',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('JWT') // Include the JWT token
            },
            success: function (response) {
                console.log("Package deleted successfully:", response);
                alert("Package deleted successfully!");
                window.location.href = 'agentcreation.html'; // Redirect to the packages list.  I've changed this, as it seems more logical to go to agent creation.
            },
            error: function (xhr, status, error) {
                console.error("Error deleting package:", error); // More specific error message
                //  window.location.href = 'packages.html'; // Removed:  This could cause a loop.
                alert('Failed to delete package.');
                // Optionally display more detailed error information
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    console.error("Server error message:", xhr.responseJSON.message);
                }
            }
        });
    }
};

});
