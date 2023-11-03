package domain.models.entities.services.lucene;

public interface DistanceCalculator {
    double getDistance(double lat1, double long1, double lat2, double long2);
}
