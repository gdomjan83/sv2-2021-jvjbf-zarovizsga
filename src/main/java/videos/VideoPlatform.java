package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public void addChannel(Channel channelToAdd) {
        if (channelToAdd == null) {
            throw new IllegalArgumentException("Channel is null.");
        }
        channels.add(channelToAdd);
    }

    public List<Channel> getChannels() {
        return new ArrayList<>(channels);
    }

    public void readDataFromFile(Path path) {
        String line;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                addChannel(createChannel(line));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getNumberOfVideos)
                .sum();
    }

    private Channel createChannel(String line) {
        String[] data = line.split(";");
        return new Channel(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2]));
    }
}
