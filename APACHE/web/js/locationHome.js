/*
 * @author Emmanuel Valenzuela 2016-2511
 * @function Init Map if isn't inside of de province. Go to own location.
 */
/* global google */
window.onload = initMap;
function initMap() {
    //Tomando la geolocalizacion del usuario en cuestion
    navigator.geolocation.getCurrentPosition(notHome);
    
    function notHome(respuesta) {
        //Mi lat y long de mi ubicacion actual
        var lat = respuesta.coords.latitude;
        var lng = respuesta.coords.longitude;
        //Objeto de google de tipo coordenada
        var gCoordenadas = new google.maps.LatLng(lat, lng);
        //Opciones del mapa
        var mapOptions = {
            zoom: 17,
            center: gCoordenadas
        };
        //creando el mapa
        var map = new google.maps.Map(document.getElementById("map2"), mapOptions);
        //creacion del marcador
        var marker = new google.maps.Marker({
            position: gCoordenadas,
            map: map,
            title: 'In NoWhere'
        });
    }
}