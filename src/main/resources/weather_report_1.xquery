xquery version "1.0";

declare namespace weather = "http://example.com/weather";

(: Get the average minimum and maximum temperature for each city. :)
for $city in doc("weather.xml")/weather:countries/weather:city
let $min_temp := (
  for $day in $city/weather:days/weather:day
  return $day/weather:temperature/weather:min
)[1]
let $max_temp := (
  for $day in $city/weather:days/weather:day
  return $day/weather:temperature/weather:max
)[1]
return element report {
  attribute city { $city/weather:name },
  element min { $min_temp },
  element max { $max_temp }
}