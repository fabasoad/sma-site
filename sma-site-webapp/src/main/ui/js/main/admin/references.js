$("#input-1a").fileinput({
    uploadUrl: '/api/v1/references',
    uploadAsync: true,
    maxFileCount: 5,
    previewFileType: 'image',
    allowedFileTypes: ['image']
});