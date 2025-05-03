function renderPackages(filteredPackages = []) {
    const packagesContainer = $(".col-lg-9 .row");
    packagesContainer.empty();

    if (filteredPackages.length === 0) {
      packagesContainer.append(
        '<div class="col-12 text-center py-5"><h4>No packages found</h4></div>',
      );
      return;
    }

    filteredPackages.forEach((pkg) => {
      const serviceIcons = `
  <div class="d-flex mt-2">
                                  ${
                                    pkg.includes &&
                                    pkg.includes.includes("flight")
                                      ? '<span class="badge bg-info me-2"><i class="bi bi-airplane"></i> Flight</span>'
                                      : ""
                                  }
                                  ${
                                    pkg.includes &&
                                    pkg.includes.includes("hotel")
                                      ? '<span class="badge bg-success"><i class="bi bi-building"></i> Hotel</span>'
                                      : ""
                                  }
                                  ${
                                    pkg.includes &&
                                    pkg.includes.includes("food")
                                      ? '<span class="badge bg-warning me-2"><i class="bi bi-egg"></i> Food</span>'
                                      : ""
                                  }
                                  ${
                                    pkg.includes &&
                                    pkg.includes.includes("transport")
                                      ? '<span class="badge bg-secondary"><i class="bi bi-bus-front"></i> Transport</span>'
                                      : ""
                                  }
                                  ${
                                    pkg.includes &&
                                    pkg.includes.includes("accommodation")
                                      ? '<span class="badge bg-primary"><i class="bi bi-house-door"></i> Accommodation</span>'
                                      : ""
                                  }
  </div>
                                  `;

      const packageHTML = `
  <div class="col-md-4 mb-4 package-card" data-id="${pkg.packageId}">
  <div class="card h-100">
  <img src="${pkg.imageUrl}" class="card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
  <div class="card-body">
  <h5 class="package-title card-title mb-2">${pkg.title}</h5>
  <p class="package-description card-text mb-2">${pkg.description}</p>
  <p class="package-duration card-text"><i class="bi bi-clock"></i> ${pkg.duration} Days</p>
  <p class="package-price card-text h5">₹${pkg.price.toLocaleString('en-IN')}</p>
                                                          ${serviceIcons}
  <a href="package-details.html?id=${
        pkg.packageId
      }" class="btn btn-sm btn-primary mt-3">Know More</a>
      <button class="btn btn-sm btn-success mt-3 add-to-cart-btn" data-package-id="${pkg.packageId}" data-package-price="${pkg.price}">Add</button>

  </div>
  </div>
  </div>
                                  `;

      packagesContainer.append(packageHTML);
    });
  }
      // Function to fetch and render packages, now with combined search and filter

      function fetchAndRenderPackages(

        searchTitle = "",

        minDuration = null,

        maxDuration = null,

        services = [],

        minPrice = null,

        maxPrice = null,

      ) {
      const jwtToken = localStorage.getItem('JWT');

        const apiUrl = "http://localhost:8081/api/packageDisplay"; // Default: fetch all



        const urlParams = new URLSearchParams(); // Use URLSearchParams



        if (searchTitle) {

          urlParams.append("title", searchTitle);

        }



        if (minDuration !== null) {

          urlParams.append("minDuration", minDuration);

        }



        if (maxDuration !== null) {

          urlParams.append("maxDuration", maxDuration);

        }



        if (services && services.length > 0) {

          urlParams.append("services", services.join(",")); // Join services

        }



        if (minPrice !== null) {

          urlParams.append("minPrice", minPrice);

        }



        if (maxPrice !== null) {

          urlParams.append("maxPrice", maxPrice);

        }



        const finalUrl = apiUrl + (urlParams.toString() ? "?" + urlParams.toString() : "");



        console.log(finalUrl);



        $.ajax({

          url: finalUrl,

          method: "GET",

          dataType: "json",

          headers: { // Add the headers option
              "Authorization": "Bearer " + jwtToken
          },

          success: function (data) {

            const transformedPackages = data.map((pkg) => ({

              packageId: pkg.packageId,

              title: pkg.title,

              description: pkg.description,

              duration: `${pkg.duration}`,

              price: `${pkg.price}`,


                includes : pkg.includedService

              ? pkg.includedService.split(/, ?/).map((s) => s.toLowerCase()) // Split by ", " or ","

              : [],



              imageUrl: pkg.imageUrl,

            }));



            renderPackages(transformedPackages);

          },

          error: function (xhr, status, error) {

            console.error("Error fetching packages:", status, error);
            window.alert("Unauthorized", status, error);
            renderPackages([]);

          },

        });

      }



      $(document).ready(function () {

        // Initial loading of all packages

        fetchAndRenderPackages();



        // Search functionality

        $(".search-box input").on("input", function () {

          const searchTerm = $(this).val();

          const activeDuration = $(".filter-section")

            .find(".filter-option:contains('Travel time')")

            .nextUntil(".divider")

            .filter(".active");

          const selectedServices = $(".filter-section")

            .find(".filter-option:contains('Popular')") // Corrected selector to 'Popular' to match HTML structure

            .nextUntil(".divider")

            .filter(".active")

            .map(function () {

              return $(this).text();

            })

            .get();



          const activeBudget = $(".filter-section")

            .find(".filter-option:contains('Budget')")

            .nextUntil(".divider")

            .filter(".active");



          let minDuration = null;

          let maxDuration = null;

          let minPrice = null;

          let maxPrice = null;



          if (activeDuration.length > 0) {

            const durationText = activeDuration.text();

            switch (durationText) {

              case "0":

                minDuration = 0;

                maxDuration = 0;

                break;

              case "0-3":

                minDuration = 0;

                maxDuration = 3;

                break;

              case "3-7":

                minDuration = 3;

                maxDuration = 7;

                break;

              case "7+":

                minDuration = 7;

                maxDuration = 9999;

                break;

            }

          }



          if (activeBudget.length > 0) {

            const budgetText = activeBudget.text();

            switch (budgetText) {

              case "T10K":

                minPrice = 0;

                maxPrice = 10000;

                break;

              case "T20K":

                minPrice = 0;

                maxPrice = 20000;

                break;

              case "T30K":

                minPrice = 0;

                maxPrice = 30000;

                break;

              case "T30K+":

                minPrice = 30000;

                maxPrice = 9999;

                break;

            }

          }



          fetchAndRenderPackages(

            searchTerm,

            minDuration,

            maxDuration,

            selectedServices,

            minPrice,

            maxPrice,

          );

        });



        // Duration filter functionality

        $(".filter-section")

          .find(".filter-option:contains('Travel time')")

          .nextUntil(".divider")

          .on("click", function () {

            const durationText = $(this).text();

            let minDuration = null;

            let maxDuration = null;

            const searchTerm = $(".search-box input").val();

            const selectedServices = $(".filter-section")

              .find(".filter-option:contains('Popular')") // Corrected selector to 'Popular'

              .nextUntil(".divider")

              .filter(".active")

              .map(function () {

                return $(this).text();

              })

              .get();



            const activeBudget = $(".filter-section")

              .find(".filter-option:contains('Budget')")

              .nextUntil(".divider")

              .filter(".active");

            let minPrice = null;

            let maxPrice = null;



            if (activeBudget.length > 0) {

              const budgetText = activeBudget.text();

              switch (budgetText) {

                case "T10K":

                  minPrice = 0;

                  maxPrice = 10000;

                  break;

                case "T20K":

                  minPrice = 0;

                  maxPrice = 20000;

                  break;

                case "T30K":

                  minPrice = 0;

                  maxPrice = 30000;

                  break;

                case "T30K+":

                  minPrice = 0;

                  maxPrice = 9999;

                  break;

              }

            }



            switch (durationText) {

              case "0":

                minDuration = 0;

                maxDuration = 0;

                break;

              case "0-3":

                minDuration = 0;

                maxDuration = 3;

                break;

              case "3-7":

                minDuration = 3;

                maxDuration = 7;

                break;

              case "7+":

                minDuration = 7;

                maxDuration = 9999;

                break;

            }

            fetchAndRenderPackages(

              searchTerm,

              minDuration,

              maxDuration,

              selectedServices,

              minPrice,

              maxPrice,

            );

            $(this).addClass("active").siblings().removeClass("active");

          });



        // Service filter functionality (allowing multiple selections)

        $(".filter-section")

          .find(".filter-option:contains('Popular')") // Corrected selector to target the 'Popular' filter option

          .nextUntil(".divider")

          .on("click", function () {

            $(this).toggleClass("active"); // Simply toggle the active class on click



            const searchTerm = $(".search-box input").val();

            const activeDuration = $(".filter-section")

              .find(".filter-option:contains('Travel time')")

              .nextUntil(".divider")

              .filter(".active");

            let minDuration = null;

            let maxDuration = null;

            const selectedServices = $(".filter-section")

              .find(".filter-option:contains('Popular')") // Corrected selector

              .nextUntil(".divider")

              .filter(".active")

              .map(function () {

                return $(this).text();

              })

              .get();



            const activeBudget = $(".filter-section")

              .find(".filter-option:contains('Budget')")

              .nextUntil(".divider")

              .filter(".active");

            let minPrice = null;

            let maxPrice = null;



            if (activeBudget.length > 0) {

              const budgetText = activeBudget.text();

              switch (budgetText) {

                case "T10K":

                  minPrice = 0;

                  maxPrice = 10000;

                  break;

                case "T20K":

                  minPrice = 0;

                  maxPrice = 20000;

                  break;

                case "T30K":

                  minPrice = 0;

                  maxPrice = 30000;

                  break;

                case "T30K+":

                  minPrice = 0;

                  maxPrice = 9999;

                  break;

              }

            }



            if (activeDuration.length > 0) {

              const durationText = activeDuration.text();

              switch (durationText) {

                case "0":

                  minDuration = 0;

                  maxDuration = 0;

                  break;

                case "0-3":

                  minDuration = 0;

                  maxDuration = 3;

                  break;

                case "3-7":

                  minDuration = 3;

                  maxDuration = 7;

                  break;

                case "7+":

                  minDuration = 7;

                  maxDuration = 9999;

                  break;

              }

            }



            fetchAndRenderPackages(

              searchTerm,

              minDuration,

              maxDuration,

              selectedServices,

              minPrice,

              maxPrice,

            );

          });



        //budget filter

        $(".filter-section")

          .find(".filter-option:contains('Budget')")

          .nextUntil(".divider")

          .on("click", function () {

            const budgetText = $(this).text();

            let minPrice = null;

            let maxPrice = null;

            const searchTerm = $(".search-box input").val();

            const activeDuration = $(".filter-section")

              .find(".filter-option:contains('Travel time')")

              .nextUntil(".divider")

              .filter(".active");

            let minDuration = null;

            let maxDuration = null;

            const selectedServices = $(".filter-section")

              .find(".filter-option:contains('Popular')") // Corrected selector

              .nextUntil(".divider")

              .filter(".active")

              .map(function () {

                return $(this).text();

              })

              .get();



            if (activeDuration.length > 0) {

              const durationText = activeDuration.text();

              switch (durationText) {

                case "0":

                  minDuration = 0;

                  maxDuration = 0;

                  break;

                case "0-3":

                  minDuration = 0;

                  maxDuration = 3;

                  break;

                case "3-7":

                  minDuration = 3;

                  maxDuration = 7;

                  break;

                case "7+":

                  minDuration = 7;

                  maxDuration = 9999;

                  break;

              }

            }



            switch (budgetText) {

              case "T10K":

                minPrice = 0;

                maxPrice = 10000;

                break;

              case "T20K":

                minPrice = 0;

                maxPrice = 20000;

                break;

              case "T30K":

                minPrice = 0;

                maxPrice = 30000;

                break;

              case "T30K+":

                minPrice = 30000;

                maxPrice = 9999;

                break;

            }



            fetchAndRenderPackages(

              searchTerm,

              minDuration,

              maxDuration,

              selectedServices,

              minPrice,

              maxPrice,

            );

            $(this).addClass("active").siblings().removeClass("active");

          });



        // Clear All filters functionality

        $(".clear-all").on("click", function () {

          $(".filter-section .active").removeClass("active");

          $(".search-box input").val("");

          fetchAndRenderPackages();

        });

      });

      $(".col-lg-9 .row").on("click", ".add-to-cart-btn", function() {

        const packageId = $(this).data("package-id");

        const packagePrice = $(this).data("package-price");

        const userId = localStorage.getItem('userId');
        console.log(userId); // Use the userId
        // const userId = 0; 
        // if (userJson) {
        //   try {
        //     const user = JSON.parse(userJson);
        //     userId = user.userId;
        //     console.log(userId); // Use the userId
        //   } catch (error) {
        //     console.error('Error parsing user data from localStorage:', error);
        //     // Handle the error, e.g., clear the invalid data:
        //     // localStorage.removeItem('user');
        //   }
        // } else {
        //   console.log('No user data found in localStorage');
        // } // Replace with the actual logged-in user ID

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



        // Assuming you have a JWT token stored in localStorage or a variable
        const jwtToken = localStorage.getItem("JWT"); 

        $.ajax({

            url: 'http://localhost:8081/cart/add', // Your backend API endpoint to add to cart

            method: 'POST',

            dataType: 'json',
            headers: { // Add the headers option
                "Authorization": "Bearer " + jwtToken
            },
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
