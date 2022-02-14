<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="application_information"/>:</h1>
        <div style="margin-left: 30%">
            <p class="personalData"><fmt:message key="applicant_name"/>: <font
                    color="#008b8b"><c:out value="${applicant.lastName}"/> <c:out value="${applicant.firstName}"/> <c:out value="${applicant.patronymic}"/></font></p>
            <p class="personalData"><fmt:message key="enrollment"/>: <font
                    color="#008b8b">№<c:out value="${applicant.enrollmentId}"/></font>
            </p>
            <p class="personalData"><fmt:message key="faculty"/>: <font color="#008b8b"><c:out value="${applicant.facultyName}"/></font>
            </p>
            <p class="personalData"><fmt:message key="subjects"/>:
                <c:forEach items="${subjects}" var="subject" varStatus="loop"><c:out value="${subject.name}"/> <font
                        color="#008b8b"><c:out value="${subject.grade}"/></font><c:if test="${!loop.last}">, </c:if></c:forEach>
            </p>
            <p class="personalData"><fmt:message key="application_state"/>: <font color="<c:choose>
                                <c:when test="${applicant.applicantState=='NOT_ENROLLED'}">firebrick</c:when>
                                    <c:otherwise>darkcyan</c:otherwise></c:choose>"><fmt:message
                    key="${applicant.applicantState}"/></font></p>
            <c:if test="${applicant.applicantState=='APPLIED' && user.userId==applicant.userId}">
                <form action="Committee" method="post">
                    <input type="hidden" name="command" value="cancel_application"/>
                    <input type="hidden" name="applicant_id" value="${applicant.id}"/>
                    <input class="submitButton" type="submit" value="<fmt:message key="cancel"/>" onclick="clicked(event)"/>
                    <script>
                        function clicked(e) {
                            if (!confirm('<fmt:message key="cancel_application_confirmation"/>.'))
                                e.preventDefault();
                        }
                    </script>
                </form>
            </c:if>
        </div>
    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>