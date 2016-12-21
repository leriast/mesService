<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Upload a file please</title>
</head>
<body>
<h1>Please upload a file</h1>
<form action="/uploadFile" method="POST" encType="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
    <tr>
        <td>Content</td>
        <td><input type="file" name="file"><br></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" value="Upload file"></td>
    </tr>

</form>
</body>
</html>