window.onload = () => {main("khabarovsk")}

async function saveData(cityName){
  try {
      const response = await fetch(`http://rojin2417733.free.nf/protfolio-task-3/data.php?q=${cityName}`);
      if (!response.ok) {
          alert ('Data was not found.')
          throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      if (data.cod && data.cod === "404") {
        // If the API response contains a 404 error code, city data is not found
        alert("City not found in the API.");
    } else{
      // console.log('Data fetched from server:', data);
      const city = cityName.toLowerCase();
      const weatherData = JSON.stringify(data[0]); // Store just the first object
      localStorage.setItem(city, weatherData);}
      // console.log("data saved in cache")
  } catch (error) {
      console.error("Error in saveData function: ", error);
  }
};

async function getData(cityName){
  try{
      storedData = await localStorage.getItem(cityName);//take from stored
      // console.log("data taken")
      // console.log(storedData);
      if (storedData) {
          const parsedData = JSON.parse(storedData);
          // console.log("data parsed")
          updateData(parsedData); // Call updateData here
      } else {
          console.log("data not in local storage.");
          return null;
      }
  }
  catch(error){
      console.error("Error fetching data from browser cache: ", error);
  }
};

function updateData(data) {//update from data taken
    document.getElementById("city").innerText = data.city; 
    document.getElementById("temperature").innerText = `Temperature: ${data.temperature} °C`;
    document.getElementById("humidity").innerText = `Humidity: ${data.humidity} %`;
    document.getElementById("wind").innerText = `Wind Speed: ${data.windSpeed} km/h`;
    document.getElementById("pressure").innerText = `Pressure: ${data.pressure} Pa`;
    document.getElementById("weatherIcon").src = `https://openweathermap.org/img/wn/${data.iconCode}@2x.png`;
    document.getElementById("description").innerText = data.weatherCondition; 
    document.getElementById("date").innerText = data.Day_and_Date;
}

async function main(cityName){
  try{
      if (navigator.onLine){
          await saveData(cityName);
      }
      await getData(cityName);
  } catch (error){
      console.error("There was an error in main function: ", error);
  }
}

const locationInput = document.getElementById("locationInput")
const form = document.getElementById("form")
const searchBar = document.getElementById("locationInput")

form.addEventListener("submit", async (event) => {
  event.preventDefault(); 
  let city = searchBar.value;
  city = city.toLowerCase()
  if (navigator.onLine) {
      await saveData(city);
  }
  getData(city);
});

//past 7 days 
async function lastWeekData(){
    try{
        response = await fetch(`http://rojin2417733.free.nf/protfolio%20task%203/data.php?last7days=true`)
        const weatherData = await response.json()
        displayWeatherData(weatherData);
      } catch (error) {
        console.error('Error fetching last 7 days weather:', error);
      }
}
function displayWeatherData(weatherData) {

    const weatherDataElement = document.getElementById('weatherData');
    weatherDataElement.innerHTML = ''; 
  
    weatherData.forEach((day) => {
  
      const dayElement = document.createElement('div');
      dayElement.classList.add('weather-day');
      dayElement.innerHTML = `
        <div class = "history" id="date">Date: ${day.Day_and_Date}</div>
        <div class = "history" id="temperature">Temperature: ${day.temperature}°C</div>
        <div class = "history" id="humidity">Humidity: ${day.humidity}%</div>
        <div class = "history" id="wind-speed">Wind Speed: ${day.windSpeed} km/h</div>
        <div class = "history" id="pressure">Pressure: ${day.pressure} hPa</div>
        <div class = "history" id="weather-icon">
          <img src="https://openweathermap.org/img/wn/${day.iconCode}@2x.png" alt="Weather Icon">
        </div>
        <div class = "history" id="weatherCondition">Description: ${day.weatherCondition}</div>
        <br>`;
      weatherDataElement.appendChild(dayElement);
    });
  }
document.getElementById("historyButton").addEventListener("click",()=>{lastWeekData()})