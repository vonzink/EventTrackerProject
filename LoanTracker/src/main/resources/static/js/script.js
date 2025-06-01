
console.log('domManipulation.js loaded');

let showEnabledOnly = true;                 

window.addEventListener('load', function () {
  console.log('DOM created');
  wireSearchAndButtons();                     
  wireToggleLink();                           
  loadApplications();                         
});

function wireSearchAndButtons() {
  let form = document.getElementById('searchForm');
  if (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault();
      let name = document.getElementById('searchName').value.trim();
      searchByLastName(name);
    });
  }

  let showAllBtn = document.getElementById('showAllBtn');
  if (showAllBtn) {
    showAllBtn.addEventListener('click', function (e) {
      e.preventDefault();
      loadApplications();                   
    });
  }
}
if (!app.enable) {
  row.classList.add('text-danger');
}
function wireToggleLink() {
  const link = document.getElementById('toggleEnableLink');
  if (!link) return;

  link.addEventListener('click', function (e) {
    e.preventDefault();
    showEnabledOnly = !showEnabledOnly;      
    link.textContent = showEnabledOnly ? 'Show disabled' : 'Show active';
    loadApplications();                 
  });
}

function loadApplications() {
  let url = showEnabledOnly
    ? 'api/applications/active'              
    : 'api/applications';                    
  sendRequest(url, displayApplications);
}

function searchByLastName(name) {
  if (!name) {
    alert('Enter a last name');
    return;
  }
  let url = 'api/applications/search/name?name=' + encodeURIComponent(name);
  sendRequest(url, displayApplications);
}

function sendRequest(url, onSuccess) {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);

  xhr.onreadystatechange = () => {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        onSuccess(JSON.parse(xhr.responseText));
      } else {
        renderError('Request failed');
      }
    }
  };
  xhr.send();
}

function displayApplications(apps) {
  let resultsDiv = document.getElementById('results');
  resultsDiv.innerHTML = '';

  if (!Array.isArray(apps) || apps.length === 0) {
    renderError('No applications found.');
    return;
  }

  let table = document.createElement('table');
  table.className = 'table table-bordered table-striped table-hover outer-table';


  let headerLabels = [
    'ID', 'Loan #', 'Borrower', 'Property Address', 'Loan Type',
    'Loan Purpose', 'Loan Amount', 'Date Submitted', 'Phone', 'E-mail', 'Status'
  ];
  let thead = document.createElement('thead');
  let headerRow = document.createElement('tr');
  headerLabels.forEach(function (lbl) {
    let th = document.createElement('th');
    th.textContent = lbl;
    headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);
  table.appendChild(thead);


  let tbody = document.createElement('tbody');
  apps.forEach(function (app) {
    let row = document.createElement('tr');

    if (!app.enable) row.classList.add('text-danger');

    let cells = [
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

    cells.forEach(function (text) {
      let td = document.createElement('td');
      td.textContent = text;
      row.appendChild(td);
    });
	
    row.addEventListener('click', function () {
      window.location.href = `/app.html?id=${app.id}`;
    });

    tbody.appendChild(row);
  });
  table.appendChild(tbody);

  let wrapper = document.createElement('div');
  wrapper.className = 'table-responsive';
  wrapper.appendChild(table);
  resultsDiv.appendChild(wrapper);
}

function renderErrorOldSchool(msg) {
  var resultsDiv = document.getElementById('results');
  while (resultsDiv.firstChild) {
    resultsDiv.removeChild(resultsDiv.firstChild);
  }
  var p = document.createElement('p');
  p.className = 'text-danger';
  p.appendChild(document.createTextNode(msg));
  resultsDiv.appendChild(p);
}
function highlightStatus(currentStatus) {
  const items = document.querySelectorAll('#statusBar .nav-link');
  items.forEach(item => {
    item.classList.remove('active');
    if (item.textContent.trim().toLowerCase() === currentStatus.toLowerCase()) {
      item.classList.add('active');
    }
  });
}