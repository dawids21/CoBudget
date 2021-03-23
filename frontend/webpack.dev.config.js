const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');

module.exports = merge(base, {
    mode: 'development',
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
        ],
    },
});