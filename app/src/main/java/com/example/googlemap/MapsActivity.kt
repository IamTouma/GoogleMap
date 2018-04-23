package com.example.googlemap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import kotlin.text.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mLatLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = mLatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val latitude = 35.68
        val longitude = 139.76

        mLatLng = LatLng(latitude, longitude)

        mMap.addMarker(MarkerOptions().position(mLatLng).title("皇居！"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17.0f))

        mMap.setOnMapClickListener({ latLng ->
            mLatLng = LatLng(latLng.latitude, latLng.longitude)
            var str = String.format(Locale.JAPANESE, "%f, %f", mLatLng.latitude, mLatLng.longitude)
            mMap.addMarker(MarkerOptions().position(mLatLng).title(str))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17.0f))
        })

        mMap.setOnMapLongClickListener { latLng ->
            mLatLng = LatLng(latLng.latitude, latLng.longitude)
            var str = String.format(Locale.JAPANESE, "%f, %f", mLatLng.latitude, mLatLng.longitude)
            mMap.addMarker(MarkerOptions().position(mLatLng).title(""+ mLatLng.latitude + " : " + mLatLng.longitude))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17.0f))
        }

//        setMarker(latitude, longitude)
//        setIcon(latitude, longitude);
    }

    private fun setMarker(latitude: Double, longitude: Double) {
        mMap.addMarker(MarkerOptions().position(mLatLng).title("皇居！"))
        zoomMap(latitude, longitude)
    }

    private fun setIcon(latitude: Double, longitude: Double) {
        zoomMap(latitude, longitude)

        val overlayOptions = GroundOverlayOptions()
        overlayOptions.image(BitmapDescriptorFactory.fromResource(R.drawable.ic_local_florist_black_24dp))
        overlayOptions.anchor(0.5f, 0.5f)
        overlayOptions.position(mLatLng, 300f, 300f)

        val overlay = mMap.addGroundOverlay(overlayOptions)
        overlay.transparency = 0.0f
    }

    private fun zoomMap(latitude: Double, longitude: Double) {
        // 表示する東西南北の緯度経度を設定
//        val south = latitude * (1 - 0.00005)
//        val west = longitude * (1 - 0.00005)
//        val north = latitude * (1 + 0.00005)
//        val east = longitude * (1 + 0.00005)
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17.0f))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17.0f))
    }

}
