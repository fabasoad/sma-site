import {restClient} from './../rest/references-rest-client.js';
import GalleryEditableBuilder from './../gallery/editable/gallery-editable-builder.js';

// Reference upload field
$("#reference-upload").fileinput({
    uploadUrl: '/api/v1/references',
    uploadAsync: false,
    maxFileCount: 1,
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    elErrorContainer: "#referenceUploadErrorBlock"
});

$("#reference-upload").on('filepreajax', (event, data) => {
    $(event.target).fileinput.uploadExtraData = {
        title: document.getElementById('reference-title').value
    };
});

$("#reference-upload").on('filebatchuploadsuccess', (event, data) => {
    refreshData();
});

// Gallery
$(document).on('click', '[data-toggle="lightbox"]', event => {
    event.preventDefault();
    $(event.target).ekkoLightbox();
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
                restClient.delete(item['id'], data => {
                    let config = {
                        message: data.message
                    };
                    if (data.type === 'error') {
                        config.className = 'alert-error';
                        config.title = 'Reference removing error';
                    } else {
                        config.callback = refreshData;
                        config.title = 'Reference removed successfully';
                    }
                    bootbox.alert(config);
                });
            }
        }
    });
};

let refreshData = () => restClient.getAll(data => {
    let referencesGallery = document.getElementById('references-gallery');
    referencesGallery.classList.add('hide');
    while (referencesGallery.firstChild) {
        referencesGallery.removeChild(referencesGallery.firstChild);
    }
    referencesGallery.appendChild(new GalleryEditableBuilder(removeCallback).build(data));
    referencesGallery.classList.remove('hide');
});

refreshData();