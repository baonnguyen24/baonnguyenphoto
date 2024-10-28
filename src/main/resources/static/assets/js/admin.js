document.addEventListener('DOMContentLoaded', function(){
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
});

let collection = document.querySelectorAll(".admin-collection");

<!-- ==== JavaScript to handle selection ==== -->
function selectCollection(collection) {
  // Update the dropdown button text
  document.getElementById('dropdownMenuButton').textContent = collection;
  // Store the selected value in the hidden input field for submission
  document.getElementById('selectedCollectionInput').textContent = collection;
}

// function setPhotoIdForDelete(photoId) {
//   document.getElementById('modalPhotoId').value = photoId;  // Set the photoId in the hidden input
// }
//
// function confirmDelete(){
//   const photoId = document.getElementById('modalPhotoId').value;
//   const deleteUrl = `/photo/${photoId}`;
//
//   fetch(deleteUrl, {method: 'DELETE'})
//       .then(response => {
//         if (response.ok) {
//           alert('Photo deleted!');
//           window.location.reload();
//         } else {
//           alert('Failed to delete photo');
//         }
//       }).catch(error => {
//         console.error('Error:', error);
//         alert('An error occurred.');
//       });
// }

 


