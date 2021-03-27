const FaviconsWebpackPlugin = require('favicons-webpack-plugin');

module.exports = {
    entry: {
        login: './src/js/login.js',
        week: './src/js/week.js',
        register: './src/js/register.js',
        index: './src/js/index.js',
        addExpense: './src/js/addExpense.js',
    },
    plugins: [
        new FaviconsWebpackPlugin({
            logo: './src/icon.png',
            cache: true,
            prefix: 'icons-[contenthash:8]/',
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
                test: /\.(svg|eot|woff|woff2|ttf)$/,
                use: ['file-loader'],
            },
        ],
    },
};