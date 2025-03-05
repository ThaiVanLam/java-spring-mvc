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
            <div class="col-md-6 col-12 mx-auto">
              <h1>Create a user</h1>
              <hr />
              <form:form
                method="post"
                action="/admin/user/create"
                modelAttribute="newUser"
              >
                <div class="row">
                  <div class="mb-3 col">
                    <label for="exampleInputEmail1" class="form-label"
                      >Email:</label
                    >
                    <form:input
                      type="email"
                      class="form-control"
                      id="exampleInputEmail1"
                      path="email"
                    />
                  </div>
                  <div class="mb-3 col">
                    <label for="exampleInputPassword1" class="form-label"
                      >Password:</label
                    >
                    <form:input
                      type="password"
                      class="form-control"
                      id="exampleInputPassword1"
                      path="password"
                    />
                  </div>
                </div>

                <div class="row">
                  <div class="mb-3 col">
                    <label for="exampleInputEmail1" class="form-label"
                      >Phone number:</label
                    >
                    <form:input
                      type="text"
                      class="form-control"
                      id="exampleInputEmail1"
                      path="phone"
                    />
                  </div>

                  <div class="mb-3 col">
                    <label for="exampleInputEmail1" class="form-label"
                      >Full Name:</label
                    >
                    <form:input
                      type="text"
                      class="form-control"
                      id="exampleInputEmail1"
                      path="fullName"
                    />
                  </div>
                </div>

                <div class="mb-3">
                  <label for="exampleInputEmail1" class="form-label"
                    >Address:</label
                  >
                  <form:input
                    type="text"
                    class="form-control"
                    id="exampleInputEmail1"
                    path="address"
                  />
                </div>
                <div class="row">
                  <div class="col">
                    <label class="form-label">Role:</label>
                    <select
                      class="form-select mb-3"
                      aria-label="Default select example"
                    >
                      <option selected value="ADMIN">ADMIN</option>
                      <option value="USER">USER</option>
                    </select>
                  </div>
                  <div class="col">
                    <label for="avatarFile" class="form-label">Avatar:</label>
                    <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg,.jpeg">
                  </div>
                  
                </div>
                <div class="col-12 mb-3">
                  <img style="max-height: 250px;display: none;" alt="avatar preview" id="avatarPreview" />
                </div>
                <div class="col-12 mb-5">
                  <button type="submit" class="btn btn-primary">Create</button>
                </div>
              </form:form>
            </div>
          </div>
        </div>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
  </body>
</html>
