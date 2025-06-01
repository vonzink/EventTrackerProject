
console.log('app.js loaded');

window.addEventListener('load', init);

function init() {
  let params  = new URLSearchParams(window.location.search);
  let appId   = params.get('id');
  let saveBtn   = document.querySelector('#formActionBtn');
  let updateBtn = document.querySelector('#updateBtn');

  if (appId) {                              
    saveBtn.classList.add('d-none');
    updateBtn.classList.remove('d-none');
    loadApplication(appId);
  } else {                                   
    saveBtn.classList.remove('d-none');
    updateBtn.classList.add('d-none');
  }

  document.querySelector('#loanAppForm').addEventListener('submit', function (e) {
    e.preventDefault();
    appId ? updateApplication(appId) : addApplication();
  });

  fillSelect('#loanType',['FHA','VA','Conventional','USDA']);
  fillSelect('#purpose',['Purchase','Refi','Cash-Out']);
  fillSelect('#status',['Submitted','Processing','Approved','CTC', 'Docs Out', 'Funded']);
}

function loadApplication(id) {
  send('GET',`api/applications/${id}`, null, function (app) {
    set('#loanNumber', app.loanNumber);
    set('#propertyAddress', app.propertyAddress);
    set('#loanAmount', app.loanAmount);
    set('#loanType', app.loanType);
    set('#purpose', app.purpose);
    set('#submittedDate', app.submittedDate);
    set('#status', app.status);

    let sw = document.querySelector('#enableSwitch');
    if (sw) sw.checked =!!app.enable;

    if (app.borrower) {
      set('#firstName', app.borrower.firstName);
      set('#lastName', app.borrower.lastName);
      set('#email', app.borrower.email);
      set('#phone', app.borrower.phone);
      set('#borrowerId', app.borrower.id);
    }
  });
}

function addApplication() {
  send('POST','api/applications', buildPayload(), function (created) {
    toast('Application created ✓');
    window.location.href = `/app.html?id=${created.id}`;
  });
}

function updateApplication(id) {
  send('PUT',`api/applications/${id}`, buildPayload(), function () {
    toast('Application updated ✓');
    loadApplication(id);
  });
}

let loanForm = document.querySelector('#loanAppForm');
function buildPayload() {
  return {

    loanNumber: loanForm.loanNumber.value,
    propertyAddress: loanForm.propertyAddress.value,
    loanAmount: Number(loanForm.loanAmount.value),
    loanType: loanForm.loanType.value,
    purpose: loanForm.purpose.value,
    submittedDate   : loanForm.submittedDate.value,
    status          : loanForm.status.value,

   
    enable: document.querySelector('#enableSwitch').checked,

    borrower: {
      id: Number(loanForm.borrowerId.value) || null,   // hidden input
      firstName: loanForm.firstName.value,
      lastName: loanForm.lastName.value,
      email: loanForm.email.value,
      phone: loanForm.phone.value
    }
  };
}

function toast(msg) {
  document.querySelector('#toastMsg').textContent = msg;
  new bootstrap.Toast(document.querySelector('#toastBox'), { delay: 1800 }).show();
}

function send(method, url, body, ok) {
  let xhr = new XMLHttpRequest();
  xhr.open(method, url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function () {
    if (xhr.readyState !== XMLHttpRequest.DONE) return;
    if (xhr.status >= 200 && xhr.status < 300) {
      ok(xhr.responseText ? JSON.parse(xhr.responseText) : null);
    } else {
      console.error(`${method} ${url} → ${xhr.status}`);
      alert('Request failed (' + xhr.status + ')');
    }
  };
  xhr.send(body ? JSON.stringify(body) : null);
}
function set(selector, value) {
  let element = document.querySelector(selector);
  if (element) element.value = (value !== null && value !== undefined) ? value : '';
}
function get(selector) {
  let element = document.querySelector(selector);
  return element ? element.value.trim() : '';
}
function fillSelect(selector, options) {
  let element = document.querySelector(selector);
  if (!element || element.options.length) return;
  options.forEach(function (opt) {
    let option = document.createElement('option');
    option.text = option.value = opt;
    element.add(option);
  });
}
