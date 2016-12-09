<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Application Forms</title>
    <link rel="stylesheet" href="/public/css/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/bootstrap-fileinput/fileinput.min.css" />
    <script src="/public/js/jquery/jquery.min.js"></script>
    <script src="/public/js/bootstrap/bootstrap.min.js"></script>
    <script src="/public/js/bootstrap-fileinput/fileinput.min.js"></script>
    <script src="/public/js/system.js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/dev/application-forms/index.js');
        });
    </script>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <jsp:include page="/carousel.jsp"/>
    <label class="control-label">Select Application Form</label>
    <input id="input-1a" type="file" name="application-form" class="file-loading" data-show-preview="false" />
    <jsp:include page="/footer.jsp"/>
</body>
</html>