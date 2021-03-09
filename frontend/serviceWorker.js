const staticCoBudget = "cobudget-site-v1";

// what to store in cache?
const assets = [
    "/index.html",
    "/js/registerServiceWorker.js",
];

// runs when the service worker is installed
self.addEventListener("install", installEvent => {
    installEvent.waitUntil(
        caches.open(staticCoBudget).then(cache => {
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
