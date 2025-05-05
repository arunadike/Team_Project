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
                  <button class="btn btn-sm btn-danger mt-3 remove-from-wishlist-btn" data-package-id="${pkg.packageId}">Remove</button>
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

  $(".col-lg-9 .row").on("click", ".remove-from-wishlist-btn", function () {
    const packageId = $(this).data("package-id");
    const userId = localStorage.getItem('userId');
    const jwtToken = localStorage.getItem('JWT');

    $.ajax({
      url: `http://localhost:8081/api/delete/${packageId}`,
      method: 'DELETE',
      headers: {
        "Authorization": "Bearer " + jwtToken
      },
      success: function (response) {
        console.log("Package removed:", response);
        alert("Package removed!");
        // displayPackagesByUserId(userId);
      },
      error: function (xhr, status, error) {
        console.error("Error removing package:", status, error);
        alert("Error removing package. Please try again.");
      }
    });
  });
});
