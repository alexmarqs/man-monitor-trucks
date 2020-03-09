export const searchNearby = (google, map, request) => {
  return new Promise((resolve, reject) => {
    const service = new google.maps.places.PlacesService(map);
    service.nearbySearch(request, (results, status) => {
      if (status === google.maps.places.PlacesServiceStatus.OK || status === google.maps.places.PlacesServiceStatus.ZERO_RESULTS) {
        resolve(results);
      } else {
        reject(status);
      } 
    });
  });
}