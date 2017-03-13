import {restClient as paramsRestClient} from '../rest/params-rest-client.js';
import {restClient as carouselImagesRestClient} from '../rest/carousel-images-rest-client.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';

import CarouselLightboxEditableBuilder from '../carousel/lightbox/editable/carousel-lightbox-editable-builder.js';
import CarouselLoader from '../carousel/carousel-loader.js';

/* Param loading flow */
let loadParam = (paramId, elementUniqueName) => {
    paramsRestClient.getById(paramId, data => {
        document.getElementById('param-' + elementUniqueName).value = data.body;
    });

    document.getElementById('param-' + elementUniqueName + '-save-button').addEventListener('click', event => {
        paramsRestClient.update(paramId, {
            body: document.getElementById('param-' + elementUniqueName).value
        }, data => BootboxAlert.show(data));
    });
};

/* Company name handling */
loadParam(Constants.PARAMS.COMPANY_NAME, 'company-name');

/* Footer year handling */
loadParam(Constants.PARAMS.FOOTER_YEAR, 'footer-year');

/* Carousel images upload field */
$("#carousel-image-upload").fileinput({
    uploadUrl: '/api/v1/carousel-images',
    uploadAsync: false,
    maxFileCount: 1,
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    elErrorContainer: "#carousel-image-upload-error-block"
});

$("#carousel-image-upload").on('filebatchuploadsuccess', (event, data) => {
    if (data.response.type === 'error') {
        BootboxAlert.show(data.response, refreshData);
    } else {
        let title = document.getElementById('carousel-image-title').value;
        if (title === '') {
            BootboxAlert.show({
                type: 'success',
                message: 'Carousel image created successfully'
            }, refreshData);
        } else {
            carouselImagesRestClient.update(data.response.id, {title: title}, json2 => {
                BootboxAlert.show(json2, refreshData);
            });
        }
        document.getElementById('carousel-image-title').value = '';
    }
});

/* Carousel images handling */
$(document).on('click', '[data-toggle="lightbox"]', event => {
    event.preventDefault();
    $(event.target.parentElement).ekkoLightbox();
});

let editCarouselImageCallback = (item, event) => {
    bootbox.prompt({
        title: item['title'] === 'null' || item['title'] == '' ? 'Carousel image' : item['title'],
        callback: title => {
            if (title !== null) {
                carouselImagesRestClient.update(item['id'], {title: title}, json => {
                    BootboxAlert.show(json, refreshData);
                });
            }
        }
    });
    $(".modal-dialog form[class='bootbox-form'] input").attr('placeholder', 'Enter new title');
};
let removeCarouselImageCallback = (item, event) => {
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
                carouselImagesRestClient.delete(item['id'], data => {
                    BootboxAlert.show(data, refreshData);
                });
            }
        }
    });
};

let refreshData = () => CarouselLoader.loadLightbox(
    new CarouselLightboxEditableBuilder(editCarouselImageCallback, removeCarouselImageCallback));

refreshData();