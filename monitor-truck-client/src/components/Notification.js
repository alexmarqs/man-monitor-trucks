import React from 'react'
import PropTypes from 'prop-types'

function Notification({errorMsg, onClose}) {
  return (
    <div className="notification">
      <div className="notification-box" role="alert">
        <strong className="font-bold">Ups! </strong>
        <span className="block sm:inline">{errorMsg}</span>
        <span className="notification-close" onClick={onClose}>
          <svg className="fill-current h-5 w-5 text-red-500" role="button" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><title>Close</title><path d="M14.348 14.849a1.2 1.2 0 0 1-1.697 0L10 11.819l-2.651 3.029a1.2 1.2 0 1 1-1.697-1.697l2.758-3.15-2.759-3.152a1.2 1.2 0 1 1 1.697-1.697L10 8.183l2.651-3.031a1.2 1.2 0 1 1 1.697 1.697l-2.758 3.152 2.758 3.15a1.2 1.2 0 0 1 0 1.698z"/></svg>
        </span>
      </div>
    </div>
  );
}

Notification.propTypes = {
  errorMsg: PropTypes.string.isRequired,
  onClose: PropTypes.func.isRequired
}

export default Notification;

