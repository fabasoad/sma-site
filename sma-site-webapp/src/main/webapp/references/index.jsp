<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/public/css/bootstrap/bootstrap.min.css" />
    <script src="/public/js/jquery/jquery.min.js"></script>
    <script src="/public/js/bootstrap/bootstrap.min.js"></script>
    <script src="/public/js/require.js/require.js"></script>
    <script src="/public/js/system.js/system.js"></script>
    <script src="/public/js/bootstrap/modal.js"></script>
    <script src="/public/js/ekko-lightbox/ekko-lightbox.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/dev/references/index.js');
        });
    </script>
    <title>References</title>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <jsp:include page="/carousel.jsp"/>
    <div id="references-gallery" class="row"></div>
    <jsp:include page="/footer.jsp"/>
</body>
</html>