# Noughts and Crosses (Tic-Tac-Toe) Game

## Overview

This project is a simple implementation of the classic Tic-Tac-Toe game, also known as Noughts and Crosses. Players can play against the computer, save their scores to a leaderboard, and view scores from other players.

## Files

- **leaderboard-2.txt**: Stores the leaderboard scores in JSON format.
- **noughtsandcrosses_2417733-2.py**: Contains the main game logic, including board drawing, player and computer moves, win and draw checks, and score management.
- **play_game_2417733-2.py**: Imports functions from `noughtsandcrosses_2417733-2.py` and manages the game flow, including user interaction and menu options.

## Setup

1. Ensure you have Python installed on your system.
2. Make sure all three files are in the same directory.

## Usage

1. **Start the Game**: Run `play_game_2417733-2.py` to begin the game.
    ```bash
    python play_game_2417733-2.py
    ```

2. **Game Options**:
    - **Play the Game**: Select option '1' to play a game of Tic-Tac-Toe against the computer.
    - **Save Score**: Select option '2' to save your score to the leaderboard. You will be prompted to enter your name.
    - **Load and Display Scores**: Select option '3' to load and view the leaderboard scores.
    - **Exit**: Select 'q' to end the game.

## Functions

- **`draw_board(board)`**: Draws the Tic-Tac-Toe board.
- **`welcome(board)`**: Displays the welcome message and initial board.
- **`initialise_board(board)`**: Initializes the board to empty spaces.
- **`get_player_move(board)`**: Prompts the player to choose a cell.
- **`choose_computer_move(board)`**: Randomly selects a cell for the computer.
- **`check_for_win(board, mark)`**: Checks if the player or computer has won.
- **`check_for_draw(board)`**: Checks if the game has ended in a draw.
- **`play_game(board)`**: Manages the game flow between the player and computer.
- **`menu()`**: Displays menu options for game actions.
- **`load_scores()`**: Loads leaderboard scores from `leaderboard-2.txt`.
- **`save_score(score)`**: Saves the player's score to `leaderboard-2.txt`.
- **`display_leaderboard(leaders)`**: Displays the leaderboard.

## Notes

- Ensure that `leaderboard-2.txt` is present in the same directory as the Python files for score saving and loading functionality to work correctly.
- The game board is initialized with numbers representing each cell, which will be replaced by 'X' or 'O' during gameplay.
---

Feel free to tweak it if needed!
