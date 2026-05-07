const table = document.getElementById("logsTable");
const appForm = document.getElementById("appForm");
const apiKeyBox = document.getElementById("apiKeyBox");

function loadLogs() {

    fetch("http://localhost:8081/api/logs")
        .then(response => response.json())
        .then(data => {

            table.innerHTML = "";

            data.forEach(log => {

                let badgeClass = "";

                switch(log.logLevel) {

                    case "INFO":
                        badgeClass = "info";
                        break;

                    case "WARNING":
                        badgeClass = "warning";
                        break;

                    case "ERROR":
                        badgeClass = "error";
                        break;

                    case "CRITICAL":
                        badgeClass = "critical";
                        break;
                }

                table.innerHTML += `
                    <tr>

                        <td>${log.applicationName || "Aplicación"}</td>

                        <td>
                            <span class="badge ${badgeClass}">
                                ${log.logLevel}
                            </span>
                        </td>

                        <td>${log.message}</td>

                        <td>${log.createdAt}</td>

                    </tr>
                `;
            });
        });
}

loadLogs();

function loadStats() {

    fetch("http://localhost:8081/api/applications")
        .then(response => response.json())
        .then(apps => {

            document.getElementById("totalApps").innerText = apps.length;
        });

    fetch("http://localhost:8081/api/logs")
        .then(response => response.json())
        .then(logs => {

            document.getElementById("totalLogs").innerText = logs.length;

            const errors = logs.filter(
                log => log.logLevel === "ERROR"
            ).length;

            const criticals = logs.filter(
                log => log.logLevel === "CRITICAL"
            ).length;

            document.getElementById("totalErrors").innerText = errors;

            document.getElementById("totalCritical").innerText = criticals;
        });
}

loadStats();

appForm.addEventListener("submit", function(event) {

    event.preventDefault();

    const inputs = appForm.querySelectorAll("input, textarea");

    fetch("http://localhost:8081/api/applications", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            name: inputs[0].value,
            email: inputs[1].value,
            description: inputs[2].value

        })

    })
    .then(response => response.json())

    .then(data => {

        apiKeyBox.innerHTML = `
            Aplicación registrada correctamente <br><br>
            API KEY: <br>
            ${data.apiKey}
        `;

        appForm.reset();

    })

    .catch(error => {

        alert(error.message);

    });

});

const select = document.querySelector("select");

select.addEventListener("change", function() {

    const level = select.value;

    if(level === "Todos") {
        loadLogs();
        return;
    }

    fetch(`http://localhost:8081/api/logs?level=${level}`)
        .then(response => response.json())
        .then(data => {

            table.innerHTML = "";

            data.forEach(log => {

                let badgeClass = "";

                switch(log.logLevel) {

                    case "INFO":
                        badgeClass = "info";
                        break;

                    case "WARNING":
                        badgeClass = "warning";
                        break;

                    case "ERROR":
                        badgeClass = "error";
                        break;

                    case "CRITICAL":
                        badgeClass = "critical";
                        break;
                }

                table.innerHTML += `
                    <tr>

                        <td>${log.applicationName || "Aplicación"}</td>

                        <td>
                            <span class="badge ${badgeClass}">
                                ${log.logLevel}
                            </span>
                        </td>

                        <td>${log.message}</td>

                        <td>${log.createdAt}</td>

                    </tr>
                `;
            });
        });
});