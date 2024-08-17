"""This imports the functions from noughtsandcrosses.py"""
from noughtsandcrosses_2417733 import *

def main():
    """Run the Tic-Tac-Toe game.
    Initializes the game board, welcomes the player, and manages game flow.
    Allows the player to play the game, save their score, load leaderboard scores,
    and exit the game
    Returns: None"""
    board = [ ['1','2','3'],\
              ['4','5','6'],\
              ['7','8','9']]

    welcome(board)
    total_score = 0
    while True:
        choice = menu()
        if choice == '1':
            score = play_game(board)
            total_score += score
            print('Your current score is:',total_score)
        if choice == '2':
            save_score(total_score)
        if choice == '3':
            leader_board = load_scores()
            display_leaderboard(leader_board)
        if choice == 'q':
            print('Thank you for playing "Tic-Tac-Toe".')
            print('Good bye')
            return

# Program execution begins here
if __name__ == '__main__':
    main()
