<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post">

    <label>
       Category : <select name="categoryList">
            <c:forEach  var="category" items= "${categories}">
                <option value="${category}">${category}</option>
            </c:forEach>
    </select>
    </label>

    <br/>
    <label>
       SubCategory : <input name="typename" type="text" placeholder="Enter SubCategory name">
    </label>
    <br/>
    <input type="submit" value="Add SubCategory">

</form>

</body>
</html>