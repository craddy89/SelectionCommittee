<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="applicants_list"/>:</h1>

        <c:choose>
            <c:when test="${not empty applicants}">

                <table id="myTable" class="display">
                    <thead>
                    <tr>
                        <th><fmt:message key="applicant"/></th>
                        <th><fmt:message key="faculty"/></th>
                        <th><fmt:message key="total_rating"/></th>
                        <th><fmt:message key="status"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${applicants}" var="applicant">
                        <tr>
                            <td>
                                <a style="color: black"
                                   href="Committee?command=view_application&id=${applicant.id}"><c:out value="${applicant.lastName}"/> <c:out value="${applicant.firstName}"/> <c:out value="${applicant.patronymic}"/></a>
                            </td>
                            <td><c:out value="${applicant.facultyName}"/></td>
                            <td><c:out value="${applicant.totalRating}"/></td>
                            <td><font
                            <c:choose>
                                    <c:when test="${applicant.applicantState=='NOT_ENROLLED'}">color="#b22222"</c:when>
                                    <c:otherwise>color="#008b8b"</c:otherwise>
                            </c:choose>><fmt:message key="${applicant.applicantState}"/><font/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p style="text-align: center"><font color="#b22222"><fmt:message key="no_applications"/></font>
                </p>
            </c:otherwise>
        </c:choose>

    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>