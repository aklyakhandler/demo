package ru.sovcombank.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sovcombank.demo.model.Card;
import ru.sovcombank.demo.model.User;
import ru.sovcombank.demo.repository.CardRepository;
import ru.sovcombank.demo.repository.UserRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public Card issueNewVisaCard(Long userId, Long accountId) {
        Card card = new Card();
        card.setNumber(getCardNumber());
        card.setPaymentSystem(Card.PaymentSystem.VISA);
        card.setCardHolder(userRepository.findById(userId).map(User::getName).get());
        card.setExpiryDate(LocalDate.now().plusYears(4));
        card.setAccountId(accountId);
        card.setUserId(userId);
        return cardRepository.save(card);
    }

    public Card getCard(Long cardId) {
        return cardRepository.findById(cardId).get();
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    /*
    этот метод не надо рефакторить,
    будем считать, что с ним все в порядке
     */
    private String getCardNumber() {
        return "1234 5678 9000 9999";
    }
}
