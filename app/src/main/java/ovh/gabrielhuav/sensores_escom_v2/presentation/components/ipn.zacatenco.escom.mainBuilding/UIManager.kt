package ovh.gabrielhuav.sensores_escom_v2.presentation.components.mapview

import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import ovh.gabrielhuav.sensores_escom_v2.R

// UIManager.kt
class UIManager(
    private val rootView: View,
    private val mapView: MapView
) {
    // Botones y vistas
    val btnStartServer: Button = rootView.findViewById(R.id.button_small_1)
    val btnConnectDevice: Button = rootView.findViewById(R.id.button_small_2)
    val btnNorth: Button = rootView.findViewById(R.id.button_north)
    val btnSouth: Button = rootView.findViewById(R.id.button_south)
    val btnEast: Button = rootView.findViewById(R.id.button_east)
    val btnWest: Button = rootView.findViewById(R.id.button_west)
    val tvBluetoothStatus: TextView = rootView.findViewById(R.id.tvBluetoothStatus)
    val btnOnlineServer: Button = rootView.findViewById(R.id.button_serverOnline)
    val buttonA: Button = rootView.findViewById(R.id.button_a)

    fun initializeViews() {
        mapView.setBluetoothServerMode(false)
    }

    fun setupMovementButtons(movementManager: MovementManager) {
        btnNorth.setOnTouchListener { _, event ->
            movementManager.handleMovement(event, 0, -1)
            true
        }
        btnSouth.setOnTouchListener { _, event ->
            movementManager.handleMovement(event, 0, 1)
            true
        }
        btnEast.setOnTouchListener { _, event ->
            movementManager.handleMovement(event, 1, 0)
            true
        }
        btnWest.setOnTouchListener { _, event ->
            movementManager.handleMovement(event, -1, 0)
            true
        }
    }

    fun setupInteractionButton(
        playerPosition: () -> Pair<Int, Int>,
        onInteract: (Int, Int) -> Unit
    ) {
        buttonA.setOnClickListener {
            val (x, y) = playerPosition()
            if (mapView.isInteractivePosition(x, y)) {
                onInteract(x, y)
            } else {
                showToast("No puedes interactuar aquí")
            }
        }
    }

    fun configureForServerMode() {
        btnConnectDevice.visibility = View.GONE
        btnStartServer.isEnabled = true
    }

    fun configureForClientMode() {
        btnConnectDevice.visibility = View.GONE
        btnStartServer.visibility = View.GONE
    }

    fun showToast(message: String) {
        Toast.makeText(rootView.context, message, Toast.LENGTH_SHORT).show()
    }

    fun updateBluetoothStatus(status: String) {
        tvBluetoothStatus.text = status
    }
}
