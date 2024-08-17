"""This module implements a caesar cipher program
made by: Rojin Singh Mahat (2417733)"""
import os
def welcome():
    """Displays a welcome message
    Parameters: None
    Returns:
    string: welcome message  and description about the program"""
    print("Welcome to the Caesar Cipher")
    print("This program encrypts and decrypts text with the Caesar Cipher.")
def enter_message():
    """Requests input from the user to determine the mode of conversion and the message
    Returns:
    tuple : (mode: str, message: str)
    mode - The mode of conversion ('e' for encrypt, 'd' for decrypt)
    message - The message to be encrypted or decrypted (converted to uppercase)"""
    message = input("Enter the message: ").upper()
    return message
def message_or_file():
    """Prompts the user to choose between processing a message or a file
    Returns:
    tuple: (mode: str, message: str or None, filename: str or None)
    mode - The mode of conversion ('e' for encrypt, 'd' for decrypt, or None if processing a file)
    message - The message to be encrypted or decrypted (None if processing a file)
    filename - The name of the file containing messages (None if processing a message)
    """
    while True:
        mode=input("Would you like to encrypt (e) or decrypt (d): ")
        if mode in ["e","d"]:
            break
        print("Invalid Mode")
    while True:
        option = input("Would you like to read from a file(f) or the console(c)? ").lower()
        if option == "c":
            message = enter_message()
            return mode, message, None
        if option == "f":
            while True:
                filename = input("Enter a filename: ")
                if is_file(filename):
                    return mode, None, filename
                print("Invalid Filename")
        else:
            print("Invalid option. Please enter 'c' for message or 'f' for file.")
def encrypt(message_to_encrypt,shift_number):
    """Encrypts the message given by user
    Parameters:
     - message_to_encrypt (str) : the message entered by the user
     - shift_number (int) : the shift value
    Returns:
    str: result"""
    result = ""
    for i in message_to_encrypt:
        if i.isalpha():
            result += chr((ord(i) - ord('A') + shift_number ) % 26 + ord('A'))
        else:
            result += i
    return result
def decrypt(message_to_decrypt,shift_number):
    """Decrypts the message given by user
    Parameters:
     - message_to_encrypt (str) : the message entered by the user
     - shift_number (int) : the shift value
    Returns:
    str: result"""
    result = ""
    for i in message_to_decrypt:
        if i.isalpha():
            result += chr((ord(i) - ord('A') - shift_number ) % 26 + ord('A'))
        else:
            result += i
    return result
def process_file(file_name,mode):
    """Processes the file and returns encrypted or decrypted message
    Parameters:
     - file_name (str) : the name of the file
     - mode (str) : decides whether the text is to be encrypted or decrypted
    Returns:
    list: string of encrypted/decrypted texts"""
    while True:
        shift = input("What is the shift number: ")
        if shift.isdigit():
            break
        print("Invalid Shift")
    shift_number=int(shift)
    results=[]
    with open(file_name,"r",encoding="utf-8") as file:
        if mode=="e":
            for line in file:
                results.append(encrypt(line.upper(),shift_number))
        else:
            for line in file:
                results.append(decrypt(line.upper(),shift_number))
        return results
def is_file(file_name):
    """checks whether the given file is present in the folder or not
    Parameters:
     - file_name (str) : name of the file given by the user
    Returns:
    Bool : True if the file is found and false otherwise"""
    return os.path.isfile(file_name)
def write_messages(messages):
    """writes the return of caesar_cipher in a separate file
    Parametrs: 
     - messages (str) : the encrypted/decrypted text"""
    with open('Results.txt', 'w',encoding="utf-8") as file:
        for line in messages:
            file.write(line if line.endswith('\n') else line + '\n')
    print("Output written to results.txt")
def main():
    """Main function that calls all the other functions as required"""
    welcome()
    while True:
        mode,message,file_name=message_or_file()
        if file_name:
            ciphered_text = process_file(file_name,mode)
            write_messages(ciphered_text)
        if message:
            while True:
                shift = input("What is the shift number: ")
                if shift.isdigit():
                    break
                print("Invalid Shift")
            shift_number=int(shift)
            if mode=='e':
                print(encrypt(message,shift_number))
            else:
                print(decrypt(message,shift_number))
        while True:
            choice = input("Would you like to encrypt or decrypt another message? (y/n): ").lower()
            if choice in ["n","y"]:
                break
            print("That's not a valid response")
        if choice=="n":
            print("Thanks for using the program, goodbye!")
            break
main()
