document.addEventListener("DOMContentLoaded", function () {
  const oneWayBtn = document.querySelector(".btn-one-way");
  const roundTripBtn = document.querySelector(".btn-light");
  const flightBoxes = document.querySelectorAll(".flight-box");
  const modal = new bootstrap.Modal(document.getElementById("flightModal"));
  const modalInput = document.getElementById("modalInput");
  const datePicker = document.getElementById("datePicker");
  const saveDetailsBtn = document.getElementById("saveDetails");
  let currentBox = null;

  // Initialize Flatpickr
  flatpickr("#datePicker", {
    dateFormat: "D, M d", // Format: Wed, Mar 26
    minDate: "today", // Disable past dates
  });

  let tripType = "one-way";

  oneWayBtn.addEventListener("click", function () {
    tripType = "one-way";
    oneWayBtn.classList.add("btn-one-way");
    oneWayBtn.classList.remove("btn-light");
    roundTripBtn.classList.add("btn-light");
    roundTripBtn.classList.remove("btn-one-way");
  });

  roundTripBtn.addEventListener("click", function () {
    tripType = "round-trip";
    roundTripBtn.classList.add("btn-one-way");
    roundTripBtn.classList.remove("btn-light");
    oneWayBtn.classList.add("btn-light");
    oneWayBtn.classList.remove("btn-one-way");
  });

  flightBoxes.forEach((box) => {
    box.addEventListener("click", function () {
      currentBox = box;
      if (box.getAttribute("data-target") === "date") {
        // Show date picker for date fields
        modalInput.style.display = "none";
        datePicker.style.display = "block";
      } else {
        // Show text input for non-date fields
        modalInput.style.display = "block";
        datePicker.style.display = "none";
      }
      modal.show();
    });
  });

  saveDetailsBtn.addEventListener("click", function () {
    if (currentBox.getAttribute("data-target") === "date") {
      // Save date from date picker
      if (datePicker.value) {
        currentBox.innerHTML = `📅 ${datePicker.value}`;
        modal.hide();
      }
    } else {
      // Save text from input
      if (modalInput.value) {
        currentBox.innerHTML = modalInput.value;
        modal.hide();
      }
    }
  });

  document.querySelector(".promo-box").addEventListener("click", function () {
    alert("Promo Code Applied Successfully!");
  });
});
