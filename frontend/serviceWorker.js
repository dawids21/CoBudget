const cacheName = "cobudget-140320212134";

// what to store in cache?
const assets = [
    "/",
    "/index.html",
    "/login.html",
    "/register.html",
    "/week.html",
    "/add-expense.html",
    "/inprogress.html",
    "/manifest.json",
    "/browserconfig.xml",
    "/favicon-16x16.png",
    "/android-chrome-192x192.png",
    "/mstile-150x150.png",
    "/favicon-32x32.png",
    "/apple-touch-icon.png",
    "/favicon.ico",
    "/safari-pinned-tab.svg",
    "/android-chrome-512x512.png",
    "/css/style.css",
    "/css/_typography.css",
    "/css/_spacing.css",
    "/css/_colors.css",
    "/css/webfonts/fa-brands-400.ttf",
    "/css/webfonts/fa-regular-400.ttf",
    "/css/webfonts/fa-solid-900.woff2",
    "/css/webfonts/fa-solid-900.eot",
    "/css/webfonts/fa-regular-400.woff2",
    "/css/webfonts/fa-brands-400.eot",
    "/css/webfonts/fa-brands-400.woff",
    "/css/webfonts/fa-regular-400.eot",
    "/css/webfonts/fa-regular-400.woff",
    "/css/webfonts/fa-brands-400.woff2",
    "/css/webfonts/fa-solid-900.svg",
    "/css/webfonts/fa-solid-900.ttf",
    "/css/webfonts/fa-solid-900.woff",
    "/css/webfonts/fa-regular-400.svg",
    "/css/webfonts/fa-brands-400.svg",
    "/css/fontawesome/all.min.css",
    "/js/service/FetchService.js",
    "/js/service/JwtService.js",
    "/js/service/RequestService.js",
    "/js/app.js",
    "/js/config.js",
    "/js/week.js",
];

// runs when the service worker is installed
self.addEventListener("install", () => {
    //TODO add popup for refreshing UI
    self.skipWaiting();
});

self.addEventListener("activate", event => {
    event.waitUntil(
        caches.keys().then(keyList => {
            return Promise.all(
                keyList.map(key => {
                    if (key !== cacheName) {
                        return caches.delete(key);
                    }
                })
            );
        })
    );
    event.waitUntil(
        caches.open(cacheName).then(cache => {
            cache.addAll(assets);
        })
    );
});

self.addEventListener("fetch", fetchEvent => {
    fetchEvent.respondWith(
        caches.match(fetchEvent.request).then(res => {
            return res || fetch(fetchEvent.request);
        })
    );
});
