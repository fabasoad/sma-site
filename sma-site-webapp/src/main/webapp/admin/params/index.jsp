<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin: Configuration</title>
    <link rel="shortcut icon" href="/public/img/logo/logo-48x48.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.5/css/fileinput.min.css" integrity="sha256-wHaOWOsKi2bZ3oIRqiFtCHTH2gE6FbdZjZ5lDHK1LpM=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/4.0.1/ekko-lightbox.min.css" integrity="sha256-wj7M9V99T9/93nK8iE1vAVrfzzAicm99o76ZNYyUrtE=" crossorigin="anonymous" />
    <link rel="stylesheet" href="/public/css/min/gallery/editable/gallery-editable-control-panel.css" />
    <link rel="stylesheet" href="/public/css/min/params.css" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/4.3.5/js/fileinput.min.js" integrity="sha256-k4tWmIiKE9bFMnbNs6LOpBhpMU+fdZfwGBHVyyZs0E8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js" integrity="sha256-4F7e4JsAJyLUdpP7Q8Sah866jCOhv72zU5E8lIRER4w=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js" integrity="sha256-1fEPhSsRKlFKGfK3eO710tEweHh1fwokU5wFGDHO+vg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.41/system.js" integrity="sha256-XcUqNv8jyMENREIx9qqoRwOWTY5R+XS/WQ5nDXG1GsI=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.6/js/bootstrap-modal.min.js" integrity="sha256-UEFikFBinV4tAScIYbW3+7BCI2zpM74VWtUa1MIR738=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/4.0.1/ekko-lightbox.min.js" integrity="sha256-H6F05S2wD+mxs23BPOMwoMWPoGqKwGjiI0y9FHvXtrk=" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/admin/params.js');
        });
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="/header.jsp">
            <jsp:param name="locationPrefix" value="/admin" />
        </jsp:include>
        <!-- Company name handling -->
        <div class="panel">
            <div class="panel-body param-panel-body">
                <div class="col-sm-2 form-group">
                    <label for="param-company-name">Company Name</label>
                </div>
                <div class="col-sm-9 form-group">
                    <input id="param-company-name" type="text" class="form-control" value="" placeholder="Enter company name"/>
                </div>
                <div class="col-sm-1 form-group">
                    <button id="param-company-name-save-button" type="button" class="btn btn-primary pull-right">Save</button>
                </div>
            </div>
            <!-- Company year handling -->
            <div class="panel-body param-panel-body">
                <div class="col-sm-2 form-group">
                    <label for="param-footer-year">Company Year</label>
                </div>
                <div class="col-sm-9 form-group">
                    <input id="param-footer-year" type="text" class="form-control" value="" placeholder="Enter company year"/>
                </div>
                <div class="col-sm-1 form-group">
                    <button id="param-footer-year-save-button" type="button" class="btn btn-primary pull-right">Save</button>
                </div>
            </div>
        </div>
        <!-- Carousel handling -->
        <div class="panel panel-default">
            <div class="panel-heading">Create carousel item</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="carousel-image-title">Title:</label>
                    <input id="carousel-image-title" type="text" class="form-control" placeholder="Enter Title"/>
                </div>
                <div class="form-group">
                    <label for="carousel-image-upload">File:</label>
                    <input id="carousel-image-upload" type="file" name="carousel-image" class="file-loading" data-show-preview="false"/>
                    <div id="carousel-image-upload-error-block" class="help-block"></div>
                </div>
            </div>
        </div>
        <div id="carousel-gallery"></div>
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>