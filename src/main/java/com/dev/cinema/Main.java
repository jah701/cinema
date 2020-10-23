package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.*;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.*;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);
        AnnotationConfigApplicationContext context =

                new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
//        movieService.getAll().forEach(logger::info);
        Movie pulpFiction = new Movie();
        pulpFiction.setTitle("Pulp Fiction");
        pulpFiction.setDescription("Best movie");
        Movie theLordOfTheRings = new Movie();
        theLordOfTheRings.setTitle("The Lord of the Rings");
        theLordOfTheRings.setDescription("Lord");
        movieService.add(pulpFiction);
        movieService.add(theLordOfTheRings);
        movieService.getById(1L);

        movieService.getAll().forEach(logger::info);

        CinemaHall redCinemaHall = new CinemaHall();
        redCinemaHall.setCapacity(13);
        redCinemaHall.setDescription("Red cinema hall");
        CinemaHall blueCinemaHall = new CinemaHall();
        blueCinemaHall.setCapacity(100);
        blueCinemaHall.setDescription("Blue cinema hall");
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(redCinemaHall);
        cinemaHallService.add(blueCinemaHall);
        MovieSession todayPulpFicMovieSession = new MovieSession();
        todayPulpFicMovieSession.setMovie(pulpFiction);
        todayPulpFicMovieSession.setCinemaHall(redCinemaHall);
        todayPulpFicMovieSession.setShowtime(LocalDate.now().plusDays(1L));
        MovieSession secondTodayPulpFicMovieSession = new MovieSession();
        secondTodayPulpFicMovieSession.setMovie(pulpFiction);
        secondTodayPulpFicMovieSession.setCinemaHall(blueCinemaHall);
        secondTodayPulpFicMovieSession.setShowtime(LocalDate.now());
        MovieSession todayLordMovieSession = new MovieSession();
        todayLordMovieSession.setMovie(theLordOfTheRings);
        todayLordMovieSession.setCinemaHall(blueCinemaHall);
        todayLordMovieSession.setShowtime(LocalDate.now());
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(todayPulpFicMovieSession);
        movieSessionService.add(secondTodayPulpFicMovieSession);
        movieSessionService.add(todayLordMovieSession);
        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setMovie(pulpFiction);
        tomorrowMovieSession.setCinemaHall(blueCinemaHall);
        tomorrowMovieSession.setShowtime(LocalDate.now().plusDays(1));
        movieSessionService.add(tomorrowMovieSession);
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(pulpFiction.getId(), LocalDate.now());
        availableSessions.forEach(logger::info);
        User alice = new User();
        alice.setEmail("alice@mail.com");
        alice.setPassword("1111");
        User bob = new User();
        bob.setEmail("bob@mail.com");
        bob.setPassword("bob");
        User badBob = new User();
        badBob.setEmail("badBob@mail.com");
        badBob.setPassword("1234");

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        try {
            authenticationService.login(badBob.getEmail(), badBob.getPassword());
        } catch (AuthenticationException e) {
            logger.info("Expected AuthenticationException:\n " + e);
        }
        alice = authenticationService.register(alice.getEmail(), alice.getPassword());
        bob = authenticationService.register(bob.getEmail(), bob.getPassword());
        try {
            logger.info("Expected alice:\n "
                    + authenticationService.login(alice.getEmail(), "1111") + "\n");
        } catch (AuthenticationException e) {
            logger.warn("Login failed: " + e);
        }
        UserService userService = context.getBean(UserService.class);
        userService.add(badBob);
        logger.info("Expected " + badBob + "\n"
                + userService.findByEmail(badBob.getEmail()).orElseThrow() + "\n");
        try {
            authenticationService.register(badBob.getEmail(), "2222");
        } catch (Exception e) {
            logger.info("Expected exception: \n" + e + "\n");
        }
        try {
            authenticationService.login(alice.getEmail(), "invalid password");
        } catch (AuthenticationException e) {
            logger.info("Expected \"Incorrect login or password\" :" + e);
        }
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(todayPulpFicMovieSession, alice);
        shoppingCartService.addSession(todayLordMovieSession, alice);
        shoppingCartService.addSession(tomorrowMovieSession, bob);

        ShoppingCart aliseShoppingCart = shoppingCartService.getByUser(alice);
        logger.info("Expected 2: \n" + aliseShoppingCart.getTickets().size());
        shoppingCartService.clear(aliseShoppingCart);
        logger.info("Expected 0: \n" + aliseShoppingCart.getTickets().size());
        shoppingCartService.addSession(todayPulpFicMovieSession, alice);
        shoppingCartService.addSession(todayLordMovieSession, alice);
        shoppingCartService.addSession(tomorrowMovieSession, alice);
        aliseShoppingCart = shoppingCartService.getByUser(alice);
        List<Ticket> aliseTickets = aliseShoppingCart.getTickets();
        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(aliseTickets, alice);
        shoppingCartService.addSession(tomorrowMovieSession, alice);
        aliseShoppingCart = shoppingCartService.getByUser(alice);
        orderService.completeOrder(aliseShoppingCart.getTickets(), alice);
        List<Order> aliseOrderHistory = orderService.getOrderHistory(alice);
        aliseOrderHistory.forEach(logger::info);
    }
}
