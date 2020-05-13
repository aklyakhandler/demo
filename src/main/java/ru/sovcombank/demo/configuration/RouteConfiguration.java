package ru.sovcombank.demo.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.sovcombank.demo.model.User;
import ru.sovcombank.demo.service.AccountService;
import ru.sovcombank.demo.service.CardService;
import ru.sovcombank.demo.service.StatisticsService;
import ru.sovcombank.demo.service.UserService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@AllArgsConstructor
public class RouteConfiguration {
    private final UserService userService;
    private final CardService cardService;
    private final AccountService accountService;
    private final StatisticsService statisticsService;


    @Bean
    RouterFunction<ServerResponse> createUser() {
        return route(POST("/user"),
                request -> request
                        .bodyToMono(User.class)
                        .map(userService::createUser)
                        .flatMap(user -> ok().bodyValue(user))
        );
    }

    @Bean
    RouterFunction<ServerResponse> deleteUser() {
        return route(DELETE("/user/{id}"),
                request -> Mono.fromRunnable(() ->
                        userService.deleteUser(
                                Long.parseLong(request.pathVariable("id"))
                        )
                )
        );
    }

    @Bean
    RouterFunction<ServerResponse> createNewDepositAccount() {
        return route(POST("/account/deposit/{userId}"),
                request -> ok().bodyValue(
                        accountService.createNewDepositAccount(
                                Long.parseLong(request.pathVariable("userId"))
                        )
                )
        );
    }

    @Bean
    RouterFunction<ServerResponse> createNewCreditAccount() {
        return route(POST("/account/credit/{userId}"),
                request -> ok().bodyValue(
                        accountService.createNewCreditAccount(
                                Long.parseLong(request.pathVariable("userId"))
                        )
                )
        );
    }


    @Bean
    RouterFunction<ServerResponse> deleteAccount() {
        return route(DELETE("/account/{id}"),
                request -> Mono.fromRunnable(() ->
                        accountService.deleteAccount(
                                Long.parseLong(request.pathVariable("id"))
                        )
                )
        );
    }

    @Bean
    RouterFunction<ServerResponse> issueNewVisaCard() {
        return route(POST("/card"),
                request -> request.bodyToMono(IssueCardRequest.class)
                        .map(issueCardRequest -> cardService.issueNewVisaCard(issueCardRequest.userId, issueCardRequest.accountId))
                        .flatMap(card -> ok().bodyValue(card))
        );
    }

    @Bean
    RouterFunction<ServerResponse> getCard() {
        return route(GET("/card/{id}"),
                request ->
                        ok().bodyValue(cardService.getCard(Long.parseLong(request.pathVariable("id"))))
        );
    }

    @Bean
    RouterFunction<ServerResponse> deleteCard() {
        return route(DELETE("/card/{id}"),
                request -> Mono.fromRunnable(() ->
                        cardService.deleteCard(Long.parseLong(request.pathVariable("id"))))
        );
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private final static class IssueCardRequest {
        private Long userId;
        private Long accountId;
    }
}
