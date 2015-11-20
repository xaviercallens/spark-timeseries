package com.cloudera.sparkts.api.java;

import com.cloudera.sparkts.DateTimeIndex;
import com.cloudera.sparkts.UniformDateTimeIndex;
import org.apache.spark.mllib.linalg.Vector;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.reflect.ClassTag$;

import java.util.List;

public final class JavaTimeSeriesFactory {
    private static final JavaTimeSeries$ JAVA_TIME_SERIES = JavaTimeSeries$.MODULE$;

    private JavaTimeSeriesFactory() {}

    public static <K> JavaTimeSeries<K> javaTimeSeriesFromIrregularSamples(
            List<Tuple2<DateTime, double[]>> samples,
            K[] keys,
            DateTimeZone zone) {
        return JAVA_TIME_SERIES.javaTimeSeriesFromIrregularSamples(
                JavaConversions.asScalaBuffer(samples),
                keys,
                zone != null ? zone : DateTimeZone.getDefault(),
                ClassTag$.MODULE$.<K>apply(keys[0].getClass()));
    }

    /**
     * This function should only be called when you can safely make the assumption that the time
     * samples are uniform (monotonously increasing) across time.
     */
    public static <K> JavaTimeSeries<K> javaTimeSeriesFromUniformSamples(
            List<double[]> samples,
            UniformDateTimeIndex index,
            K[] keys) {
        return JAVA_TIME_SERIES.javaTimeSeriesFromUniformSamples(
                JavaConversions.asScalaBuffer(samples),
                index,
                keys,
                ClassTag$.MODULE$.<K>apply(keys[0].getClass()));
    }

    public static <K> JavaTimeSeries<K> javaTimeSeriesFromVectors(
            Iterable<Vector> samples,
            DateTimeIndex index,
            K[] keys) {
        return JAVA_TIME_SERIES.javaTimeSeriesFromVectors(
                JavaConversions.iterableAsScalaIterable(samples),
                index,
                keys,
                ClassTag$.MODULE$.<K>apply(keys[0].getClass()));
    }
}