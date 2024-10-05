let a = ["Bao Nguyen", "Nguyen Nguyen", "Joe", "Chô", "Bao", "NVĐ", "Bun"];
document.querySelector("#admin-name").innerHTML = a[Math.floor(Math.random() * a.length)]; 
// Greeting Notice
let today = new Date();
let time = today.toLocaleString("en-US", {
  hour12: false,
  hour: "2-digit",
});

function greeting(a) {
  if (a < 12 && a >= 5) {
    document.querySelector("#greeting").innerHTML = "Good morning";
  } else if (a >= 12 && a < 17) {
    document.querySelector("#greeting").innerHTML = "Good afternoon";
  } else if (a >= 17 && a <= 22) {
    document.querySelector("#greeting").innerHTML = "Good evening";
  } else {
    document.querySelector("#greeting").innerHTML = "It's late! Sleep Sleep";
  }
}

greeting(time);
// end greeting notice

let collection = document.querySelectorAll(".admin-collection");
 


