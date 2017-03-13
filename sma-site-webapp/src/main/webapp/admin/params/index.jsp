<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Configuration</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/fileinput.min.css" />
    <link rel="stylesheet" href="/public/css/ekko-lightbox.min.css" />
    <link rel="stylesheet" href="/public/css/min/gallery/editable/gallery-editable-control-panel.css" />
    <link rel="stylesheet" href="/public/css/min/params.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/fileinput.min.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script src="/public/js/require.js"></script>
    <script src="/public/js/system.js"></script>
    <script src="/public/js/modal.js"></script>
    <script src="/public/js/ekko-lightbox.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/params.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="locationPrefix" value="/admin" />
        </jsp:include>
        <!-- Company name handling -->
        <div class="panel">
            <div class="panel-body param-panel-body">
                <div class="col-sm-2 form-group">
                    <label for="param-company-name">Company Name</label>
                </div>
                <div class="col-sm-9 form-group">
                    <input id="param-company-name" type="text" class="form-control" value="" placeholder="Enter company name"/>
                </div>
                <div class="col-sm-1 form-group">
                    <button id="param-company-name-save-button" type="button" class="btn btn-primary pull-right">Save</button>
                </div>
            </div>
            <!-- Company year handling -->
            <div class="panel-body param-panel-body">
                <div class="col-sm-2 form-group">
                    <label for="param-footer-year">Company Year</label>
                </div>
                <div class="col-sm-9 form-group">
                    <input id="param-footer-year" type="text" class="form-control" value="" placeholder="Enter company year"/>
                </div>
                <div class="col-sm-1 form-group">
                    <button id="param-footer-year-save-button" type="button" class="btn btn-primary pull-right">Save</button>
                </div>
            </div>
        </div>
        <!-- Carousel handling -->
        <div class="panel panel-default">
            <div class="panel-heading">Create carousel item</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="carousel-image-title">Title:</label>
                    <input id="carousel-image-title" type="text" class="form-control" placeholder="Enter Title"/>
                </div>
                <div class="form-group">
                    <label for="carousel-image-upload">File:</label>
                    <input id="carousel-image-upload" type="file" name="carousel-image" class="file-loading" data-show-preview="false"/>
                    <div id="carousel-image-upload-error-block" class="help-block"></div>
                </div>
            </div>
        </div>
        <div id="carousel-gallery"></div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>