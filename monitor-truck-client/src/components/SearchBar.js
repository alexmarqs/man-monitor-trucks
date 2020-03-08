import React, {useState} from 'react';
import PropTypes from 'prop-types';
import Dropdown from './Dropdown';
import {OPTIONS_POI, OPTIONS_RADIUS} from '../utils/mapUtils';

function SearchBar( {isLoading, onSearch} ) {
  const [searchLicensePlace, setSearchLicensePlace] = useState("");
  const [selectedPoi, setSelectedPoi] = useState(-1);
  const [selectedRadius, setSelectedRadius] = useState(-1);
  const [invalid, setInvalid] = useState(false);

  const handleSubmit = e => {
    e.preventDefault();
    if (searchLicensePlace.trim() === "" || selectedPoi < 0) {
      setInvalid(true);
      return;
    }
    setInvalid(false);
    onSearch({
      licensePlate: searchLicensePlace,
      poiType: OPTIONS_POI[selectedPoi].value,
      radius: selectedRadius < 0 ? null : OPTIONS_RADIUS[selectedRadius].value
    });
  }

  return (
    <form className="form-search" onSubmit={handleSubmit}>
      <div className="md:flex">
        <div className="form-item">
          <label className="label-field" htmlFor="license-search">License Place*</label>
          <input className="input-field" id="license-search" value={searchLicensePlace} type="text" 
                placeholder="Search by license plate" onChange={e => setSearchLicensePlace(e.target.value)}></input>
        </div>
        <div className="form-item">
          <label className="label-field md:pt-0 pt-2" htmlFor="license-search">POI Type*</label>
          <Dropdown value={selectedPoi} onSelect={(val) => setSelectedPoi(val)} placeHolder="Select POI type" options={OPTIONS_POI}></Dropdown>
        </div>
        <div className="form-item">
          <label className="label-field md:pt-0 pt-2" htmlFor="license-search">Radius</label>
          <Dropdown value={selectedRadius} onSelect={(val) => setSelectedRadius(val)} placeHolder="Select radius" options={OPTIONS_RADIUS}></Dropdown>
        </div>
        <div className="md:flex-shrink self-end mt-2 mt-1">
          <button className={"btn-field " + (isLoading ? "opacity-50 cursor-not-allowed" : "")}>
            {isLoading ? "Loading" : "Apply"}
          </button>
        </div>
      </div>
      {invalid && <div className="invalid-search">*Please fill the required fields</div>}
    </form>    
  );
}

SearchBar.propTypes = {
  isLoading: PropTypes.bool.isRequired,
  onSearch: PropTypes.func.isRequired
}

export default SearchBar;

