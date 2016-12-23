import {restClient} from './../rest/references-rest-client.js';
import GalleryEditableBuilder from './../gallery/editable/gallery-editable-builder.js';

// document.getElementById("reference-upload").addEventListener('click', event => {
//     bootbox.prompt("Title", result => {
//         $(event.target).fileinput({
//             uploadUrl: '/api/v1/references',
//             uploadAsync: false,
//             maxFileCount: 1,
//             previewFileType: 'image',
//             allowedFileTypes: ['image'],
//             elErrorContainer: "#referenceUploadErrorBlock",
//             uploadExtraData: { title: result }
//         });
//     });
// });

$("#reference-upload").fileinput({
    uploadUrl: '/api/v1/references',
    uploadAsync: false,
    maxFileCount: 1,
    previewFileType: 'image',
    allowedFileTypes: ['image'],
    elErrorContainer: "#referenceUploadErrorBlock",
    layoutTemplates: {
        main1: '{preview}\n' +
            '<div class="kv-upload-progress hide"></div>\n' +
            '<div class="input-group {class}">\n' +
            '   {caption}\n' +
            '   <div class="input-group-btn">\n' +
            '       {remove}\n' +
            '       {cancel}\n' +
            '       {upload}\n' +
            '       <button type="button" tabindex="500" title="Create" class="{css}">Create</button>\n' +
            '       {browse}\n' +
            '   </div>\n' +
            '</div>'
    }
});

// $("#reference-upload").on('filepreupload', (event, data) => {
//     bootbox.prompt("Title", result => data.form.append('title', result));
// });

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