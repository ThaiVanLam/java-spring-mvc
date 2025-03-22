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
            <li class="breadcrumb-item active">
              <a href="/admin/product">Product</a>
            </li>
            <li class="breadcrumb-item active">Update</li>
          </ol>
        </div>
        <div class="mt-5">
          <div class="row">
            <div class="col-md-6 col-12 mx-auto">
              <h3>Update a product</h3>
              <hr />
              <form:form
                method="post"
                modelAttribute="newProduct"
                action="/admin/product/update"
                enctype="multipart/form-data"
              >
                <div class="mb-3" style="display: none">
                  <label class="form-label">Id:</label>
                  <form:input type="text" class="form-control" path="id" />
                </div>
                <div class="row">
                  <div class="mb-3 col">
                    <c:set var="errorName">
                      <form:errors path="name" cssClass="invalid-feedback" />
                    </c:set>
                    <label class="form-label">Name:</label>
                    <form:input
                      type="text"
                      class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                      path="name"
                    />
                    ${errorName}
                  </div>
                  <div class="mb-3 col">
                    <c:set var="errorPrice">
                      <form:errors path="price" cssClass="invalid-feedback" />
                    </c:set>
                    <label class="form-label">Price:</label>
                    <form:input
                      type="number"
                      class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                      path="price"
                      step="0.1"
                    />
                    ${errorPrice}
                  </div>
                </div>
                <div class="form-group mb-3">
                  <c:set var="errorDetailDesc">
                    <form:errors
                      path="detailDesc"
                      cssClass="invalid-feedback"
                    />
                  </c:set>
                  <label>Detail description:</label>
                  <form:textarea
                    class="form-control ${not empty errorDetailDesc ? 'is-invalid' : ''}"
                    rows="3"
                    path="detailDesc"
                  ></form:textarea>
                  ${errorDetailDesc}
                </div>

                <div class="row mb-3">
                  <div class="col">
                    <c:set var="errorShortDesc">
                      <form:errors
                        path="shortDesc"
                        cssClass="invalid-feedback"
                      />
                    </c:set>
                    <label>Short description:</label>
                    <form:input
                      type="text"
                      class="form-control ${not empty errorShortDesc ? 'is-invalid' : ''}"
                      path="shortDesc"
                    />
                    ${errorShortDesc}
                  </div>
                  <div class="col">
                    <c:set var="errorQuantity">
                      <form:errors
                        path="quantity"
                        cssClass="invalid-feedback"
                      />
                    </c:set>
                    <label>Quantity:</label>
                    <form:input
                      type="number"
                      class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                      path="quantity"
                    />
                    ${errorQuantity}
                  </div>
                </div>
                <div class="mb-3 row">
                  <div class="col">
                    <label>Factory:</label>
                    <form:select path="factory" class="form-select">
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
                    src="${imagePath}"
                    style="max-height: 250px; display: block"
                    alt="avatar preview"
                    id="avatarPreview"
                  />
                </div>

                <div class="col-12 mb-5">
                  <button type="submit" class="btn btn-warning">Update</button>
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
