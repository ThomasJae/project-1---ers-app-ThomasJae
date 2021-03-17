window.onload = function() {
	document.getElementById("h3").addEventListener("mouseover", welcome);
	document.getElementById("list").addEventListener("click", displayQuery);
	document.getElementById("resolve").addEventListener("click", showForm);
}

function welcome() {
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState==4 && xhr.status==200) {
			let userJSON = JSON.parse(xhr.responseText);
			DOMManipulation(userJSON);
		}
	}
	xhr.open("POST", "http://localhost:8080/project-1---ers-app-ThomasJae/user.json");
	xhr.send();
}
function displayQuery() {
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState==4 && xhr.status==200) {
			let ticketJSON = JSON.parse(xhr.responseText);
			listDOMManipulation(ticketJSON);
		}
	}
	xhr.open("POST", "http://localhost:8080/project-1---ers-app-ThomasJae/listplus.json");
	xhr.send();
}

function showForm() {
	document.getElementById("verify").style.display = 'block';
}

function DOMManipulation(userJSON) {
	if (userJSON != null) {
		document.getElementById("h3").innerText = "Welcome back " + userJSON.firstName;
		document.getElementById("h6").innerText = "(" + userJSON.username + ")";
	}
}
function listDOMManipulation(ticketJSON) {
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "listData");
	let stat = document.getElementById("selectStatus").value;
	if (stat == 0 || stat == 4) {
		for (let x of ticketJSON){
			row = document.createElement("tr");
			col1 = document.createElement("td");
			col1.innerHTML = x.ticketId;
			col2 = document.createElement("td");
			col2.innerHTML = '$' + x.ticketAmount.toFixed(2);
			col3 = document.createElement("td");
			if (x.submitted != null)
				col3.innerHTML = ('0' + x.submitted.monthValue).slice(-2) + '/' + ('0' + x.submitted.dayOfMonth).slice(-2) +
				'/' + ('0' + x.submitted.year).slice(-2) + ' ' + ('0' + x.submitted.hour).slice(-2) + ':' + 
				('0' + x.submitted.minute).slice(-2) + ':' + ('0' + x.submitted.second).slice(-2);
			else
				col3.innerHTML = 'N/A';
			col4 = document.createElement("td");
			if (x.resolved != null)
				col4.innerHTML = ('0' + x.resolved.monthValue).slice(-2) + '/' + ('0' + x.resolved.dayOfMonth).slice(-2) +
				'/' + ('0' + x.resolved.year).slice(-2) + ' ' + ('0' + x.resolved.hour).slice(-2) + ':' + 
				('0' + x.resolved.minute).slice(-2) + ':' + ('0' + x.resolved.second).slice(-2);
			else
				col4.innerHTML = 'N/A';
			col5 = document.createElement("td");
			col5.innerText = x.description;
			col6 = document.createElement("td");
			col6.innerText = x.authorId;
			col7 = document.createElement("td");
			if (x.resolverId != 0)
				col7.innerText = x.resolverId;
			else
				col7.innerText = 'N/A';
			col8 = document.createElement("td");
			col8.innerText = x.ticketStatus;
			col9 = document.createElement("td");
			col9.innerText = x.ticketType;
			row.appendChild(col1);
			row.appendChild(col2);
			row.appendChild(col3);
			row.appendChild(col4);
			row.appendChild(col5);
			row.appendChild(col6);
			row.appendChild(col7);
			row.appendChild(col8);
			row.appendChild(col9);
			newBody.prepend(row);
		}
		document.getElementById("listData").parentNode.replaceChild(newBody, document.getElementById("listData"));
	} else if (stat == 1) {
		for (let x of ticketJSON){
			if (x.ticketStatus == "PENDING"){
			row = document.createElement("tr");
			col1 = document.createElement("td");
			col1.innerHTML = x.ticketId;
			col2 = document.createElement("td");
			col2.innerHTML = '$' + x.ticketAmount.toFixed(2);
			col3 = document.createElement("td");
			if (x.submitted != null)
				col3.innerHTML = ('0' + x.submitted.monthValue).slice(-2) + '/' + ('0' + x.submitted.dayOfMonth).slice(-2) +
				'/' + ('0' + x.submitted.year).slice(-2) + ' ' + ('0' + x.submitted.hour).slice(-2) + ':' + 
				('0' + x.submitted.minute).slice(-2) + ':' + ('0' + x.submitted.second).slice(-2);
			else
				col3.innerHTML = 'N/A';
			col4 = document.createElement("td");
			if (x.resolved != null)
				col4.innerHTML = ('0' + x.resolved.monthValue).slice(-2) + '/' + ('0' + x.resolved.dayOfMonth).slice(-2) +
				'/' + ('0' + x.resolved.year).slice(-2) + ' ' + ('0' + x.resolved.hour).slice(-2) + ':' + 
				('0' + x.resolved.minute).slice(-2) + ':' + ('0' + x.resolved.second).slice(-2);
			else
				col4.innerHTML = 'N/A';
			col5 = document.createElement("td");
			col5.innerText = x.description;
			col6 = document.createElement("td");
			col6.innerText = x.authorId;
			col7 = document.createElement("td");
			if (x.resolverId != 0)
				col7.innerText = x.resolverId;
			else
				col7.innerText = 'N/A';
			col8 = document.createElement("td");
			col8.innerText = x.ticketStatus;
			col9 = document.createElement("td");
			col9.innerText = x.ticketType;
			row.appendChild(col1);
			row.appendChild(col2);
			row.appendChild(col3);
			row.appendChild(col4);
			row.appendChild(col5);
			row.appendChild(col6);
			row.appendChild(col7);
			row.appendChild(col8);
			row.appendChild(col9);
			newBody.prepend(row);
			}
		}
		document.getElementById("listData").parentNode.replaceChild(newBody, document.getElementById("listData"));
	} else if (stat == 2) {
		for (let x of ticketJSON){
			if (x.ticketStatus == "APPROVED"){
			row = document.createElement("tr");
			col1 = document.createElement("td");
			col1.innerHTML = x.ticketId;
			col2 = document.createElement("td");
			col2.innerHTML = '$' + x.ticketAmount.toFixed(2);
			col3 = document.createElement("td");
			if (x.submitted != null)
				col3.innerHTML = ('0' + x.submitted.monthValue).slice(-2) + '/' + ('0' + x.submitted.dayOfMonth).slice(-2) +
				'/' + ('0' + x.submitted.year).slice(-2) + ' ' + ('0' + x.submitted.hour).slice(-2) + ':' + 
				('0' + x.submitted.minute).slice(-2) + ':' + ('0' + x.submitted.second).slice(-2);
			else
				col3.innerHTML = 'N/A';
			col4 = document.createElement("td");
			if (x.resolved != null)
				col4.innerHTML = ('0' + x.resolved.monthValue).slice(-2) + '/' + ('0' + x.resolved.dayOfMonth).slice(-2) +
				'/' + ('0' + x.resolved.year).slice(-2) + ' ' + ('0' + x.resolved.hour).slice(-2) + ':' + 
				('0' + x.resolved.minute).slice(-2) + ':' + ('0' + x.resolved.second).slice(-2);
			else
				col4.innerHTML = 'N/A';
			col5 = document.createElement("td");
			col5.innerText = x.description;
			col6 = document.createElement("td");
			col6.innerText = x.authorId;
			col7 = document.createElement("td");
			if (x.resolverId != 0)
				col7.innerText = x.resolverId;
			else
				col7.innerText = 'N/A';
			col8 = document.createElement("td");
			col8.innerText = x.ticketStatus;
			col9 = document.createElement("td");
			col9.innerText = x.ticketType;
			row.appendChild(col1);
			row.appendChild(col2);
			row.appendChild(col3);
			row.appendChild(col4);
			row.appendChild(col5);
			row.appendChild(col6);
			row.appendChild(col7);
			row.appendChild(col8);
			row.appendChild(col9);
			newBody.prepend(row);
			}
		}
		document.getElementById("listData").parentNode.replaceChild(newBody, document.getElementById("listData"));
	} else if (stat == 3) {
		for (let x of ticketJSON){
			if (x.ticketStatus == "DENIED"){
			row = document.createElement("tr");
			col1 = document.createElement("td");
			col1.innerHTML = x.ticketId;
			col2 = document.createElement("td");
			col2.innerHTML = '$' + x.ticketAmount.toFixed(2);
			col3 = document.createElement("td");
			if (x.submitted != null)
				col3.innerHTML = ('0' + x.submitted.monthValue).slice(-2) + '/' + ('0' + x.submitted.dayOfMonth).slice(-2) +
				'/' + ('0' + x.submitted.year).slice(-2) + ' ' + ('0' + x.submitted.hour).slice(-2) + ':' + 
				('0' + x.submitted.minute).slice(-2) + ':' + ('0' + x.submitted.second).slice(-2);
			else
				col3.innerHTML = 'N/A';
			col4 = document.createElement("td");
			if (x.resolved != null)
				col4.innerHTML = ('0' + x.resolved.monthValue).slice(-2) + '/' + ('0' + x.resolved.dayOfMonth).slice(-2) +
				'/' + ('0' + x.resolved.year).slice(-2) + ' ' + ('0' + x.resolved.hour).slice(-2) + ':' + 
				('0' + x.resolved.minute).slice(-2) + ':' + ('0' + x.resolved.second).slice(-2);
			else
				col4.innerHTML = 'N/A';
			col5 = document.createElement("td");
			col5.innerText = x.description;
			col6 = document.createElement("td");
			col6.innerText = x.authorId;
			col7 = document.createElement("td");
			if (x.resolverId != 0)
				col7.innerText = x.resolverId;
			else
				col7.innerText = 'N/A';
			col8 = document.createElement("td");
			col8.innerText = x.ticketStatus;
			col9 = document.createElement("td");
			col9.innerText = x.ticketType;
			row.appendChild(col1);
			row.appendChild(col2);
			row.appendChild(col3);
			row.appendChild(col4);
			row.appendChild(col5);
			row.appendChild(col6);
			row.appendChild(col7);
			row.appendChild(col8);
			row.appendChild(col9);
			newBody.prepend(row);
			}
		}
		document.getElementById("listData").parentNode.replaceChild(newBody, document.getElementById("listData"));
	}
}