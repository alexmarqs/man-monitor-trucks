export const MAP_STYLES = {
  width: '100%',
  height: '100%'   
 };

export const INIT_MAP_COORDS = { 
  lat: "38.736946", 
  lng: "-9.142685"
};

export const MAP_ZOOM = 15;

export const OPTIONS_POI = [
  { label: "View all", value: ["restaurant", "gas_station", "lodging"] },
  { label: "Restaurants", value: ["restaurant"] },
  { label: "Gas Stations", value: ["gas_station"] },
  { label: "Hotels", value: ["lodging"] }
];

export const OPTIONS_RADIUS = [
  { label: "No radius", value: null},
  { label: "100 m", value: "100" },
  { label: "300 m", value: "300" },
  { label: "800 m", value: "800" }
];  

export const buildRequestToGooglePlacesAPI = (latestPosition, poiType, radius, rankByDistance) => {
  const request = {
    location: latestPosition,
    types: poiType
  }
  return radius ? {...request, radius: radius} : {...request, rankBy: rankByDistance};
}