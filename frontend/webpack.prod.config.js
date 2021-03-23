const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');

module.exports = merge(base, {
    mode: 'production',
    output: {
        filename: '[name].[contenthash].bundle,js',
        path: path.resolve(__dirname, 'dist'),
        clean: true,
    },
});