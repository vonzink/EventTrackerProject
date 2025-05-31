function loadHTMLPartial(id, file) {
  fetch(file)
    .then(response => response.text())
    .then(data => {
      document.getElementById(id).innerHTML = data;
    })
    .catch(err => {
      console.error(`Error loading ${file}:`, err);
    });
}

window.addEventListener('load', function () {
//  loadHTMLPartial('nav-placeholder', 'partials/nav.html');
//  loadHTMLPartial('footer-placeholder', 'partials/footer.html');
  // loadHTMLPartial('menu-placeholder', 'partials/menu.html'); // if needed
});