// src/setupProxy.js
const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/gyeonggi',
        createProxyMiddleware({
            target: 'http://localhost:8080',
            changeOrigin: true,
        })
    );
    app.use(
        '/gyeonggi',
        createProxyMiddleware({
            target: 'http://localhost:8864',
            changeOrigin: true,
        })
    );
};