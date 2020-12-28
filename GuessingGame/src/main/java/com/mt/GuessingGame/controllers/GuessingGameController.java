package com.mt.GuessingGame.controllers;

import com.mt.GuessingGame.dao.GameDao;
import com.mt.GuessingGame.dao.GuessDao;
import com.mt.GuessingGame.dto.Game;
import com.mt.GuessingGame.dto.Guess;
import com.mt.GuessingGame.viewmodel.GameViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.*;

@RestController
@RequestMapping("/api")
public class GuessingGameController {

    private final GameDao gameDao;
    private final GuessDao guessDao;

    public GuessingGameController(GameDao gameDao, GuessDao guessDao) {
        this.gameDao = gameDao;
        this.guessDao = guessDao;
    }

    //"begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody Game game) {
        game.setAnswer(generateGameAnswer());
        return new GameViewModel(gameDao.add(game));
    }

    //"guess" – POST – Makes a guess by passing the guess and gameId in as JSON.
    // The passing program must calculate the results of the guess and mark the game finished if the guess is correct. It returns the Round object with the results filled in.
    @PostMapping("/guess")
    public ResponseEntity<Guess> createGuess(@RequestBody Guess guess) throws InvalidGuessException {
        validateGuess(guess.getGuess());

        guess.setResult(checkGuess(guess));
        Guess result = guessDao.add(guess);
        return ResponseEntity.ok(result);
    }


    // "game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
    @GetMapping("/game")
    public List<GameViewModel> getAllGames() {
        List<Game> games =  gameDao.getAll();
        List<GameViewModel> gameViews = new ArrayList<>();
        for (Game game: games){
            gameViews.add(new GameViewModel(game));
        }
        return gameViews;
    }

    // "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameViewModel> getGameById(@PathVariable int gameId) {
        Game game = gameDao.findById(gameId);

        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        GameViewModel result = new GameViewModel((game));
        return ResponseEntity.ok(result);
    }

    // "rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Guess>> getRoundsByIGameId(@PathVariable int gameId) {
        List<Guess> guesses = guessDao.getGuessesForGame(gameId);

        if (guesses == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        Collections.sort(guesses, Comparator.comparing(Guess::getTime));
        //most recent guess first
        Collections.reverse(guesses);
        return ResponseEntity.ok(guesses);
    }

    public void validateGuess(String input) throws InvalidGuessException {
        Set<Character> set = new HashSet<>();
        char[] characters = input.toCharArray();
        if (characters.length != 4 ) {
            throw new InvalidGuessException("Guess must be 4 different numbers");
        }
        for (char ch : characters) {
            //add return false is the char is not unique
            if (!set.add(ch)) {
                throw new InvalidGuessException("Guess cannot have duplicate numbers");
            }
            if(Character.isAlphabetic(ch)){
                throw new InvalidGuessException("Guess must be 4 different numbers");
            }
        }
    }

    public String checkGuess(Guess guess){
        Game game = gameDao.findById(guess.getGameId());
        int exactMatches = 0;
        int partialMatches = 0;
        ArrayList<String> potentialPartialMatches = new ArrayList<>();

        //mark game as finished if the guess matches the game answer
        if (guess.getGuess().equals(game.getAnswer())) {
            game.setFinished(true);
            gameDao.update(game);
        }

        //calculate number of exact matches
        for(int i=0; i<game.getAnswer().length(); i++){
            if(game.getAnswer().charAt(i) == guess.getGuess().charAt(i)){
                exactMatches+=1;
            }else{
                //number of non-exact matches from the game answer
                potentialPartialMatches.add(Character.toString(((game.getAnswer().charAt(i)))));
            }
        }

        //calculate number of partial matches
        for(String letter : potentialPartialMatches){
            if(guess.getGuess().contains(letter)){
                partialMatches++;
            }
        }

        String result =  "e:"+exactMatches+":p:"+partialMatches;
       return  result;
    }

    public String generateGameAnswer() {
        Set<Integer> digits = new HashSet<>();
        String answer = "";
        int randomNumber;

        while (answer.length() < 4) {
            Random rand = new Random();
            randomNumber = rand.nextInt(10);
            if (!digits.contains(randomNumber)) {
                digits.add(randomNumber);
                answer += Integer.toString(randomNumber);
            }
        }
        return answer;
    }

}
