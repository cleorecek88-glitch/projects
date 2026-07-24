def parse_fen(fen):
    # Split the FEN string into its six parts
    fen_pieces, to_move, castling_rights, ep, hm, fm = fen.split()

    # Store the chess board
    board = [[]]

    # Convert the FEN board into a 2D list
    for char in fen_pieces:
        # Numbers represent empty squares
        if char.isdigit():
            board[-1].extend(["."] * int(char))

        # "/" starts a new row
        elif char == "/":
            board.append([])

        # Otherwise add the chess piece
        else:
            board[-1].append(char)

    return {
        "board": board,
        "turn": to_move,
    }


def generate_moves(position):
    # Store all possible moves
    moves = []

    board = position["board"]
    turn = position["turn"]

    # White moves up the board, black moves down
    direction = -1 if turn == "w" else 1

    # Check if a piece belongs to the current player
    def is_friend(piece):
        if piece == ".":
            return False
        return piece.isupper() if turn == "w" else piece.islower()

    # Check if a piece belongs to the opponent
    def is_enemy(piece):
        if piece == ".":
            return False
        return piece.islower() if turn == "w" else piece.isupper()

    # Examine every square on the board
    for row in range(8):
        for col in range(8):

            piece = board[row][col]

            # Skip empty squares
            if piece == ".":
                continue

            # Skip opponent's pieces
            if turn == "w" and piece.islower():
                continue

            if turn == "b" and piece.isupper():
                continue

            # ---------------- Pawn ----------------
            if piece.lower() == "p":

                next_row = row + direction

                # Move one square forward
                if 0 <= next_row < 8 and board[next_row][col] == ".":
                    moves.append(((row, col), (next_row, col)))

                    # Move two squares from the starting rank
                    start_row = 6 if turn == "w" else 1

                    if row == start_row:
                        two_row = row + 2 * direction

                        if board[two_row][col] == ".":
                            moves.append(((row, col), (two_row, col)))

                # Capture diagonally
                for dc in (-1, 1):
                    new_col = col + dc

                    if (
                        0 <= next_row < 8
                        and 0 <= new_col < 8
                        and is_enemy(board[next_row][new_col])
                    ):
                        moves.append(((row, col), (next_row, new_col)))

            # ---------------- Knight ----------------
            elif piece.lower() == "n":

                knight_moves = [
                    (-2, -1), (-2, 1),
                    (-1, -2), (-1, 2),
                    (1, -2), (1, 2),
                    (2, -1), (2, 1),
                ]

                for dr, dc in knight_moves:

                    new_row = row + dr
                    new_col = col + dc

                    if (
                        0 <= new_row < 8
                        and 0 <= new_col < 8
                        and not is_friend(board[new_row][new_col])
                    ):
                        moves.append(((row, col), (new_row, new_col)))

    return moves


def apply_move(position, move):
    # Copy the board so the original isn't modified
    board = [row[:] for row in position["board"]]

    (start_row, start_col), (end_row, end_col) = move

    # Move the piece
    board[end_row][end_col] = board[start_row][start_col]

    # Empty the original square
    board[start_row][start_col] = "."

    # Switch turns
    return {
        "board": board,
        "turn": "b" if position["turn"] == "w" else "w",
    }