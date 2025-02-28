<!DOCTYPE html>
<html>
<head>
    <title>Update Animal</title>
</head>
<body>
    <h1>Update Animal</h1>
    <form action="animal?action=update" method="post">
        Animal ID: <input type="number" name="id"><br>
        Name: <input type="text" name="name"><br>
        Gender: <input type="text" name="gender"><br>
        Age: <input type="number" name="age"><br>
        Aviary ID: <input type="number" name="aviaryId"><br>
        Species ID: <input type="number" name="speciesId"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>