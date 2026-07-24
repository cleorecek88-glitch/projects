from pprint import pprint


def parse_fen(fen):
    # Split the FEN string into its six parts
    fen_pieces, to_move, castling_rights, ep, hm, fm = fen.split()

    # Store the chess board
    pieces = [[]]

    # Convert the FEN board into a 2D list
    for char in fen_pieces:
        # Numbers represent empty squares
        if char.isdigit():
            pieces[-1].extend(["."] * int(char))

        # "/" means start a new row
        elif char == "/":
            pieces.append([])

        # Otherwise add the chess piece
        else:
            pieces[-1].append(char)

    # Return the board and whose turn it is
    return {
        "board": pieces,
        "turn": to_move
    }


def generate_moves(position):
    # Store all possible moves
    moves = []

    board = position["board"]
    turn = position["turn"]
    
    # If is white piece's turn it is negative, if it's black piece it is positive 
    direction = -1 if turn == "w" else 1
    
    
    def is_friend(piece):
        # if it is a friend, meaning an empty spaces, then it is a friend
        if piece == ".":
            return False
        return piece.isupper() if turn == "w" else piece.islower()

    def is_enemy(piece):
        if piece == ".":
            return False
        return piece.islower() if turn == "w" else piece.isupper()

    # Generate moves for every piece
    for row in range(8):
        for col in range(8):

            piece = board[row][col]

            if piece == ".":
                continue

            if turn == "w" and piece.islower():
                continue

            if turn == "b" and piece.isupper():
                continue

            # Pawn movement
            if piece.lower() == "p":

                next_row = row + direction

                if 0 <= next_row < 8 and board[next_row][col] == ".":
                    moves.append(((row, col), (next_row, col)))

    return moves


def apply_move(position, move):
    # Make a copy of the board
    board = [row[:] for row in position["board"]]

    (start_row, start_col), (end_row, end_col) = move

    # Move the piece
    board[end_row][end_col] = board[start_row][start_col]

    # Empty the original square
    board[start_row][start_col] = "."

    # Return the updated position
    return {
        "board": board,
        "turn": "b" if position["turn"] == "w" else "w"
    }
    
if __name__=="__main__":
    string = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

    pieces = parse_fen(string)
    pprint(pieces)
