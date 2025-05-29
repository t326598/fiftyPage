package com.fifty.fifty.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifty.fifty.domain.YoutubeChartData;
import com.fifty.fifty.mapper.YoutubeChartMapper;

@Service
public class YoutubeService {

    private static List<String> keywords = List.of("FIFTY FIFTY", "fiftyfifty", "피프티");
    private static List<String> excludeKeywords = List.of("어블름", "ablume", "EBUBE");

    @Autowired
    private YoutubeChartMapper youtubeMapper;


    public List<YoutubeChartData> getTodayChart() {
        return youtubeMapper.getTodayChart();
    }

    public void updateYoutubeChart() throws IOException, InterruptedException {
        List<YoutubeChartData> videos = fetchTop10YoutubeVideos();

    for (int i = 0; i < videos.size(); i++) {
        YoutubeChartData video = videos.get(i);

        // 1) HTML 엔티티 디코딩
        String decodedTitle = StringEscapeUtils.unescapeHtml4(video.getTitle());
        video.setTitle(decodedTitle);

        video.setNo(i + 1);

        YoutubeChartData existing = youtubeMapper.findByVideoId(video.getVideoId());
        if (existing != null) {
            youtubeMapper.updateChart(video);
        } else {
            video.setPreviousViewCount(0);
            youtubeMapper.insertChart(video);
        }
    }
    }


    @Value("${youtube.api.key}")
    private String apiKey;
    public List<YoutubeChartData> fetchTop10YoutubeVideos() throws IOException, InterruptedException {
    String publishedAfter = "2024-09-24T00:00:00Z";
    int maxResultsPerKeyword = 20;
    int maxResultsToReturn = 10;

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    List<YoutubeChartData> result = new ArrayList<>();
    List<String> collectedIds = new ArrayList<>();

    for (String keyword : keywords) {
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q="
                + URLEncoder.encode(keyword, StandardCharsets.UTF_8)
                + "&type=video&maxResults=" + maxResultsPerKeyword
                + "&order=viewCount&publishedAfter=" + publishedAfter
                + "&key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());

        for (JsonNode item : root.get("items")) {
            String videoId = item.get("id").get("videoId").asText();

            if (collectedIds.contains(videoId)) continue;

            String title = item.get("snippet").get("title").asText().toLowerCase();
            boolean excluded = excludeKeywords.stream()
                                .map(String::toLowerCase)
                                .anyMatch(kw -> title.toLowerCase().contains(kw));
            String publishedAtRaw = item.get("snippet").get("publishedAt").asText();
            String publishedAtFormatted = publishedAtRaw.replace("T", " ").replace("Z", "");

            if (excluded) continue;

            YoutubeChartData video = new YoutubeChartData();
            video.setVideoId(videoId);
            video.setTitle(item.get("snippet").get("title").asText());
            video.setThumbnailUrl(item.get("snippet").get("thumbnails").get("high").get("url").asText());
            video.setPublishedAt(publishedAtFormatted);
            video.setVideoUrl("https://www.youtube.com/watch?v=" + videoId);

            // 🔽 조회수 추가로 받아오기 (Videos API로)
            String statsUrl = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id="
                    + videoId + "&key=" + apiKey;
            HttpRequest statsRequest = HttpRequest.newBuilder()
                    .uri(URI.create(statsUrl))
                    .build();
            HttpResponse<String> statsResponse = client.send(statsRequest, HttpResponse.BodyHandlers.ofString());
            JsonNode statsRoot = mapper.readTree(statsResponse.body());

            JsonNode stats = statsRoot.get("items").get(0).get("statistics");
            long viewCount = stats.has("viewCount") ? stats.get("viewCount").asLong() : 0;
            video.setViewCount(viewCount);

            result.add(video);
            collectedIds.add(videoId);

            if (result.size() >= maxResultsToReturn) break;
        }
        if (result.size() >= maxResultsToReturn) break;
    }

    return result;
}

}
