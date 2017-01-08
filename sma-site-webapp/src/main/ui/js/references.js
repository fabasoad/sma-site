import ReferencesBuilder from './references/view/references-builder.js';
import ReferencesLoader from './references/references-loader.js';

$(document).on('click', '[data-toggle="lightbox"]', function(event) {
    event.preventDefault();
    $(event.target.parentElement).ekkoLightbox();
});

ReferencesLoader.load(new ReferencesBuilder());