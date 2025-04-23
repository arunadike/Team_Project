/**
 * 
 */
function fetchUsers() {
    fetch("http://localhost:8081/api/person") 
        .then(response => response.json()) 
        .then(data => {
            let output = "<ul>";
            data.forEach(user => {
                output += `<li>ID: ${user.name}, Name: ${user.age}</li>`;
            });
            output += "</ul>";
            document.getElementById("output").innerHTML = output;
        })
        .catch(error => console.error("Error fetching users:", error));
}