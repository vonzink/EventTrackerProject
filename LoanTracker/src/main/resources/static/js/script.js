// js/domManipulation.js
console.log('domManipulation.js loaded');

/* ------------------------------------------------------------------
   GLOBAL STATE
-------------------------------------------------------------------*/
let showEnabledOnly = true;                   // default view → hide disabled

/* ------------------------------------------------------------------
   BOOTSTRAP
-------------------------------------------------------------------*/
window.addEventListener('load', () => {
  console.log('DOM created');
  wireSearchAndButtons();                     // search form + "Show All" btn
  wireToggleLink();                           // "Show disabled / Show active"
  loadApplications();                         // initial table paint
});

/* ------------------------------------------------------------------
   WIRE SEARCH FORM & EXPLICIT 'SHOW ALL' BUTTON
-------------------------------------------------------------------*/
function wireSearchAndButtons() {
  const form = document.getElementById('searchForm');
  if (form) {
    form.addEventListener('submit', e => {
      e.preventDefault();
      const name = document.getElementById('searchName').value.trim();
      searchByLastName(name);
    });
  }

  const showAllBtn = document.getElementById('showAllBtn');
  if (showAllBtn) {
    showAllBtn.addEventListener('click', e => {
      e.preventDefault();
      loadApplications();                     // obeys current toggle state
    });
  }
}
if (!app.enable) {
  row.classList.add('text-danger');
}
/* ------------------------------------------------------------------
   WIRE THE SUBTLE ENABLE/DISABLE TOGGLE LINK
-------------------------------------------------------------------*/
function wireToggleLink() {
  const link = document.getElementById('toggleEnableLink');
  if (!link) return;

  link.addEventListener('click', e => {
    e.preventDefault();
    showEnabledOnly = !showEnabledOnly;       // flip flag
    link.textContent = showEnabledOnly ? 'Show disabled'
                                       : 'Show active';
    loadApplications();                       // repaint table
  });
}

/* ------------------------------------------------------------------
   FETCH AND DISPLAY APPLICATIONS
-------------------------------------------------------------------*/
function loadApplications() {
  const url = showEnabledOnly
    ? 'api/applications/active'               // only enabled records
    : 'api/applications';                     // enabled + disabled

  sendRequest(url, displayApplications);
}

/* ------------------------------------------------------------------
   SEARCH BY BORROWER LAST NAME
-------------------------------------------------------------------*/
function searchByLastName(name) {
  if (!name) {
    alert('Enter a last name');
    return;
  }
  const url = 'api/applications/search/name?name=' + encodeURIComponent(name);
  sendRequest(url, displayApplications);
}

/* ------------------------------------------------------------------
   GENERIC XHR WRAPPER
-------------------------------------------------------------------*/
function sendRequest(url, onSuccess) {
  const xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);

  xhr.onreadystatechange = () => {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        onSuccess(JSON.parse(xhr.responseText));
      } else {
        renderError('Request failed (' + xhr.status + ')');
      }
    }
  };
  xhr.send();
}

/* ------------------------------------------------------------------
   RENDER TABLE
-------------------------------------------------------------------*/
function displayApplications(apps) {
  const resultsDiv = document.getElementById('results');
  resultsDiv.innerHTML = '';

  if (!Array.isArray(apps) || apps.length === 0) {
    renderError('No applications found.');
    return;
  }

  const table = document.createElement('table');
  table.className = 'table table-bordered table-striped table-hover outer-table';

  /* header */
  const headerLabels = [
    'ID', 'Loan #', 'Borrower', 'Property Address', 'Loan Type',
    'Loan Purpose', 'Loan Amount', 'Date Submitted', 'Phone', 'E-mail', 'Status'
  ];
  const thead = document.createElement('thead');
  const headerRow = document.createElement('tr');
  headerLabels.forEach(lbl => {
    const th = document.createElement('th');
    th.textContent = lbl;
    headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);
  table.appendChild(thead);

  /* body */
  const tbody = document.createElement('tbody');
  apps.forEach(app => {
    const row = document.createElement('tr');

    /* red text for disabled rows */
    if (!app.enable) row.classList.add('text-danger');

    const cells = [
      app.id,
      app.loanNumber,
      app.borrower ? `${app.borrower.firstName} ${app.borrower.lastName}` : '',
      app.propertyAddress,
      app.loanType,
      app.purpose,
      '$' + Number(app.loanAmount).toLocaleString(),
      app.submittedDate,
      app.borrower ? app.borrower.phone  : '',
      app.borrower ? app.borrower.email  : '',
      app.status
    ];

    cells.forEach(text => {
      const td = document.createElement('td');
      td.textContent = text;
      row.appendChild(td);
    });

    /* click-through to detail page */
    row.addEventListener('click', () => {
      window.location.href = `/app.html?id=${app.id}`;
    });

    tbody.appendChild(row);
  });
  table.appendChild(tbody);

  /* wrap for responsiveness */
  const wrapper = document.createElement('div');
  wrapper.className = 'table-responsive';
  wrapper.appendChild(table);
  resultsDiv.appendChild(wrapper);
}

/* ------------------------------------------------------------------
   HELPER: ERROR DISPLAY
-------------------------------------------------------------------*/
function renderError(msg) {
  document.getElementById('results').innerHTML =
    `<p class="text-danger">${msg}</p>`;
}

/* ------------------------------------------------------------------
   HELPER: STATUS BAR (UNCHANGED)
-------------------------------------------------------------------*/
function highlightStatus(currentStatus) {
  const items = document.querySelectorAll('#statusBar .nav-link');
  items.forEach(item => {
    item.classList.remove('active');
    if (item.textContent.trim().toLowerCase() === currentStatus.toLowerCase()) {
      item.classList.add('active');
    }
  });
}