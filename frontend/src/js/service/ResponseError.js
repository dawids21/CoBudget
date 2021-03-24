export default class ResponseError extends Error {
    constructor(message, responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
};