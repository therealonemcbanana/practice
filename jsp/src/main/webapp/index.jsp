<!DOCTYPE html>
<html>
<head>
    <title>Database Management</title>
</head>
<body>
    <h1>Database Management System</h1>
    <form action="selectTable.jsp" method="get">
        <label for="table">Select Table:</label>
        <select name="table" id="table">
            <option value="animal">Animal</option>
            <option value="animalFood">AnimalFood</option>
            <option value="animalEmployee">AnimalEmployee</option>
            <option value="aviary">Aviary</option>
            <option value="employee">Employee</option>
            <option value="food">Food</option>
            <option value="species">Species</option>
            <!-- Добавьте другие таблицы по мере необходимости -->
        </select>
        <br>
        <label for="action">Select Action:</label>
        <select name="action" id="action">
            <option value="create">Create</option>
            <option value="read">Read</option>
            <option value="update">Update</option>
            <option value="delete">Delete</option>
        </select>
        <br>
        <input type="submit" value="Go">
    </form>
</body>
</html>