package MapsLambdaAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PractiseSessions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        LinkedHashMap<String, List<String>> roads = new LinkedHashMap<>();

        String input = reader.readLine();

        while (!"END".equals(input)) {

            String[] data = input.split("->");
            String command = data[0];

            if (command.equals("Add")) {
                String road = data[1];
                String racer = data[2];

                if (!roads.containsKey(road)) {

                    roads.put(road, new ArrayList<>());
                }
                roads.get(road).add(racer);


            } else if (command.equals("Move")) {

                String currentRoad = data[1];
                String racerToMove = data[2];
                String nextRoad = data[3];

                if (roads.get(currentRoad).contains(racerToMove)) {
                    roads.get(currentRoad).remove(racerToMove);
                    roads.get(nextRoad).add(racerToMove);
                }

            } else if (command.equals("Close")) {

                String roadToMove = data[1];

                if (roads.containsKey(roadToMove)) {
                    roads.remove(roadToMove);
                }

            }

            input = reader.readLine();
        }

        System.out.println("Practice sessions:");
        roads
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int sort = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    if (sort == 0) {
                        sort = e1.getKey().compareTo(e2.getKey());
                    }
                    return sort;

                })
                .forEach(v -> {
                    System.out.println(String.format("%s", v.getKey() ));
                    v.getValue()
                            .forEach(m -> {
                                System.out.println(String.format("++%s", m));
                            });
                });

    }
}
