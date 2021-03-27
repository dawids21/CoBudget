const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');
const HtmlPlugin = require('html-webpack-plugin');

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
    plugins: [
        new HtmlPlugin({
            title: 'CoBudget | Welcome',
            template: './src/pages/index.html',
            filename: 'index.html',
            chunks: ['index'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Login',
            template: './src/pages/login.html',
            filename: 'login.html',
            chunks: ['login'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Register',
            template: './src/pages/register.html',
            filename: 'register.html',
            chunks: ['register'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Week view',
            template: './src/pages/week.html',
            filename: 'week.html',
            chunks: ['week'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Add expense',
            template: './src/pages/add-expense.html',
            filename: 'add-expense.html',
            chunks: ['addExpense'],
        }),
    ],
});