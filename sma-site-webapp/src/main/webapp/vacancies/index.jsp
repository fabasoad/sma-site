<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Vacancies</title>
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
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
            <tbody></tbody>
        </table>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>