<!DOCTYPE html>
<html>
<head>
    <title>Create Animal-Employee Relationship</title>
</head>
<body>
    <h1>Create Animal-Employee Relationship</h1>
    <form action="animalEmployee" method="post">
        <input type="hidden" name="action" value="create">
        Animal ID: <input type="number" name="animalId"><br>
        Employee ID: <input type="number" name="employeeId"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>