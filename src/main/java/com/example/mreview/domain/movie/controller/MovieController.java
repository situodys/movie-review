package com.example.mreview.domain.movie.controller;

import com.example.mreview.domain.movie.dto.MovieDTO;
import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.service.MovieService;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody MovieDTO movieDTO) {
        log.info("movieDTO: {}", movieDTO);

        for (MovieImageDTO movieImageDTO : movieDTO.getMovieImageDTOList()) {
            System.out.println(movieImageDTO);
        }

        Long mno = movieService.register(movieDTO);
        return new ResponseEntity<>(mno, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("pageRequestDTO: {}",pageRequestDTO);
        PageResponseDTO result = movieService.getList(pageRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping({"/read", "modify"})
    public ResponseEntity<MovieInfoDetailDTO> read(Long mno) {
        log.info("mno: {}", mno);

        MovieInfoDetailDTO result = movieService.getMovie(mno);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
