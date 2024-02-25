package com.example.bookmyticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmyticket.dto.MovieShowRequest;
import com.example.bookmyticket.models.Booking;
import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Seat;
import com.example.bookmyticket.models.Theater;
import com.example.bookmyticket.models.UsersData;
import com.example.bookmyticket.repository.BookingRepository;
import com.example.bookmyticket.repository.MovieRepository;
import com.example.bookmyticket.repository.MovieShowRepository;
import com.example.bookmyticket.repository.SeatRepository;
import com.example.bookmyticket.repository.TheaterRepository;
import com.example.bookmyticket.repository.UserRepository;

@Service
public class MovieShowServiceImpl implements MovieShowService {

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private SeatRepository seatRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private UserRepository userRepository;

     @Autowired
    private BookingRepository bookingRepository;
    
     @Override
    public List<MovieShow> getAllMovieShows() {
        return movieShowRepository.findAll();
    }

    @Override
    public List<Seat> getAvailableSeats(String showId) {
        MovieShow show = movieShowRepository.findById(showId)
                             .orElseThrow(() -> new RuntimeException("Show not found"));

        List<Seat> allSeats = seatRepository.findByMovieShowId(showId);

        List<Seat> availableSeats = allSeats.stream()
                .filter(seat -> !seat.getBooked())
                .collect(Collectors.toList());

        return availableSeats;

    }

   
    @Override
    public MovieShow createMovieShow(MovieShowRequest movieShowRequest) {
        Movie movie = movieRepository.findById(movieShowRequest.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieShowRequest.getMovieId()));

        Theater theater = theaterRepository.findById(movieShowRequest.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found with id: " + movieShowRequest.getTheaterId()));

        MovieShow movieShow = new MovieShow();
        movieShow.setMovie(movie);
        movieShow.setTheater(theater);
        movieShow.setShowTime(movieShowRequest.getShowTime());
        movieShow.setAvailableSeats(movieShowRequest.getAvailableSeats());

        MovieShow savedMovieShow = movieShowRepository.save(movieShow);
        createSeatsForMovieShow(savedMovieShow);
        return savedMovieShow;
    }

   

    @Override
    public void createSeatsForMovieShow(MovieShow movieShow) {
        int availableSeats = movieShow.getAvailableSeats();
        final int numRows = 10; // Assuming all theaters have 10 rows
        int seatsPerRow = availableSeats / numRows;
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
                Seat seat = new Seat();
                seat.setMovieShow(movieShow);
                seat.setRow(row);
                seat.setNumber(seatNumber);
                seat.setBooked(false); 
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

    @Override
    public List<MovieShow> getMovieShowsForMovieAndTheater(String movieId, String theaterId) {
        return movieShowRepository.findByMovieIdAndTheaterId(movieId, theaterId);
    }
    
}