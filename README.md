[![Build Status](https://travis-ci.org/Jmaquin/poker-kata.svg?branch=master)](https://travis-ci.org/Jmaquin/poker-kata)
[![codecov](https://codecov.io/gh/Jmaquin/poker-kata/branch/master/graph/badge.svg)](https://codecov.io/gh/Jmaquin/poker-kata)

# Poker kata:
Implement the calculation of the probability of an hand to win against another hand and determine the game winner.
This kata is based on Texas Hold'em poker.
https://en.wikipedia.org/wiki/Texas_hold_%27em

## Game rules:
Computer draw two random cards.
 
Player draw two random cards.

Other game rules are described below(from https://en.wikipedia.org/wiki/Texas_hold_%27em):
Two cards, known as the hole cards, are dealt face down to each player,
and then five community cards are dealt face up in three stages. 
The stages consist of a series of three cards ("the flop"), 
later an additional single card ("the turn" or "fourth street"), 
and a final card ("the river" or "fifth street"). 
Each player seeks the best five card poker hand from any combination of the seven cards of the five community cards and their own two hole cards. 
If a player's best five-card poker hand consists only of the five community cards and none of the player's hole cards, it is called "playing the board". 
If you play the board on the river, then you can do no better than tie the other player(s) in the game if no player can make a better hand than the board represents,
using either or both hole cards.

## User Story 1:
**In order to** know if i have more chances to win than the computer before "the flop"  
**As a** player
**I want to** calculate my chances to win before "the flop"  

## User Story 2:
**In order to** know if i have more chances to win than the computer after "the flop"  
**As a** player
**I want to** calculate my chances to win after "the flop"   

## User Story 3:
**In order to** know if i have more chances to win than the computer after "the turn"  
**As a** player
**I want to** calculate my chances to win after "the turn" 

## User Story 4:
**In order to** determine the game winner  
**As a** player
**I want to** know the winning hand(computer, player or draw) after "the river"