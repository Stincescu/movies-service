package com.pentalog.pentastagiu.service;

import com.pentalog.pentastagiu.repository.model.HibernateMovieRepository;
import com.pentalog.pentastagiu.repository.model.Movie;
import com.pentalog.pentastagiu.service.api.HibernateMovieService;
import com.pentalog.pentastagiu.service.dto.MovieDTO;
import com.pentalog.pentastagiu.web.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateMovieServiceImpl implements HibernateMovieService {

    private final HibernateMovieRepository movieRepository;

    public HibernateMovieServiceImpl(HibernateMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(String id) {
        return movieRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie", id));
    }

    @Override
    public Movie create(MovieDTO movieDTO) {
        Movie movie = new Movie();
        //when crating  new entity, we don't set the id, we let it to be managed by the db implementation
        movie
                .setName(movieDTO.getName())
                .setPosterUrl(movieDTO.getPosterUrl())
                .setRating(movieDTO.getRating());
        //the movie contains an id here
        return movieRepository.save(movie);
    }

    @Override
    public void delete(String movieId) {
        Movie movie = getById(movieId);
        movieRepository.delete(movie);
    }

    @Override
    public void update(String movieId, MovieDTO movieDTO) {
        Movie dbMovie = getById(movieId);
        dbMovie
                .setName(movieDTO.getName())
                .setPosterUrl(movieDTO.getPosterUrl())
                .setRating(movieDTO.getRating());
        Movie updatedMovie = movieRepository.save(dbMovie);
    }

    @Override
    public List<Movie> search(String startsWith) {
        return movieRepository.findAllByNameStartingWith(startsWith);

    }
}
