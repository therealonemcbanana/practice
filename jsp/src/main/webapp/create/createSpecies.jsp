<!DOCTYPE html>
<html>
<head>
    <title>Create Species</title>
</head>
<body>
    <h1>Create Species</h1>
    <form action="species" method="post">
        <input type="hidden" name="action" value="create">
        Title: <input type="text" name="title"><br>
        Description: <input type="text" name="description"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>