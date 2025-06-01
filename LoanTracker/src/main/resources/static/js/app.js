console.log('donManipulation.js loaded');

function populateSelect(id, options) {
	const select = document.getElementById(id);
	select.innerHTML = '';
	options.forEach(option => {
		const opt = document.createElement('option');
		opt.value = option;
		opt.textContent = option;
		select.appendChild(opt);
	});
}

const purposes = ['Purchase', 'Refi'];
const loanTypes = ['FHA', 'Conventional', 'VA', 'USDA', 'JUMBO'];
const statuses = ['Lead', 'Application', 'Documentation', 'Underwriting', 'Approved', 'CTC', 'Funded'];

window.addEventListener('load', function () {
	console.log('DOM created');

	populateSelect('purpose', purposes);
	populateSelect('loanType', loanTypes);
	populateSelect('status', statuses);

	init();
});

function init() {
	console.log('in init()');

	document.getElementById('saveBtn').addEventListener('click', function (e) {
		e.preventDefault();
		const appData = collectFormData();
		const appId = getQueryParam('id');

		if (appId) {
			updateApplication(appId, appData);
		} else {
			addApplication(appData);
		}
	});
}

function getQueryParam(name) {
	return new URLSearchParams(window.location.search).get(name);
}

const appId = getQueryParam('id');

if (appId) {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', `/api/applications/${appId}`, true);
	xhr.onreadystatechange = function () {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				const app = JSON.parse(xhr.responseText);
				populateForm(app);
			} else {
				alert('Failed to load application.');
			}
		}
	};
	xhr.send();
}

function populateForm(app) {
	document.getElementById('loanNumber').value = app.loanNumber;
	document.getElementById('propertyAddress').value = app.propertyAddress;
	document.getElementById('loanAmount').value = app.loanAmount;
	document.getElementById('loanType').value = app.loanType;
	document.getElementById('purpose').value = app.purpose;
	document.getElementById('submittedDate').value = app.submittedDate;
	document.getElementById('status').value = app.status;

	// borrower
	document.getElementById('firstName').value = app.borrower.firstName;
	document.getElementById('lastName').value = app.borrower.lastName;
	document.getElementById('email').value = app.borrower.email;
	document.getElementById('phone').value = app.borrower.phone;
}

function updateApplication(appId, app) {
	const xhr = new XMLHttpRequest();
	xhr.open('PUT', `/api/applications/${appId}`);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function () {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				alert('Application updated successfully!');
			} else {
				alert('Failed to update application.');
			}
		}
	};
	xhr.send(JSON.stringify(app));
}

function addApplication(app) {
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/api/applications');
	xhr.setRequestHeader('Content-type', 'application/json');

	xhr.onreadystatechange = function () {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 201 || xhr.status === 200) {
				alert('Application created successfully!');
			} else {
				alert('Failed to create application.');
			}
		}
	};
	console.log('Sending PUT to:', `/api/applications/${appId}`);
	console.log('Payload:', app);
	xhr.send(JSON.stringify(app));
}











