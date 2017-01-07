<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/2001/XMLSchema">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Main</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/system.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/main.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="title" value="Administration" />
            <jsp:param name="locationPrefix" value="/admin" />
        </jsp:include>
        <jsp:include page="/wysiwyg.jsp"/>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>