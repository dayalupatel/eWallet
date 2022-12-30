// Go to payment section
function changeSectionFromDashboard(section) {
    var active_btn = document.getElementsByClassName("active"); // find active btn            
    active_btn[0].className = active_btn[0].className.replace(" active", ""); // make deactive
       
    var nav_btn = document.getElementById(section); // make this to be active
    nav_btn.className += " active";
}

// Dynamically changing Section according to active link 
function changeSection(clicked_id) {
    var deleteSection = document.getElementsByClassName("active-section");
    var addSection = document.getElementsByClassName(clicked_id);
    
    deleteSection[0].className = deleteSection[0].className.replace(" active-section", "");
    addSection[0].className += " active-section";
    console.log(clicked_id+" is selected");
    return clicked_id;
}


// Add active class to the current button of sidebar
// var header = document.getElementById("nav-items");
var btns = document.getElementsByClassName("click-nav");
for (var i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function() {
    var current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });

}

function display(paraId, btnId) {
    var x = document.getElementsByClassName(paraId);
    var y = document.getElementById(btnId);

    console.log(x[0]);

    for(var i=0; i< x.length; i++) {
        if (x[i].style.display === 'none') {
            x[i].style.display = 'inline';
            y.innerHTML = "Show My Balance";
        } else {
            x[i].style.display = 'none';
            y.innerHTML = "Hide My Balance";
        }
    }
}
