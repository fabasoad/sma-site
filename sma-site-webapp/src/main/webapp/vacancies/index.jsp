<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Vacancies</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/min/vacancies.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script src="/public/js/require.js"></script>
    <script src="/public/js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/vacancies.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="title" value="Southern Maritime Agency" />
        </jsp:include>
        <jsp:include page="/carousel.jsp"/>
        <div class="scrolled-container">
            <table id="vacancies-table" class="table table-hover table-sm hide">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Rank</th>
                        <th>Vessel Type</th>
                        <th>Joining Date</th>
                        <th>Contract Duration</th>
                        <th>Nationality</th>
                        <th>Wage</th>
                    </tr>
                </thead>
            </table>
        </div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>