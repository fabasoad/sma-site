<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>References</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/4.0.1/ekko-lightbox.min.css" integrity="sha256-wj7M9V99T9/93nK8iE1vAVrfzzAicm99o76ZNYyUrtE=" crossorigin="anonymous" />
    <link rel="stylesheet" href="/public/css/min/gallery/editable/gallery-editable-control-panel.css" />
    <link rel="stylesheet" href="/public/css/min/references.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js" integrity="sha256-1fEPhSsRKlFKGfK3eO710tEweHh1fwokU5wFGDHO+vg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.41/system.js" integrity="sha256-XcUqNv8jyMENREIx9qqoRwOWTY5R+XS/WQ5nDXG1GsI=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.6/js/bootstrap-modal.min.js" integrity="sha256-UEFikFBinV4tAScIYbW3+7BCI2zpM74VWtUa1MIR738=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/4.0.1/ekko-lightbox.min.js" integrity="sha256-H6F05S2wD+mxs23BPOMwoMWPoGqKwGjiI0y9FHvXtrk=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/references.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp"/>
        <jsp:include page="/carousel.jsp"/>
        <div id="references-gallery"></div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>