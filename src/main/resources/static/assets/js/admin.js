
<!-- ==== Showing Greeting ==== -->
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

<!-- ==== Reload Gallery Section when a collection is selected ==== -->
// document.querySelectorAll('.admin-collection').forEach(item => {
//   item.addEventListener('click', function(e){
//     e.preventDefault();
//
//     const galleryName = this.getAttribute('data-gallery');
//
//     // Update hidden input field with selected gallery
//     document.getElementById('selectedCollection').value = galleryName;
//
//     // Submit the form to refresh photo section
//     document.getElementById('galleryForm').submit();
//   });
// });

<!-- ==== JavaScript to disable/enable + button when Collection is not selected ==== -->
document.addEventListener('DOMContentLoaded', function() {

  const dropDownItems = document.querySelectorAll('.dropdown-item');
  const selectedGalleryName = document.getElementById('selectedGalleryName');
  const addNewItemsButton = document.getElementById('addNewItems');
  const hiddenInput = document.getElementById('selectedCollection');
  const galleryForm = document.getElementById('galleryForm');

  if(hiddenInput.value){
    addNewItemsButton.classList.remove('disabled-link');
    console.log('Gallery selected on load: ', hiddenInput.value);
  }

  dropDownItems.forEach(item => {
    item.addEventListener('click', function(event) {
      event.preventDefault();

      // Get the selected gallery name
      const galleryName = this.getAttribute('data-gallery');

      // Update the displayed gallery name
      selectedGalleryName.textContent = galleryName;

      // Update hidden input value
      hiddenInput.value = galleryName;

      // Enable the + button by removing the disabled-link class
      addNewItemsButton.classList.remove('disabled-link');

      console.log('Selected Gallery: ', galleryName);
      console.log('Hidden input value: ', hiddenInput.value);
      console.log('addNewItems button class list: ', addNewItemsButton.classList);

      // Submit the form to refresh photo section
      galleryForm.submit();
    });
  });
});
