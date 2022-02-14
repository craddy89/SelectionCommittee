<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content" style="text-align: unset">
        <h1><fmt:message key="registration"/></h1>
        <form id="regForm" action="Committee" method="post">
            <input name="command" type="hidden" value="registration"/>

            <input class="register_field" name="login" value="${validValues.get(0)}"
                   placeholder="<fmt:message key="login"/>"><font
                color="#b22222"><c:if test="${errors.get(0)!=null}"><fmt:message key="${errors.get(0)}"/></c:if><c:if
                test="${error!=null}"><fmt:message key="${error}"/></c:if></font><br>
            <input class="register_field" name="password" placeholder="<fmt:message key="password"/>"
                   type="password"/><font
                color="#b22222"><c:if test="${errors.get(1)!=null}"><fmt:message
                key="${errors.get(1)}"/></c:if></font><br>
            <input class="register_field" name="repeat_password" placeholder="<fmt:message key="password"/>"
                   type="password"/><font
                color="#b22222"><c:if test="${errors.get(2)!=null}"><fmt:message
                key="${errors.get(2)}"/></c:if></font><br>
            <input class="register_field" name="lastName" value="${validValues.get(3)}"
                   placeholder="<fmt:message key="surname"/>"/><font
                color="#b22222"><c:if test="${errors.get(3)!=null}"><fmt:message
                key="${errors.get(3)}"/></c:if></font><br>
            <input class="register_field" name="firstName" value="${validValues.get(4)}"
                   placeholder="<fmt:message key="name"/>"/><font color="#b22222"><c:if
                test="${errors.get(4)!=null}"><fmt:message key="${errors.get(4)}"/></c:if></font><br>
            <input class="register_field" name="patronymic" value="${validValues.get(5)}"
                   placeholder="<fmt:message key="patronymic"/>"/><font
                color="#b22222"><c:if test="${errors.get(5)!=null}"><fmt:message
                key="${errors.get(5)}"/></c:if></font><br>
            <input class="register_field" type="radio" name="user_role" value="applicant" checked/><fmt:message
                key="applicant"/><input
                class="register_field" style="margin-left: 10px" type="radio" name="user_role" value="admin"
                <c:if test="${validValues.get(6)=='admin'}">checked</c:if>/><fmt:message key="admin"/><br/><font
                color="#b22222"> <c:if test="${errors.get(6)!=null}"><fmt:message
                key="${errors.get(6)}"/><br></c:if></font>

            <input class="submitButton" type="submit" value="<fmt:message key="register"/>"/>

        </form>
        <c:remove var="errors" scope="session"/>
        <c:remove var="error" scope="session"/>
        <c:remove var="validValues" scope="session"/>
    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>