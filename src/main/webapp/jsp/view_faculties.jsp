<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="faculties"/>:</h1>

        <ol >
            <c:forEach var="faculty" items="${faculties}">
                <li><a style="color: darkcyan" href="Committee?command=view_faculty&id=${faculty.id}"><c:out value="${faculty.name}"/></a></li>
            </c:forEach>
        </ol>
        <c:if test="${user.role=='ADMIN'}">
            <form class="submitButton" action="Committee" method="get">
                <input type="hidden" name="command" value="add_faculty"/>
                <input type="submit" value="<fmt:message key="add_faculty"/>"/>
            </form>
        </c:if>

    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>