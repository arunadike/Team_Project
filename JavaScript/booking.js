document.addEventListener("DOMContentLoaded", function () {
  // Initialize date pickers
  flatpickr("#departureDate", {
    dateFormat: "D, M d",
    minDate: "today",
  });

  flatpickr("#returnDate", {
    dateFormat: "D, M d",
    minDate: "today",
  });

  // Trip type toggle
  const oneWayBtn = document.getElementById("oneWayBtn");
  const roundTripBtn = document.getElementById("roundTripBtn");
  const returnDateCol = document.getElementById("returnDateCol");

  let isRoundTrip = false;

  oneWayBtn.addEventListener("click", function () {
    isRoundTrip = false;
    oneWayBtn.classList.add("btn-one-way");
    oneWayBtn.classList.remove("btn-light");
    roundTripBtn.classList.add("btn-light");
    roundTripBtn.classList.remove("btn-one-way");
    returnDateCol.style.display = "none";
  });

  roundTripBtn.addEventListener("click", function () {
    isRoundTrip = true;
    roundTripBtn.classList.add("btn-one-way");
    roundTripBtn.classList.remove("btn-light");
    oneWayBtn.classList.add("btn-light");
    oneWayBtn.classList.remove("btn-one-way");
    returnDateCol.style.display = "block";
  });

  // Initialize as one-way
  oneWayBtn.click();

  // Search button functionality
  
});
