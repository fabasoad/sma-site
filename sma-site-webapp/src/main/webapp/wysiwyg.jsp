<link rel="stylesheet" href="/public/css/min/admin/wysiwyg.css" />
<script src="https://cdn.jsdelivr.net/npm/jquery.hotkeys@0.1.0/jquery.hotkeys.js" integrity="sha256-kvb4z6BdcTgy2c8PcpBRijmYasAbX9Djgyzw7aYUCiE=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-wysiwyg/0.3.3/bootstrap3-wysihtml5.min.js" integrity="sha256-mumxR2KEPXAwa2YFtqPTi91mrqackBT8GIF8TFNGf4k=" crossorigin="anonymous"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        SystemJS.import('/public/js/min/core/wysiwyg.js');
    });
</script>
<div class="wysiwyg-container">
    <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
        <div class="btn-group">
            <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font"><i class="glyphicon glyphicon-font"></i><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a data-edit="fontName Serif" style="font-family:'Serif'">Serif</a></li><li><a data-edit="fontName Sans" style="font-family:'Sans'">Sans</a></li><li><a data-edit="fontName Arial" style="font-family:'Arial'">Arial</a></li><li><a data-edit="fontName Arial Black" style="font-family:'Arial Black'">Arial Black</a></li><li><a data-edit="fontName Courier" style="font-family:'Courier'">Courier</a></li><li><a data-edit="fontName Courier New" style="font-family:'Courier New'">Courier New</a></li><li><a data-edit="fontName Comic Sans MS" style="font-family:'Comic Sans MS'">Comic Sans MS</a></li><li><a data-edit="fontName Helvetica" style="font-family:'Helvetica'">Helvetica</a></li><li><a data-edit="fontName Impact" style="font-family:'Impact'">Impact</a></li><li><a data-edit="fontName Lucida Grande" style="font-family:'Lucida Grande'">Lucida Grande</a></li><li><a data-edit="fontName Lucida Sans" style="font-family:'Lucida Sans'">Lucida Sans</a></li><li><a data-edit="fontName Tahoma" style="font-family:'Tahoma'">Tahoma</a></li><li><a data-edit="fontName Times" style="font-family:'Times'">Times</a></li><li><a data-edit="fontName Times New Roman" style="font-family:'Times New Roman'">Times New Roman</a></li><li><a data-edit="fontName Verdana" style="font-family:'Verdana'">Verdana</a></li></ul>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font Size"><i class="glyphicon glyphicon-text-height"></i>&nbsp;<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a data-edit="fontSize 5"><span style="font-size: large;">Huge</span></a></li>
                <li><a data-edit="fontSize 3"><span style="font-size: small;">Normal</span></a></li>
                <li><a data-edit="fontSize 1"><span style="font-size: xx-small;">Small</span></a></li>
            </ul>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary" data-edit="bold" title="" data-original-title="Bold (Ctrl/Cmd+B)"><i class="glyphicon glyphicon-bold"></i></a>
            <a class="btn btn-primary" data-edit="italic" title="" data-original-title="Italic (Ctrl/Cmd+I)"><i class="glyphicon glyphicon-italic"></i></a>
            <a class="btn btn-primary" data-edit="underline" title="" data-original-title="Underline (Ctrl/Cmd+U)"><i class="glyphicon glyphicon-text-width"></i></a>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary" data-edit="insertunorderedlist" title="" data-original-title="Bullet list"><i class="glyphicon glyphicon-list"></i></a>
            <a class="btn btn-primary" data-edit="insertorderedlist" title="" data-original-title="Number list"><i class="glyphicon glyphicon-list-alt"></i></a>
            <a class="btn btn-primary" data-edit="outdent" title="" data-original-title="Reduce indent (Shift+Tab)"><i class="glyphicon glyphicon-indent-left"></i></a>
            <a class="btn btn-primary" data-edit="indent" title="" data-original-title="Indent (Tab)"><i class="glyphicon glyphicon-indent-right"></i></a>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary" data-edit="justifyleft" title="" data-original-title="Align Left (Ctrl/Cmd+L)"><i class="glyphicon glyphicon-align-left"></i></a>
            <a class="btn btn-primary" data-edit="justifycenter" title="" data-original-title="Center (Ctrl/Cmd+E)"><i class="glyphicon glyphicon-align-center"></i></a>
            <a class="btn btn-primary" data-edit="justifyright" title="" data-original-title="Align Right (Ctrl/Cmd+R)"><i class="glyphicon glyphicon-align-right"></i></a>
            <a class="btn btn-primary" data-edit="justifyfull" title="" data-original-title="Justify (Ctrl/Cmd+J)"><i class="glyphicon glyphicon-align-justify"></i></a>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Hyperlink"><i class="glyphicon glyphicon-link"></i></a>
            <div class="dropdown-menu input-append">
                <input class="span2" placeholder="URL" type="text" data-edit="createLink">
                <button class="btn" type="button">Add</button>
            </div>
            <a class="btn btn-primary" data-edit="unlink" title="" data-original-title="Remove Hyperlink"><i class="glyphicon glyphicon-remove"></i></a>

        </div>

        <div class="btn-group">
            <a class="btn btn-primary" title="" id="pictureBtn" data-original-title="Insert picture (or just drag &amp; drop)"><i class="glyphicon glyphicon-picture"></i></a>
            <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 37px; height: 30px;">
        </div>
        <div class="btn-group">
            <a class="btn btn-primary" data-edit="undo" title="" data-original-title="Undo (Ctrl/Cmd+Z)"><i class="glyphicon glyphicon-backward"></i></a>
            <a class="btn btn-primary" data-edit="redo" title="" data-original-title="Redo (Ctrl/Cmd+Y)"><i class="glyphicon glyphicon-forward"></i></a>
        </div>
    </div>

    <div id="editor" class="well"></div>
    <button id="wysiwyg-save-button" type="button" class="btn btn-primary pull-right">Save</button>

</div>