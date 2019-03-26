<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Application Forms</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.41/system.js" integrity="sha256-XcUqNv8jyMENREIx9qqoRwOWTY5R+XS/WQ5nDXG1GsI=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js" integrity="sha256-4F7e4JsAJyLUdpP7Q8Sah866jCOhv72zU5E8lIRER4w=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/application-forms.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="locationPrefix" value="/admin" />
        </jsp:include>
        <table id="application-forms-table" class="table table-hover table-sm">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
        </table>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>