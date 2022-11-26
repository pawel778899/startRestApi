package com.example.startrestapi;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VideoApi {


    private List<Video> videoList;

    public VideoApi() {
        this.videoList = new ArrayList<>();
        videoList.add(new Video(1l, "BRACIA FIGO FAGOT", "https://youtu.be/WVu23_Tu81A"));
        videoList.add(new Video(2l, "Narzędzia developerskie przeglądarki spod F12", "https://youtu.be/omTJJnPodNs"));
    }


    @GetMapping
    public ResponseEntity<List<Video>> getVideos() {
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity addVideos(@RequestBody Video video) {
        boolean add = videoList.add(video);
        if (add) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity modVideo(@RequestBody Video newVideo) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == newVideo.getId()).findFirst();
        if (first.isPresent()) {
            videoList.remove(first.get());
            videoList.add(newVideo);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity removeVideo(@PathVariable long id) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        if (first.isPresent()) {
            videoList.remove(first.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}