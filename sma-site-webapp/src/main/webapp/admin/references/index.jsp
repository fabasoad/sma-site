<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
    <jsp:include page="/header.jsp">
        <jsp:param name="title" value="Administration" />
        <jsp:param name="locationPrefix" value="/admin" />
    </jsp:include>
    <label class="control-label">Select Reference</label>
    <input id="input-1a" type="file" name="reference" class="file-loading" data-show-preview="true" />
    <jsp:include page="/footer.jsp"/>
</body>
</html>