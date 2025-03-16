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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
      $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#avatarPreview").attr("src", imgURL);
          $("#avatarPreview").css({ display: "block" });
        });
      });
    </script>

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
          <h2>Products</h2>
          <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
            <li class="breadcrumb-item active">Product</li>
          </ol>
        </div>
        <div class="mt-5">
          <div class="row">
            <div class="col-md-6 col-12 mx-auto">
              <h3>Create a product</h3>
              <hr />
              <form:form
                method="post"
                modelAttribute="product"
                enctype="multipart/form-data"
              >
                <div class="row">
                  <div class="mb-3 col">
                    <label class="form-label">Name:</label>
                    <form:input
                      type="text"
                      class="form-control"
                      id="exampleInputEmail1"
                      path="name"
                    />
                  </div>
                  <div class="mb-3 col">
                    <label class="form-label">Price:</label>
                    <form:input
                      type="number"
                      class="form-control"
                      path="price"
                    />
                  </div>
                </div>
                <div class="form-group mb-3">
                  <label>Detail description:</label>
                  <form:textarea
                    class="form-control"
                    rows="3"
                    path="detailDesc"
                  ></form:textarea>
                </div>

                <div class="row mb-3">
                  <div class="col">
                    <label>Short description:</label>
                    <form:input
                      type="text"
                      class="form-control"
                      path="shortDesc"
                    />
                  </div>
                  <div class="col">
                    <label>Quantity:</label>
                    <form:input
                      type="number"
                      class="form-control"
                      path="quantity"
                    />
                  </div>
                </div>
                <div class="mb-3 row">
                  <div class="col">
                    <label>Factory:</label>
                    <form:select path="factory" class="form-select">
                      <form:option value="0">Choose factory</form:option>
                      <form:option value="Apple">Apple (MacBook)</form:option>
                      <form:option value="Asus">Asus</form:option>
                      <form:option value="Lenovo">Lenovo</form:option>
                      <form:option value="Dell">Dell</form:option>
                      <form:option value="LG">LG</form:option>
                      <form:option value="Acer">Acer</form:option>
                    </form:select>
                  </div>
                  <div class="col">
                    <label>Target:</label>
                    <form:select class="form-select" path="target">
                      <form:option value="0">Choose target</form:option>
                      <form:option value="Gaming">Gaming</form:option>
                      <form:option value="SINHVIEN-VANPHONG"
                        >Sinh viên - Văn phòng</form:option
                      >
                      <form:option value="THIET-KE-DO-HOA"
                        >Thiết kế đồ họa</form:option
                      >
                      <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                      <form:option value="DOANH-NHAN">Doanh nhân</form:option>
                    </form:select>
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-6">
                    <label for="avatarFile" class="form-label">Image:</label>
                    <input
                      class="form-control"
                      type="file"
                      id="avatarFile"
                      accept=".png, .jpg,.jpeg"
                      name="hoidanitFile"
                    />
                  </div>
                </div>
                <div class="col-12 mb-3">
                  <img
                    style="max-height: 250px; display: none"
                    alt="avatar preview"
                    id="avatarPreview"
                  />
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
