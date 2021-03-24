export default class ConfigApp {
    constructor(profile) {
        if (profile === "dev") {
            this.restUrl = "http://localhost:8080";
        } else if (profile === "prod") {
            this.restUrl = "https://cobudget-backend.herokuapp.com";
        } else {
            throw new Error("Undefined profile");
        }
    }

    getRestUrl() {
        return this.restUrl;
    }
}