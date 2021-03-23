const path = require('path');

module.exports = {
    mode: 'production',
    output: {
        filename: '[name].[contenthash].bundle,js',
        path: path.resolve(__dirname, 'dist'),
        clean: true,
    },
};