<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>users</title>
</head>
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript"></script>
<script>
    function changeCompanyValue(){
        console.log(this);

        $(this.parent().parent().children('#comp')).val($(this).val());
    }
</script>
<body>
<c:if test="${not empty users}">
    <ul>
        <c:forEach var="user" items="${users}">
            <br>
            <table>
                <tr>
                    <td><a>username</a></td>
                    <td><input value="${user.getUsername()}"></td>
                </tr>
                <tr>
                    <td><a>password </a></td>
                    <td><input value="${user.getPassword()}"></td>
                </tr>
                <tr>
                    <td><a>enabled</a></td>
                    <td><input value="${user.isEnabled()}"></td>
                </tr>
                <tr>
                    <td><a>firstname</a></td>
                    <td><input value="${user.getFirstname()}"></td>
                </tr>
                <tr>
                    <td><a>secondname</a></td>
                    <td><input value="${user.getSecondname()}"></td>
                </tr>
                <tr>
                    <td><a>department</a></td>
                    <td><input value="${user.getDepartment()}"></td>
                </tr>
                <tr>
                    <td><a>priority</a></td>
                    <td><input value="${user.getPriority()}"></td>
                </tr>
                <tr>
                    <td><a>id_company</a></td>
                    <td><input id="comp" value="${user.getCompany().getCompany_name()}" list="companies"></td>
                    <td><select id="companies" onchange="changeCompanyValue()" >
                        <c:forEach var="company" items="${companies}">
                        <option>"${company.getCompany_name()}"</option>
                        </c:forEach>
                    </select></td>
                </tr>
            </table>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
