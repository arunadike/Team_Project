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
  document.getElementById("searchBtn").addEventListener("click", function () {
    const departure = document.getElementById("departure").value;
    const destination = document.getElementById("destination").value;
    const departureDate = document.getElementById("departureDate").value;
    const returnDate = isRoundTrip
      ? document.getElementById("returnDate").value
      : "";
    const passengers = document.getElementById("passengers").value;

    if (
      !departure ||
      !destination ||
      !departureDate ||
      (isRoundTrip && !returnDate)
    ) {
      alert("Please fill all required fields");
      return;
    }

    alert(
      `Searching flights from ${departure} to ${destination}\nDeparture: ${departureDate}\n${isRoundTrip ? "Return: " + returnDate : "One Way"}\nPassengers: ${passengers}`,
    );
  });
});
