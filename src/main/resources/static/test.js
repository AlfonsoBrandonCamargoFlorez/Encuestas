
// manage cross erasing input values
var clears = document.querySelectorAll(".clear");
var inputs = document.querySelectorAll("input");

for (let i=0; i<3; i++) {
  inputs[i].addEventListener("input", function(e) {
    if (e.target.value.length > 0) {
      clears[i].style.display = "block";
    }
    else {
      clears[i].style.display = "none";
    }
  });

  clears[i].addEventListener("click", function() {
    inputs[i].value = "";
    this.style.display = "none";
  });
}
