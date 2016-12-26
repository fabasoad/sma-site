import {restClient} from './rest/references-rest-client.js';
import GalleryBuilder from './gallery/gallery-builder.js';

$(document).on('click', '[data-toggle="lightbox"]', function(event) {
    event.preventDefault();
    $(event.target.parentElement).ekkoLightbox();
});

restClient.getAll(data => {
    document.getElementById('references-gallery').appendChild(new GalleryBuilder().build(data));
});