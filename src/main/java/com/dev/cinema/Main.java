package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException,
            InvocationTargetException {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Movie name");
        movie.setDescription("Movie desc");
        movie = movieService.add(movie);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Best hall ever");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        MovieSession movieSession2 = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession2.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession2.setMovie(movie);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        LocalDate date = LocalDate.now();
        movieSession.setShowTime(date);
        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(1L, LocalDate.now()).forEach(System.out::println);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User bob = authenticationService.register("bob@gmail.com", "qwerty123456");
        System.out.println(authenticationService.login("bob@gmail.com", "qwerty123456"));

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, bob);
        System.out.println(shoppingCartService.getByUser(bob));

    }
}
