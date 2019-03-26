<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/2001/XMLSchema">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Application Forms</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.5/css/fileinput.min.css" integrity="sha256-wHaOWOsKi2bZ3oIRqiFtCHTH2gE6FbdZjZ5lDHK1LpM=" crossorigin="anonymous" />
    <link rel="stylesheet" href="/public/css/min/application-forms.css"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.5/js/fileinput.min.js" integrity="sha256-k4tWmIiKE9bFMnbNs6LOpBhpMU+fdZfwGBHVyyZs0E8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js" integrity="sha256-4F7e4JsAJyLUdpP7Q8Sah866jCOhv72zU5E8lIRER4w=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js" integrity="sha256-1fEPhSsRKlFKGfK3eO710tEweHh1fwokU5wFGDHO+vg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.41/system.js" integrity="sha256-XcUqNv8jyMENREIx9qqoRwOWTY5R+XS/WQ5nDXG1GsI=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/application-forms.js');
        });
    </script>
</head>
<body>
<div class="container">
    <jsp:include page="/header.jsp"/>
    <jsp:include page="/carousel.jsp"/>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <h1 class="text-center login-title">Select Application Form</h1>
            <div class="account-wall">
                <img class="img-responsive center-block"
                     src="/public/img/logo/logo-96x96.png"
                     alt="Southern Maritime Agency"/>
                <form class="form-signin">
                    <div class="form-group">
                        <label for="application-form-sender-name">Name:</label>
                        <input id="application-form-sender-name" type="text" class="form-control" placeholder="Enter your name"/>
                    </div>
                    <div class="form-group">
                        <label for="application-form-upload">File:</label>
                        <input id="application-form-upload" type="file" name="application-form"
                               class="file-loading" data-show-preview="false"/>
                        <div id="application-form-upload-error-block" class="help-block"></div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
    <jsp:include page="/footer.jsp"/>
</div>
</body>
</html>