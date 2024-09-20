<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Customer</title>
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

        .container {
            max-width: 600px;
            width: 100%;
            padding: 20px;
            background-color: #66fcf1;
            border-radius: 10px;
            box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.5); /* Inset border */
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 0;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 10px 15px;
            border: 2px solid #1f2833; 
            border-radius: 40px;
            background-color: #f4f4f4;
            color: #1f2833;
            font-size: 18px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1f2833;
            color: #fff;
        }
    </style>
    <script>
        function addCustomer() {
            const form = document.getElementById('addCustomerForm');
            const formData = new FormData(form);

            fetch('/electricitybillingsystems/addCustomer', {
                method: 'POST',
                body: new URLSearchParams(formData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                alert(data.message);
                if (data.success) {
                    form.reset(); // Clear form after successful submission
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred. Please check the console for details.');
            });
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Add Customer</h1>
        <form id="addCustomerForm">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
            <label for="contactNumber">Contact Number:</label>
            <input type="number" id="contactNumber" name="contactNumber" required>
            <label for="meterNumber">Meter Number:</label>
            <input type="number" id="meterNumber" name="meterNumber" required>
            <button type="button" onclick="addCustomer()">Add Customer</button>
        </form>
    </div>
</body>
</html>
