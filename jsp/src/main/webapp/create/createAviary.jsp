<!DOCTYPE html>
<html>
<head>
    <title>Create Aviary</title>
</head>
<body>
    <h1>Create Aviary</h1>
    <form action="aviary" method="post">
        <input type="hidden" name="action" value="create">
        Size: <input type="number" name="size"><br>
        State: <input type="text" name="state"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>