<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Regenerate Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1f2833;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        h1 {
            text-align: center;
            color: #1f2833;
            margin-bottom: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #66fcf1;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        button {
            padding: 10px 20px;
            border: 2px solid #1f2833;
            border-radius: 40px;
            background-color: #fff;
            color:#1f2833 ;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
        }

        button:hover {
            background-color:  #1f2833;
            color: #fff;
        }

        #report {
            white-space: pre-wrap; /* Preserves formatting */
            background-color: #f1f1f1;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 20px;
            margin-top: 20px;
            font-family: monospace; /* Use monospace font for code-like appearance */
            color: #333;
            max-height: 400px; /* Limits the height and adds scroll if needed */
            overflow-y: auto;
            text-align: left;
        }
    </style>
    
    <script>
        function regenerateReport() {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "regenerateReport", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        document.getElementById("reportResult").innerHTML = xhr.responseText;
                    } else {
                        document.getElementById("reportResult").innerText = "Failed to regenerate report.";
                    }
                }
            };

            xhr.send();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Regenerate Report</h1>
        <button onclick="regenerateReport()">Regenerate Report</button>
        <div id="reportResult"></div>
    </div>
</body>
</html>
