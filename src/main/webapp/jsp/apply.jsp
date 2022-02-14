<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="apply_for"/> <font color="#008b8b"><c:out value="${faculty.name}"/></font>:</h1>

        <form id="infoMessage" action="Committee" method="post">
            <p class="register_field"><fmt:message key="enter_subject_ratings"/>:</p>
            <c:if test="${error!=null}"><p class="register_field" style="color: firebrick"><fmt:message
                    key="${error}"/></p>
                <c:remove var="error" scope="session"/>
            </c:if>
            <input name="command" type="hidden" value="apply"/>

            <c:forEach items="${faculty.requiredSubjects}" var="subject">
                <input class="register_field" name="grade" placeholder="${subject.name}"/><br>
            </c:forEach>
            <input type="hidden" name="id" value="${faculty.id}"/>
            <input class="submitButton" type="submit" value="<fmt:message key="apply"/>"/><br>
        </form>
    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>
