console.log('domManipulation.js loaded');

window.addEventListener('load', function(e) {
	console.log('DOM created');
	init();
});

function init() {
	console.log('in init()');

	let form = document.getElementById('searchForm');
	if (form) {
		form.addEventListener('submit', function(e) {
			e.preventDefault();
			let name = document.getElementById('searchName').value;
			searchByLastName(name);
		});
	}
	let showAll = document.getElementById('showAllBtn');
	showAll.addEventListener("click", function(e) {
		searchByLastName();
	})
}

function searchByLastName(name) {
	let url;
	if (name) {
		url = 'api/applications/search/name?name=' + encodeURIComponent(name);

	} else {
		url = `api/applications/`;
	}
	let xhr = new XMLHttpRequest();

	xhr.open('GET', url, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let apps = JSON.parse(xhr.responseText);
				displayApplication(apps)
			} else {
				let resultsDiv = document.getElementById('results');
				resultsDiv.textContent = 'Error retrieving applications.';
			}
		}
	};
	xhr.send();
}

function displayApplication(apps) {
	let resultsDiv = document.getElementById('results');
	resultsDiv.innerHTML = '';
	if (apps.length === 0) {
		resultsDiv.textContent = 'No applications found.';
		return;
	}

	let table = document.createElement('table');
	table.className = 'table table-bordered table-striped table-hover  outer-table';

	let thead = document.createElement('thead');
	let headerRow = document.createElement('tr');
	let headers = [
		'ID', 'Borrower', 'Property Address', 'Loan Type', 'Loan Purpose',
		'Loan Amount', 'Date Submitted', 'Phone', 'E-mail', 'Status'
	];

	headers.forEach(text => {
		let th = document.createElement('th');
		th.textContent = text;
		headerRow.appendChild(th);
	});

	thead.appendChild(headerRow);
	table.appendChild(thead);

	let tbody = document.createElement('tbody');
	for (let app of apps) {
		let row = document.createElement('tr');

		let cells = [
			app.id,
			`${app.borrower.firstName} ${app.borrower.lastName}`,
			app.propertyAddress,
			app.loanType,
			app.purpose,
			app.loanAmount,
			app.submittedDate,
			app.borrower.phone,
			app.borrower.email,
			app.status
		];
		cells.forEach(function(cellText) {
			let td = document.createElement('td');
			td.textContent = cellText;
			row.appendChild(td);
		});

		row.addEventListener('click', function () {
		      window.location.href = `/app.html?id=${app.id}`;
		    });
		
		tbody.appendChild(row);
	}
	table.appendChild(tbody);

	let wrapper = document.createElement('div');
	wrapper.className = 'table-responsive';
	wrapper.appendChild(table);

	resultsDiv.appendChild(wrapper);
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

// Example usage:
//highlightStatus("Underwriting");
