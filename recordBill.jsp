<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Record Bill</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1f2833;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        h1 {
            color: #66fcf1;
            margin-bottom: 20px;
        }

        form {
            background-color: #66fcf1;
            padding: 20px;
            border-radius: 10px;
            box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.5); /* Inset border */
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #f4f4f4;
            color: #1f2833;
            border: 2px solid #1f2833;
            border-radius: 40px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #1f2833;
            color: #fff;
        }

        #result {
            margin-top: 20px;
            font-size: 16px;
            font-weight: bold;
        }
    </style>
    
    <script>
        function recordBill() {
            const form = document.getElementById('recordBillForm');
            const formData = new FormData(form);

            fetch('/electricitybillingsystems/recordBill', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred. Please check the console for details.');
            });
        }
    </script>
</head>
<body>
    <h1>Record Bill</h1>
    <form id="recordBillForm">
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>
        <br>
        <label for="meternumber">Meter Number:</label>
        <input type="text" id="meternumber" name="meternumber" required>
        <br>
        <label for="unitsconsumed">Units Consumed:</label>
        <input type="text" id="unitsconsumed" name="unitsconsumed" required>
        <br>
        <label for="amount">Rate per unit:</label>
        <input type="text" id="amount" name="amount" required>
        <br>
        <button type="button" onclick="recordBill()">Record Bill</button>
    </form>
</body>
</html>
