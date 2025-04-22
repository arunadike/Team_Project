const packages = [
    // Budget: 10K (2 packages)
    {
      id: 1,
      title: "Pondicherry Weekend Escape",
      location: "Pondicherry",
      image:
        "https://images.unsplash.com/photo-1624257146471-78ea613e1649?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹9,800",
      perPerson: "₹2,450 per night",
      type: "Domestic",
      budget: "T10K",
      category: "Beaches",
      duration: "4 Days",
      rating: 4.2,
      includes: ["hotel"],
    },
    {
      id: 2,
      title: "Mysuru Cultural Tour",
      location: "Mysuru",
      image:
        "https://images.unsplash.com/photo-1665910690956-2d16ce1cf515?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹9,500",
      perPerson: "₹2,375 per night",
      type: "Domestic",
      budget: "T10K",
      category: "Cultural",
      duration: "4 Days",
      rating: 4.1,
      includes: ["hotel"],
    },
  
    // Budget: 20K (3 packages)
    {
      id: 3,
      title: "Karnataka Wildlife Adventure",
      location: "Karnataka",
      image:
        "https://images.unsplash.com/photo-1631714712922-eaa39e4452fa?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹19,500",
      perPerson: "₹3,900 per night",
      type: "Domestic",
      budget: "T20K",
      category: "Nature",
      duration: "5 Days",
      rating: 4.3,
      includes: ["hotel"],
    },
    {
      id: 4,
      title: "Vengurla Coastal Special",
      location: "Vengurla",
      image:
        "https://images.unsplash.com/photo-1636287305600-d83684766cfc?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹18,900",
      perPerson: "₹3,780 per night",
      type: "Domestic",
      budget: "T20K",
      category: "Beaches",
      duration: "5 Days",
      rating: 4.0,
      includes: ["hotel"],
    },
    {
      id: 5,
      title: "Ooty Hill Station Retreat",
      location: "Ooty",
      image:
        "https://images.unsplash.com/photo-1638886540342-240980f60d25?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹20,500",
      perPerson: "₹4,100 per night",
      type: "Domestic",
      budget: "T20K",
      category: "Mountains",
      duration: "5 Days",
      rating: 4.2,
      includes: ["hotel"],
    },
  
    // Budget: 30K (3 packages)
    {
      id: 6,
      title: "Goa Beach Package",
      location: "Goa",
      image:
        "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹28,500",
      perPerson: "₹4,750 per night",
      type: "Domestic",
      budget: "T30K",
      category: "Beaches",
      duration: "6 Days",
      rating: 4.4,
      includes: ["flight", "hotel"],
    },
    {
      id: 7,
      title: "Kerala Backwaters Tour",
      location: "Kerala",
      image:
        "https://images.unsplash.com/photo-1587283506208-2f3bfef269a1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODR8fEtlcmFsYXxlbnwwfHwwfHx8MA%3D%3D",
      price: "₹29,800",
      perPerson: "₹4,966 per night",
      type: "Domestic",
      budget: "T30K",
      category: "Nature",
      duration: "6 Days",
      rating: 4.5,
      includes: ["flight", "hotel"],
    },
    {
      id: 8,
      title: "Jaipur Heritage Experience",
      location: "Jaipur",
      image:
        "https://images.unsplash.com/photo-1561486008-1011a284acfb?q=80&w=1732&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹31,200",
      perPerson: "₹5,200 per night",
      type: "Domestic",
      budget: "T30K",
      category: "Historical",
      duration: "6 Days",
      rating: 4.3,
      includes: ["flight", "hotel"],
    },
  
    // Budget: 40K (3 packages)
    {
      id: 9,
      title: "Himalayan Trekking Adventure",
      location: "Himachal Pradesh",
      image:
        "https://images.unsplash.com/photo-1593183981460-e9276b5a5587?q=80&w=1851&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹38,500",
      perPerson: "₹5,500 per night",
      type: "Domestic",
      budget: "T40K",
      category: "Mountains",
      duration: "7 Days",
      rating: 4.6,
      includes: ["flight", "hotel"],
    },
    {
      id: 10,
      title: "Udaipur Royal Stay",
      location: "Udaipur",
      image:
        "https://images.unsplash.com/photo-1633702738734-443da2c18f3c?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      price: "₹39,900",
      perPerson: "₹5,700 per night",
      type: "Domestic",
      budget: "T40K",
      category: "Luxury",
      duration: "7 Days",
      rating: 4.7,
      includes: ["flight", "hotel"],
    },
    
  ];
  
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
  