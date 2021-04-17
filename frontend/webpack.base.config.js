const FaviconsPlugin = require('favicons-webpack-plugin');
const path = require('path');

module.exports = {
    entry: {
        login: './src/js/login.js',
        index: './src/js/index.js',
        register: './src/js/register.js',
        landing: './src/js/landing.js',
        addExpense: './src/js/addExpense.js',
        userConfig: './src/js/userConfig.js',
    },
    plugins: [
        new FaviconsPlugin({
            logo: './src/icon.png',
            cache: true,
            favicons: {
                appName: 'CoBudget',
                appShortName: 'CoBudget',
                background_color: '#E9ECEC',
                theme_color: '#478401',
                orientation: 'portrait-primary',
                start_url: '/index.html',
            },
        }),
    ],
    module: {
        rules: [
            {
                test: /\.html$/,
                use: ['html-loader'],
            },
            {
                test: /\.js$/,
                include: path.resolve(__dirname, 'src/js'),
                use: ['babel-loader'],
            },
            {
                test: /\.(svg|eot|woff|woff2|ttf)$/,
                use: ['file-loader'],
            },
        ],
    },
};