'use strict';

import * as ReactDOM from "react-dom";
import MainApp from "./api/mainApp";
import React from "react";

ReactDOM.render(
	<React.StrictMode>
		<MainApp />
	</React.StrictMode>,
	document.getElementById('react')
);
