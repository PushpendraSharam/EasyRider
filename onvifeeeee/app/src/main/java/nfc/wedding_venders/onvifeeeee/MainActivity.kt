package nfc.wedding_venders.onvifeeeee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnvifListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onvifManager = OnvifManager()
        onvifManager.setOnvifResponseListener(this)
        val device = OnvifDevice("192.168.0.131", "username", "password")
    }

    // Called by the SDK each time a request is performed on the camera, when the result is parsed
    override fun requestPerformed(response: OnvifResponse) {
        Log.d("ONVIF", "Request ${response.request.type} performed.")
        Log.d("ONVIF","Succeeded: ${response.success},
                message: ${response.parsingUIMessage}")

        if (response.request.type == GetDeviceInformation) {
            currentDevice.getProfiles()

        } else if (response.request.type == GetProfiles) {
            currentDevice.getStreamURI()

        } else if (response.request.type == GetStreamURI) {
            Log.d("ONVIF", "Stream URI retrieved: ${currentDevice.rtspURI}")
        }
    }