<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 5px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 5px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<table class="tg">
    <tr>
        <th>
            <h2>Select User</h2>
        </th>
        <th>
            <h2>Add User</h2>
        </th>
    </tr>
    <tr>
        <td>
            <form name="test" action="setName">
                <c:if test="${!empty user.name}">
                    <input readonly type="text" name="nameSelect" value="${poleName}">
                    <input disabled type="submit"
                           value="<spring:message text=" Select User"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="text" name="nameSelect" value="${poleName}">
                    <input type="submit"
                           value="<spring:message text=" Select User"/>"/>
                </c:if>
            </form>

        </td>
        <td>
            <c:url var="addAction" value="/users/add"/>
            <form:form action="${addAction}" commandName="user">
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:label path="name">
                                <spring:message text="Name"/>
                            </form:label>
                        </td>
                        <td>
                            <form:label path="Age">
                                <spring:message text="Age"/>
                            </form:label>
                        </td>
                        <td>
                            <form:label path="Admin">
                                <spring:message text="isAdmin"/>
                            </form:label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                        <td>
                            <form:input path="name"/>
                        </td>
                        <td>
                            <form:input path="Age"/>
                        </td>
                        <td>
                            <form:input path="Admin"/>
                        </td>
                        <td colspan="2">
                            <c:if test="${!empty user.name}">
                                <input type="submit"
                                       value="<spring:message text=" Edit User"/>"/>
                            </c:if>
                            <c:if test="${empty user.name}">
                                <input type="submit"
                                       value="<spring:message text=" Add User"/>"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>

<br/>

<h1>User List</h1>

<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Age</th>
            <th width="60">isAdmin</th>
            <th width="60">createdDate</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><a href="/userdata/${user.id}" target="_blank">${user.name}</a></td>
                <td>${user.age}</td>
                <td>${user.admin}</td>
                <td>${user.createdDate}</td>
                <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br/>
<table>
    <tr>
        <td align="center">
            <a href="<c:url value='/previousPage'/>">Previous page</a>
        </td>
        <td align="center">
            <a href="<c:url value='/nextPage'/>">Next page</a>
        </td>
    </tr>
</table>
</body>
</html>
