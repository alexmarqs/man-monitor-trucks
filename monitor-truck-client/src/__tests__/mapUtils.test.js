import {buildRequestToGooglePlacesAPI} from "../utils/mapUtils";

const expectedResultWithRadius = {
  location: {
    lat: 1,
    lng: 1
  },
  types: ["food"],
  radius: "500"
}

const expectedResultWithoutRadius = {
  location: {
    lat: 1,
    lng: 1
  },
  types: ["food"],
  rankBy: 1
}

it('test requests to google places api', () => {
  expect(buildRequestToGooglePlacesAPI({lat: 1, lng: 1}, ["food"], "500", 1)).toEqual(expectedResultWithRadius);
  expect(buildRequestToGooglePlacesAPI({lat: 1, lng: 1}, ["food"], null, 1)).toEqual(expectedResultWithoutRadius);
});