<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function doAjax(id) {


        $.ajax({
            url: 'getStatistic/' + id,
            type: 'GET',
            success: function (data) {
                var result = data;
                $('#res').html(data);
                // $('#res').load(data);
            }
        });
    }

    function searchContact(contact) {
        $.ajax({
            url: 'getInfoByContact/' + contact,
            type: 'GET',
            success: function (data) {
                console.log(data);
                var resu = data;
                $('#cont').html(data);
                $.each(data, function (index) {
                    $('#cont').html(data[index].statistic);
                    //document.getElementById('cont').innerText((data[index].statistic));
                    //    alert(data[index].statistic);
                });
            }
        });
    }

</script>
<body>
<c:if test="${not empty tasks}">

    <ul>
        <c:forEach var="task" items="${tasks}">
            <a id="${task.getId()}" onclick="doAjax(${task.getId()})">task # ${task.getId()}</a><br>

        </c:forEach>
    </ul>

</c:if>
<br>
<br>
<div id="cont">

</div>
<br><br><br>
<div id="res">
    <c:if test="${not empty counts}">

        <ul>
            <c:forEach var="count" items="${counts}">
                <a>${count}</a><br>
            </c:forEach>
        </ul>

    </c:if>

</div>
<td align="right">contact</td>
<td><input id="contact" type="text" name="contact"/></td>
<input type="submit" value="Save" onclick="searchContact($('#contact').val())"/>
</body>
</html>