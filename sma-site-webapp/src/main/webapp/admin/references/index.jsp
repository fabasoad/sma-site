<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: References</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/fileinput.min.css" />
    <link rel="stylesheet" href="/public/css/min/gallery/editable/gallery-editable-control-panel.css" />
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
            SystemJS.import('/public/js/min/admin/references.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="title" value="Administration" />
            <jsp:param name="locationPrefix" value="/admin" />
        </jsp:include>
        <div class="panel panel-default">
            <div class="panel-heading">Create Reference</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="reference-title">Title:</label>
                    <input id="reference-title" type="text" class="form-control" placeholder="Enter Title"/>
                </div>
                <div class="form-group">
                    <label for="reference-upload">File:</label>
                    <input id="reference-upload" type="file" name="reference" class="file-loading" data-show-preview="false"/>
                    <div id="reference-upload-error-block" class="help-block"></div>
                </div>
            </div>
        </div>
        <div id="references-gallery"></div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>