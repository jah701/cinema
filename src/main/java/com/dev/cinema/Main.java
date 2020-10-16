package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws AuthenticationException,
            InvocationTargetException {
        logger.info("Starting 'Movie' test . . .");
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(logger::info);

        Movie movie = new Movie();
        movie.setTitle("Movie name");
        movie.setDescription("Movie desc");
        movie = movieService.add(movie);

        movieService.getAll().forEach(logger::info);

        logger.info("Starting 'Cinema Hall' test . . .");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Best hall ever");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(logger::info);

        logger.info("Starting 'Movie Session' test . . .");
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

        movieSessionService.findAvailableSessions(1L, LocalDate.now()).forEach(logger::info);

        logger.info("Starting 'Authentication Service' test . . .");
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User bob = authenticationService.register("bob@gmail.com", "qwerty123456");
        logger.info(authenticationService.login("bob@gmail.com", "qwerty123456"));

        logger.info("Starting 'Shopping Cart Service' test . . .");
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, bob);
        logger.info(shoppingCartService.getByUser(bob));

        logger.info("Starting 'Order Service' test . . .");
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(bob).getTickets(), bob);

        List<Order> orderList = orderService.getOrderHistory(bob);
        orderList.forEach(logger::info);
    }
}
