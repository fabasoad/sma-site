export default class RestUtils {

    static handleResponse(data, callback) {
        if (typeof callback === 'function') {
            let json;
            try {
                json = JSON.parse(data.responseText);
                json.status = data.status;
            } catch (e) {
                json = {
                    type: 'error',
                    message: data.responseText,
                    status: data.status
                };
            }
            callback(json);
        }
    }
}