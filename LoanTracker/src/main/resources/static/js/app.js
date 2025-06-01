// js/app.js
console.log('app.js loaded');

window.addEventListener('load', initForm);

function initForm() {
  const params = new URLSearchParams(window.location.search);
  const appId  = params.get('id');

  const saveBtn   = qs('#formActionBtn');
  const updateBtn = qs('#updateBtn');
  const enableSw  = qs('#enableSwitch');

  /* mode switch ------------------------------------------------ */
  if (appId) {                          // --- edit mode ---
    saveBtn.classList.add('d-none');
    updateBtn.classList.remove('d-none');
    loadApplication(appId);
  } else {                              // --- create mode ---
    saveBtn.classList.remove('d-none');
    updateBtn.classList.add('d-none');
  }

  /* form submit ------------------------------------------------ */
  qs('#loanAppForm').addEventListener('submit', e => {
    e.preventDefault();
    appId ? updateApplication(appId) : addApplication();
  });

  /* static select options ------------------------------------- */
  fillSelect('#loanType', ['FHA', 'VA', 'Conventional', 'USDA']);
  fillSelect('#purpose',  ['Purchase', 'Refi', 'Cash-Out']);
  fillSelect('#status',   ['Submitted', 'Processing', 'Approved',
                           'CTC', 'Docs Out', 'Funded']);
}

/* -------- READ one record ------------------------------------ */
function loadApplication(id) {
  send('GET', `api/applications/${id}`, null, app => {
    set('#loanNumber'     , app.loanNumber);
    set('#propertyAddress', app.propertyAddress);
    set('#loanAmount'     , app.loanAmount);
    set('#loanType'       , app.loanType);
    set('#purpose'        , app.purpose);
    set('#submittedDate'  , app.submittedDate);
    set('#status'         , app.status);

    const sw = qs('#enableSwitch');
    if (sw) sw.checked = !!app.enable;

    if (app.borrower) {
      set('#firstName' , app.borrower.firstName);
      set('#lastName'  , app.borrower.lastName);
      set('#email'     , app.borrower.email);
      set('#phone'     , app.borrower.phone);
      set('#borrowerId', app.borrower.id);        // NEW line
    }
  });
}

/* -------- CREATE --------------------------------------------- */
function addApplication() {
  send('POST', 'api/applications', buildPayload(), created => {
	toast('Application created ✓');
    window.location.href = `/app.html?id=${created.id}`;
  });
}

/* -------- UPDATE --------------------------------------------- */
function updateApplication(id) {
  send('PUT', `api/applications/${id}`, buildPayload(), () => {
toast('Application updated ✓');
    loadApplication(id);
  });
}

/* -------- helpers -------------------------------------------- */
function buildPayload() {
  return {
    loanNumber      : get('#loanNumber'),
    propertyAddress : get('#propertyAddress'),
    loanAmount      : Number(get('#loanAmount')),
    loanType        : get('#loanType'),
    purpose         : get('#purpose'),
    submittedDate   : get('#submittedDate'),
    status          : get('#status'),
    enable          : qs('#enableSwitch') ? qs('#enableSwitch').checked : true,
    borrower: {
      firstName : get('#firstName'),
      lastName  : get('#lastName'),
      email     : get('#email'),
      phone     : get('#phone')
    }
  };
}

function toast(msg) {
  qs('#toastMsg').textContent = msg;
  const t = new bootstrap.Toast(qs('#toastBox'), { delay: 1800 });
  t.show();
}

function send(method, url, body, ok) {
  const xhr = new XMLHttpRequest();
  xhr.open(method, url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = () => {
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

/* DOM utils ---------------------------------------------------- */
const qs  = sel => document.querySelector(sel);
const set = (sel, val) => qs(sel) && (qs(sel).value = val ?? '');
const get = sel => (qs(sel) ? qs(sel).value.trim() : '');

function fillSelect(selector, options) {
  const sel = qs(selector);
  if (!sel || sel.options.length) return;
  options.forEach(opt => {
    const o = document.createElement('option');
    o.text = o.value = opt;
    sel.add(o);
  });
}



