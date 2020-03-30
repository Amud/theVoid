package com.amud.thevoid.utils

import android.graphics.Bitmap
import com.amud.thevoid.Location
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage


class DetectionHelper {

    fun recognizeLandmarksCloud(bitmap: Bitmap?, block: (Location?) -> Unit) {
        if (bitmap == null)
            return

        if (true ) {
            block(Location("", 27.1751, 78.0421))
            return
        }

        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val options =
            FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(30)
                .build()
        val detector = FirebaseVision.getInstance()
            .getVisionCloudLandmarkDetector(options)
        detector.detectInImage(image)
            .addOnSuccessListener { firebaseVisionCloudLandmarks ->
                val landmark = firebaseVisionCloudLandmarks.maxBy { it.confidence }
                if (landmark != null && landmark.locations.isEmpty()) {
                    block(
                        Location(
                            landmark.landmark,
                            landmark.locations[0].latitude,
                            landmark.locations[0].longitude
                        )
                    )
                } else {
                    block(null)
                }
            }
            .addOnFailureListener {
                block(null)
            }
    }
}

