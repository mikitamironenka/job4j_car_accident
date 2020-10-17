<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<body>
Заявки :
<a href="<c:url value='/save'/>">Добавить инцидент</a>
<a href="<c:url value='/edit'/>">Редактировать инцидент</a>
<table class="table table-bordered table-hover text-center"
       data-toggle="" data-search="false" data-filter-control="true"
       data-show-export="true" data-click-to-select="true">
    <thead>
    <tr class="">
        <th data-field=id data-filter-control="select"
            data-sortable="true">Номер правонарушения</th>
        <th data-field=name data-filter-control="select"
            data-sortable="true">Наименование правонарушения</th>
        <th data-field=text data-filter-control="select"
            data-sortable="true">Описание правонарушения</th>
        <th data-field=address data-filter-control="select"
            data-sortable="true">Адрес правонарушения</th>
    </tr>

    </thead>

    <tbody>
    <c:forEach items="${accidents}" var="accident">

        <tr>
            <td>${accident.id}</td>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
        </tr>
    </c:forEach>


    </tbody>


</table>
</div>


</body>
</html>