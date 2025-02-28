<!DOCTYPE html>
<html>
<head>
    <title>Update Species</title>
</head>
<body>
    <h1>Update Species</h1>
    <form action="species?action=update" method="post">
        Species ID: <input type="number" name="id"><br>
        Title: <input type="text" name="title"><br>
        Description: <input type="text" name="description"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>