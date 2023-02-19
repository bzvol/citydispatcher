package hu.kisspd.citydp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hu.kisspd.citydp.model.City;
import hu.kisspd.citydp.model.Line;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.stream.Collectors;

public class JsonSaver {
    public static void saveData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        var cities = Shared.getMapPanel().getCities();
        Shared.setTemporaryCitySet(new HashSet<>(cities.values()));
        var lines = Shared.getMapPanel().getLines();
        Shared.setTemporaryLineSet(new HashSet<>(lines.values()));

        var json_cities = cities.values().stream().map(City::toJson).toList();
        var json_lines = lines.values().stream().map(Line::toJson).toList();

        var json = new JsonObject();
        json.add("cities", gson.toJsonTree(json_cities));
        json.add("lines", gson.toJsonTree(json_lines));

        try (var writer = new FileWriter(file)) {
            writer.write(gson.toJson(json));
        } catch (Exception e) {
            Util.showError("Nem sikerült menteni a fájlt!", e);
        }
    }

    public static void loadData(File file) {
        try (var reader = new FileReader(file)) {
            var json = new Gson().fromJson(reader, JsonObject.class);
            var json_cities = json.getAsJsonArray("cities");
            var json_lines = json.getAsJsonArray("lines");

            for (var json_city : json_cities) {
                City city = City.fromJson(json_city.getAsJsonObject());
                Shared.getMapPanel().addCity(city);
            }

            for (var json_line : json_lines) {
                Line line = Line.fromJson(json_line.getAsJsonObject());
                Shared.getMapPanel().addLine(line);
            }
        } catch (Exception e) {
            Util.showError("Nem sikerült betölteni a fájlt!", e);
        }
    }
}
