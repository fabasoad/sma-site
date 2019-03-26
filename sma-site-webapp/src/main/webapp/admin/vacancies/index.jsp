<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Vacancies</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/css/bootstrap-datetimepicker.min.css" integrity="sha256-GHbtsUI7RV35ZczwKVEWUuHSkOQ/+BNRJLvRASxxW1Y=" crossorigin="anonymous" />
    <link rel="stylesheet" href="/public/css/min/admin/vacancies.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js" integrity="sha256-4iQZ6BVL4qNKlQ27TExEhBN1HFPvAvAMbFavKKosSWQ=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/js/bootstrap-datetimepicker.min.js" integrity="sha256-ScVDRNgFuj4dCU3LFasiWmfG2a9Da06fFZv9HovGBb4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js" integrity="sha256-4F7e4JsAJyLUdpP7Q8Sah866jCOhv72zU5E8lIRER4w=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js" integrity="sha256-1fEPhSsRKlFKGfK3eO710tEweHh1fwokU5wFGDHO+vg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.41/system.js" integrity="sha256-XcUqNv8jyMENREIx9qqoRwOWTY5R+XS/WQ5nDXG1GsI=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/vacancies.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
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
        <div class="scrolled-container">
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
        </div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>