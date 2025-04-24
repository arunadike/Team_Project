$(document).ready(function() {
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
            const itemPrice = parseFloat(item.price) || 0;
            const quantity = parseInt(item.noOfPersons) || 1;

            totalItems += quantity;
            totalPrice += itemPrice * quantity;

            const cartItemHTML = `
          <div class="row mb-4 d-flex justify-content-between align-items-center" data-cart-item-id="${item.cartItemID}">
            <div class="col-md-2 col-lg-2 col-xl-2">
              <a href="#"><img src="${item.package1.imageUrl || '/Pages/assets/default-image.jpg'}" class="img-fluid rounded-3" alt="${item.package1.title || 'Package Image'}"></a>
            </div>
            <div class="col-md-3 col-lg-3 col-xl-3">
              <h6 class="text-dark">${item.package1.title || 'Package Title'}</h6>
              <h6 class="mb-0">${item.package1.description || ''}</h6>
            </div>
            <div class="col-md-3 col-lg-3 col-xl-3" style="max-width: 100px;">
              <h6 class="mb-0">Persons:</h6>
            </div>
            <div class="col-md-1 col-lg-1 col-xl-2 d-flex" style="width: 70px;">
              <i class="fas fa-minus change-quantity" data-action="decrement" data-cart-item-id="${item.cartItemID}"></i>
              <input id="quantity-${item.cartItemID}" min="1" name="quantity" value="${quantity}" type="number"
                class="form-control form-control-sm quantity-input" data-cart-item-id="${item.cartItemID}" />
              <i class="fas fa-plus change-quantity" data-action="increment" data-cart-item-id="${item.cartItemID}"></i>
            </div>
            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1 d-flex justify-content-between align-items-center">
              <h6 class="mb-0 price-item" data-cart-item-id="${item.cartItemID}">Rs. ${(itemPrice * quantity).toFixed(2)}</h6>
              <button class="btn btn-primary btn-sm ms-2 delete-item" style="border-radius: 2px;"
                data-mdb-tooltip-init title="Remove item" data-cart-item-id="${item.cartItemID}">
                <i class="bi bi-trash"></i>
              </button>
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

        // Make an AJAX DELETE request to your backend
        $.ajax({
            url: `http://localhost:8081/cart/delete/${cartItemId}`, // Adjust the URL as needed
            method: 'DELETE',
            success: function (response) {
                console.log("Item deleted successfully:", response);
                // If the backend successfully deleted the item, remove it from the UI
                cartItemRow.remove();

                // Update the cartItems array and re-render the summary
                cartItems = cartItems.filter(item => item.cartItemID !== cartItemId);
                let totalItems = 0;
                let totalPrice = 0;
                cartItems.forEach(item => {
                    totalItems += parseInt(item.noOfPersons) || 1;
                    totalPrice += (parseFloat(item.price) || 0) * (parseInt(item.noOfPersons) || 1);
                });
                updateSummary(totalItems, totalPrice);
            },
            error: function (xhr, status, error) {
                console.error("Error deleting item:", status, error);
                // Optionally display an error message to the user
                alert("Failed to remove item from cart.");
            }
        });
    });

    // --- Event listener for changing quantity ---
    $(document).on('click', '.change-quantity', function () {
        const action = $(this).data('action');
        const cartItemId = $(this).data('cart-item-id');
        const quantityInput = $(this).siblings('.quantity-input');
        let currentQuantity = parseInt(quantityInput.val());

        if (action === 'increment') {
            quantityInput.val(currentQuantity + 1).trigger('change');
        } else if (action === 'decrement' && currentQuantity > 1) {
            quantityInput.val(currentQuantity - 1).trigger('change');
        }
    });

    $(document).on('change', '.quantity-input', function () {
        const cartItemId = $(this).data('cart-item-id');
        const newQuantity = parseInt($(this).val());
        const cartItemRow = $(this).closest('.row');
        const priceElement = cartItemRow.find('.price-item');
        const itemData = cartItems.find(item => item.cartItemID === cartItemId);

        if (itemData) {
            const itemPrice = parseFloat(itemData.price) || 0;
            const updatedPrice = itemPrice * newQuantity;
            priceElement.text(`Rs. ${updatedPrice.toFixed(2)}`);

            let totalItems = 0;
            let totalPrice = 0;
            $('.quantity-input').each(function () {
                totalItems += parseInt($(this).val());
                const itemId = $(this).data('cart-item-id');
                const currentItemData = cartItems.find(item => item.cartItemID === itemId);
                if (currentItemData) {
                    totalPrice += (parseFloat(currentItemData.price) || 0) * parseInt($(this).val());
                }
            });
            updateSummary(totalItems, totalPrice);

            // Make an AJAX PUT/POST request to update quantity on the backend
            $.ajax({
                url: `http://localhost:8081/cart/update/${cartItemId}`, // Adjust the URL as needed
                method: 'PUT', // Or POST, depending on your API
                contentType: 'application/json',
                data: JSON.stringify({ noOfPersons: newQuantity }),
                success: function (response) {
                    console.log("Quantity updated successfully:", response);
                    // Optionally update the cartItems array if the backend returns updated data
                    const updatedItemIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);
                    if (updatedItemIndex !== -1) {
                        cartItems[updatedItemIndex].noOfPersons = newQuantity;
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error updating quantity:", status, error);
                    // Optionally revert the quantity in the UI or display an error message
                    alert("Failed to update quantity in cart.");
                    // You might want to reload the cart to ensure data consistency
                }
            });
        }
    });

    // --- Fetch cart items from the API and render them ---
    $.ajax({
        url: 'http://localhost:8081/cart/cartGet',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log("Successfully fetched cart items:", data);
            cartItems = data;
            renderCartItems(cartItems);
            // Call updateSummary here after the initial rendering
        },
        error: function (xhr, status, error) {
            console.error("Error fetching cart items:", status, error);
            $("#cart-items-container").html('<p class="text-danger">Failed to load cart items.</p>');
            updateSummary(0, 0);
        }
    });
});

$(document).ready(function() {
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
        const itemPrice = parseFloat(item.price) || 0;
        const quantity = parseInt(item.noOfPersons) || 1;

        totalItems += quantity;
        totalPrice += itemPrice * quantity;

        const cartItemHTML = `
          <div class="row mb-4 d-flex justify-content-between align-items-center" data-cart-item-id="${item.cartItemID}">
            <div class="col-md-2 col-lg-2 col-xl-2">
              <a href="#"><img src="${item.package1.imageUrl || '/Pages/assets/default-image.jpg'}" class="img-fluid rounded-3" alt="${item.package1.title || 'Package Image'}"></a>
            </div>
            <div class="col-md-3 col-lg-3 col-xl-3">
              <h6 class="text-dark">${item.package1.title || 'Package Title'}</h6>
              <h6 class="mb-0">${item.package1.description || ''}</h6>
            </div>
            <div class="col-md-3 col-lg-3 col-xl-3" style="max-width: 100px;">
              <h6 class="mb-0">Persons:</h6>
            </div>
            <div class="col-md-1 col-lg-1 col-xl-2 d-flex" style="width: 70px;">
              <i class="fas fa-minus change-quantity" data-action="decrement" data-cart-item-id="${item.cartItemID}"></i>
              <input id="quantity-${item.cartItemID}" min="1" name="quantity" value="${quantity}" type="number"
                class="form-control form-control-sm quantity-input" data-cart-item-id="${item.cartItemID}" />
              <i class="fas fa-plus change-quantity" data-action="increment" data-cart-item-id="${item.cartItemID}"></i>
            </div>
            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1 d-flex justify-content-between align-items-center">
              <h6 class="mb-0 price-item" data-cart-item-id="${item.cartItemID}">Rs. ${(itemPrice * quantity).toFixed(2)}</h6>
              <button class="btn btn-primary btn-sm ms-2 delete-item" style="border-radius: 2px;"
                data-mdb-tooltip-init title="Remove item" data-cart-item-id="${item.cartItemID}">
                <i class="bi bi-trash"></i>
              </button>
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
    $(document).on('click', '.delete-item', function() {
      const cartItemId = $(this).data('cart-item-id');
      const cartItemRow = $(this).closest('.row');

      // Make an AJAX DELETE request to your backend
      $.ajax({
        url: `http://localhost:8081/cart/delete/${cartItemId}`, // Adjust the URL as needed
        method: 'DELETE',
        success: function(response) {
          console.log("Item deleted successfully:", response);
          // If the backend successfully deleted the item, remove it from the UI
          cartItemRow.remove();

          // Update the cartItems array and re-render the summary
          cartItems = cartItems.filter(item => item.cartItemID !== cartItemId);
          let totalItems = 0;
          let totalPrice = 0;
          cartItems.forEach(item => {
            totalItems += parseInt(item.noOfPersons) || 1;
            totalPrice += (parseFloat(item.price) || 0) * (parseInt(item.noOfPersons) || 1);
          });
          updateSummary(totalItems, totalPrice);
        },
        error: function(xhr, status, error) {
          console.error("Error deleting item:", status, error);
          // Optionally display an error message to the user
          alert("Failed to remove item from cart.");
        }
      });
    });

    // --- Event listener for changing quantity ---
    $(document).on('click', '.change-quantity', function() {
      const action = $(this).data('action');
      const cartItemId = $(this).data('cart-item-id');
      const quantityInput = $(this).siblings('.quantity-input');
      let currentQuantity = parseInt(quantityInput.val());

      if (action === 'increment') {
        quantityInput.val(currentQuantity + 1).trigger('change');
      } else if (action === 'decrement' && currentQuantity > 1) {
        quantityInput.val(currentQuantity - 1).trigger('change');
      }
    });

    $(document).on('change', '.quantity-input', function() {
      const cartItemId = $(this).data('cart-item-id');
      const newQuantity = parseInt($(this).val());
      const cartItemRow = $(this).closest('.row');
      const priceElement = cartItemRow.find('.price-item');
      const itemData = cartItems.find(item => item.cartItemID === cartItemId);

      if (itemData) {
        const itemPrice = parseFloat(itemData.price) || 0;
        const updatedPrice = itemPrice * newQuantity;
        priceElement.text(`Rs. ${updatedPrice.toFixed(2)}`);

        let totalItems = 0;
        let totalPrice = 0;
        $('.quantity-input').each(function() {
          totalItems += parseInt($(this).val());
          const itemId = $(this).data('cart-item-id');
          const currentItemData = cartItems.find(item => item.cartItemID === itemId);
          if (currentItemData) {
            totalPrice += (parseFloat(currentItemData.price) || 0) * parseInt($(this).val());
          }
        });
        updateSummary(totalItems, totalPrice);

        // Make an AJAX PUT/POST request to update quantity on the backend
        $.ajax({
          url: `http://localhost:8081/cart/update/${cartItemId}`, // Adjust the URL as needed
          method: 'PUT', // Or POST, depending on your API
          contentType: 'application/json',
          data: JSON.stringify({ noOfPersons: newQuantity }),
          success: function(response) {
            console.log("Quantity updated successfully:", response);
            // Optionally update the cartItems array if the backend returns updated data
            const updatedItemIndex = cartItems.findIndex(item => item.cartItemID === cartItemId);
            if (updatedItemIndex !== -1) {
              cartItems[updatedItemIndex].noOfPersons = newQuantity;
            }
          },
          error: function(xhr, status, error) {
            console.error("Error updating quantity:", status, error);
            // Optionally revert the quantity in the UI or display an error message
            alert("Failed to update quantity in cart.");
            // You might want to reload the cart to ensure data consistency
          }
        });
      }
    });

    // --- Fetch cart items from the API and render them ---
    $.ajax({
      url: 'http://localhost:8081/cart/cartGet',
      method: 'GET',
      dataType: 'json',
      success: function(data) {
        console.log("Successfully fetched cart items:", data);
        cartItems = data;
        renderCartItems(cartItems);
        // Call updateSummary here after the initial rendering
      },
      error: function(xhr, status, error) {
        console.error("Error fetching cart items:", status, error);
        $("#cart-items-container").html('<p class="text-danger">Failed to load cart items.</p>');
        updateSummary(0, 0);
      }
    });
   });
