<!DOCTYPE html>
<html>
<head>
    <title>Delete Animal-Food Relationship</title>
</head>
<body>
    <h1>Delete Animal-Food Relationship</h1>
    <form action="animalFood?action=delete" method="post">
        Food ID: <input type="number" name="foodId"><br>
        Animal ID: <input type="number" name="animalId"><br>
        <input type="submit" value="Delete">
    </form>
</body>
</html>