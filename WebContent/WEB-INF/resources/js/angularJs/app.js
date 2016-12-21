var siteApp = angular.module('app', [
    'crOrder'
]);

siteApp.config(
['$locationProvider', '$httpProvider',
  function($locationProvider, $httpProvider) {
    // to be able to use standard (not hash-bang) urls
    $locationProvider.html5Mode(true);

    // this header is needed for zend to be able to recognize
    // angular requests as _request->isXmlHttpRequest()
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  }
]
);
