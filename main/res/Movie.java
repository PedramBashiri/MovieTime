import java.io.Serializable;

/**
 * Created by Nitin1992 on 29-04-2015.
 */
public class Movie implements Serializable {
    @Override
    public String toString() {
        return super.toString();
    }

    String title, plot, ratings;

    public String getTitle() {
        return title;
    }
    public String getPlot() {
        return plot;
    }
    public String getRatings() {
        return ratings;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
