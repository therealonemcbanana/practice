<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <h1>Update Employee</h1>
    <form action="employee?action=update" method="post">
        Employee ID: <input type="number" name="id"><br>
        Name: <input type="text" name="name"><br>
        Salary: <input type="number" name="salary"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>