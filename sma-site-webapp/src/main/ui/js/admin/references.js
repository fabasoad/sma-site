import {restClient} from './../rest/references-rest-client.js';
import GalleryEditableBuilder from './../gallery/editable/gallery-editable-builder.js';
import Constants from './../core/constants.js';

let showMessage = data => {
    let config = {
        message: data.message,
        title: Constants.APPLICATION_NAME
    };
    if (data.type === 'error') {
        config.className = 'alert-error';
    } else {
        config.callback = refreshData;
    }
    bootbox.alert(config);
};

// Reference upload field
$("#reference-upload").fileinput({
    uploadUrl: '/api/v1/references',
    uploadAsync: false,
    maxFileCount: 1,
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    elErrorContainer: "#referenceUploadErrorBlock"
});

$("#reference-upload").on('filebatchuploadsuccess', (event, data) => {
    if (data.response.type === 'error') {
        showMessage(data.response);
    } else {
        let title = document.getElementById('reference-title').value;
        if (title === '') {
            showMessage({
                type: 'success',
                message: 'Reference created successfully'
            });
        } else {
            restClient.update(data.response.id, {title: title}, json2 => {
                showMessage(json2);
            });
        }
    }
});

// Gallery
$(document).on('click', '[data-toggle="lightbox"]', event => {
    event.preventDefault();
    $(event.target.parentElement).ekkoLightbox();
});

let editCallback = (item, event) => {
    bootbox.prompt({
        title: item['title'],
        callback: title => {
            if (title !== null) {
                restClient.update(item['id'], {title: title}, json => {
                    showMessage(json);
                });
            }
        }
    });
    $(".modal-dialog form[class='bootbox-form'] input").attr('placeholder', 'Enter new title');
};

let removeCallback = (item, event) => {
    let title = item['title'] === 'null' || item['title'] == '' ? 'selected item' : "'" + item['title'] + "'";
    bootbox.confirm({
        title: Constants.APPLICATION_NAME,
        message: "Do you really want to remove " + title + "?",
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
                    showMessage(data);
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
    referencesGallery.appendChild(new GalleryEditableBuilder(editCallback, removeCallback).build(data));
    referencesGallery.classList.remove('hide');
});

refreshData();