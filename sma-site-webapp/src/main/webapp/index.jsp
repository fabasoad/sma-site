<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Southern Maritime Agency</title>
    <link rel="stylesheet" href="/public/css/bootstrap/bootstrap.min.css" />
    <script src="/public/js/jquery/jquery.min.js"></script>
    <script src="/public/js/bootstrap/bootstrap.min.js"></script>
    <script src="/public/js/system.js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/main.js');
        });
    </script>
</head>
<body>
    <jsp:include page="/header.jsp">
        <jsp:param name="title" value="Southern Maritime Agency" />
    </jsp:include>
    <jsp:include page="/carousel.jsp"/>
    <div id="main-container" class="container"></div>
    <jsp:include page="/footer.jsp"/>
</body>
</html>