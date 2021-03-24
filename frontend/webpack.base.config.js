module.exports = {
    entry: {
        login: './src/js/login.js',
        week: './src/js/week.js',
        register: './src/js/register.js',
        index: './src/js/index.js',
        addExpense: './src/js/addExpense.js',
    },
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