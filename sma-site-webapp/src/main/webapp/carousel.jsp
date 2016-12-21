<link rel="stylesheet" href="/public/css/min/carousel.css" />
<script type="application/javascript" src="/public/js/carousel.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        $('#myCarousel').carousel({
            interval: 4000,
        });
    });
</script>
<div class="container">
    <!-- Indicators -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="/public/img/carousel/carousel-01.JPG" alt="Chania">
                <%--<div class="carousel-caption">--%>
                    <%--<h3>Header of Slide 1</h3>--%>
                    <%--<p>Details of Slide 1. Lorem Ipsum Blah Blah Blah....</p>--%>
                <%--</div>--%>
            </div>
            <div class="item">
                <img src="/public/img/carousel/carousel-02.JPG" alt="Chania">
                <%--<div class="carousel-caption">--%>
                    <%--<h3>Header of Slide 2</h3>--%>
                    <%--<p>Details of Slide 2. Lorem Ipsum Blah Blah Blah....</p>--%>
                <%--</div>--%>
            </div>
            <div class="item">
                <img src="/public/img/carousel/carousel-03.JPG" alt="Flower">
                <%--<div class="carousel-caption">--%>
                    <%--<h3>Header of Slide3</h3>--%>
                    <%--<p>Details of Slide 3. Lorem Ipsum Blah Blah Blah....</p>--%>
                <%--</div>--%>
            </div>
        </div>
        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="fa fa-angle-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="fa fa-angle-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
    </div>
</div>