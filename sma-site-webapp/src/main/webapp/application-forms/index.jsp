<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/2001/XMLSchema">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Application Forms</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png"/>
    <link rel="stylesheet" href="/public/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/public/css/fileinput.min.css"/>
    <link rel="stylesheet" href="/public/css/min/application-forms.css"/>
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/fileinput.min.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script src="/public/js/require.js"></script>
    <script src="/public/js/system.js"></script>
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