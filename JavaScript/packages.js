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
        const packageHTML = `
  <div class="col-md-4 mb-4 package-card" data-id="${pkg.id}" data-title="${pkg.title}" data-price="${pkg.price}">
  <div class="card h-100">
  <img src="${pkg.imageUrl}" class="card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
  <div class="card-body">
  <h5 class="package-title card-title mb-2">${pkg.title}</h5>
  <p class="package-description card-text mb-2">${pkg.description}</p>
  <p class="package-duration card-text"><i class="bi bi-clock"></i> ${pkg.duration}</p>
  <p class="package-price card-text h5">${pkg.price}</p>
            ${serviceIcons}
  <button class="btn btn-sm btn-primary mt-3">Details</button>
  <button class="btn btn-sm btn-success mt-3 add-to-cart-btn" data-package-id="${pkg.id}" data-package-price="${pkg.price}">Add</button>
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
                    duration: `${pkg.duration}`,
                    price: pkg.price, // Keep as number for now
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

    // Event listener for the "Add to Cart" button (using event delegation)
    $(".col-lg-9 .row").on("click", ".add-to-cart-btn", function() {
        const packageId = $(this).data("package-id");
        const packagePrice = $(this).data("package-price");

        // **Important:** You'll need to get the following information:
        // 1. User ID: How is the user currently logged in or identified?
        // 2. Start Date: How do you want the user to select this? (e.g., a default, a calendar picker)
        // 3. Number of Persons: How do you want the user to specify this? (e.g., a default of 1, an input field)
        // 4. Insurance: How do you want to handle this? (e.g., a checkbox, a default of false)

        // For this example, I'll make some assumptions:
        const userId = 1; // Replace with the actual logged-in user ID
        const startDate = new Date().toISOString(); // Current date as default
        const noOfPersons = 1; // Default to 1 person
        const insurance = false; // Default to no insurance


        const cartItemData = {
            user: { userId: userId }, // Assuming your backend can handle just the ID
            package1: { packageId: packageId }, // Assuming your backend can handle just the ID
            startDate: startDate,
            noOfPersons: noOfPersons,
            insurance: insurance,
            price: packagePrice // Use the price from the package
        };
        console.log(cartItemData);

        $.ajax({
            url: 'http://localhost:8081/cart/add', // Your backend API endpoint to add to cart
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(cartItemData),
            success: function(response) {
                console.log("Package added to cart:", response);
                alert("Package added to cart!"); // Or a more user-friendly notification
                // Optionally update the cart display
            },
            error: function(xhr, status, error) {
                console.error("Error adding package to cart:", status, error);
                alert("Error adding to cart. Please try again."); // Or a more user-friendly error message
            }
        });
    });
  });
