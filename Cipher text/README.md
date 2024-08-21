# Cipher Text Project

This project is my final Python project for the 1st semester at Herald College Kathmandu. It implements a Caesar Cipher program, which is a basic encryption technique used to secure messages.

## Overview

The program allows users to:
- Encrypt text using a Caesar cipher by shifting characters by a specified number.
- Decrypt text that was encrypted using a Caesar cipher.
- Process either individual messages or entire text files.

### Features
- **Encryption**: Shifts each letter in the input text by a user-defined number.
- **Decryption**: Reverses the shift to return the original message.
- **File Processing**: Reads text from a file, processes it, and writes the output to a new file.

### How It Works
- The user chooses whether to encrypt or decrypt a message or file.
- For messages, the user inputs the text directly.
- For files, the program reads the file, processes each line, and saves the results to `Results.txt`.
- The user specifies the shift value, which determines how many positions each letter is shifted in the alphabet.

### Example Usage
Would you like to encrypt (e) or decrypt (d): e
Would you like to read from a file(f) or the console(c)? c
Enter the message: HELLO WORLD
What is the shift number: 3
KHOOR ZRUOG

### Code Highlights
- **Functions**: The program is divided into functions for clarity, including `encrypt`, `decrypt`, and `process_file`.
- **User Input Validation**: Ensures the user enters valid modes, filenames, and shift numbers.
- **File Handling**: Processes and outputs results to files when necessary.

### How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/rojin-0/Projects.git
2. Navigate to the project directory
3. Run the cesar_cipher_2417733.py on command line using "python cesar_cipher_2417733.py"
   Or any other software that supports python, like vscode, etc.
