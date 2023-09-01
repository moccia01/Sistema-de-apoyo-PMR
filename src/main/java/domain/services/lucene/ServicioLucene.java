package domain.services.lucene;

public class ServicioLucene implements DistanceCalculator{

    public ServicioLucene() {
    }

    @Override
    public double getDistance(double lat1, double long1, double lat2, double long2) {
        return org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
    }
}
