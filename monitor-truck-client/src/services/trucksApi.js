import {API_TRUCKS} from "../configs/appConfigs";
import axios from "axios";

export const getLatestTruckPositionsBy = (licensePlate) => {
  return axios.get(API_TRUCKS + "/" + licensePlate + "/latestPositions");
}

