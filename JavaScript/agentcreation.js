
  // Function to render packages with enhanced features
function renderPackages(filteredPackages = packages) {
    const packagesContainer = $(".col-lg-9 .row");
    packagesContainer.empty();
  
    if (filteredPackages.length === 0) {
      packagesContainer.append(
        '<div class="col-12 text-center py-5"><h4>No packages found matching your filters</h4></div>',
      );
      return;
    }
  
    filteredPackages.forEach((pkg) => {
      // Create icons for included services
      const serviceIcons = `
        <div class="d-flex mt-2">
          ${pkg.includes.includes("flight") ? '<span class="badge bg-info me-2"><i class="bi bi-airplane"></i> Flight</span>' : ""}
          ${pkg.includes.includes("hotel") ? '<span class="badge bg-success"><i class="bi bi-building"></i> Hotel</span>' : ""}
        </div>
      `;
  
      // Create star rating
      const stars = Array(Math.floor(pkg.rating))
        .fill('<i class="bi bi-star-fill text-warning"></i>')
        .join("");
      const halfStar =
        pkg.rating % 1 >= 0.5
          ? '<i class="bi bi-star-half text-warning"></i>'
          : "";
      const emptyStars = Array(5 - Math.ceil(pkg.rating))
        .fill('<i class="bi bi-star text-warning"></i>')
        .join("");
      const ratingStars = `<div class="mb-2">${stars}${halfStar}${emptyStars} <small>${pkg.rating}</small></div>`;
  
      packagesContainer.append(`
        <div class="col-lg-4 col-md-4 mb-4 package-card" data-id="${pkg.id}" data-type="${pkg.type}" data-budget="${pkg.budget}" data-category="${pkg.category}">
          <div class="card h-100">
            <img src="${pkg.image}" class="package-image card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
            <div class="card-body">
              <div class="d-flex justify-content-between align-items-start">
                <h5 class="package-title card-title mb-2">${pkg.title}</h5>
                <span class="badge bg-primary">${pkg.duration}</span>
              </div>
              <p class="package-location card-text text-muted mb-2"><i class="bi bi-geo-alt"></i> ${pkg.location}</p>
              ${ratingStars}
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <span class="package-price h5">${pkg.price}</span>
                  <span class="package-per-person d-block text-muted small">${pkg.perPerson}</span>
                </div>
                <button class="btn btn-sm btn-danger remove-btn">Remove</button>
              </div>
              ${serviceIcons}
            </div>
          </div>
        </div>
      `);
    });
  }
  
  // Initialize when document is ready
  $(document).ready(function () {
    renderPackages();
  
    // Enhanced filter functionality
    let activeFilters = {
      type: null,
      budget: null,
      category: null,
      duration: null,
      rating: null,
      includes: [],
    };
  
    // Filter option click handler with enhanced logic
    $(".filter-option, .filter-suboption").click(function () {
      const $this = $(this);
      const isSuboption = $this.hasClass("filter-suboption");
      const parentOption = isSuboption
        ? $this.prevAll(".filter-option").first().text()
        : null;
  
      // Toggle active class
      if (isSuboption) {
        $this.siblings(".filter-suboption").removeClass("active");
        $this.toggleClass("active");
      } else {
        $this.siblings(".filter-option").removeClass("active");
        $this.toggleClass("active");
      }
  
      // Update active filters with enhanced options
      if (parentOption === "Popular") {
        activeFilters.type = $this.hasClass("active") ? $this.text() : null;
      } else if (parentOption === "Budget") {
        activeFilters.budget = $this.hasClass("active") ? $this.text() : null;
      } else if (parentOption === "Finest") {
        activeFilters.category = $this.hasClass("active") ? $this.text() : null;
      } else if (parentOption === "Duration") {
        activeFilters.duration = $this.hasClass("active") ? $this.text() : null;
      } else if (parentOption === "Includes") {
        const value = $this.data("value");
        if ($this.hasClass("active")) {
          activeFilters.includes.push(value);
        } else {
          activeFilters.includes = activeFilters.includes.filter(
            (item) => item !== value,
          );
        }
      } else if ($this.text() === "All") {
        activeFilters.category = null;
        $(".filter-suboption").removeClass("active");
      }
  
      // Apply filters
      applyFilters();
    });
  
    // Clear all filters
    $(".clear-all").click(function () {
      $(".filter-option, .filter-suboption").removeClass("active");
      $('.filter-option:contains("All")').addClass("active");
      activeFilters = {
        type: null,
        budget: null,
        category: null,
        duration: null,
        rating: null,
        includes: [],
      };
      applyFilters();
    });
  
    // Enhanced search functionality
    $(".search-box input").on("input", function () {
      const searchTerm = $(this).val().toLowerCase();
      if (searchTerm.length > 2) {
        const filtered = packages.filter(
          (pkg) =>
            pkg.title.toLowerCase().includes(searchTerm) ||
            pkg.location.toLowerCase().includes(searchTerm) ||
            pkg.category.toLowerCase().includes(searchTerm),
        );
        renderPackages(filtered);
      } else if (searchTerm.length === 0) {
        renderPackages(packages);
      }
    });
  
    // Enhanced filter application
    function applyFilters() {
      let filtered = packages;
  
      // Apply all active filters
      if (activeFilters.type) {
        filtered = filtered.filter((pkg) => pkg.type === activeFilters.type);
      }
  
      if (activeFilters.budget) {
        // Ensure we're comparing the exact same budget strings
        filtered = filtered.filter((pkg) => pkg.budget === activeFilters.budget);
      }
  
      if (activeFilters.category) {
        filtered = filtered.filter(
          (pkg) => pkg.category === activeFilters.category,
        );
      }
  
      if (activeFilters.duration) {
        filtered = filtered.filter(
          (pkg) => pkg.duration === activeFilters.duration,
        );
      }
  
      if (activeFilters.includes.length > 0) {
        filtered = filtered.filter((pkg) =>
          activeFilters.includes.every((item) => pkg.includes.includes(item)),
        );
      }
  
      renderPackages(filtered);
    }
  
    // Add event listener for remove buttons
    $(document).on("click", ".remove-btn", function () {
      const packageCard = $(this).closest(".package-card");
      const packageId = packageCard.data("id");
      const selectedPackage = packages.find((pkg) => pkg.id === packageId);
      if (confirm(`Are you sure you want to remove ${selectedPackage.title}?`)) {
        packageCard.remove();
        alert(`${selectedPackage.title} has been removed.`);
      }
    });
  });
  