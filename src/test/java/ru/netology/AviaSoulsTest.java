package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviaSoulsTest {

    @Test
    public void ticketCompareToTest() {
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 12000, 12_20, 13_55);
        Ticket ticket2 = new Ticket("Санкт-Петербург", "Казань", 16000, 10_40, 13_00);
        Ticket ticket3 = new Ticket("Казань", "Москва", 14000, 12_15, 14_35);

        assertTrue(ticket1.compareTo(ticket2) < 0);
        assertTrue(ticket2.compareTo(ticket3) > 0);
        assertTrue(ticket1.compareTo(ticket3) < 0);
    }

    @Test
    public void ticketSearchTest() {
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 12000, 12_20, 13_55);
        Ticket ticket2 = new Ticket("Санкт-Петербург", "Казань", 16000, 10_40, 13_00);
        Ticket ticket3 = new Ticket("Казань", "Москва", 14000, 12_15, 14_35);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] searchResult = manager.search("Санкт-Петербург", "Казань");

        Assertions.assertArrayEquals(new Ticket[]{ticket2}, searchResult);

    }

    @Test
    public void ticketTimeComparatorTest() {
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 12000, 12_20, 13_55);
        Ticket ticket2 = new Ticket("Санкт-Петербург", "Казань", 16000, 10_40, 13_00);
        Ticket ticket3 = new Ticket("Казань", "Москва", 14000, 12_15, 14_35);

        Ticket[] tickets = {ticket1, ticket2, ticket3};

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Arrays.sort(tickets, timeComparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket1, ticket3, ticket2}, tickets);
    }

    @Test
    public void logicComparatorTest() {
        Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 12000, 12_20, 13_55);
        Ticket ticket2 = new Ticket("Москва", "Санкт-Петербург", 11000, 18_00, 19_40);
        Ticket ticket3 = new Ticket("Москва", "Санкт-Петербург", 10000, 11_30, 13_05);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Comparator<Ticket> ticketComparator = new TicketTimeComparator();
        Ticket[] searchResult = manager.searchAndSortBy("Москва", "Санкт-Петербург", ticketComparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket1, ticket2, ticket3}, searchResult);
    }
}