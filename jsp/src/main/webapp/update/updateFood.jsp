<!DOCTYPE html>
<html>
<head>
    <title>Update Food</title>
</head>
<body>
    <h1>Update Food</h1>
    <form action="food?action=update" method="post">
        Food ID: <input type="number" name="id"><br>
        Name: <input type="text" name="name"><br>
        Amount: <input type="number" name="amount"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>