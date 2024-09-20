<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculate Total Revenue</title>
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
        }

        h1 {
            text-align: center;
            color: #1f2833;
            margin-bottom: 20px;
        }

        .container {
            max-width: 500px;
            padding: 20px;
            background-color: #66fcf1;
            border-radius: 8px;
            box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.5); /* Inset border effect */
            text-align: center;
        }

        button {
            padding: 10px 20px;
            border: 2px solid #1f2833;
            border-radius: 40px;
            background-color: #fff;
            color: #1f2833;
            font-size: 16px;
            cursor: pointer;
            margin: 20px 0;
        }

        button:hover {
            background-color: #1f2833;
            color: #fff;
        }

        #revenueDisplay {
            font-size: 18px;
            color: #333;
            margin-top: 20px;
        }
    </style>
    <script>
        function calculateTotalRevenue() {
            fetch('calculateRevenue') // Updated URL to match the servlet mapping
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        document.getElementById('revenueDisplay').innerText = 'Total Revenue: ₹' + data.totalRevenue;
                    } else {
                        document.getElementById('revenueDisplay').innerText = 'Error: ' + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    document.getElementById('revenueDisplay').innerText = 'An error occurred while calculating the total revenue.';
                });
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Calculate Total Revenue</h1>
        <button onclick="calculateTotalRevenue()">Calculate Total Revenue</button>
        <p id="revenueDisplay"></p> <!-- Display area for total revenue -->
    </div>
</body>
</html>
