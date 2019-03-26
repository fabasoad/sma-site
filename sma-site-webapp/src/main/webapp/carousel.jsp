<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.css" integrity="sha256-fDncdclXlALqR3HOO34OGHxek91q8ApmD3gGldM+Rng=" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.js" integrity="sha256-4OK8Th0+5QJMThqlimytmqQvxjqMic4YATocjyuUh1w=" crossorigin="anonymous"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        SystemJS.import('/public/js/min/core/carousel.js');
    });
</script>
<!-- Indicators -->
<div id="sma-carousel" class="carousel slide hide" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox"></div>
    <!-- Left and right controls -->
    <a class="left carousel-control" href="#sma-carousel" role="button" data-slide="prev">
        <span class="fa fa-angle-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#sma-carousel" role="button" data-slide="next">
        <span class="fa fa-angle-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
    <ol class="carousel-indicators"></ol>
</div>