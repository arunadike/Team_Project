$(document).ready(function () {
    // Get the package ID from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const packageId = urlParams.get('id');
    const JWT=localStorage.getItem('JWT');

    if (!packageId) {
        alert('Package ID is missing!');
        window.location.href = 'packages.html';
        return;
    }

    // Function to fetch package details and display
    function fetchPackageDetails(packageId) {
        $.ajax({
            url: `http://localhost:8081/api/package/${packageId}`,
            method: 'GET',
            headers:{
                "Authorization": "Bearer "+JWT
            },
            dataType: 'json',
            success: function (packageData) {
                console.log("Package Data:", packageData);
                if (!packageData) {
                    alert('Package not found!');
                    window.location.href = 'packages.html';
                    return;
                }

                $('#package-image').attr('src', packageData.imageUrl);
                $('#package-title').text(packageData.title);
                $('#package-description').text(packageData.description);
                $('#package-duration').text(`Duration: ${packageData.duration} Days`);
                $('#package-price').text(`₹${packageData.price.toLocaleString('en-IN')}`);

                const includedServicesList = $('#included-services-list');
                includedServicesList.empty();
                if (packageData.includedService) {
                    const services = packageData.includedService.split(', ');
                    services.forEach(service => {
                        let serviceBadge = '';
                        const trimmedService = service.toLowerCase().trim();
                        if (trimmedService === 'flight') {
                            serviceBadge =
                                `<span class="badge bg-info me-2"><i class="bi bi-airplane"></i> Flight</span>`;
                        } else if (trimmedService === 'hotel') {
                            serviceBadge =
                                `<span class="badge bg-success"><i class="bi bi-building"></i> Hotel</span>`;
                        } else if (trimmedService === 'food') {
                            serviceBadge =
                                `<span class="badge bg-warning me-2"><i class="bi bi-egg"></i> Food</span>`;
                        } else if (trimmedService === 'transport') {
                            serviceBadge =
                                `<span class="badge bg-secondary"><i class="bi bi-bus-front"></i> Transport</span>`;
                        } else if (trimmedService === 'accommodation') {
                            serviceBadge =
                                `<span class="badge bg-primary"><i class="bi bi-house-door"></i> Accommodation</span>`;
                        }
                        includedServicesList.append(serviceBadge);
                    });
                }
                fetchReviews(
                packageId); // Call fetchReviews after package details are loaded

            },
            error: function (xhr, status, error) {
                console.error("Error fetching package details:", status, error);
                let errorMessage = 'Failed to load package details.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage += "\n" + xhr.responseJSON.message;
                } else if (xhr.responseText) {
                    errorMessage += "\n" + xhr.responseText;
                }
                alert(errorMessage);
                window.location.href = 'packages.html';
            }
        });
    }

    // Function to fetch reviews and display
    function fetchReviews(packageId) {
        $.ajax({
            url: `http://localhost:8081/api/reviews/${packageId}`,
            method: 'GET',
            headers:{
                "Authorization": "Bearer "+JWT
            },
            dataType: 'json',
            success: function (reviews) {
                console.log("Reviews:", reviews);
                const reviewsList = $('#reviews-list');
                reviewsList.empty();

                if (reviews.length === 0) {
                    reviewsList.append('<p>No reviews yet.</p>');
                    return;
                }

                reviews.forEach(review => {
                    // Check if review.booking is defined before accessing its properties.
                    let userName = 'Anonymous User';
                    if (review.booking && review.booking.user) {
                        userName = review.booking.user.username;
                    } else {
                        console.warn("Review is missing user/booking data:",
                        review);
                    }
                    const reviewCard = `<div class="col-md-6 mb-4">
                                        <div class="review-card">
                                            <div class="review-user">${userName}</div>
                                            <div class="review-rating">${generateStarRating(review.rating)}</div>
                                            <p class="review-comment">${review.reviewComment ? review.reviewComment : 'No comment'}</p>
                                            <p class="review-timestamp">Posted: ${formatTimestamp(review.reviewDate)}</p>
                                        </div>
                                    </div>`;
                    reviewsList.append(reviewCard);
                });
            },
            error: function (xhr, status, error) {
                console.error("Error fetching reviews:", status, error);
                $('#reviews-list').html('<p>Failed to load reviews.</p>');
            }
        });
    }

    function generateStarRating(rating) {
        let stars = '';
        for (let i = 1; i <= 5; i++) {
            stars += `<i class="bi bi-star${i <= rating ? '-fill' : ''} star-icon"></i>`;
        }
        return stars;
    }

    function formatTimestamp(timestamp) {
        try {
            const date = new Date(timestamp);
            return date.toLocaleString();
        } catch (error) {
            console.error("Error formatting timestamp:", error);
            return 'Invalid Date';
        }
    }

    window.deletePackage = function () {
        console.log("Delete icon clicked for package ID:", packageId);
        if (confirm("Are you sure you want to delete this package?")) {
            $.ajax({
                url: `http://localhost:8081/api/delete/${packageId}`, // Corrected URL
                method: 'DELETE',
                headers: {
                    "Authorization": "Bearer " + JWT  // Include the JWT token
                },
                success: function (response) {
                    console.log("Package deleted successfully:", response);
                    alert("Package deleted successfully!");
                    window.location.href = 'agentcreation.html'; // Redirect to the packages list
                },
                error: function (xhr, status, error) {
                    console.error("Unauthorized access");
                    window.location.href = 'packages.html'; 
                    alert('Failed to delete package.');
                    // Optionally display more detailed error information
                    if (xhr.responseJSON && xhr.responseJSON.message) {
                        console.error("Server error message:", xhr.responseJSON.message);
                    }
                }
            });
        }
    };
 
 

    fetchPackageDetails(packageId); // Start the process by fetching package details




});
