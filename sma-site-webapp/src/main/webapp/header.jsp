<link rel="stylesheet" href="/public/css/min/index.css"/>
<link rel="stylesheet" href="/public/css/min/header.css"/>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        SystemJS.import('/public/js/min/header.js');
    });
</script>
<div class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header hide">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${param.locationPrefix}/"></a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav hide">
            <li><a href="${param.locationPrefix}/">Main</a></li>
            <li><a href="${param.locationPrefix}/news">News</a></li>
            <li><a href="${param.locationPrefix}/vacancies">Vacancies</a></li>
            <li><a href="${param.locationPrefix}/references">References</a></li>
            <li><a href="${param.locationPrefix}/application-forms">Application Forms</a></li>
            <li><a href="${param.locationPrefix}/contacts">Contacts</a></li>
        </ul>
    </div>
</div>