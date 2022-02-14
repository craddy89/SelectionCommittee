<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@include file="jspf/head.jspf" %>
<body>
<%@include file="jspf/header.jspf" %>

<div id="site_content">

  <div id="banner"></div>
  <%@include file="jspf/side_menu.jspf" %>

  <div id="content">


    <h1><fmt:message key="personal_settings"/>:</h1>
    <div style="float: left;width: 60%;margin-left: 3%; margin-right: 2%">
      <c:choose>
        <c:when test="${user.role=='ADMIN'}">

          <h3 style="margin-left: 20%"><fmt:message key="control_panel"/>:</h3>
          <script>
            let confirmationMessage;
          </script>
          <c:choose>
            <c:when test="${enrollment.state=='OPENED'}">
              <script>
                confirmationMessage='<fmt:message key="enrollment"/> №<c:out value="${enrollment.enrollmentId}"/> <fmt:message key="close_enrollment_confirmation"/>.';
              </script>
              <form class="controlPanel" action="Committee" method="post">
                <input name="command" value="close_enrollment" type="hidden"/>
                <input type="submit" value="<fmt:message key="close_enrollment"/>" onclick="clicked(event)"/>
              </form>
            </c:when>
            <c:otherwise>
              <script>
                const currentEnrollmentId=parseInt(<c:out value="${enrollment.enrollmentId}"/>);
                let newEnrollmentId=1;
                if(!isNaN(currentEnrollmentId)){
                  newEnrollmentId+=currentEnrollmentId;
                }
                confirmationMessage='<fmt:message key="enrollment"/> №'+newEnrollmentId+' <fmt:message key="open_enrollment_confirmation"/>.';
              </script>
              <form class="controlPanel" action="Committee" method="post">
                <input name="command" value="open_enrollment" type="hidden"/>
                <input type="submit" value="<fmt:message key="open_enrollment"/>" onclick="clicked(event)"/>
              </form>
            </c:otherwise>
          </c:choose>
          <script>
            function clicked(e) {
              if (!confirm(confirmationMessage))
                e.preventDefault();
            }
          </script>
        </c:when>
        <c:otherwise>
          <h3><fmt:message key="applications"/>:</h3>
          <c:choose>
            <c:when test="${ not empty applicants}">
              <c:forEach items="${applicants}" var="applicant">
                <p><a style="color:black"
                      href="Committee?command=view_application&&id=${applicant.id}">
                  <fmt:message key="enrollment"/>
                  №<c:out value="${applicant.enrollmentId}"/> <c:out value="${applicant.facultyName}"/> <font color=" <c:choose>
                                <c:when test="${applicant.applicantState=='NOT_ENROLLED'}">firebrick</c:when>
                                    <c:otherwise>darkcyan</c:otherwise></c:choose>"><fmt:message
                        key="${applicant.applicantState}"/></font></a>
                </p>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <p style="margin-left: 5%"><font color="#b22222"><fmt:message key="no_applications"/></font>
              </p>
            </c:otherwise>
          </c:choose>
        </c:otherwise>
      </c:choose>
    </div>
    <div style="float:right;width: 35%">
      <h3><fmt:message key="personal_data"/></h3>
      <p class="personalData"><fmt:message key="Surname"/>: <font color="#008b8b"><c:out value="${user.lastName}"/></font></p>
      <p class="personalData"><fmt:message key="Name"/>: <font color="#008b8b"><c:out value="${user.firstName}"/></font></p>
      <p class="personalData"><fmt:message key="Patronymic"/>: <font color="#008b8b"><c:out value="${user.patronymic}"/></font></p>
      <form action="Committee" method="get">
        <input type="hidden" name="command" value="edit_profile"/>
        <input class="submitButton" type="submit" value="<fmt:message key="edit"/>"/>
      </form>
    </div>
  </div>

</div>

<%@ include file="jspf/footer.jspf" %>

</body>
</html>