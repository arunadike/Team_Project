// Function to render packages with enhanced features
function renderPackages(filteredPackages = []) { // Expecting an array of package objects
  const packagesContainer = $(".col-lg-9 .row");
  packagesContainer.empty();

  if (filteredPackages.length === 0) {
    packagesContainer.append(
      '<div class="col-12 text-center py-5"><h4>No packages found</h4></div>',
    );
    return;
  }

  filteredPackages.forEach((pkg) => {
    // Create icons for included services (adjust based on your 'includes' array)
    const serviceIcons = `
      <div class="d-flex mt-2">
        ${pkg.includes.includes("flight") ? '<span class="badge bg-info me-2"><i class="bi bi-airplane"></i> Flight</span>' : ""}
        ${pkg.includes.includes("hotel") ? '<span class="badge bg-success"><i class="bi bi-building"></i> Hotel</span>' : ""}
        ${pkg.includes.includes("food") ? '<span class="badge bg-warning me-2"><i class="bi bi-egg"></i> Food</span>' : ""}
        ${pkg.includes.includes("transport") ? '<span class="badge bg-secondary"><i class="bi bi-bus-front"></i> Transport</span>' : ""}
        ${pkg.includes.includes("accommodation") ? '<span class="badge bg-primary"><i class="bi bi-house-door"></i> Accommodation</span>' : ""}
      </div>
    `;

    // Basic HTML structure for each package (adapt to your UI)
    // In the renderPackages function:
const packageHTML = `
<div class="col-md-4 mb-4 package-card" data-id="${pkg.id}">
    <div class="card h-100">
        <img src="${pkg.imageUrl}" class="card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
        <div class="card-body">
            <h5 class="package-title card-title mb-2">${pkg.title}</h5>
            <p class="package-description card-text mb-2">${pkg.description}</p>
            <p class="package-duration card-text"><i class="bi bi-clock"></i> ${pkg.duration}</p>
            <p class="package-price card-text h5">${pkg.price}</p>
            ${serviceIcons}
            <a href="package-details.html?id=${pkg.id}" class="btn btn-sm btn-primary mt-3">Know More</a>
        </div>
    </div>
</div>
`;

    packagesContainer.append(packageHTML);
  });
}

$(document).ready(function(){
  $.ajax({
    url: 'http://localhost:8081/api/packageDisplay', // Your API endpoint
    method: 'GET',
    dataType: 'json', // Expect JSON response
    success: function(databasePackages) {

      const transformedPackages = databasePackages.map(pkg => {
        const newPkg = {
          id: pkg.packageId,
          title: pkg.title,
          description: pkg.description,
          duration: `${pkg.duration} Days`,
          price: `₹${pkg.price.toLocaleString('en-IN')}`, // Format as Indian Rupees
          includes: pkg.includedService ? pkg.includedService.split(', ').map(s => s.toLowerCase()) : [],
          imageUrl: pkg.imageUrl // Use imageUrl from the database response
        };
        console.log(newPkg);
        return newPkg;
      });
      console.log(transformedPackages);
      renderPackages(transformedPackages); // <---- UNCOMMENT THIS LINE
    },
    error: function(xhr, status, error) {
      console.error("Error fetching packages:", status, error);
      // Optionally display an error message to the user
    }
  });
});
