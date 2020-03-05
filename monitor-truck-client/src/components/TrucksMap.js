import React, {useState} from 'react';
import {Map, GoogleApiWrapper, Marker} from 'google-maps-react';
import {MAP_STYLES, MAP_ZOOM, INIT_MAP_COORDS, OPTIONS_POI}  from '../utils/mapUtils';
import {GOOGLE_MAPS_API_KEY} from '../configs/appConfigs';
import currentLocationIcon from "../assets/images/icn-current-location.png";
import firstLocationIcon from "../assets/images/icn-first-location.png";
import locationIcon from "../assets/images/icn-path.png";
import gasStationIcon from "../assets/images/icn-gas-station.png";
import hotelIcon from "../assets/images/icn-hotel.png";
import restaurantIcon from "../assets/images/icn-restaurant.png";
import SearchBar from "./SearchBar";
import {getLatestTruckPositionsBy} from "../services/trucksApi";
import {searchNearby} from "../services/googlePlacesApi";
import Notification from "./Notification";
import {buildRequestToGooglePlacesAPI} from "../utils/mapUtils";

function TrucksMap(props) {
  const [nearPlaces, setNearPlaces] = useState([]);
  const [latestPositions, setLatestPositions] = useState([]);
  const [lastTruckPosition, setLastTruckPosition] = useState({});
  const [error, setError] = useState(null);
  const mapRef = React.useRef(null);
  const {google} = props;

  const handleSearch = async(searchFilter) => {
    setError(null);
    setNearPlaces([]); 

    let currentLocation;
    try {
      const response = await getLatestTruckPositionsBy(searchFilter.licensePlate);
      const data = await response.json();
      if (response.ok) {
        currentLocation = {
          lat: data[0].location.lat,
          lng: data[0].location.lng,
        }
        setLatestPositions(data);
        setLastTruckPosition(currentLocation);
      } else {
        throw new Error(data.message);
      }
    } catch(error) {
      setError("Error when invoking Monitor Trucks API: " + error.message);
      return;
    }

    try { 
      // using promise all due to view all option 
      const results = await Promise.all(searchFilter.poiType.map(async(type) => {
        const request = buildRequestToGooglePlacesAPI(currentLocation,[type], searchFilter.radius, google.maps.places.RankBy.DISTANCE);
        const result = await searchNearby(google, mapRef.current.map, request);
        return result;
      }));
      const nearPois = [].concat(...results);
      setNearPlaces(nearPois);
    } catch (error) {
      setError("Error when invoking Google Places API: " + error);
    } 
  }

  const renderTruckMarkers = () => {
    return latestPositions.map((pos, index) => 
       <Marker key={pos.id} position={{lat: pos.location.lat, lng: pos.location.lng}} 
                     icon={{
                      url: index > 0 ? (index === latestPositions.length-1 ? firstLocationIcon : locationIcon) : currentLocationIcon,
                      anchor: new google.maps.Point(16,16),
                      scaledSize: new google.maps.Size(index > 0 ? 16 : 32 , index > 0 ? 16 : 32)
                    }}/>
    );
  }

  const renderPoiMarkers = () => {
    return nearPlaces.map((place) =>
      <Marker key={place.id} position={place.geometry.location} 
        icon={{
          url: place.types.indexOf(OPTIONS_POI[1].value[0]) > -1 ? restaurantIcon : (place.types.indexOf(OPTIONS_POI[2].value[0]) > -1 ? gasStationIcon : hotelIcon),
          anchor: new google.maps.Point(16,16),
          scaledSize: new google.maps.Size(32,32)
        }}/>   
    );
}

  return (
    <div>
      <Map ref={mapRef} google={google} zoom={MAP_ZOOM} style={MAP_STYLES} initialCenter={INIT_MAP_COORDS} center={lastTruckPosition}>
        {renderTruckMarkers()}
        {renderPoiMarkers()}
      </Map>
      <SearchBar onSearch={handleSearch}></SearchBar>
      {error && <Notification errorMsg={error} onClose={() => setError(null)}></Notification>}
    </div>
  );  
}

export default GoogleApiWrapper({
  apiKey: GOOGLE_MAPS_API_KEY
})(TrucksMap);


