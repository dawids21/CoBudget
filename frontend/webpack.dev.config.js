const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');

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
        new HtmlWebpackPlugin({
            title: 'CoBudget | Welcome',
            template: './src/pages/index.html',
            filename: 'index.html',
            chunks: ['index'],
        }),
        new HtmlWebpackPlugin({
            title: 'CoBudget | Login',
            template: './src/pages/login.html',
            filename: 'login.html',
            chunks: ['login'],
        }),
        new HtmlWebpackPlugin({
            title: 'CoBudget | Register',
            template: './src/pages/register.html',
            filename: 'register.html',
            chunks: ['register'],
        }),
        new HtmlWebpackPlugin({
            title: 'CoBudget | Week view',
            template: './src/pages/week.html',
            filename: 'week.html',
            chunks: ['week'],
        }),
        new HtmlWebpackPlugin({
            title: 'CoBudget | Add expense',
            template: './src/pages/add-expense.html',
            filename: 'add-expense.html',
            chunks: ['addExpense'],
        }),
    ],
});