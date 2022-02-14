<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="faculty"/>: <font color="#008b8b"><c:out value="${faculty.name}"/></font></h1>

        <ol>
            <ul><fmt:message key="Capacity"/>: <font color="#008b8b"><c:out value="${faculty.capacity}"/></font></ul>
            <ul><fmt:message key="required_subjects"/>: <c:forEach items="${faculty.requiredSubjects}" var="subject" varStatus="loop">
                <font
                        color="#008b8b"><c:out value="${subject.name}"/></font><c:if test="${!loop.last}">, </c:if></c:forEach></ul>
        </ol>
        <c:if test="${user.role=='APPLICANT'  && enrollment.state=='OPENED'}">
            <form class="submitButton" action="Committee" method="get">
                <input type="hidden" name="command" value="apply"/>
                <input type="hidden" name="id" value="${faculty.id}"/>
                <input  type="submit" value="<fmt:message key="apply"/>"/>
            </form>
        </c:if>

        <c:if test="${user==null && enrollment.state=='OPENED'}">
            <div class="submitButton">
                <font color="#b22222"><fmt:message key="log_in_to_apply"/></font>
            </div>
        </c:if>

        <c:if test="${user.role=='ADMIN'}">
            <form class="submitButton" action="Committee" method="post">
                <input type="hidden" name="command" value="delete_faculty"/>
                <input type="hidden" name="faculty_name" value="${faculty.name}"/>
                <input type="hidden" name="id" value="${faculty.id}"/>
                <input type="submit" value="<fmt:message key="delete_faculty"/>" onclick="clicked(event)"/>
                <script>
                    function clicked(e) {
                        if (!confirm('${faculty.name} <fmt:message key="remove_faculty_confirmation"/>.'))
                            e.preventDefault();
                    }
                </script>
            </form>
        </c:if>
    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>