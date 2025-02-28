<!DOCTYPE html>
<html>
<head>
    <title>Create Animal</title>
</head>
<body>
    <h1>Create Animal</h1>
    <form action="animal" method="post">
        <input type="hidden" name="action" value="create">
        Name: <input type="text" name="name"><br>
        Gender: <input type="text" name="gender"><br>
        Age: <input type="number" name="age"><br>
        Aviary ID: <input type="number" name="aviaryId"><br>
        Species ID: <input type="number" name="speciesId"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>