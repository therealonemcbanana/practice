<!DOCTYPE html>
<html>
<head>
    <title>Create Employee</title>
</head>
<body>
    <h1>Create Employee</h1>
    <form action="employee" method="post">
        <input type="hidden" name="action" value="create">
        Name: <input type="text" name="name"><br>
        Salary: <input type="number" name="salary"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>