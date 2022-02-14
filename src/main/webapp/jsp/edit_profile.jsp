<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

    <div id="banner"></div>
    <%@include file="jspf/side_menu.jspf" %>

    <div id="content" style="text-align: unset" >
        <h1 style="text-align: center"><fmt:message key="edit_profile"/>: </h1>
        <form id="regForm" action="Committee" method="post"  >
            <input name="command" type="hidden" value="edit_profile" />

            <input class="register_field" value="${user.lastName}" name="lastName" placeholder="<fmt:message key="surname"/>"/><font color="#b22222"><c:if test="${errors.get(3)!=null}"><fmt:message key="${errors.get(3)}"/></c:if></font><br>
            <input class="register_field" value="${user.firstName}" name="firstName" placeholder="<fmt:message key="name"/>"/><font color="#b22222"><c:if test="${errors.get(4)!=null}"><fmt:message key="${errors.get(4)}"/></c:if></font><br>
            <input class="register_field" value="${user.patronymic}" name="patronymic" placeholder="<fmt:message key="patronymic"/>"/><font color="#b22222"><c:if test="${errors.get(5)!=null}"><fmt:message key="${errors.get(5)}"/></c:if></font><br>
            <input class="register_field" name="password" placeholder="<fmt:message key="old_password"/>" type="password"/><font color="#b22222"><c:if test="${error!=null}"><fmt:message key="${error}"/></c:if></font><br>

            <input class="submitButton" type="submit" value="<fmt:message key="save"/>"/>

        </form>
        <c:remove var="errors"  scope="session"/>
        <c:remove var="error"  scope="session"/>
    </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>