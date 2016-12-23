import {restClient} from './../rest/references-rest-client.js';
import GalleryEditableBuilder from './../gallery/editable/gallery-editable-builder.js';

$("#input-1a").fileinput({
    uploadUrl: '/api/v1/references',
    uploadAsync: true,
    maxFileCount: 5,
    previewFileType: 'image',
    allowedFileTypes: ['image']
});

$(document).on('click', '[data-toggle="lightbox"]', function(event) {
    event.preventDefault();
    $(this).ekkoLightbox();
});

let removeCallback = (item, event) => {
    bootbox.confirm({
        title: 'Reference removing confirmation',
        message: "Do you really want to remove '" + item['title'] + "'?",
        buttons: {
            cancel: {
                label: 'No',
                className: 'btn-success'
            },
            confirm: {
                label: 'Yes',
                className: 'btn-danger'
            }
        },
        callback: result => {
            if (result) {
                restClient.delete(item['id'], message => console.log(message));
            }
        }
    });
};

restClient.getAll(data => {
    document.getElementById('references-gallery').appendChild(new GalleryEditableBuilder(removeCallback).build(data));
});