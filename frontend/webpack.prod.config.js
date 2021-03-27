const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');
const HtmlPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = merge(base, {
    mode: 'production',
    output: {
        filename: '[name].[contenthash].bundle.js',
        path: path.resolve(__dirname, 'dist'),
        clean: true,
    },
    plugins: [
        new MiniCssExtractPlugin({filename: '[name].[contenthash].css'}),
        new HtmlPlugin({
            title: 'CoBudget | Welcome',
            template: './src/pages/index.html',
            filename: 'index.html',
            chunks: ['index'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Login',
            template: './src/pages/login.html',
            filename: 'login.html', //TODO try to add content hash
            chunks: ['login'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Week view',
            template: './src/pages/week.html',
            filename: 'week.html', // TODO try to add content hash
            chunks: ['week'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Register',
            template: './src/pages/register.html',
            filename: 'register.html',
            chunks: ['register'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Add expense',
            template: './src/pages/add-expense.html',
            filename: 'add-expense.html',
            chunks: ['addExpense'],
        }),
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [MiniCssExtractPlugin.loader, 'css-loader'],
            },
        ],
    },
});