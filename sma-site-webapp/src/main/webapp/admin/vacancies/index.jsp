<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Vacancies</title>
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="/public/css/min/admin/vacancies.css" />
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/moment.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script src="/public/js/require.js"></script>
    <script src="/public/js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/dev/admin/vacancies.js');
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
            <div class="panel-body">
                <div class="btn-group">
                    <button id="vacancy-confirm-button" type="button" class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </div>
            </div>
        </div>
        <table id="vacancies-table" class="table table-hover table-sm">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Rank</th>
                    <th>Vessel Type</th>
                    <th>Joining Date</th>
                    <th>Contract Duration</th>
                    <th>Nationality</th>
                    <th>Wage</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
        </table>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>