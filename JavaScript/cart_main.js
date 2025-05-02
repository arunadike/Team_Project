$(document).ready(function() {
    let cartItems = [];
    let bookingIdMap = {};
    const jwtToken = localStorage.getItem("JWT"); 

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
                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1 d-flex justify-content-between align-items-center">
                        <h6 class="mb-0 price-item" data-cart-item-id="${item.cartItemID}">Rs. ${(itemPrice * quantity).toFixed(2)}</h6>
                        <div class="d-flex ms-2">
                            <button class="btn btn-primary btn-sm me-1 save-item" style="border-radius: 2px;"
                                data-mdb-tooltip-init title="Save item" data-cart-item-id="${item.cartItemID}">
                                <i class="bi bi-save"></i>
                            </button>
                            <button class="btn btn-danger btn-sm me-1 delete-item" style="border-radius: 2px;background-color:red"
                                data-mdb-tooltip-init title="Remove item" data-cart-item-id="${item.cartItemID}">
                                <i class="bi bi-trash"></i>
                            </button>
                            <button class="btn btn-success btn-sm me-1 initiate-payment-btn" style="border-radius: 2px;background-color:green"
                                data-cart-item-id="${item.cartItemID}"
                                data-package-id="${item.package1.packageId}"
                                data-price="${(itemPrice * quantity).toFixed(2)}">
                                <i class="bi bi-check-circle"></i> Book Now
                            </button>
                            <button class="btn btn-info btn-sm review-btn" data-package-id="${item.package1.packageId}" data-booking-id="${bookingIdMap[item.cartItemID]}">Review</button>
                        </div>
                    </div>
                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                        <a href="#!" class="text-muted remove-all-item" data-cart-item-id="${item.cartItemID}"><i class="fas fa-times"></i></a>
                    </div>
                </div>
                <hr class="my-4">
            `;
            cartItemsContainer.append(cartItemHTML);
        });

        updateSummary(totalItems, totalPrice);
    }

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

    $(document).on('click', '.delete-item', function() {
        const cartItemId = $(this).data('cart-item-id');
        const cartItemRow = $(this).closest('.row');

        $.ajax({
            url: `http://localhost:8081/cart/delete/${cartItemId}`,
            method: 'DELETE',
            headers: { // Add the headers option
                "Authorization": "Bearer " + jwtToken
            },
            success: function(response) {
                console.log("Item deleted successfully:", response);
                cartItemRow.remove();
                cartItems = cartItems.filter(item => item.cartItemID !== cartItemId);
                delete bookingIdMap[cartItemId]; // Remove bookingId from the map
                updateCartDisplay();
            },
            error: function(xhr, status, error) {
                console.error("Error deleting item:", status, error);
                alert("Failed to remove item from cart.");
            }
        });
    });

    $(document).on('change', '.quantity-input', function() {
        const cartItemId = $(this).data('cart-item-id');
        const newQuantity = parseInt($(this).val());
        const cartItemRow = $(this).closest('.row');
        const priceElement = cartItemRow.find('.price-item');
        const itemDataIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);

        if (itemDataIndex !== -1) {
            cartItems[itemDataIndex].noOfPersons = newQuantity;
            const itemPrice = parseFloat(cartItems[itemDataIndex].package1.price) || 0;
            const insurance = cartItems[itemDataIndex].insurance || false;
            const updatedPrice = itemPrice * newQuantity + (insurance ? 500 * newQuantity : 0);
            priceElement.text(`Rs. ${updatedPrice.toFixed(2)}`);
            updateCartDisplay();
        }
    });

    $(document).on('change', '.insurance-checkbox', function() {
        const cartItemId = $(this).data('cart-item-id');
        const isChecked = $(this).prop('checked');
        const cartItemRow = $(this).closest('.row');
        const priceElement = cartItemRow.find('.price-item');
        const itemDataIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);

        if (itemDataIndex !== -1) {
            cartItems[itemDataIndex].insurance = isChecked;
            console.log(`Insurance for item ${cartItemId} is now: ${isChecked}`);

            const quantity = parseInt(cartItems[itemDataIndex].noOfPersons) || 1;
            const basePrice = parseFloat(cartItems[itemDataIndex].package1.price) || 0;
            const insuranceCostPerItem = 500.00;
            const updatedItemPrice = basePrice * quantity + (isChecked ? insuranceCostPerItem * quantity : 0);
            priceElement.text(`Rs. ${updatedItemPrice.toFixed(2)}`);
            updateCartDisplay();
        }
    });

    $(document).on('click', '.save-item', function() {
        const cartItemId = $(this).data('cart-item-id');
        const itemToUpdate = cartItems.find(item => item.cartItemID === cartItemId);

        if (itemToUpdate) {
            $.ajax({
                url: `http://localhost:8081/cart/updateCart/${cartItemId}`,
                method: 'PUT',
                headers: { // Add the headers option
                    "Authorization": "Bearer " + jwtToken
                },
                contentType: 'application/json',
                data: JSON.stringify({
                    noOfPersons: itemToUpdate.noOfPersons,
                    insurance: itemToUpdate.insurance,
                    package1: { packageId: itemToUpdate.package1.packageId } // Ensure packageId is sent
                }),
                success: function(response) {
                    console.log(`Item ${cartItemId} updated successfully:`, response);
                    alert(`Item ${cartItemId} saved successfully!`);
                },
                error: function(xhr, status, error) {
                    console.error(`Error updating item ${cartItemId}:`, status, error);
                    alert(`Failed to save item ${cartItemId}.`);
                }
            });
        } else {
            console.warn(`Cart item with ID ${cartItemId} not found in local cartItems array.`);
        }
    });

    $(document).on('click', '.initiate-payment-btn', function() {
        const cartItemId = $(this).data('cart-item-id');
        const packageId = $(this).data('package-id');
        const amount = parseFloat($(this).data('price'));
        const itemToBook = cartItems.find(item => item.cartItemID === cartItemId);

        if (itemToBook) {
            var options = {
                "key": "rzp_test_XrIUc52C7IOx6I",
                "amount": amount * 100,
                "currency": "INR",
                "name": "Adventure Awaits Travel",
                "description": itemToBook.package1.title,
                "order_id": "",
                "handler": function (response) {
                    console.log("Razorpay Success Response:", response);
                    createBookingAndHandlePayment('Paid', response.razorpay_payment_id, cartItemId, packageId, amount, itemToBook);
                },
                "prefill": {
                    "name": "Dikshit",
                    "email": "dikshit.sharma@example.com",
                    "contact": "6712527522"
                },
                "theme": {
                    "color": "#3399cc"
                }
            };
            var rzp1 = new Razorpay(options);
            rzp1.open();

            rzp1.on('payment.failed', function (response){
                console.log("Razorpay Failed Response:", response);
                createBookingAndHandlePayment('Failed', response.error.reason, cartItemId, packageId, amount, itemToBook);
                alert('Payment failed. Please try again.');
            });
        } else {
            console.warn(`Cart item with ID ${cartItemId} not found.`);
        }
    });

    function createBookingAndHandlePayment(paymentStatus, paymentId, cartItemId, packageId, totalPrice, cartItemDetails) {
        $.ajax({
            url: 'http://localhost:8081/booking',
            method: 'POST',
            headers: { // Add the headers option
                "Authorization": "Bearer " + jwtToken
            },
            contentType: 'application/json',
            data: JSON.stringify({
                user: { userId: localStorage.getItem('userId') }, // Assuming userId is stored in localStorage
                package1: { packageId: packageId },
                orderDate: new Date().toISOString().slice(0, 10),
                price: totalPrice,
                paymentStatus: paymentStatus,
                paymentMethod: 'Razorpay',
            }),
            success: function(bookingResponse) {
                console.log("Booking created:", bookingResponse);
                const bookingId = bookingResponse.bookingId;
                bookingIdMap[cartItemId] = bookingId; // Store the bookingId
                if (paymentStatus === 'Paid') {
                    alert(`Booking successful! Payment ID: ${paymentId}`);
                } else {
                    alert(`Booking created with payment status: ${paymentStatus} (${paymentId})`);
                }
                //  Now call updateBookingPaymentStatus *after* you have the bookingId
                updateBookingPaymentStatus(cartItemId, paymentStatus, paymentId, bookingId);
            },
            error: function(xhr, status, error) {
                console.error("Error creating booking:", status, error);
                alert("Failed to create booking.");
            }
        });
    }

    function updateBookingPaymentStatus(cartItemId, paymentStatus, paymentId, bookingId) {
        $.ajax({
            url: `http://localhost:8081/bookingPayment/updateStatusByCartItem/${cartItemId}`,
            method: 'PUT',headers: { // Add the headers option
                "Authorization": "Bearer " + jwtToken
            },
            contentType: 'application/json',
            data: JSON.stringify({
                paymentStatus: paymentStatus,
                paymentMethod: 'Razorpay',
                transactionId: paymentId,
                booking: { bookingId: bookingId } // Include bookingId here
            }),
            success: function(paymentResponse) {
                console.log("BookingPayment updated:", paymentResponse);
            },
            error: function(xhr, status, error) {
                console.error("Error updating BookingPayment:", status, error);
            }
        });
    }

    $(document).on('click', '.review-btn', function() {
        const packageId = $(this).data('package-id');
        const cartItemId = $(this).closest('.row').data('cart-item-id'); // Assuming cart item ID is available on the row
        const userId = parseInt(localStorage.getItem("userId")); // Hardcoded userId, consider fetching dynamically
        console.log("User ID:", userId);
        if (cartItemId) {
            $.ajax({
                url: `http://localhost:8081/bookingGet`, // Using the /bookingGet endpoint
                method: 'GET',
                headers: { // Add the headers option
                    "Authorization": "Bearer " + jwtToken
                },
                dataType: 'json',
                success: function(bookingData) {
                    console.log("All booking data fetched for review:", bookingData);
                    if (bookingData && bookingData.length > 0) {
                        // Filter bookings to find the one associated with the current cart item and user
                        const relevantBooking = bookingData.find(booking =>
                            booking.package1 && booking.package1.packageId === parseInt(packageId) &&
                            booking.user && booking.user.userId === userId
                        );
                        console.log(relevantBooking);

                        if (relevantBooking) {
                            const bookingId = relevantBooking.bookingId;
                            window.location.href = `webfeedback.html?packageId=${packageId}&bookingPaymentId=${bookingId}&userId=${userId}`;
                        } else {
                            console.warn(`No booking found for package ID: ${packageId}, cart item ID: ${cartItemId}, and user ID: ${userId}`);
                            alert("No booking information available for review for this item. bookingID:");
                        }
                    } else {
                        console.warn("No booking data found.");
                        alert("No booking information available for review.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching all booking data for review:", status, error);
                    alert("Failed to fetch booking information for review.");
                }
            });
        } else {
            console.warn("Cart item ID not found for review button.");
            alert("Could not retrieve booking information for review.");
        }
    });

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

    $.ajax({
        url: 'http://localhost:8081/cart/cartGet',
        method: 'GET',
        headers: { // Add the headers option
            "Authorization": "Bearer " + jwtToken
        },
        dataType: 'json',
        success: function(data) {
            console.log("Cart items fetched:", data);
            cartItems = data;
            // Fetch booking IDs for the cart items
            $.ajax({
                url: 'http://localhost:8081/booking/bookingGet',
                method: 'GET',
                dataType: 'json',
                success: function(bookingData) {
                    console.log("Bookings fetched:", bookingData);
                    bookingData.forEach(booking => {
                        if (booking.cartItem && booking.cartItem.cartItemID) {
                            bookingIdMap[booking.cartItem.cartItemID] = booking.bookingId;
                        }
                    });
                    renderCartItems(cartItems);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching bookings:", status, error);
                    renderCartItems(cartItems); // Render cart items even if booking fetch fails
                }
            });
        },
        error: function(xhr, status, error) {
            console.error("Error fetching cart items:", status, error);
            $("#cart-items-container").html('<p>Failed to load cart items.</p>');
            updateSummary(0, 0);
        }
    });
});
