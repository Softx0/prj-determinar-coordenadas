/* 
 * @author Emmanuel Valenzuela 2016-2511
 * @function Init Map if is inside of de province.
 */
/* global google */
window.onload = initMap;
function initMap() {
    //var latitud = document.getElementById("latitud");
    //var longitud = document.getElementById("longitud");
    //Coordenadas Puerto Plata
    var myLatLng = {
        lat: 19.73434,
        lng: -70.7332
    };
    //var gCoordenadas = new google.maps.LatLng(longitud, latitud);
    
    //Opciones para el mapa
    var mapOptions = {
        zoom: 10,
        center: myLatLng
    };
    //Creando el mapa
    var map = new google.maps.Map(document.getElementById("map1"), mapOptions);
    //Creando el marcador
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: 'Puerto Plata!!'
    });
}