<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin");
function connect_database(){//connect to database
    try{
        $connection = new mysqli ("sql301.infinityfree.com", "if0_35985066", "pzqrkoWz4wO6Ue", "if0_35985066_weather_db");
        if(mysqli_connect_errno()){ 
            echo '{"error": "Database connection failed"}';}
        // } else{echo "Connected.";}
        return $connection;
    }catch(Exception $e){
        echo "Couldn't connect to database. $e";
    }
}

function fetchAPIData($cityName){//fetch data from opwnweather API
    try{
        $url = "https://api.openweathermap.org/data/2.5/weather?units=metric&q=$cityName&apikey=2962752cf80ba6de00a0e1bc205965a6";
        $response = @file_get_contents($url);
        $data = json_decode($response, true);
        if(isset($data['main']) && isset($data['weather'])){
            return $data;
        } else {
            return null; // Return null if city data was not found
        }    }
    catch(Exception){
        // echo"failed to fetch data from api. $th";
    }
}

function insertData($connection,$data){//insert data from opwnweather API into database
    try{
        //ectracting data
        $cityName = $data["name"];
        $weather_condition = $data['weather'][0]['main'];
        $temperature = $data['main']['temp'];
        $humidity = $data['main']['humidity'];
        $wind_speed = $data['wind']['speed'];
        $pressure = $data['main']['pressure'];
        $iconcode = $data['weather'][0]['icon'];
        $Day_and_Date = date("l, M j, Y", $data['dt']);
        $jusDT = $data['dt'];

        //if same city data for same day exists update it
        $sameCityDate = "SELECT * FROM weather_data WHERE city='$cityName' and Day_and_Date='$Day_and_Date'";
        $existingRows = mysqli_query($connection,$sameCityDate);
        if (mysqli_num_rows($existingRows)>0){
            $updateValue = "UPDATE weather_data SET temperature='$temperature', weatherCondition='$weather_condition', pressure='$pressure', windSpeed='$wind_speed', humidity='$humidity', iconCode='$iconcode', dt='$jusDT' WHERE city='$cityName' AND Day_and_Date='$Day_and_Date'";
            $result = mysqli_query($connection,$updateValue);
            if ($result> 0){}else{echo "Error. ".mysqli_error($connection);}
        }//if same city data not exist for same day. insert it
        else{
            $insertData = "INSERT INTO weather_data (city, temperature, Day_and_Date, pressure, windSpeed, humidity, weatherCondition, iconCode, dt)
            VALUE('$cityName', '$temperature', '$Day_and_Date', '$pressure', '$wind_speed', '$humidity', '$weather_condition', '$iconcode','$jusDT')";
            $result = mysqli_query($connection,$insertData);
            if ($result){}else{echo "Error".mysqli_error($connection);}
        }
        return [$cityName,$Day_and_Date];
    } catch(Exception $e){
        echo "Some error occured $e";
    }
}

function getData($connection,$cityName,$Day_and_Date){//take data from database
    $insertedData = "SELECT * FROM weather_data WHERE city='$cityName' AND Day_and_Date='$Day_and_Date';";
    $result = mysqli_query($connection,$insertedData);
    $data = [];
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    return $data;
}

function jsonMaker($result){//make json from data
    if (!empty($result)) {
        $databaseFormatData = [];
        foreach ($result as $row) {
            $databaseFormatData[] = [
                "city" => $row['city'],
                "Day_and_Date" => $row['Day_and_Date'],
                "temperature" => $row["temperature"],
                "humidity" => $row["humidity"],
                "pressure" => $row["pressure"],
                "windSpeed" => $row["windSpeed"],
                "weatherCondition" => $row["weatherCondition"],
                "iconCode" => $row["iconCode"],
                // "dt"=> $row["dt"],
            ];
        }
        return json_encode($databaseFormatData);}
    else {echo "Error. No data found.";}
}

function main($cityName){//put everything in a flow
    $connection = connect_database();
    $data = fetchAPIData($cityName);
    if ($data){
    [$cityName,$Day_and_Date] = insertData($connection,$data);
    $result = getData($connection,$cityName,$Day_and_Date);
    echo jsonMaker($result);}
    else {
        // Return error JSON if city data was not found
        $result = ["cod" => "404", "message" => "city not found"];
        echo json_encode($result);
    }
}

// Check if the request is for the current weather data
if (!isset($_GET['last7days'])) {
    // Call main() only when the request is not for past data
    if (isset($_GET["q"])) {
        $cityName = $_GET["q"];
    } else {
        $cityName = "khabarovsk";
    }

    main($cityName);
}

//to get last 7 days weather
function get_past_weather_data($connection,$cityName) {
    $query = "SELECT * FROM (
        SELECT * FROM weather_data 
        WHERE city = '$cityName' 
        ORDER BY dt DESC 
        LIMIT 7
      ) sub 
      ORDER BY dt ASC";
    $result = $connection->query($query);
    if ($result) {
        return $result->fetch_all(MYSQLI_ASSOC); // this fetch all rows as an array of associative arrays
    } else {
        return null;
    }
}

if (isset($_GET['last7days']) && $_GET['last7days'] == 'true') {
    $connection = connect_database();
    $data = get_past_weather_data($connection,"khabarovsk");
    
    // Encode the data into JSON
    echo json_encode($data);
    exit;
}
