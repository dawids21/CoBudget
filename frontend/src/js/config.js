export default class ConfigApp {
    constructor() {
        this.restUrl = process.env.BACKEND_URL;
    }

    getRestUrl() {
        return this.restUrl;
    }
}