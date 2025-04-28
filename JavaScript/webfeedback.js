<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Package Feedback Form</title>
    <link rel="stylesheet" href="webfeedback.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="nav_footer_feedback.css">
</head>

<body>
    <div class="feedback-form-cnt">
        <h2>Travel Package Feedback</h2>
        <form id="reviewForm" class="checkform">
            <div class="form-field">
                <label for="rating">Rating</label>
                <div class="star-rating">
                    <input type="radio" id="star1" name="rating" value="1" required><label for="star1"
                        title="1 star">&#9733;</label>
                    <input type="radio" id="star2" name="rating" value="2"><label for="star2"
                        title="2 stars">&#9733;</label>
                    <input type="radio" id="star3" name="rating" value="3"><label for="star3"
                        title="3 stars">&#9733;</label>
                    <input type="radio" id="star4" name="rating" value="4"><label for="star4"
                        title="4 stars">&#9733;</label>
                    <input type="radio" id="star5" name="rating" value="5"><label for="star5"
                        title="5 stars">&#9733;</label>
                </div>
            </div>

            <div class="form-field">
                <label for="comments">Comments</label>
                <textarea id="comments" name="comments" rows="4" required></textarea>
            </div>
            <div class="form-field">
                <input type="hidden" id="packageId" name="packageId">
                <input type="hidden" id="bookingPaymentId" name="bookingPaymentId">
                <input type="hidden" id="userId" name="userId">
                <button type="submit" class="box-btn">Submit</button>
            </div>
        </form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            // --- Get URL parameters ---
            const urlParams = new URLSearchParams(window.location.search);
            const bookingPaymentId = urlParams.get('bookingPaymentId');
            const userId = urlParams.get('userId');
            const packageId = urlParams.get('packageId');

            // --- Set the hidden field values ---
            $('#packageId').val(packageId);
            $('#bookingPaymentId').val(bookingPaymentId);
            $('#userId').val(userId);

            // --- Form submission ---
            $('#reviewForm').submit(function (event) {
                event.preventDefault();

                const rating = $('input[name="rating"]:checked').val();
                const comment = $('#comments').val();
                const packageId = $('#packageId').val();
                const bookingPaymentId = $('#bookingPaymentId').val();
                const userId = $('#userId').val();

                if (!rating) {
                    alert('Please select a rating.');
                    return;
                }

                if (!comment) {
                    alert('Please enter a comment.');
                    return;
                }

                const reviewData = {
                    rating: parseInt(rating),
                    reviewComment: comment,
                    package1: {
                        packageId: parseInt(packageId)
                    },
                    booking: {
                        bookingId: parseInt(bookingPaymentId)
                    },
                    user: {
                        userId: parseInt(userId)
                    }
                };

                $.ajax({
                    url: 'http://localhost:8081/api/reviewPost',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(reviewData),
                    success: function (response) {
                        console.log('Review submitted successfully:', response);
                        alert('Thank you for your review!');
                        window.location.href = 'cart.html';
                    },
                    error: function (xhr, status, error) {
                        console.error('Error submitting review:', status, error);
                        alert('Failed to submit your review. Please try again.');
                    }
                });
                console.log('Submit button clicked');
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            let cartItems = []; // Store the fetched cart items

            // --- Function to render cart items ---
            function renderCartItems(items) {
                const cartItemsContainer = $("#cart-items-container");
                cartItemsContainer.empty();

                if (!items || items.length === 0) {
                    cartItemsContainer.html('<p>Your cart is empty.</p>');
                    updateSummary(0, 0);
                    return;
                }

                let totalItems = 0;
                let totalPrice = 0;

                items.forEach(item => {
                    const itemPrice = parseFloat(item.package1.price) || 0;
                    const quantity = parseInt(item.noOfPersons) || 1;
                    const insurance = item.insurance || false;
                    //const bookingPaymentId = 1001; // Removed hardcoded booking ID

                    totalItems += quantity;
                    totalPrice += item.package1.price * quantity;

                    const cartItemHTML = `
                        <div class="row mb-4 d-flex justify-content-between align-items-center" data-cart-item-id="${item.cartItemID}">
                            <div class="col-md-2 col-lg-2 col-xl-2">
                                <a href="#"><img src="${item.package1.imageUrl || '/Pages/assets/default-image.jpg'}" class="img-fluid rounded-3" alt="${item.package1.title || 'Package Image'}"></a>
                            </div>
                            <div class="col-md-3 col-lg-3 col-xl-3">
                                <h6 class="text-dark">${item.package1.title || 'Package Title'}</h6>
                                <h6 class="mb-0">${item.package1.description || ''}</h6>
                            </div>
                            <div class="col-md-3 col-lg-3 col-xl-3" style="max-width: 150px;">
                                <h6 class="mb-0">Persons:</h6>
                                <div class="d-flex" style="width: 100px;">
                                    <i class="fas fa-minus change-quantity" data-action="decrement" data-cart-item-id="${item.cartItemID}"></i>
                                    <input id="quantity-${item.cartItemID}" min="1" name="quantity" value="${quantity}" type="number"
                                        class="form-control form-control-sm quantity-input" data-cart-item-id="${item.cartItemID}" />
                                    <i class="fas fa-plus change-quantity" data-action="increment" data-cart-item-id="${item.cartItemID}"></i>
                                </div>
                                <div class="form-check mt-2">
                                    <input class="form-check-input insurance-checkbox" type="checkbox" value="" id="insurance-${item.cartItemID}" data-cart-item-id="${item.cartItemID}" ${item.insurance ? 'checked' : ''}>
                                    <label class="form-check-label" for="insurance-${item.cartItemID}">
                                        Insurance
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-2 col-lg-2 col-xl-2 d-flex justify-content-end align-items-center">
                                <h6 class="mb-0 price-item" data-cart-item-id="${item.cartItemID}">Rs. ${(itemPrice * quantity).toFixed(2)}</h6>
                                <button class="btn btn-primary btn-sm ms-2 save-item" style="border-radius: 2px;"
                                    data-mdb-tooltip-init title="Save item" data-cart-item-id="${item.cartItemID}">
                                    <i class="bi bi-save"></i>
                                </button>
                                <button class="btn btn-danger btn-sm ms-2 delete-item" style="border-radius: 2px;background-color:red"
                                    data-mdb-tooltip-init title="Remove item" data-cart-item-id="${item.cartItemID}">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                            <div class="col-md-2 col-lg-1 col-xl-1 text-end">
                                <button class="btn btn-info btn-sm review-btn" data-package-id="${item.package1.packageId}" data-booking-payment-id="${item.booking.bookingId}">Review</button>
                            </div>
                        </div>
                        <hr class="my-4">
                    `;
                    cartItemsContainer.append(cartItemHTML);
                });

                updateSummary(totalItems, totalPrice);
            }

            // --- Function to update the cart summary ---
            function updateSummary(totalItems, totalPrice) {
                $('.mb-0.text-muted').text(`${totalItems} items`);
                $('.d-flex.justify-content-between.mb-4 h5:first-child').text(`items ${totalItems}`);
                $('.d-flex.justify-content-between.mb-4 h5:last-child').text(`Rs. ${totalPrice.toFixed(2)}`);
                const addCharges = 15.00;
                $('.d-flex.justify-content-between.mb-5 h5:first-child').text(`Add charges`);
                $('.d-flex.justify-content-between.mb-5 h5:last-child').text(`Rs. ${addCharges.toFixed(2)}`);
                $('.d-flex.justify-content-between.mb-5:last-child h5:first-child').text(`Total amount`);
                $('.d-flex.justify-content-between.mb-5:last-child h5:last-child').text(`Rs. ${(totalPrice + addCharges).toFixed(2)}`);
            }

            // --- Event listener for deleting a single item ---
            $(document).on('click', '.delete-item', function () {
                const cartItemId = $(this).data('cart-item-id');
                const cartItemRow = $(this).closest('.row');

                $.ajax({
                    url: `http://localhost:8081/cart/delete/${cartItemId}`,
                    method: 'DELETE',
                    success: function (response) {
                        console.log("Item deleted successfully:", response);
                        cartItemRow.remove();
                        cartItems = cartItems.filter(item => item.cartItemID !== cartItemId);
                        updateCartDisplay();
                    },
                    error: function (xhr, status, error) {
                        console.error("Error deleting item:", status, error);
                        alert("Failed to remove item from cart.");
                    }
                });
            });

            // --- Event listener for changing quantity (using 'change' event on input) ---
            $(document).on('change', '.quantity-input', function () {
                const cartItemId = $(this).data('cart-item-id');
                const newQuantity = parseInt($(this).val());
                const cartItemRow = $(this).closest('.row');
                const priceElement = cartItemRow.find('.price-item');
                const itemDataIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);

                if (itemDataIndex !== -1) {
                    cartItems[itemDataIndex].noOfPersons = newQuantity;
                    const itemPrice = parseFloat(cartItems[itemDataIndex].package1.price) || 0;
                    const updatedPrice = itemPrice * newQuantity;
                    priceElement.text(`Rs. ${updatedPrice.toFixed(2)}`);
                    updateCartDisplay(); // Update summary after quantity change
                    // Backend update will happen on save
                }
            });

            // --- Event listener for insurance checkbox change ---
            $(document).on('change', '.insurance-checkbox', function () {
                const cartItemId = $(this).data('cart-item-id');
                const isChecked = $(this).prop('checked');
                const cartItemRow = $(this).closest('.row');
                const priceElement = cartItemRow.find('.price-item');
                const itemDataIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);

                if (itemDataIndex !== -1) {
                    cartItems[itemDataIndex].insurance = isChecked;
                    console.log(`Insurance for item ${cartItemId} is now: ${isChecked}`);

                    // --- Update the displayed price in the UI ---
                    const quantity = parseInt(cartItems[itemDataIndex].noOfPersons) || 1;
                    const basePrice = parseFloat(cartItems[itemDataIndex].package1.price) || 0;
                    let updatedItemPrice = basePrice * quantity;
                    const insuranceCostPerItem = 500.00;

                    if (isChecked) {
                        updatedItemPrice += insuranceCostPerItem * quantity;
                    }
                    priceElement.text(`Rs. ${updatedItemPrice.toFixed(2)}`);
                    updateCartDisplay(); // Update summary after insurance change
                    // Backend update will happen on save
                }
            });

            // --- Event listener for saving the cart item ---
            $(document).on('click', '.save-item', function () {
                const cartItemId = $(this).data('cart-item-id');
                console.log(cartItems);
                const itemToUpdate = cartItems.find(item => item.cartItemID === cartItemId);
                console.log(itemToUpdate);

                if (itemToUpdate) {
                    // Make an AJAX PUT/POST request to update the item on the backend
                    $.ajax({
                        url: `http://localhost:8081/cart/updateCart/${cartItemId}`, // Use the same update endpoint
                        method: 'PUT', // Or POST, depending on your API
                        contentType: 'application/json',
                        data: JSON.stringify({
                            noOfPersons: itemToUpdate.noOfPersons,
                            insurance: itemToUpdate.insurance,
                            package1: itemToUpdate.package1
                            // You might need to send other relevant data as well
                        }),
                        success: function (response) {
                            console.log(`Item ${cartItemId} updated successfully:`, response);
                            alert(`Item ${cartItemId} saved successfully!`);
                            // Optionally update the cartItems array with the response from the server
                        },
                        error: function (xhr, status, error) {
                            console.error(`Error updating item ${cartItemId}:`, status, error);
                            alert(`Failed to save item ${cartItemId}.`);
                        }
                    });
                } else {
                    console.warn(`Cart item with ID ${cartItemId} not found in local cartItems array.`);
                }
            });

            // --- Event listener for "Review" button ---
            $(document).on('click', '.review-btn', function () {
                const packageId = $(this).data('package-id');
                const bookingPaymentId = $(this).data('booking-payment-id'); // Get from data attribute
                const userId = 3; // Stays hardcoded.

                // Open the review form (assuming webfeedback.html is your review form)
                window.location.href = `webfeedback.html?packageId=${packageId}&bookingPaymentId=${bookingPaymentId}&userId=${userId}`;
            });

            // --- Function to re-render the cart summary ---
            function updateCartDisplay() {
                let totalItems = 0;
                let totalPrice = 0;
                cartItems.forEach(item => {
                    const quantity = parseInt(item.noOfPersons) || 1;
                    totalItems += quantity;
                    let itemBasePrice = parseFloat(item.package1.price) || 0;
                    let currentItemPrice = itemBasePrice * quantity;
                    const insuranceCostPerItem = 500.00;
                    if (item.insurance) {
                        currentItemPrice += insuranceCostPerItem * quantity;
                    }
                    totalPrice += currentItemPrice;
                });
                updateSummary(totalItems, totalPrice);
            }

            // --- Fetch cart items from the API and render them ---
            $.ajax({
                url: 'http://localhost:8081/cart/cartGet',
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    console.log("Successfully fetched cart items:", data);
                    cartItems = data;
                    renderCartItems(cartItems);
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching cart items:", status, error);
                    $("#cart-items-container").html('<p class="text-danger">Failed to load cart items.</p>');
                    updateSummary(0, 0);
                }
            });
        });
    </script>
</body>

</html>
