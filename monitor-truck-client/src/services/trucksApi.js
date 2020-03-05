import {API_TRUCKS} from "../configs/appConfigs";

export const getLatestTruckPositionsBy = (licensePlate) => {
  return fetch(API_TRUCKS + "/" + licensePlate + "/latestPositions");
}

