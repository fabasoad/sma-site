<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>News</title>
    <link rel="stylesheet" href="/public/css/bootstrap/bootstrap.min.css" />
    <script src="/public/js/jquery/jquery.min.js"></script>
    <script src="/public/js/bootstrap/bootstrap.min.js"></script>
    <script src="/public/js/system.js/system.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            SystemJS.import('/public/js/min/news.js');
        });
    </script>
</head>
<body>
    <jsp:include page="/header.jsp">
        <jsp:param name="title" value="Southern Maritime Agency" />
    </jsp:include>
    <jsp:include page="/carousel.jsp"/>
    <!-- http://bootsnipp.com/snippets/featured/list-of-blog-posts -->
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div id="postlist">
                    <div class="panel">
                        <div class="panel-heading">
                            <div class="text-center">
                                <div class="row">
                                    <div class="col-sm-9">
                                        <h3 class="pull-left">Welcome</h3>
                                    </div>
                                    <div class="col-sm-3">
                                        <h4 class="pull-right">
                                            <small><em>2014-07-30 18:30:00</em></small>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                            consequat. Duis aute irure dolor in... <a href="#">Read more</a>
                        </div>
                    </div>

                    <div class="panel">
                        <div class="panel-heading">
                            <div class="text-center">
                                <div class="row">
                                    <div class="col-sm-9">
                                        <h3 class="pull-left">Post With image</h3>
                                    </div>
                                    <div class="col-sm-3">
                                        <h4 class="pull-right">
                                            <small><em>2014-07-30 18:30:00</em></small>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <a href="#" class="thumbnail">
                                <img alt="Image" src="http://i.imgur.com/tAHVmXi.jpg">
                            </a>
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation... <a href="#">Read more</a>
                        </div>
                    </div>
                </div>
                <div class="text-center"><a href="#" id="loadmore" class="btn btn-primary">Older Posts...</a></div>
            </div>
        </div>
    </div>
    <jsp:include page="/footer.jsp"/>
</body>
</html>