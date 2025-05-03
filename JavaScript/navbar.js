$(document).ready(function () {
    role=localStorage.getItem('role');
    console.log("geet inside navbar.js");
    console.log(role);
    var link= "packages.html";
    if(role=="ROLE_AGENT"){
        link="agentcreation.html";
    }  
    const package=$("#package"); 
    console.log(package);
    package.attr("href",link);

});