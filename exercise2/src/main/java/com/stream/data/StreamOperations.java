package com.stream.data;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamOperations {
	private static final Logger logger = LoggerFactory.getLogger(StreamOperations.class);
	public static void main(String[] args) {
		logger.info("Display the names of all players");
		logger.info("=========================================");
			List<String> playerNames = Data.players.stream().map(p->p.playerName).collect(Collectors.toList());
			playerNames.forEach(System.out::println);
			
			logger.info("=========================================");
			logger.info("Display the name of players whose highest score is more than 100 and belong to a particular country");
			logger.info("=========================================");
			Data.players.stream().filter(p->p.getHighestScore() > 100)
			 .collect(Collectors.toMap(Player::getPlayerName, Player::getCountry)).forEach((k,v)->System.out.println(k+" : "+v));
	
			
			logger.info("=========================================");
			logger.info("Create a LinkedHashSet containing names of all Players, whose highest score is more than or equal to 100");
			logger.info("=========================================");
			LinkedHashSet<Player> collect = Data.players.stream().filter(p->p.getHighestScore() >= 100)
			.collect(Collectors.toCollection(LinkedHashSet::new));
			System.out.println(collect);
			
			logger.info("=========================================");
			logger.info("Display the average runs scored by players from a particular Country");
			logger.info("=========================================");

			Map<Country, Double> collect2 = Data.players.stream().collect(Collectors.groupingBy(Player::getCountry, Collectors.averagingInt(Player::getRuns)));
			collect2.forEach((k,v) -> System.out.println(k.countryName +":"+v) );
	
			
			logger.info("=========================================");
			logger.info("Create a list with names of Players sorted as per country and then by matchesPlayed(desc)");
			logger.info("=========================================");
			Data.players.stream().sorted((p1,p2) -> p1.getMatchesPlayed() > p2.getMatchesPlayed() ? 1 : p1.getMatchesPlayed() < p2.getMatchesPlayed()?-1:0)
			.sorted((p1,p2) -> p1.getCountry().getCountryName().compareTo(p2.getCountry().getCountryName()))
			.collect(Collectors.toList()).forEach(System.out::println);
			
			logger.info("=========================================");
			logger.info("Create a map with the PlayerName and CountryName of all players who have played more than 200 matches");
			logger.info("=========================================");
			Map<String, String> collect3 = Data.players.stream().filter(p -> p.getMatchesPlayed() > 200).collect(Collectors.toMap(p->p.getPlayerName(), p1->p1.getCountry().getCountryName()));
			collect3.entrySet().forEach(System.out::println);
			
			logger.info("=========================================");
			logger.info("Display the details of the player who has scored maximum runs");
			logger.info("=========================================");
			Player palyer= Data.players.stream()
					.max((p1,p2) -> p1.getRuns()- p2.getRuns())
					.get();
					System.out.println(palyer);
					
			logger.info("=========================================");
			logger.info("Search and display the player details for a given player name and country name");
			logger.info("=========================================");
			Data.players.stream().filter(p->p.getPlayerName().equalsIgnoreCase("Sachin Tendulkar")
											&& p.getCountry().getCountryName().equalsIgnoreCase("India"))
			.collect(Collectors.toList()).forEach(System.out::println);;
			
	}

}
