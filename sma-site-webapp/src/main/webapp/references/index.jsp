<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/min/gallery/editable/gallery-editable-control-panel.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/require.js"></script>
    <script src="/public/js/system.js"></script>
    <script src="/public/js/modal.js"></script>
    <script src="/public/js/ekko-lightbox.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/references.js');
        });
    </script>
    <title>References</title>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="title" value="Southern Maritime Agency" />
        </jsp:include>
        <jsp:include page="/carousel.jsp"/>
        <div id="references-gallery"></div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>