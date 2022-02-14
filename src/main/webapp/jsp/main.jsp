<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>

    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">


        <h1><fmt:message key="enrollment"/><c:if test="${not empty enrollment}"> №<c:out value="${enrollment.enrollmentId}"/></c:if>:
            <c:choose>
            <c:when test="${enrollment.state=='OPENED'}"><font color="#008b8b"><fmt:message key="${enrollment.state}"/></font></h1>
        <p id="infoMessage"><fmt:message key="start_date"/>: <font color="#008b8b"><fmt:formatDate value="${enrollment.startDate}"
                                                                                                   pattern="dd-MM-yyyy HH:mm"/></font></p>
        </c:when>
        <c:otherwise><span style="color: #b22222; "><fmt:message key="CLOSED"/></span>
            <c:if test="${not empty enrollment}"><p id="infoMessage"><fmt:message key="end_date"/>: <font color="#b22222"><fmt:formatDate value="${enrollment.endDate}"
                                                                                                                                          pattern="dd-MM-yyyy HH:mm"/></font></p></c:if>
            <p id="infoMessage"><span style="color: #b22222; "><fmt:message key="come_back_later"/></span></p>
        </c:otherwise>
        </c:choose>
    </div>
</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>