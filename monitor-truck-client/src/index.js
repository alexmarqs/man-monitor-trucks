import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
import './assets/styles/main.css';

// The project supports all modern browsers. Support for Internet Explorer 9, 10, and 11 requires polyfills. 
// For a set of polyfills to support older browsers, use react-app-polyfill!
// More info: https://github.com/facebook/create-react-app/blob/master/packages/react-app-polyfill/README.md

// When editing the browserslist, this does not include polyfills automatically for you, 
// you will still need to polyfill language features as needed based 
// on the browsers you are supporting, you may notice that your changes don't get picked up right away. 
// This is due to an issue in babel-loader not detecting the change in your package.json. 
// A quick solution is to delete the node_modules/.cache folder and try again.

ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
