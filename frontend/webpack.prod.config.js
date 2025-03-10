const path = require('path');
const base = require('./webpack.base.config.js');
const {merge} = require('webpack-merge');
const HtmlPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const Dotenv = require('dotenv-webpack');
const WorkboxPlugin = require('workbox-webpack-plugin');

module.exports = merge(base, {
    mode: 'production',
    output: {
        filename: '[name].[contenthash].bundle.js',
        path: path.resolve(__dirname, 'dist'),
        clean: true,
    },
    optimization: {
        minimize: true,
        minimizer: [
            new CssMinimizerPlugin({
                test: /\.css$/,
            }),
            new TerserPlugin(),
        ],
    },
    plugins: [
        new WorkboxPlugin.GenerateSW({
            skipWaiting: true,
            clientsClaim: true,
        }),
        new Dotenv({
            path: '',
            systemvars: true,
            safe: true,
        }),
        new MiniCssExtractPlugin({filename: '[name].[contenthash].css'}),
        new HtmlPlugin({
            title: 'CoBudget | Welcome',
            template: './src/pages/landing.html',
            filename: 'landing.html',
            chunks: ['landing'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Login',
            template: './src/pages/login.html',
            filename: 'login.html', //TODO try to add content hash
            chunks: ['login'],
        }),
        new HtmlPlugin({
            title: 'CoBudget | Week view',
            template: './src/pages/index.html',
            filename: 'index.html', // TODO try to add content hash
            chunks: ['index'],
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
        new HtmlPlugin({
            title: 'CoBudget | Configuration',
            template: './src/pages/user-config.html',
            filename: 'user-config.html',
            chunks: ['userConfig'],
        }),
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: 'css-loader',
                        options: {
                            importLoaders: 1,
                            esModule: true,
                            modules: {
                                exportLocalsConvention: 'camelCaseOnly',
                            },
                        },
                    },
                ],
                include: /\.module\.css$/,
            },
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                ],
                exclude: /\.module\.css$/,
            },
        ],
    },
});