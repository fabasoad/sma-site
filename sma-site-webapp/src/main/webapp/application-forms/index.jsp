<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Application Forms</title>
    <link rel="stylesheet" href="/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/public/css/fileinput.min.css" />
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
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Select Application Form</h1>
        </div>
    </div>
    <!--<label class="control-label">Select Application Form</label>-->
    <input id="application-form-upload" type="file" name="application-form" class="file-loading" data-show-preview="false"/>
    <jsp:include page="/footer.jsp"/>
</div>
</body>
</html>