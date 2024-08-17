"""This module contains random to fill up a random square,
os to relate to file paths,
json to work with json data"""
import random
import os.path
import json
random.seed()

def draw_board(board):
    """Draw the Tic-Tac-Toe board
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns: None"""
    print("-------------------")
    for i in range(3):
        print("| ", end=" ")
        for j in range(3):
            print(board[i][j]," | ", end=" ")
        print("\n-------------------")

def welcome(board):
    """Print the welcome message and display the initial Tic-Tac-Toe board
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns: None"""
    print('Welcome to "Tic-Tac-Toe!".')
    draw_board(board)
    print("Select the suqare with their corresponding number.")

def initialise_board(board):
    """Initialize the Tic-Tac-Toe board by setting all elements to a single space ' '
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns:
    - board (list): The initialized Tic-Tac-Toe board"""
    for i in range(3):
        for j in range(3):
            board[i][j] = " "
    return board

def get_player_move(board):
    """Get the player's move by asking for the cell number
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns:
    - row (int): The row index for the player's move
    - col (int): The column index for the player's move"""
    while True:
        try:
            coords = int(input("Choose the cell: "))
            if coords in [1,2,3,4,5,6,7,8,9]:
                # and return row and col
                row = (coords-1)//3
                col = (coords-1)%3
                if board[row][col]==" ":
                    break
                print("It has already been selected.")
            else:
                print("The number you entered is not a cell")
                continue
        except ValueError:
            print("You are supposed to enter a number.")
    return row,col

def choose_computer_move(board):
    """Choose a move for the computer by randomly selecting an available cell
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns:
    - row (int): The row index for the computer's move
    - col (int): The column index for the computer's move"""
    # develop code to let the computer chose a cell to put a nought in
    while True:
        row = random.randint(0,2)
        col = random.randint(0,2)
    # and return row and col
        if board[row][col]==" ":
            break
    return row, col

def check_for_win(board, mark):
    """Check if either the player or the computer has won
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    - mark (str): The player's or computer's mark ('X' or 'O')
    Returns:
    - win (bool): True if someone has won, False otherwise"""
    # develop code to check if either the player or the computer has won
    for i in range(3):
        if board[i][0]==board[i][1]==board[i][2]==mark or \
            board[0][i]==board[1][i]==board[2][i]==mark:
            return True
    if board[0][0] == board[1][1] == board[2][2] == mark or \
        board[0][2] == board[1][1] == board[2][0] == mark:
        return True
    # return True if someone won, False otherwise
    return False

def check_for_draw(board):
    """Check if all cells are occupied, resulting in a draw
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns:
    - draw (bool): True if the board is completely occupied, False otherwise"""
    # develop cope to check if all cells are occupied
    completely_occupied = True
    for i in range(3):
        for j in range(3):
            if board[i][j]==" ":
                completely_occupied = False
    # return True if it is, False otherwise
    return completely_occupied

def play_game(board):
    """Play the Tic-Tac-Toe game
    Parameters:
    - board (list): A 2D list representing the Tic-Tac-Toe board
    Returns:
    - score (int): 1 if the player wins, -1 if the computer wins, 0 for a draw"""
    # develop code to play the game
    # star with a call to the initialise_board(board) function to set
    initialise_board(board)
    # the board cells to all single spaces ' '
    # then draw the board
    draw_board(board)
    # then in a loop, get the player move, update and draw the board
    while True:
        player_row,player_col = get_player_move(board)
        board[player_row][player_col]="X"
        draw_board(board)
    # check if the player has won by calling check_for_win(board, mark),
        if check_for_win(board,"X"):
            print("Congratulations! You've won.")
    # if so, return 1 for the score
            return 1
    # if not check for a draw by calling check_for_draw(board)
        if check_for_draw(board):
            print("It's a draw!")
            return 0
        # if drawn, return 0 for the score
    # if not, then call choose_computer_move(board)
    # to choose a move for the computer
        computer_row,computer_col = choose_computer_move(board)
    # update and draw the board
        board[computer_row][computer_col]="O"
        draw_board(board)
    # check if the computer has won by calling check_for_win(board, mark),
        if check_for_win(board,"O"):
            print("Computer Wins!!")
            return -1
    # if so, return -1 for the score
    # if not check for a draw by calling check_for_draw(board)
        if check_for_draw(board):
            print("It's a draw!")
            return 0

def menu():
    """Display the menu options and get user input
    Parameters: None
    Returns:
    - choice (str): User's choice from the menu options"""

    while True:
        # get user input of either '1', '2', '3' or 'q'
        print("1 - Play the game\n\
2 - Save score in file 'leaderboard.txt'\n\
3 - Load and display the scores from the 'leaderboard.txt'\n\
q - End the program")
        choice = input("Select one of the above to proceed.\n=>")
        if choice in ["1",'2','3','q']:
            return choice
        else:
            print("That's not an option. Try again.")

def load_scores():
    """Load the leaderboard scores from the file 'leaderboard.txt'
    Parameters: None
    Returns:
    - leaders (dict): A Python dictionary containing player names as keys and scores as values"""
    if os.path.isfile("leaderboard.txt"):
        with open("leaderboard.txt","r",encoding="UTF-8") as file:
            leaders = json.load(file)
    return leaders

def save_score(score):
    """Save the current score to the file 'leaderboard.txt' after asking for the player's name
    Parameters:
    - score (int): The current score to be saved
    Returns: None"""
    while True:
        try:
            # develop code to ask the player for their name
            username = input("Enter your name: ")
            # and then save the current score to the file 'leaderboard.txt'
            leaders = load_scores()
            if username in leaders:
                leaders[username]+=score
            else:
                leaders[username]=score
            with open("leaderboard.txt","w",encoding="UTF-8") as file:
                json.dump(leaders,file)
            print("Saved!")
            break
        except FileNotFoundError as e:
            print("Error: ",e)

def display_leaderboard(leaders):
    """Display the leaderboard scores
    Parameters:
    - leaders (dict): A Python dictionary containing player names as keys and their scores as values
    Returns: None"""
    # Check if the leaderboard is empty
    if not leaders:
        print("Leaderboard is empty.")
    else:
        print("Leaderboard:")
        print(f"{'Player':<15}| Score")
        print("-" * 25)
        for player, score in leaders.items():
            print(f"{player:<15}| {score}")
