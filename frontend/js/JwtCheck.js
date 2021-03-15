import JwtService from "./service/JwtService.js";

const jwtService = new JwtService();
if (jwtService.checkExpire()) {
    jwtService.logout();
}
