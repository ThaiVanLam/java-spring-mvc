<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>
  <body>
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <div class="mx-4">
          <h2>Manage Users</h2>
          <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
            <li class="breadcrumb-item active">Users</li>
          </ol>
        </div>
        <div class="mt-5">
          <div class="row">
            <div class="col-12 mx-auto">
              <div class="d-flex justify-content-between">
                <h3>User detail with id = ${id}</h3>
              </div>
              <hr />
              <div class="card" style="width: 60%">
                <div class="card-header">User information</div>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">ID: 1</li>
                  <li class="list-group-item">Email: ${user.id}</li>
                  <li class="list-group-item">FullName: ${user.fullName}</li>
                  <li class="list-group-item">Address: ${user.address}</li>
                </ul>
              </div>
              <a href="/admin/user" class="btn btn-success mt-3">Back</a>
            </div>
          </div>
        </div>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
  </body>
</html>
