package com.endava.myapplication.triangulation;

import java.util.Arrays;
import java.util.List;

/**
 * Using the law of cosines: a^2 = b^2 + c^2 - 2*b*c*cos A
 * We can rewrite it as cos A = (b^2 + c^2 - a^) / (2 * b * c)
 * We will determine angle A up to a minus sign because cos is symmetric
 * Then we can determine the slopes and cartesian positions
 * <p>
 * https://en.wikipedia.org/wiki/Law_of_cosines
 */
class CartesianTriangle {
    List<CartesianPosition> getPositionsMatchingDistances(CartesianPosition a, CartesianPosition b,
                                                          double distanceCToA, double distanceCToB) {
        double distanceAToB = a.getDistanceFrom(b);
        double cosA = (distanceCToA * distanceCToA + distanceAToB * distanceAToB - distanceCToB * distanceCToB) /
                (2 * distanceCToA * distanceAToB);
        double aRadians = Math.acos(cosA);

        double slopeAToB = a.getSlopeTo(b);
        double slopeAToCUp = slopeAToB + aRadians;
        double slopeAToCDown = slopeAToB - aRadians;
        return Arrays.asList(a.getWithSlopeAndDistance(slopeAToCUp, distanceCToA),
                a.getWithSlopeAndDistance(slopeAToCDown, distanceCToA));
    }
}
