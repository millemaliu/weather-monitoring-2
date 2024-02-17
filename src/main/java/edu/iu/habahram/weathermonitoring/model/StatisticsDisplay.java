package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay
        implements Observer, DisplayElement{
    private float avg_temp;
    private float min_temp;
    private float max_temp;

    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Average Temp: %s</label><br />", avg_temp);
        html += String.format("<label>Max Temp: %s</label><br />", max_temp);
        html += String.format("<label>Min Temp: %s</label>", min_temp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "current-statistics";
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.avg_temp = temperature;
        this.min_temp = temperature * 0.5f;
        this.max_temp = temperature * 1.5f;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
