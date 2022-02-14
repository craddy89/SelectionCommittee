<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content">

        <h1><fmt:message key="subjects"/>:</h1>

        <ol style="text-align: left; margin-left: 40% ">
            <c:forEach var="subject" items="${subjects}">
                <li><c:out value="${subject.name}"/>
                    <form action="Committee" method="post"
                          style="display: inline; padding: 0px 100px 0px 0px;alignment: right">
                        <input type="hidden" name="command" value="delete_subject"/>
                        <input type="hidden" name="subject_id" value="${subject.subjectId}"/>
                        <input type="hidden" name="subject_name" value="${subject.name}"/>
                        <input type="submit" value="delete"/>
                    </form>
                </li>
            </c:forEach>

        </ol>

        <form id="infoMessage" action="Committee" method="post">
            <c:if test="${error!=null}">  <p style="text-align: center;margin-top: 10px"><font color="#b22222"><fmt:message key="${error}"/></font></p></c:if>
            <input type="hidden" name="command" value="add_subject"/>
            <input class="submitButton" style="text-align: left" type="text" name="subject_name" placeholder="<fmt:message key="subject_name"/>"/>
            <input class="submitButton" type="submit" value="<fmt:message key="add_subject"/>"/>
        </form>

    </div>

</div>

<c:remove var="error" scope="session"/>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>
