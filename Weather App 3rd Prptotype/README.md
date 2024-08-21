**Weather App**

**Overview**

This weather app allows users to check the current weather and view historical weather data for the past 7 days for any city. The app fetches weather data from the OpenWeather API and stores it in a database for retrieval and display.

**Files**

- data.php: Handles the server-side logic for fetching weather data from the API, storing it in the database, and providing the data in JSON format.
- index.html: Contains the HTML structure of the app, including forms for user input and sections for displaying weather data.
- script.js: Manages client-side interactions, including fetching data from the server, updating the UI, and handling user input.
- style.css: Defines the visual style of the app, including layout, colors, and fonts.

**Setup**

**Database Setup:**

- Create a MySQL database and import the required schema into it.
- Update the database connection details in data.php with your own credentials.
  
 **API Key:**

- Replace the placeholder API key in data.php with your own OpenWeather API key.

**Deployment:**

- Host these files on a web server that supports PHP.

**Usage**

- Current Weather:
    Enter a city name in the search bar and click "Search" to view the current weather for that city.
- Historical Data:
    Click the "History" button to view weather data for the past 7 days for the default city ("khabarovsk"). You can change that by changing the default city from both PHP and JS files.

