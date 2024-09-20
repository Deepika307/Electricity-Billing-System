<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Electricity Billing System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background-image: url('https://cdn.gencraft.com/prod/user/ea357ac7-767f-46c8-b058-057af2dd7114/1f94cb3e-06ce-49c2-9b93-61fcdba71f1f/image/image1_0.jpg?Expires=1723274683&Signature=G5VRHtyDij2dwoNeAw9iXn-MyVP8QUqPoZoIXaVlut-zRNM~Pxf1~QyVy4ChY~YzzkpYx-dhdqqAbYuF93AW3ABBR8JFeL9RIRozI9gocXZLWz1HayS9N4-N6bBhNqSvgYgrDCB47dtCflKFW10Uq8AjtmlqfEl55jA6f21RCdjWJN93FuNABVjaavzokALvu86VYxBkNo4Z0OO-qeQSxSSewGetdJ~3h~KPOAqtolZnLFbx-mK-TlKq-5m4Y0WZgdJ1EPV0MrDXYUl5XHxwZTmfoIBier3CuwdUSNF2bh2DKK-px8AlXpQdTWOtLjRtojfODWNJgBYpgXznXLoXqA__&Key-Pair-Id=K3RDDB1TZ8BHT8');
    background-repeat: no-repeat;
    background-position: center center;
    background-attachment: fixed;
    background-size: cover;
            background-size: cover; 
            background-repeat: no-repeat; 
            background-position: center center; 
            position: relative;
        }
        h1 {
            background: rgba(255, 255, 255, 0.3); /* Semi-transparent background */
            backdrop-filter: blur(10px); /* Blur effect */
            -webkit-backdrop-filter: blur(10px); /* Safari support */
            padding: 20px;
            margin: 20px;
            border-radius: 10px;
            text-align: center;
            font-size: 2em;
            color: #1f2833;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex; /* Use Flexbox to align items horizontally */
            gap: 10px; /* Add some space between the buttons */
        }
        li {
            margin: 0; /* Remove default margin from list items */
        }
        ul li a {
            text-decoration: none;
            color: #10a5f5; /* Change text color */
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-size: 18px;
            font-weight: bold;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 40px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(10, 10, 10, 0.1);
            transition: background-color 0.3s, color 0.3s;
        }
        a:hover {
            background-color: #007bff;
            color: #fff;
        }
        .auth-buttons {
            position: absolute;
            top: 20px;
            right: 20px;
            display: flex;
            gap: 10px;
        }
        .auth-buttons a {
            font-size: 16px;
            font-weight: normal;
            padding: 8px 16px;
            border-radius: 30px;
            background-color: #fff;
            color: #007bff;
            box-shadow: 0 2px 4px rgba(10, 10, 10, 0.1);
            transition: background-color 0.3s, color 0.3s;
        }
        .auth-buttons a:hover {
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="auth-buttons">
        <a href="index.html">Logout</a>
    </div>
    <h1>Electricity Billing System</h1>
    <ul>
        <li><a href="addCustomer.jsp">Add Customer</a></li>
        <li><a href="recordBill.jsp">Record Bill</a></li>
        <li><a href="generateReport.jsp">Generate Report</a></li>
        <li><a href="calculateTotalRevenue.jsp">Calculate Total Revenue</a></li>
    </ul>
</body>
</html>