const path = require('path');

module.exports = {
    entry: {
        login: './src/js/login.js',
        week: './src/js/week.js',
    },
    module: {
        rules: [
            {
                test: /\/.html$/,
                use: ['html-loader'],
            },
            {
                test: /\.(svg|eot|woff|woff2|ttf)$/,
                use: ['file-loader'],
            },
        ],
    },
};