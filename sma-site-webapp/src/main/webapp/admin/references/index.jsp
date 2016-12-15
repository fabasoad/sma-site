<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin: References</title>
    <link rel="stylesheet" href="/public/css/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/bootstrap-fileinput/fileinput.min.css" />
    <script src="/public/js/jquery/jquery.min.js"></script>
    <script src="/public/js/bootstrap/bootstrap.min.js"></script>
    <script src="/public/js/bootstrap-fileinput/fileinput.min.js"></script>
    <script src="/public/js/system.js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/references.js');
        });
    </script>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <label class="control-label">Select Reference</label>
    <input id="input-1a" type="file" name="reference" class="file-loading" data-show-preview="true" />
    <jsp:include page="/footer.jsp"/>
</body>
</html>