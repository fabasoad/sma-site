import BootboxAlert from './core/bootbox-alert.js';

$("#application-form-upload").fileinput({
    uploadUrl: '/api/v1/application-forms',
    uploadAsync: false,
    maxFileCount: 1,
    elErrorContainer: "application-form-upload-error-block"
});

$("#application-form-upload").on('filebatchuploadsuccess', (event, data) => {
    if (data.response.type === 'error') {
        BootboxAlert.show(data.response);
    } else {
        BootboxAlert.show({
            type: 'success',
            message: 'Application form uploaded successfully'
        });
    }
});