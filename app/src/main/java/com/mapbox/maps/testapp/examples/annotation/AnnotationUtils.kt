package com.mapbox.maps.testapp.examples.annotation

import android.content.Context
import com.mapbox.common.Logger
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.*

/**
 * Useful utilities used throughout the testapp.
 */
object AnnotationUtils {
  private const val TAG = "AnnotationUtils"
  val STYLES =
    arrayOf(Style.MAPBOX_STREETS, Style.OUTDOORS, Style.LIGHT, Style.DARK, Style.SATELLITE_STREETS)

  /**
   * Utility for getting a list of random points.
   *
   * @return a a list of random points
   */
  fun createRandomPoints(): List<Point> {
    val random = Random()
    val points: MutableList<Point> = ArrayList<Point>()
    for (i in 0 until random.nextInt(10)) {
      points.add(
        Point.fromLngLat(
          random.nextDouble() * -360.0 + 180.0,
          random.nextDouble() * -180.0 + 90.0
        )
      )
    }
    return points
  }

  /**
   * Utility for getting a list of lists of random points
   *
   * @return a list of lists of random points
   */
  fun createRandomPointsList(): List<List<Point>> {
    val random = Random()
    val points = mutableListOf<Point>()
    val firstLast = Point.fromLngLat(
      random.nextDouble() * -360.0 + 180.0,
      random.nextDouble() * -180.0 + 90.0
    )
    points.add(firstLast)
    for (i in 0 until random.nextInt(10)) {
      points.add(
        Point.fromLngLat(
          random.nextDouble() * -360.0 + 180.0,
          random.nextDouble() * -180.0 + 90.0
        )
      )
    }
    points.add(firstLast)
    return listOf(points)
  }

  /**
   * Utility for getting a random point
   *
   * @return a random point
   */
  fun createRandomPoint(): Point {
    val random = Random()
    return Point.fromLngLat(
      random.nextDouble() * -360.0 + 180.0,
      random.nextDouble() * -180.0 + 90.0
    )
  }

  /**
   * Load the string content from a assets file
   *
   * @param context the context
   * @param fileName the file to load
   */
  fun loadStringFromAssets(context: Context, fileName: String): String? {
    return try {
      val inputStream = context.assets.open(fileName)
      val rd = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
      val sb = StringBuilder()
      rd.forEachLine {
        sb.append(it)
      }
      sb.toString()
    } catch (e: IOException) {
      Logger.e(TAG, "Unable to parse $fileName")
      null
    }
  }
}