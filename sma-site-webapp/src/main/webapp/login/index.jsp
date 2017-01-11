<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign in to SMA</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link href="/public/css/bootstrap.min.css" rel="stylesheet">
    <link href="/public/css/min/login.css" rel="stylesheet">
    <script src="/public/js/jquery.min.js"></script>
    <script src="/public/js/bootstrap.min.js"></script>
    <script src="/public/js/system.js"></script>
    <script src="/public/js/bootbox.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/dev/login.js');
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to Southern Maritime Agency</h1>
            <div class="account-wall">
                <img class="img-responsive center-block"
                     src="/public/img/logo/logo-96x96.png"
                     alt="Southern Maritime Agency">
                <div class="form-signin">
                    <input type="text" class="form-control" placeholder="Email" required autofocus>
                    <input type="password" class="form-control" placeholder="Password" required>
                    <button id="login-button" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
                    <label class="checkbox pull-left">
                        <input type="checkbox" value="remember-me">Remember me
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>