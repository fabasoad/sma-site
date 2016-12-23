$("#application-form-upload").fileinput({
    uploadUrl: '/api/v1/application-forms',
    uploadAsync: true,
    maxFileCount: 1
});