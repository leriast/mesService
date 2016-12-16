<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>companies</title>
</head>
<body>
<c:if test="${not empty companies}">

    <ul>

        <c:forEach var="company" items="${companies}">
            <br>
            <table>
                <%--<tr>--%>
                    <%--<td><a id="${company.getId_company()}">id</a></td>--%>
                    <%--<td><input value="${company.getId_company()}"></td>--%>
                <%--</tr>--%>
                <tr>
                    <td><a>name</a></td>
                    <td><input value="${company.getCompany_name()}"></td>
                </tr>
                <tr>
                    <td><a>telephone </a></td>
                    <td><input value="${company.getTelephone()}"></td>
                </tr>
                <tr>
                    <td><a>address</a></td>
                    <td><input value="${company.getAddress()}"></td>
                </tr>
            </table>
        </c:forEach>

    </ul>
</c:if>
<form:form action="/register" method="post" commandName="companyForm">
    <table border="0">
        <tr>
            <td>name:</td>
            <td><input name="company_name"/></td>
        </tr>
        <tr>
            <td>telephone:</td>
            <td><input name="telephone"/></td>
        </tr>
        <tr>
            <td>address:</td>
            <td><input name="address"/></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
