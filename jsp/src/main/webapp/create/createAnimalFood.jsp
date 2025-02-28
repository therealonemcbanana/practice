<!DOCTYPE html>
<html>
<head>
    <title>Create Animal-Food Relationship</title>
</head>
<body>
    <h1>Create Animal-Food Relationship</h1>
    <form action="animalFood" method="post">
        <input type="hidden" name="action" value="create">
        Food ID: <input type="number" name="foodId"><br>
        Animal ID: <input type="number" name="animalId"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>