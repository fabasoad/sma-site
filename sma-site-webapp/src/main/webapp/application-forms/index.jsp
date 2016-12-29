<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/2001/XMLSchema">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Application Forms</title>
    <link rel="stylesheet" href="/public/css/min/application-forms.css"/>
    <link rel="stylesheet" href="/public/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/public/css/fileinput.min.css"/>
    <link rel="shortcut icon" href="/public/img/logo/logo_96x96.png"/>
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/fileinput.min.js"></script>
    <script src="/public/js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/application-forms.js');
        });
    </script>
</head>
<body>
<div class="container sma-container">
    <jsp:include page="/header.jsp">
        <jsp:param name="title" value="Southern Maritime Agency"/>
    </jsp:include>
    <jsp:include page="/carousel.jsp"/>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <h1 class="text-center login-title">Select Application Form</h1>
            <div class="account-wall">
                <img class="img-responsive center-block"
                     src="/public/img/logo/logo_96x96.png"
                     alt="Southern Maritime Agency">
                <form class="form-signin">
                    <input id="application-form-upload" type="file" name="application-form"
                           class="file-loading" data-show-preview="false"/>
                </form>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
    <jsp:include page="/footer.jsp"/>
</div>
</body>
</html>