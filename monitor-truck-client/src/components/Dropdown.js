import React, {useState} from 'react';
import PropTypes from 'prop-types';
import arrowIcon from "../assets/images/icn-arrow-down.png";

const Dropdown = props => {
  const [toggle, setToggle] = useState(false);
  const {placeHolder, options, value, onSelect} = props;
    
  return (
    <div className="dropdown-container" onClick={() => setToggle(!toggle)}>
      <div className="dropdown-header">
        <div className="mr-10 w-32">{value < 0 ? placeHolder : options[value].label}</div>
        <img className="h-2 w-3" src={arrowIcon} alt="Dropdown arrow"></img>  
      </div>
      {toggle && <ul className="dropdown-list">
          {options.map((item, index)=> (
            <li className={"dropdown-item" + (index === value ? " font-bold text-teal-500" : " text-gray-700")} key={index} onClick={() => onSelect(index)}>
              {item.label}
            </li>
          ))}
        </ul>}
    </div>
  );
}

Dropdown.propTypes = {
  options: PropTypes.array.isRequired,
  placeHolder: PropTypes.string.isRequired,
  onSelect: PropTypes.func.isRequired,
  value: PropTypes.number.isRequired
}

export default Dropdown;
