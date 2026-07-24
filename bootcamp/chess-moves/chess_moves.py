from pprint import pprint

def parse_fen(fen):
    fen_pieces, to_move, castling_rights, ep, hm, fm = fen.split(" ")
    pieces = [[]]
    for char in fen_pieces:
        # checks if the string char is a digit
        if char.isdigit():
            # it appends the number (e.g 8 empty spaces) full stops to end of the string
            pieces[-1].extend(["."] * int(char))
        elif char == "/":
            pieces.append([])
        else:
            pieces[-1].append(char)

    return pieces, to_move

# board = parse_fen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 -1")
# pprint(board)

# fen_itemsW = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
# piece_position = parse_fen(fen_itemsW)
# print(piece_position)

# fen_itemsB = "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1"
# piece_positionB = parse_fen(fen_itemsB)
# print(piece_positionB)
def to_square(row, col):
    files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    rank = 8 - row
    return f"{files[col]}{rank}"

print(to_square(4, 4))

def knights_moves(board, row, col, all_moves, is_white_piece, from_sq):
    knight_offsets = [
        (-2, -1), (-2, 1), (-1, -2), (-1, 2),
        (1, -2),  (1, 2),  (2, -1),  (2, 1)
    ]

    for dr, dc in knight_offsets:
        # current position (row, col)
        target = (row + dr, col + dc)
        
        if target in board:
            target_piece = board[target]
            if target_piece == '.' or (is_white_piece != target_piece.isupper()):
                all_moves.append(from_sq + to_square(target[0], target[1]))
            elif target_piece != "." and (is_white_piece == target_piece.isupper()):
                pass
    
    return all_moves
                
    

ALL_MOVES = []
def generate_moves(board):

    active_color = board.get('turn', 'w')

    directions = {
        'R': [(-1, 0), (1, 0), (0, -1), (0, 1)],
        'B': [(-1, -1), (-1, 1), (1, -1), (1, 1)],
        'Q': [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]
    }

    knight_offsets = [
        (-2, -1), (-2, 1), (-1, -2), (-1, 2),
        (1, -2),  (1, 2),  (2, -1),  (2, 1)
    ]

    king_offsets = [
        (-1, -1), (-1, 0), (-1, 1),
        (0, -1),           (0, 1),
        (1, -1),  (1, 0),  (1, 1)
    ]

    for pos, piece in board.items():
        if not isinstance(pos, tuple) or piece == '.':
            continue 

        row, col = pos
        is_white_piece = piece.isupper()

        if (active_color == 'w' and not is_white_piece) or (active_color == 'b' and is_white_piece):
            continue 

        piece_type = piece.upper()
        from_sq = to_square(row, col)

        if piece_type == 'N':
            all_moves = knights_moves(board, )

        elif piece_type == 'K':
            for dr, dc in king_offsets:
                target = (row + dr, col + dc)
                if target in board:
                    target_piece = board[target]
                    if target_piece == '.' or (is_white_piece != target_piece.isupper()):
                        all_moves.append(from_sq + to_square(target[0], target[1]))

        elif piece_type in directions:
            for dr, dc in directions[piece_type]:
                r, c = row + dr, col + dc
                while (r, c) in board:
                    target_piece = board[(r, c)]
                    if target_piece == '.':
                        all_moves.append(from_sq + to_square(r, c))
                    elif is_white_piece != target_piece.isupper():
                        all_moves.append(from_sq + to_square(r, c))
                        break
                    else:
                        break
                    r += dr
                    c += dc

        elif piece_type == 'P':
            step = -1 if is_white_piece else 1
            start_row = 6 if is_white_piece else 1

            if (row + step, col) in board and board[(row + step, col)] == '.':
                all_moves.append(from_sq + to_square(row + step, col))
                if row == start_row and board[(row + 2 * step, col)] == '.':
                    all_moves.append(from_sq + to_square(row + 2 * step, col))

            for c_offset in [-1, 1]:
                diag = (row + step, col + c_offset)
                if diag in board and board[diag] != '.':
                    if is_white_piece != board[diag].isupper():
                        all_moves.append(from_sq + to_square(diag[0], diag[1]))

    return all_moves


def apply_move(board, move_str):
    files = {'a': 0, 'b': 1, 'c': 2, 'd': 3, 'e': 4, 'f': 5, 'g': 6, 'h': 7}

    from_row = 8 - int(move_str[1])
    from_col = files[move_str[0]]
    to_row = 8 - int(move_str[3])
    to_col = files[move_str[2]]

    new_board = board.copy()
    new_board[(to_row, to_col)] = new_board[(from_row, from_col)]
    new_board[(from_row, from_col)] = '.'

    if 'turn' in new_board:
        new_board['turn'] = 'b' if new_board['turn'] == 'w' else 'w'

    return new_board

# def generate_moves(board):
#     raise NotImplementedError("This function is not implemented yet.")



# def apply_move(board, move):
#     raise NotImplementedError("This function is not implemented yet.")