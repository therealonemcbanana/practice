<!DOCTYPE html>
<html>
<head>
    <title>Create Food</title>
</head>
<body>
    <h1>Create Food</h1>
    <form action="food" method="post">
        <input type="hidden" name="action" value="create">
        Name: <input type="text" name="name"><br>
        Amount: <input type="number" name="amount"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>