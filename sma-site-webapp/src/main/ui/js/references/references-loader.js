import {restClient} from '../rest/references-rest-client.js';

export default class ReferencesLoader {

    static load(builder) {
        let referencesGallery = document.getElementById('references-gallery');
        referencesGallery.classList.add('hide');
        restClient.getAll(data => {
            while (referencesGallery.firstChild) {
                referencesGallery.removeChild(referencesGallery.firstChild);
            }
            referencesGallery.appendChild(builder.build(data));
            referencesGallery.classList.remove('hide');
        });
    }
}